package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.siani.itrules.Document;
import org.siani.itrules.Frame;
import org.siani.itrules.RuleEngine;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class IntentionsGeneratorByFrame {

	public static final String SRC = "src";
	public static final String INTENTION = "Intention";
	private static final String JAVA_EXTENSION = ".java";
	private static final String intentionRules = "/fileTemplates/internal/intention.itr";
	private final Project project;
	private final TaraBoxFile taraBoxFile;
	private final PsiDirectory srcDirectory;

	public IntentionsGeneratorByFrame(Project project, TaraBoxFile taraBoxFile) {
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
		if (psiFile instanceof TaraBoxFile) {
			Concept[] intentions = getIntentions(((TaraBoxFile) psiFile));
			if (intentions.length > 0)
				createIntentionClasses(intentions);
		}
	}

	private void createIntentionClasses(Concept[] concepts) {
		PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(concepts[0].getQualifiedName(),
			GlobalSearchScope.moduleScope(TaraUtil.getModuleOfFile(concepts[0].getFile())));
		PsiDirectory destiny = getDestiny(concepts[0].getFile());
		Map<String, List<Concept>> destinyFiles = collectFilesOfConcepts(concepts);
		for (Map.Entry<String, List<Concept>> entry : destinyFiles.entrySet()) {
			String content = createIntentionClass(entry.getValue());
			if (content == null) continue;
			writeIntentionClass(new File(destiny.getVirtualFile().getPath(), entry.getKey() + JAVA_EXTENSION), content);
		}
	}

	private void writeIntentionClass(File file, String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException ignored) {
		}
	}

	private String createIntentionClass(List<Concept> concepts) {
		Frame frame = createFrame(concepts);
		RuleEngine engine = new RuleEngine(getClass().getResourceAsStream(intentionRules));
		Document document = new Document();
		engine.render(frame, document);
		return document.content();
	}

	private Frame createFrame(List<Concept> concepts) {
		Set<Concept> processedConcepts = new HashSet<>();
		Frame frame = null;
		Stack<Frame> frameStack = new Stack<>();
		for (Concept concept : concepts) {
			PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(concepts.get(0).getQualifiedName(),
				GlobalSearchScope.moduleScope(TaraUtil.getModuleOfFile(concepts.get(0).getFile())));
			if (aClass != null) continue;
			for (Concept aConcept : getPathToConcept(concept)) {
				if (isProcessed(processedConcepts, aConcept)) continue;
				if (frameStack.isEmpty()) {
					frame = new Frame("intention");
					frame.addSlot("name", aConcept.getName());
					frame.addSlot("box", aConcept.getFile().getBox());
					if (aConcept.getParentConcept() != null)
						frame.addSlot("parent", TaraPsiImplUtil.getParentOf(aConcept).getQualifiedName());
					frameStack.push(frame);
				} else {
					Frame subFrame = createSubFrameOfConcept(aConcept);
					frameStack.peek().addSlot("sub", subFrame);
					frameStack.push(subFrame);
				}
				processedConcepts.add(aConcept);
			}
			frameStack.clear();
			frameStack.push(frame);
		}
		return frame;
	}

	private boolean isProcessed(Set<Concept> processedConcepts, Concept concept) {
		for (Concept processedConcept : processedConcepts)
			if (processedConcept.equals(concept)) return true;
		return false;
	}

	private Frame createSubFrameOfConcept(Concept aConcept) {
		Frame frame = new Frame("sub");
		frame.addSlot("name", aConcept.getName());
		if (aConcept.getParentConcept() != null)
			frame.addSlot("parent", TaraPsiImplUtil.getParentOf(aConcept).getQualifiedName());
		return frame;
	}

	private List<Concept> getPathToConcept(Concept concept) {
		List<Concept> list = new ArrayList<>();
		Concept aConcept = concept;
		list.add(aConcept);
		while ((aConcept = TaraPsiImplUtil.getConceptContextOf(aConcept)) != null)
			list.add(0, aConcept);
		return list;
	}

	private Map<String, List<Concept>> collectFilesOfConcepts(Concept[] concepts) {
		Map<String, List<Concept>> fileNames = new HashMap<>();
		for (Concept concept : concepts) {
			Concept aConcept = getRootConcept(concept);
			if (aConcept != null) {
				if (!fileNames.containsKey(aConcept.getName()))
					fileNames.put(aConcept.getName(), new ArrayList<Concept>());
				fileNames.get(aConcept.getName()).add(concept);
			}
		}
		return fileNames;
	}

	private Concept getRootConcept(Concept concept) {
		Concept aConcept = concept;
		while (TaraPsiImplUtil.getConceptContextOf(aConcept) != null)
			aConcept = TaraPsiImplUtil.getConceptContextOf(aConcept);
		return aConcept;
	}

	private PsiDirectory getDestiny(TaraBoxFileImpl conceptFile) {
		List<PsiDirectory> packages = createSrcPackageForFile(conceptFile);
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
