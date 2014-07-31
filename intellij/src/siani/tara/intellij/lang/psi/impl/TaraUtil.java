package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;

import java.util.*;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContextOf;

public class TaraUtil {

	private TaraUtil() {
	}

	@NotNull
	public static List<Concept> findRootConcept(PsiElement element, String identifier) {
		List<Concept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getModuleFiles(element.getContainingFile())) {
			Concept[] concepts = taraFile.getConcepts();
			extractConceptsByName(identifier, result, concepts);
		}
		return result;
	}

	private static void extractConceptsByName(String identifier, List<Concept> result, Concept[] concepts) {
		for (Concept concept : concepts)
			if (identifier.equals(concept.getName()))
				result.add(concept);
	}

	public static String getMetaQualifiedName(Concept concept) {
		Concept container = concept;
		String id = (container.getType() != null) ? container.getType() : "";
		while (TaraPsiImplUtil.getContextOf(container) != null) {
			container = TaraPsiImplUtil.getContextOf(container);
			String containerName = container != null && container.getType() != null ? container.getType() : "";
			String name = (!containerName.isEmpty() ? "." : "") + id;
			id = containerName + (!id.isEmpty() ? name : "");
		}
		return id;
	}

	@NotNull
	public static Concept[] getRootConceptsOfFile(TaraFileImpl taraFile) {
		List<Concept> list = new ArrayList();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, Concept.class);
		if (concepts != null) {
			for (Concept concept : concepts) {
				extractCases(concept, list);
				list.add(concept);
			}
		}
		return list.toArray(new Concept[list.size()]);
	}

	private static void extractCases(Concept concept, List<Concept> list) {
		List<Concept> cases = Arrays.asList(concept.getCases());
		list.addAll(cases);
		for (Concept aCase : cases) {
			extractCases(aCase, list);
		}
	}

	@NotNull
	private static TaraFileImpl[] getModuleFiles(PsiFile psiFile) {
		Project project = psiFile.getProject();
		Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(psiFile.getVirtualFile());
		if (module == null) return new TaraFileImpl[0];
		List<TaraFileImpl> taraFiles = getTaraFilesOfModule(project, module);
		return taraFiles.toArray(new TaraFileImpl[taraFiles.size()]);
	}

	public static List<TaraFileImpl> getTaraFilesOfModule(Project project, Module module) {
		List<TaraFileImpl> taraFiles = new ArrayList<>();
		Collection<VirtualFile> files = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.moduleScope(module));
		for (VirtualFile file : files)
			if (file != null) {
				TaraFileImpl taraFile = (TaraFileImpl) PsiManager.getInstance(project).findFile(file);
				if (taraFile != null) taraFiles.add(taraFile);
			}
		return taraFiles;
	}


	@NotNull
	public static Attribute[] findAttributeDuplicates(Attribute attribute) {
		List<Attribute> result = new ArrayList<>();
		List<Attribute> attributes = TaraPsiImplUtil.getAttributesInBody((Body) attribute.getParent());
		for (Attribute taraAttribute : attributes)
			if (taraAttribute.getName() != null && taraAttribute.getName().equals(attribute.getName()))
				result.add(taraAttribute);
		return result.toArray(new Attribute[result.size()]);
	}

	@NotNull
	public static List<Concept> findAllConceptsOfFile(TaraFile taraFile) {
		List<Concept> collection = new ArrayList<>();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, Concept.class);
		if (concepts != null) {
			Collections.addAll(collection, concepts);
			for (Concept concept : concepts)
				collectChildren(concept, collection);
		}
		return collection;
	}

	private static void collectChildren(Concept concept, List<Concept> collection) {
		for (Concept child : TaraUtil.getChildrenOf(concept)) {
			collection.add(child);
			collectChildren(child, collection);
		}
	}

	public static VirtualFile findChildFileOf(VirtualFile file, String name) {
		if (file == null || file.getChildren() == null) return null;
		for (VirtualFile virtualFile : file.getChildren())
			if (virtualFile.getName().split("\\.")[0].equals(name))
				return virtualFile;
		return null;
	}

	public static TaraFile getTaraFileFromVirtual(Project project, VirtualFile vFile) {
		PsiFile file = PsiManager.getInstance(project).findFile(vFile);
		if (file instanceof TaraFile) return (TaraFile) file;
		return null;
	}

	@NotNull
	public static Concept[] getChildrenOf(Concept concept) {
		List<Concept> childrenOf = TaraPsiImplUtil.getChildrenOf(concept);
		return childrenOf.toArray(new Concept[childrenOf.size()]);
	}

	public static Concept findChildOf(Concept concept, String name) {
		List<Concept> children = TaraPsiImplUtil.getChildrenOf(concept);
		for (Concept child : children)
			if (child.getName() != null && child.getName().equals(name))
				return child;
		return null;
	}

	public static VirtualFile getSourcePath(Project project, PsiFile psiFile) {
		return ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(psiFile.getOriginalFile().getVirtualFile());
	}

	public static TaraFile getOrCreateFile(String myDestination, Project myProject) {
		return null;
	}

	@NotNull
	public static Collection<VirtualFile> getSourceRoots(@NotNull PsiElement foothold) {
		final Module module = ModuleUtilCore.findModuleForPsiElement(foothold);
		if (module != null) return getSourceRoots(module);
		return Collections.emptyList();
	}

	@NotNull
	public static Collection<VirtualFile> getSourceRoots(@NotNull Module module) {
		final Set<VirtualFile> result = new LinkedHashSet<>();
		final ModuleRootManager manager = ModuleRootManager.getInstance(module);
		result.addAll(Arrays.asList(manager.getSourceRoots()));
		result.addAll(Arrays.asList(manager.getContentRoots()));
		return result;
	}

	public static Module getModuleOfFile(PsiFile file) {
		ProjectFileIndex fileIndex = ProjectRootManager.getInstance(file.getProject()).getFileIndex();
		VirtualFile vfile = (file.getVirtualFile() != null) ? file.getVirtualFile() : file.getOriginalFile().getVirtualFile();
		return fileIndex.getModuleForFile(vfile);
	}

	public static String getLocalQNConcept(Concept concept) {
		Concept container = concept;
		String id = container.getName();
		while (getContextOf(container) != null) {
			container = getContextOf(container);
			String name = container.getName();
			id = (name == null ? "anonymous" : name) + "." + id;
		}
		return id;
	}

	public static String composeConceptQN(Identifier identifier) {
		Concept concept = getContextOf(identifier);
		String path = concept.getName();
		while (concept != null) {
			concept = getContextOf(concept);
			if (concept != null) path = concept.getName() + "." + path;
		}
		return path;
	}
}
