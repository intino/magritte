package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Doc;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.io.File;
import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;
import static com.intellij.psi.JavaPsiFacade.getInstance;

public class IntentionsGenerator {

	public static final String SRC = "src";
	private final Project project;
	private final TaraBoxFile taraBoxFile;
	private final PsiDirectory srcDirectory;

	public IntentionsGenerator(Project project, TaraBoxFile taraBoxFile) {
		this.project = project;
		this.taraBoxFile = taraBoxFile;
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(taraBoxFile));
		this.srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraBoxFile.getManager(), srcDirectory);
	}

	public void generate() {
		final Set<File> pathsToRefresh = new HashSet<>();
		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				pathsToRefresh.add(VfsUtil.virtualToIoFile(srcDirectory.getVirtualFile()));
				try {
					processFile(taraBoxFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void processFile(PsiFile psiFile) {
		if (psiFile instanceof TaraBoxFile)
			for (Concept intention : getIntentions(((TaraBoxFile) psiFile)))
				createIntentionClasses(getIntentionsPath(intention));
	}

	private List<Concept> getIntentionsPath(Concept intention) {
		List<Concept> list = new ArrayList<>();
		Concept contextOf = intention;
		while ((contextOf = TaraPsiImplUtil.getConceptContextOf(contextOf)) != null)
			list.add(0, contextOf);
		list.add(intention);
		return list;
	}

	private void createIntentionClasses(List<Concept> path) {
		List<PsiClass> psiClasses = new ArrayList<>();
		psiClasses.add(createClass(path.get(0)));
		for (int i = 1; i < path.size(); i++)
			psiClasses.add(createInnerClass(path.get(i), psiClasses.get(psiClasses.size() - 1)));
	}

	private PsiClass createClass(Concept concept) {
		PsiClass aClass = getClass(concept);
		if (aClass != null) {
			setDocumentation(concept.getDoc(), aClass);
			return aClass;
		}
		Concept parentConcept = concept.getParentConcept() != null ?
			TaraPsiImplUtil.getConceptContextOf(ReferenceManager.resolve(concept.getSignature().getParentReference())) : null;
		PsiDirectory destiny = getDestiny(concept);
		PsiClass anInterface = JavaDirectoryService.getInstance().createInterface(destiny, concept.getName());
		setDocumentation(concept.getDoc(), anInterface);
		if (parentConcept != null) {
			PsiClass parentClass = getClass(concept) != null ? getClass(concept) :
				JavaDirectoryService.getInstance().createInterface(destiny, concept.getName());
			aClass.getExtendsList().add(getElementFactory(project).createClassReferenceElement(parentClass));
		}
		return anInterface;
	}

	private void setDocumentation(final Doc doc, final PsiClass aClass) {
		WriteCommandAction action = new WriteCommandAction(project, aClass.getContainingFile()) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				if (doc != null) {
					PsiDocComment docComment = aClass.getDocComment();
					PsiDocComment text = getElementFactory(project).createDocCommentFromText("/**\n* " + doc.getDocText() + "\n*/");
					if (docComment != null)
						docComment.delete();
					aClass.getParent().addBefore(text, aClass);
				}
			}
		};
		action.execute();
	}

	private PsiClass createInnerClass(final Concept concept, final PsiClass container) {
		final PsiClass[] aClass = new PsiClass[1];
		if ((aClass[0] = getClass(concept)) != null) {
			setDocumentation(concept.getDoc(), aClass[0]);
			return aClass[0];
		}
		WriteCommandAction action = new WriteCommandAction(project, container.getContainingFile()) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				aClass[0] = IntentionsGenerator.this.getClass(concept);
				if (aClass[0] == null) {
					aClass[0] = getElementFactory(project).createInterface(concept.getName());
					if (concept.getParentConcept() != null)
						setParentToClass(concept, aClass[0]);
					container.add(aClass[0]);
					setDocumentation(concept.getDoc(), aClass[0]);
				}
			}
		};
		action.execute();
		return aClass[0];
	}

	private void setParentToClass(Concept concept, PsiClass aClass) {
		Concept parent = (concept.getParentConceptName() != null) ? concept.getParentConcept() : null;
		if (parent == null) return;
		PsiClass parentClass = getClass(parent);
		if (parentClass == null)
			parentClass = createClass(parent);
		aClass.getExtendsList().add(getElementFactory(project).createClassReferenceElement(parentClass));
	}

	private PsiClass getClass(Concept concept) {
		return getInstance(project).findClass(concept.getQualifiedName(),
			GlobalSearchScope.moduleScope(TaraUtil.getModuleOfFile(concept.getContainingFile())));
	}

	private PsiDirectory getDestiny(Concept concept) {
		List<PsiDirectory> packages = createSrcPackageForFile(concept.getFile());
		return (packages.isEmpty()) ? srcDirectory : packages.get(packages.size() - 1);
	}

	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("src directory not found");
	}

	private Concept[] getIntentions(TaraBoxFile taraBoxFile) {
		List<Concept> intentions = new ArrayList<>();
		List<Concept> allConceptsOfFile = TaraUtil.getAllConceptsOfFile(taraBoxFile);
		for (Concept concept : allConceptsOfFile)
			if (concept.isIntention())
				intentions.add(concept);
		return intentions.toArray(new Concept[intentions.size()]);
	}

	private List<PsiDirectory> createSrcPackageForFile(TaraBoxFile file) {
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
}
