package siani.tara.intellij.codegeneration;

import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.actions.TaraTemplates;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.io.File;
import java.util.*;

public class IntentionsGenerator {

	public static final String GEN = "gen";
	public static final String EXTENSIBLE = "Extensible";
	private final Project project;
	private final TaraFile taraFile;
	private final PsiDirectory genDirectory;

	public IntentionsGenerator(Project project, TaraFile taraFile) {
		this.project = project;
		this.taraFile = taraFile;
		VirtualFile gen = getGenDirectory(TaraUtil.getSourceRoots(taraFile));
		genDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraFile.getManager(), gen);
	}

	private static void refreshFiles(Set<File> pathsToRefresh) {
		LocalFileSystem.getInstance().refreshIoFiles(pathsToRefresh, true, true, null);
	}

	public void generate() {
		final Set<File> pathsToRefresh = new HashSet<>();
		ApplicationManager.getApplication().runReadAction(new Runnable() {
			@Override
			public void run() {
				VirtualFile content = ProjectRootManager.getInstance(project).getFileIndex().getContentRootForFile(taraFile.getVirtualFile());
				VirtualFile parentDir = content == null ? taraFile.getVirtualFile().getParent() : content;
				String outputPath = new File(VfsUtil.virtualToIoFile(parentDir), GEN).getAbsolutePath();
				pathsToRefresh.add(new File(outputPath));
				try {
					processFile(taraFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshFiles(pathsToRefresh);
			}
		});
	}

	private void processFile(PsiFile psiFile) {
		if (psiFile instanceof TaraFile) {
			final TaraFile taraFile = ((TaraFile) psiFile);
			final String[] conceptIntentions = getConceptIntentions(taraFile);
			if (taraFile.getConcept() != null && conceptIntentions.length > 0) {
				final PsiClass sourceClass = getSourceClass(psiFile, taraFile.getConcept());
				writeInnerClasses(conceptIntentions, sourceClass, false);
				PsiClass genClass = getGenClass(taraFile);
				writeInnerClasses(conceptIntentions, genClass, true);
			}
		}
	}

	private VirtualFile getGenDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && GEN.equals(file.getName())) return file;
		throw new RuntimeException("gen directory not found");

	}

	private void writeInnerClasses(final String[] conceptIntentions, final PsiClass sourceClass, final boolean extensible) {
		WriteCommandAction action = new WriteCommandAction(project, sourceClass.getContainingFile()) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				for (String conceptIntention : conceptIntentions) {
					PsiClass clazz = sourceClass;
					String[] split = conceptIntention.split("\\.");
					for (int i = 1; i < split.length; i++) {
						String name = split[i] + (extensible ? EXTENSIBLE : "");
						PsiClass innerClass = clazz.findInnerClassByName(name, false);
						if (innerClass != null)
							clazz = innerClass;
						else clazz = createInnerClass(clazz, split[i], name, extensible);
						LocalFileSystem.getInstance().refresh(true);
						VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
					}
				}
			}
		};
		action.execute();
	}

	private PsiClass createInnerClass(PsiClass clazz, String qn, String name, boolean extensible) {
		String text = FileTemplateManager.getInstance().getInternalTemplate("TaraInnerClass.java").getText();
		text = text.replace("${NAME}", name).replace("${PARENT}", (!extensible ? "extends " + qn + EXTENSIBLE : ""));
		PsiClass aClass = JavaPsiFacade.getElementFactory(project).createClassFromText(text, clazz);
		PsiClass innerClass = aClass.findInnerClassByName(name, false);
		clazz.add(innerClass);
		return innerClass;
	}

	private String[] getConceptIntentions(TaraFile taraFile) {
		List<String> intentions = new ArrayList<>();
		List<Concept> allConceptsOfFile = TaraUtil.findAllConceptsOfFile(taraFile);
		for (Concept concept : allConceptsOfFile)
			if (concept.isIntention())
				intentions.add(TaraUtil.getLocalQNConcept(concept));
		return intentions.toArray(new String[intentions.size()]);
	}

	private List<PsiDirectory> createPackage(TaraFile file) {
		String[] packet = file.getPackage().getHeaderReference().getText().split("\\.");
		List<PsiDirectory> directories = new ArrayList<>();
		for (String s : packet) {
			PsiDirectory baseDirectory = (directories.isEmpty()) ? genDirectory : directories.get(directories.size() - 1);
			PsiDirectory subdirectory = baseDirectory.findSubdirectory(s);
			if (subdirectory != null) directories.add(subdirectory);
			else directories.add(DirectoryUtil.createSubdirectories(s, baseDirectory, "."));
		}
		return directories;
	}

	private PsiClass getGenClass(TaraFile file) {
		PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(taraFile.getConcept().getQualifiedName() + EXTENSIBLE,
			GlobalSearchScope.moduleScope(TaraUtil.getModuleOfFile(file)));
		List<PsiDirectory> packages = createPackage(file);
		return (aClass != null) ? aClass : JavaDirectoryService.getInstance().createClass((packages.isEmpty()) ? genDirectory : packages.get(packages.size() - 1),
			file.getConcept().getName() + EXTENSIBLE, TaraTemplates.getTemplate("TARA_INTENTION"), true, getOptionsForGenClass());
	}

	private PsiClass getSourceClass(PsiFile psiFile, Concept concept) {
		PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(taraFile.getConcept().getQualifiedName(),
			GlobalSearchScope.moduleScope(TaraUtil.getModuleOfFile(psiFile)));
		return (aClass != null) ? aClass : JavaDirectoryService.getInstance().createClass(psiFile.getParent(), concept.getName(),
			"TaraIntentionClass", true, getOptionsForSrcClass(concept.getName()));
	}

	private Map<String, String> getOptionsForSrcClass(String parent) {
		Map<String, String> map = new HashMap<>();
		map.put("PARENT", "extends " + parent + EXTENSIBLE);
		return map;
	}

	private Map<String, String> getOptionsForGenClass() {
		Map<String, String> map = new HashMap<>();
		map.put("PARENT", "");
		return map;
	}
}
