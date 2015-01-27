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
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.FacetTarget;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.*;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getConceptContainerOf;

public class TaraUtil {

	private static final String FACET_APPLY = "@facetApply";
	private static final String FACET_TARGET = "@facetTarget";

	private TaraUtil() {
	}

	@NotNull
	public static List<Concept> findRootConcept(PsiElement element, String identifier) {
		List<Concept> result = new ArrayList<>();
		for (TaraBoxFileImpl taraFile : getModuleFiles(element.getContainingFile())) {
			Collection<Concept> concepts = taraFile.getConcepts();
			extractConceptsByName(identifier, result, concepts);
		}
		return result;
	}

	private static void extractConceptsByName(String identifier, List<Concept> result, Collection<Concept> concepts) {
		for (Concept concept : concepts)
			if (identifier.equals(concept.getName()))
				result.add(concept);
	}

	public static String getMetaQualifiedName(Concept concept) {
		PsiElement element = concept;
		String type = concept != null && !concept.isSub() ? concept.getType() : "";
		while ((element = TaraPsiImplUtil.getContextOf(element)) != null)
			if (element instanceof Concept && !((Concept) element).isSub())
				type = ((Concept) element).getType() + (type != null && type.length() > 0 ? "." + type : "");
			else if (element instanceof TaraFacetApply || element instanceof TaraFacetTarget) {
				if (element instanceof TaraFacetTarget)
					type = FACET_TARGET + "(" + ((TaraFacetTarget) element).getIdentifierReference().getText() + ")" + "." + type;
				else type = FACET_APPLY + "(" +
					getFacetPath((TaraFacetApply) element) + ")" + "." + type;
				Concept conceptContextOf = getConceptContainerOf(element);
				if (conceptContextOf != null) {
					type = conceptContextOf.getType() + type;
					element = conceptContextOf;
				}
			}
		return type;
	}

	public static List<Concept> buildConceptCompositionPathOf(Concept concept) {
		Concept aConcept = concept;
		List<Concept> path = new ArrayList<>();
		path.add(aConcept);
		while (concept != null && !concept.isAggregated()) {
			Concept parent = TaraPsiImplUtil.getConceptContainerOf(concept);
			if (parent != null)
				if (parent.isSub() && !concept.isSub() || !parent.isSub() && !concept.isSub())
					path.add(0, parent);
			concept = parent;
		}
		return path;
	}


	private static String getFacetPath(TaraFacetApply apply) {
		PsiElement element = apply;
		String path = "";
		while (element instanceof TaraFacetApply) {
			path = ((TaraFacetApply) element).getMetaIdentifierList().get(0).getText() + (path.length() > 0 ? "$" + path : "");
			element = TaraPsiImplUtil.getContextOf(element);
		}
		return path;
	}

	public static String getMetaQualifiedName(TaraConceptReference reference) {
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(reference);
		String metaQualifiedName = getMetaQualifiedName(concept);
		if (reference.getIdentifierReference() == null) return "";
		List<TaraIdentifier> identifierList = reference.getIdentifierReference().getIdentifierList();
		PsiElement resolve = ReferenceManager.resolve(identifierList.get(identifierList.size() - 1));
		if (resolve == null || !Identifier.class.isInstance(resolve)) return null;
		Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(resolve);
		String type = contextOf.getType();
		return metaQualifiedName + "." + type;
	}

