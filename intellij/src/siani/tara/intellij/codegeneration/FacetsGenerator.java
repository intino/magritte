package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.io.File;
import java.util.*;

public class FacetsGenerator {

	public static final String SRC = "src";
	private static final String INTENTION = "Intention";
	private final Project project;
	private final TaraFile taraFile;
	private final PsiDirectory srcDirectory;

	public FacetsGenerator(Project project, TaraFile taraFile) {
		this.project = project;
		this.taraFile = taraFile;
		VirtualFile src = getSRCDirectory(TaraUtil.getSourceRoots(taraFile));
		srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraFile.getManager(), src);
	}

	public void generate() {
		final Set<File> pathsToRefresh = new HashSet<>();
		ApplicationManager.getApplication().runReadAction(new Runnable() {
			@Override
			public void run() {
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
			final Concept[] facets = getFacets(taraFile);
			for (Concept intention : facets) createFacetClass(intention);
		}
	}

	private PsiClass createFacetClass(Concept concept) {
		PsiFile file = concept.getContainingFile();
		PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(concept.getQualifiedName(),
			GlobalSearchScope.moduleScope(TaraUtil.getModuleOfFile(file)));
		String parent = "_" + concept.getName() + "_";
		PsiDirectory destiny = getDestiny(concept);
		return (aClass != null) ? aClass :
			JavaDirectoryService.getInstance().
				createClass(destiny, concept.getName(), "TaraFacetClass", false, getOptionsForFacetClass(parent));
	}

	private Map<String, String> getOptionsForFacetClass(String parent) {
		Map<String, String> map = new HashMap<>();
		map.put("PARENT", parent);
		return map;
	}

	private PsiDirectory getDestiny(Concept concept) {
		List<PsiDirectory> packages = createSrcPackageForFile(concept.getFile());
		return (packages.isEmpty()) ? srcDirectory : packages.get(packages.size() - 1);
	}

	private VirtualFile getSRCDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("Src directory not found");
	}

	private Concept[] getFacets(TaraFile taraFile) {
		Model model = TaraLanguage.getMetaModel(taraFile);
		List<Concept> facets = new ArrayList<>();
		if (model == null) return new Concept[0];
		List<Concept> allConceptsOfFile = TaraUtil.findAllConceptsOfFile(taraFile);
		for (Concept concept : allConceptsOfFile) {
			Node node = findNode(concept, model);
			if (node != null && node.getObject().getType().equals(INTENTION))
				facets.add(concept);
		}
		return facets.toArray(new Concept[facets.size()]);
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

	protected Node findNode(Concept concept, Model model) {
		return model.searchNode(TaraUtil.getMetaQualifiedName(concept));
	}
}
