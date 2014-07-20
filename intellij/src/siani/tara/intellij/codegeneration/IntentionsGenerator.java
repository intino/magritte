package siani.tara.intellij.codegeneration;

import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.io.File;
import java.util.*;

public class IntentionsGenerator {

	public static final String SRC = "src";
	public static final String INTENTION = "Intention";
	private final Project project;
	private final TaraFile taraFile;
	private final PsiDirectory srcDirectory;

	public IntentionsGenerator(Project project, TaraFile taraFile) {
		this.project = project;
		this.taraFile = taraFile;
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(taraFile));
		this.srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraFile.getManager(), srcDirectory);
	}

	public void generate() {//TODO
		final Set<File> pathsToRefresh = new HashSet<>();
		ApplicationManager.getApplication().runReadAction(new Runnable() {
			@Override
			public void run() {
				VirtualFile content = ProjectRootManager.getInstance(project).getFileIndex().getContentRootForFile(taraFile.getVirtualFile());
				VirtualFile parentDir = content == null ? taraFile.getVirtualFile().getParent() : content;
				pathsToRefresh.add(VfsUtil.virtualToIoFile(srcDirectory.getVirtualFile()));
				try {
					processFile(taraFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void processFile(PsiFile psiFile) {
		if (psiFile instanceof TaraFile) {
			final TaraFile taraFile = ((TaraFile) psiFile);
			final Concept[] intentions = getIntentions(taraFile);
			for (Concept intention : intentions) createIntentionClass(intention);
//			final PsiClass sourceClass = getSourceClass(psiFile, taraFile.getConcept());
//			writeInnerClasses(intentions, sourceClass, false);
//			PsiClass genClass = getGenClass(taraFile);
//			writeInnerClasses(intentions, genClass, true);
		}
	}

	private PsiClass createIntentionClass(Concept concept) {
		PsiFile file = concept.getContainingFile();
		PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(concept.getQualifiedName(),
			GlobalSearchScope.moduleScope(TaraUtil.getModuleOfFile(file)));
		String parent = (concept.getParentConcept() != null) ? concept.getParentConcept() : INTENTION;
		PsiDirectory destiny = getDestiny(concept);
		return (aClass != null) ? aClass :
			JavaDirectoryService.getInstance().
				createClass(destiny, concept.getName(), "TaraIntentionClass", false, getOptionsForIntentionClass(parent));
	}

	private PsiDirectory getDestiny(Concept concept) {
		List<PsiDirectory> packages = createSrcPackageForFile(concept.getFile());
		return (packages.isEmpty()) ? srcDirectory : packages.get(packages.size() - 1);
	}

	private Map<String, String> getOptionsForIntentionClass(String parent) {
		Map<String, String> map = new HashMap<>();
		map.put("PARENT", parent);
		return map;
	}

	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("src directory not found");

	}

	private void writeInnerClasses(final String[] conceptIntentions, final PsiClass sourceClass, final boolean extensible) {
		WriteCommandAction action = new WriteCommandAction(project, sourceClass.getContainingFile()) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				for (String conceptIntention : conceptIntentions) {
					PsiClass clazz = sourceClass;
					String[] split = conceptIntention.split("\\.");
					for (int i = 1; i < split.length; i++) {
						String name = split[i] + (extensible ? INTENTION : "");
						PsiClass innerClass = clazz.findInnerClassByName(name, false);
						if (innerClass != null)
							clazz = innerClass;
						else clazz = createInnerClass(clazz, split[i], name, extensible);
					}
				}
			}
		};
		action.execute();
	}

	private PsiClass createInnerClass(PsiClass clazz, String qn, String name, boolean extensible) {
		String text = FileTemplateManager.getInstance().getJ2eeTemplate("TaraInnerClass.java").getText();
		text = text.replace("${NAME}", name).replace("${PARENT}", (!extensible ? "extends " + qn + INTENTION : ""));
		PsiClass aClass = JavaPsiFacade.getElementFactory(project).createClassFromText(text, clazz);
		PsiClass innerClass = aClass.findInnerClassByName(name, false);
		clazz.add(innerClass);
		return innerClass;
	}

	private Concept[] getIntentions(TaraFile taraFile) {
		List<Concept> intentions = new ArrayList<>();
		Concept[] allConceptsOfFile = TaraUtil.getRootConceptsOfFile((TaraFileImpl) taraFile);
		for (Concept concept : allConceptsOfFile)
			if (concept.isIntention())
				intentions.add(concept);
		return intentions.toArray(new Concept[intentions.size()]);
	}

	private List<PsiDirectory> createSrcPackageForFile(TaraFile file) {
		String[] packet = file.getBoxReference().getHeaderReference().getText().split("\\.");
		List<PsiDirectory> directories = new ArrayList<>();
		for (String s : packet) {
			PsiDirectory baseDirectory = (directories.isEmpty()) ? srcDirectory : directories.get(directories.size() - 1);
			PsiDirectory subdirectory = baseDirectory.findSubdirectory(s);
			if (subdirectory != null) directories.add(subdirectory);
			else directories.add(DirectoryUtil.createSubdirectories(s, baseDirectory, "."));
		}
		return directories;
	}

//	private PsiClass getGenClass(TaraFile file) {
//		PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(taraFile.getConcept().getQualifiedName() + INTENTION,
//			GlobalSearchScope.moduleScope(TaraUtil.getModuleOfFile(file)));
//		List<PsiDirectory> packages = createSrcPackageForFile(file);
//		return (aClass != null) ? aClass : JavaDirectoryService.getInstance().createClass((packages.isEmpty()) ? srcDirectory : packages.get(packages.size() - 1),
//			file.getConcept().getName() + INTENTION, TaraTemplates.getTemplate("TARA_INTENTION"), true, getOptionsForGenClass());
//	}


	private Map<String, String> getOptionsForGenClass() {
		Map<String, String> map = new HashMap<>();
		map.put("PARENT", "");
		return map;
	}
}