	@NotNull
	public static Collection<Concept> getRootConceptsOfFile(TaraBoxFile taraBoxFile) {
		List<Concept> list = new ArrayList<>();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraBoxFile, Concept.class);
		if (concepts != null)
			for (Concept concept : concepts) {
				list.add(concept);
				list.addAll(concept.getSubConcepts());
			}
		return list;
	}

	@NotNull
	private static TaraBoxFileImpl[] getModuleFiles(PsiFile psiFile) {
		Project project = psiFile.getProject();
		Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(psiFile.getVirtualFile());
		if (module == null) return new TaraBoxFileImpl[0];
		List<TaraBoxFileImpl> taraFiles = getTaraFilesOfModule(module);
		return taraFiles.toArray(new TaraBoxFileImpl[taraFiles.size()]);
	}

	public static List<TaraBoxFileImpl> getTaraFilesOfModule(Module module) {
		List<TaraBoxFileImpl> taraFiles = new ArrayList<>();
		Collection<VirtualFile> files = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.moduleScope(module));
		for (VirtualFile file : files)
			if (file != null) {
				TaraBoxFileImpl taraFile = (TaraBoxFileImpl) PsiManager.getInstance(module.getProject()).findFile(file);
				if (taraFile != null) taraFiles.add(taraFile);
			}
		return taraFiles;
	}

	@NotNull
	public static List<Concept> getAllConceptsOfFile(TaraBoxFile taraBoxFile) {
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
		for (Concept child : TaraUtil.getInnerConceptsOf(concept)) {
			collection.add(child);
			collectChildren(child, collection);
		}
	}

	@NotNull
	public static Collection<Concept> getInnerConceptsOf(Concept concept) {
		return TaraPsiImplUtil.getInnerConceptsOf(concept);
	}

	public static Concept findChildOf(Concept concept, String name) {
		List<Concept> children = TaraPsiImplUtil.getInnerConceptsOf(concept);
		for (Concept child : children)
			if (child.getName() != null && child.getName().equals(name))
				return child;
		return null;
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


	public static Module getModuleOfDirectory(PsiDirectory directory) {
		ProjectFileIndex fileIndex = ProjectRootManager.getInstance(directory.getProject()).getFileIndex();
		return fileIndex.getModuleForFile(directory.getVirtualFile());
	}

	public static VirtualFile getSrcRoot(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && "src".equals(file.getName())) return file;
		throw new RuntimeException("src directory not found");
	}

	public static boolean isTerminalBox(TaraBoxFileImpl boxFile) {
		ModuleConfiguration instance = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(boxFile));
		return instance != null && instance.isTerminal();
	}

	public static Concept findConceptByQN(String qualifiedName, PsiFile file) {
		if (file == null) return null;
		List<TaraBoxFileImpl> filesOfModule = getTaraFilesOfModule(ModuleProvider.getModuleOf(file));
		for (TaraBoxFileImpl taraFile : filesOfModule)
			for (Concept concept : getRootConceptsOfFile(taraFile))
				if (concept.getQualifiedName().equalsIgnoreCase(qualifiedName)) return concept;
		return null;
	}

	public static TaraConceptReference[] getLinksOf(Concept concept) {
		return concept.getBody() == null ? new TaraConceptReference[0] : concept.getBody().getConceptLinks();
	}

	public static Node findNode(Concept concept, Model model) {
		Model.SearchNode searchTree = createSearchTree(concept);
		return model != null ? model.searchNode(searchTree) : null;
	}

	private static Model.SearchNode createSearchTree(Concept concept) {
		Model.SearchNode forward = null;
		Concept forwardConcept = concept;
		Model.SearchNode previous = new Model.SearchNode(forwardConcept.getType());
		addProperties(concept, previous);
		while ((forwardConcept = TaraPsiImplUtil.getConceptContainerOf(forwardConcept)) != null) {
			forward = new Model.SearchNode(forwardConcept.getType());
			addProperties(forwardConcept, forward);
			forward.setNext(previous);
			previous.setPrevious(forward);
			previous = forward;
		}
		return forward != null ? forward : previous;
	}

	private static void addProperties(Concept concept, Model.SearchNode searchNode) {
		PsiElement contextOf = TaraPsiImplUtil.getContextOf(concept);
		if (contextOf instanceof FacetApply)
			searchNode.setInFacetApply(((FacetApply) contextOf).getFacetName());
		else if (contextOf instanceof FacetTarget)
			searchNode.setInFacetApply(((FacetTarget) contextOf).getDestinyName());
		List<String> facets = new ArrayList<>();
		for (FacetApply apply : concept.getFacetApplies()) facets.add(apply.getFacetName());
		searchNode.setFacets(facets);
	}

	public static Inflector getInflector(Module module) {
		return InflectorFactory.getInflector(ModuleConfiguration.getInstance(module).getLanguage());
	}
}
