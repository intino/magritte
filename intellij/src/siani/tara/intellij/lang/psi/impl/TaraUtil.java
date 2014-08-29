package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.*;
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
		for (TaraBoxFileImpl taraFile : getModuleFiles(element.getContainingFile())) {
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
		Concept container = concept.isSub() ? TaraPsiImplUtil.getParentOf(concept) : concept;
		if (container == null)
			return null;
		String id = (container.getType() != null) ? container.getType() : "";
		while (TaraPsiImplUtil.getContextOf(container) != null) {
			container = container != null && container.isSub() ? TaraPsiImplUtil.getParentOf(container) : TaraPsiImplUtil.getContextOf(container);
			String containerName = container != null && container.getType() != null ? container.getType() : "";
			String name = (!containerName.isEmpty() ? "." : "") + id;
			id = containerName + (!id.isEmpty() ? name : "");
		}
		return id;
	}

	public static String getMetaQualifiedName(TaraConceptReference reference) {
		Concept concept = TaraPsiImplUtil.getContextOf(reference);
		String metaQualifiedName = getMetaQualifiedName(concept);
		List<TaraIdentifier> identifierList = reference.getIdentifierReference().getIdentifierList();
		PsiElement resolve = ReferenceManager.resolve(identifierList.get(identifierList.size() - 1), false);
		if (resolve == null || !Identifier.class.isInstance(resolve)) return null;
		Concept contextOf = TaraPsiImplUtil.getContextOf(resolve);
		String type = contextOf.getType();
		return metaQualifiedName + "." + type;
	}

	@NotNull
	public static Concept[] getRootConceptsOfFile(TaraBoxFile taraBoxFile) {
		Set<Concept> list = new HashSet<>();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraBoxFile, Concept.class);
		if (concepts != null) {
			for (Concept concept : concepts) {
				list.add(concept);
				extractSubConcepts(concept, list);
			}
		}
		return list.toArray(new Concept[list.size()]);
	}

	private static void extractSubConcepts(Concept concept, Set<Concept> list) {
		List<Concept> cases = Arrays.asList(concept.getSubConcepts());
		list.addAll(cases);
	}

	@NotNull
	private static TaraBoxFileImpl[] getModuleFiles(PsiFile psiFile) {
		Project project = psiFile.getProject();
		Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(psiFile.getVirtualFile());
		if (module == null) return new TaraBoxFileImpl[0];
		List<TaraBoxFileImpl> taraFiles = getTaraFilesOfModule(project, module);
		return taraFiles.toArray(new TaraBoxFileImpl[taraFiles.size()]);
	}

	public static List<TaraBoxFileImpl> getTaraFilesOfModule(Project project, Module module) {
		List<TaraBoxFileImpl> taraFiles = new ArrayList<>();
		Collection<VirtualFile> files = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.moduleScope(module));
		for (VirtualFile file : files)
			if (file != null) {
				TaraBoxFileImpl taraFile = (TaraBoxFileImpl) PsiManager.getInstance(project).findFile(file);
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
	public static List<Concept> findAllConceptsOfFile(TaraBoxFile taraBoxFile) {
		List<Concept> collection = new ArrayList<>();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraBoxFile, Concept.class);
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

	public static TaraBoxFile getTaraFileFromVirtual(Project project, VirtualFile vFile) {
		PsiFile file = PsiManager.getInstance(project).findFile(vFile);
		if (file instanceof TaraBoxFile) return (TaraBoxFile) file;
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

	public static TaraBoxFile getOrCreateFile(String destiny, Project project) {
		TaraBoxFileImpl boxFile = (TaraBoxFileImpl) PsiFileFactory.getInstance(project).
			createFileFromText(destiny + "." + TaraFileType.INSTANCE.getDefaultExtension(), TaraFileType.INSTANCE, "");
		VirtualFileManager.getInstance().refreshWithoutFileWatcher(false);
		return boxFile;
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

	public static Module getModuleOfDirectory(PsiDirectory file) {
		ProjectFileIndex fileIndex = ProjectRootManager.getInstance(file.getProject()).getFileIndex();
		return fileIndex.getModuleForFile(file.getVirtualFile());
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

	public static Concept findConceptByQN(String qualifiedName, PsiFile file) {
		List<TaraBoxFileImpl> filesOfModule = getTaraFilesOfModule(file.getProject(), getModuleOfFile(file));
		for (TaraBoxFileImpl taraFile : filesOfModule) {
			Concept[] rootConceptsOfFile = getRootConceptsOfFile(taraFile);
			for (Concept concept : rootConceptsOfFile)
				if (concept.getQualifiedName().equals(qualifiedName)) return concept;
		}
		return null;
	}

	public static TaraConceptReference[] getLinksOf(Concept concept) {
		return concept.getBody() == null ? new TaraConceptReference[0] : concept.getBody().getConceptLinks();
	}
}
