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
import org.jetbrains.annotations.Nullable;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.Language;
import siani.tara.intellij.TaraRuntimeException;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.semantic.Allow;
import siani.tara.semantic.Assumption;

import java.util.*;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

public class TaraUtil {

	private static final String FACET_APPLY = "@facetApply";
	private static final String FACET_TARGET = "@facetTarget";

	private TaraUtil() {
	}

	@NotNull
	public static List<Node> findRootNode(PsiElement element, String identifier) {
		List<Node> result = new ArrayList<>();
		for (TaraModelImpl taraFile : getModuleFiles(element.getContainingFile())) {
			Collection<Node> nodes = taraFile.getRootNodes();
			extractNodesByName(identifier, result, nodes);
		}
		return result;
	}

	@Nullable
	public static Language getLanguage(PsiElement element) {
		if (element == null) return null;
		PsiFile file = element.getContainingFile();
		return TaraLanguage.getLanguage(file.getVirtualFile() == null ? file.getOriginalFile() : file);
	}

	@Nullable
	public static Collection<Allow> getAllowsOf(Node node) {
		Language language = getLanguage(node);
		if (language == null) return null;
		return language.allows(node.resolve().getFullType());
	}

	@Nullable
	public static Collection<String> getTypesOf(Node node) {
		Language language = getLanguage(node);
		if (language == null) return null;
		return language.types(node.resolve().getFullType());
	}

	@Nullable
	public static Collection<Allow> getAllowsOf(Node context, String fullType) {
		Language language = getLanguage(context);
		if (language == null) return null;
		return language.allows(fullType);
	}

	@Nullable
	public static Collection<Assumption> getAssumptionsOf(Node node) {
		Language language = getLanguage(node);
		if (language == null) return null;
		return language.assumptions(node.resolve().getFullType());
	}

	private static void extractNodesByName(String identifier, List<Node> result, Collection<Node> nodes) {
		for (Node node : nodes)
			if (identifier.equals(node.getName()))
				result.add(node);
	}

	public static String getMetaQualifiedName(Node node) {
		PsiElement element = node;
		String type = node != null && !node.isSub() ? node.getType() : "";
		while ((element = TaraPsiImplUtil.getContextOf(element)) != null)
			if (isNodeNotSub(element)) type = buildType((Node) element, type);
			else if (isInFacet(element)) {
				type = buildType(element, type);
				Node nodeContextOf = getContainerNodeOf(element);
				if (nodeContextOf != null) {
					type = nodeContextOf.getType() + type;
					element = nodeContextOf;
				}
			}
		return type;
	}

	private static String buildType(Node element, String type) {
		return element.getType() + (type != null && type.length() > 0 ? "." + type : "");
	}

	private static boolean isInFacet(PsiElement element) {
		return element instanceof TaraFacetApply || element instanceof TaraFacetTarget;
	}

	private static boolean isNodeNotSub(PsiElement element) {
		return element instanceof Node && !((Node) element).isSub();
	}

	private static String buildType(PsiElement element, String type) {
		type = element instanceof TaraFacetTarget ?
			FACET_TARGET + "(" + ((TaraFacetTarget) element).getIdentifierReference().getText() + ")" + "." + type :
			FACET_APPLY + "(" + getFacetPath((TaraFacetApply) element) + ")" + "." + type;
		return type;
	}

	public static List<Node> buildNodeCompositionPathOf(Node node) {
		Node aNode = node;
		List<Node> path = new ArrayList<>();
		path.add(aNode);
		while (node != null && !node.isAggregated()) {
			Node parent = TaraPsiImplUtil.getContainerNodeOf(node);
			if (parent != null && !parent.isSub() && !node.isSub())
				path.add(0, parent);
			node = parent;
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

	@NotNull
	public static Collection<Node> getRootNodesOfFile(TaraModel file) {
		List<Node> list = new ArrayList<>();
		Node[] nodes = PsiTreeUtil.getChildrenOfType(file, Node.class);
		if (nodes == null) return list;
		for (Node node : nodes) {
			list.add(node);
			list.addAll(node.getSubNodes());
		}
		Collection<? extends Node> aggregatedNodes = findAggregatedNodes(file);
		for (Node aggregated : aggregatedNodes) {
			if (list.contains(aggregated)) continue;
			list.add(aggregated);
		}
		return list;
	}

	@NotNull
	private static TaraModelImpl[] getModuleFiles(PsiFile psiFile) {
		Module module = ModuleProvider.getModuleOf(psiFile);
		if (module == null) return new TaraModelImpl[0];
		List<TaraModelImpl> taraFiles = getTaraFilesOfModule(module);
		return taraFiles.toArray(new TaraModelImpl[taraFiles.size()]);
	}

	public static List<TaraModelImpl> getTaraFilesOfModule(Module module) {
		List<TaraModelImpl> taraFiles = new ArrayList<>();
		if (module == null) return taraFiles;
		Collection<VirtualFile> files = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.moduleScope(module));
		for (VirtualFile file : files)
			if (file != null) {
				TaraModelImpl taraFile = (TaraModelImpl) PsiManager.getInstance(module.getProject()).findFile(file);
				if (taraFile != null) taraFiles.add(taraFile);
			}
		return taraFiles;
	}

	@NotNull
	public static List<Node> getAllNodesOfFile(TaraModel taraModel) {
		List<Node> collection = new ArrayList<>();
		Node[] nodes = PsiTreeUtil.getChildrenOfType(taraModel, Node.class);
		if (nodes != null) {
			Collections.addAll(collection, nodes);
			for (Node node : nodes) collectInner(node, collection);
		}
		return collection;
	}

	private static void collectInner(Node node, List<Node> collection) {
		for (Node child : TaraPsiImplUtil.getAllInnerNodesOf(node)) {
			collection.add(child);
			collectInner(child, collection);
		}
	}

	@NotNull
	public static Collection<Node> getInnerNodesOf(Node node) {
		return TaraPsiImplUtil.getInnerNodesOf(node);
	}

	public static Node findInner(Node node, String name) {
		Collection<Node> inner = node.getInnerNodes();
		for (Node child : inner)
			if (child.getName() != null && child.getName().equals(name))
				return child;
		return null;
	}

	public static TaraModel getOrCreateFile(String destiny, Project project) {
		TaraModelImpl boxFile = (TaraModelImpl) PsiFileFactory.getInstance(project).
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
		throw new TaraRuntimeException("src directory not found");
	}

	public static Node findNodeByQN(String qualifiedName, PsiFile file) {
		if (file == null) return null;
		List<TaraModelImpl> filesOfModule = getTaraFilesOfModule(ModuleProvider.getModuleOf(file));
		for (TaraModelImpl taraFile : filesOfModule)
			for (Node node : getRootNodesOfFile(taraFile))
				if (node.getQualifiedName().equalsIgnoreCase(qualifiedName)) return node;
		return null;
	}

	public static Collection<NodeReference> getLinksOf(Node node) {
		return node.getBody() == null ? Collections.EMPTY_LIST : node.getBody().getNodeLinks();
	}

	public static Inflector getInflector(Module module) {
		return InflectorFactory.getInflector(ModuleConfiguration.getInstance(module).getLanguage());
	}

	public static Collection<? extends Node> findAggregatedNodes(TaraModel file) {
		Set<Node> aggregated = new HashSet<>();
		for (Node node : getAllNodesOfFile(file))
			if (node.isAnnotatedAsAggregated()) aggregated.add(node);
		return aggregated;
	}


}
