package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.Language;
import siani.tara.intellij.TaraRuntimeException;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.semantic.Allow;

import java.util.*;
import java.util.stream.Collectors;

public class TaraUtil {

	private TaraUtil() {
	}

	@NotNull
	public static List<Node> findRootNode(PsiElement element, String identifier) {
		List<Node> result = new ArrayList<>();
		for (TaraModel taraFile : getModuleFiles(element.getContainingFile())) {
			Collection<Node> nodes = taraFile.getIncludes();
			extractNodesByName(identifier, result, nodes);
		}
		return result;
	}

	private static void extractNodesByName(String identifier, List<Node> result, Collection<Node> nodes) {
		result.addAll(nodes.stream().
			filter(node -> identifier.equals(node.getName())).collect(Collectors.toList()));
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

	@NotNull
	public static Collection<String> getTypesOf(Node node) {
		Language language = getLanguage(node);
		if (language == null) return Collections.emptyList();
		return language.types(node.resolve().getFullType());
	}

	@Nullable
	public static Variable getOverriddenVariable(Variable variable) {
		Node node = TaraPsiImplUtil.getContainerNodeOf(variable);
		if (node == null) return null;
		Node parent = node.getParentNode();
		while (parent != null) {
			for (Variable parentVar : parent.getVariables())
				if (isOverridden(variable, parentVar))
					return parentVar;
			parent = parent.getParentNode();
		}
		return null;
	}

	private static boolean isOverridden(Variable variable, Variable parentVar) {
		return parentVar.getType() != null && parentVar.getType().equals(variable.getType()) && parentVar.getName() != null && parentVar.getName().equals(variable.getName());
	}

	public static Allow.Parameter getCorrespondingAllow(Node container, PsiElement element) {
		if (element instanceof Variable) return null;
		return element instanceof VarInit ? getCorrespondingAllow(container, (VarInit) element) :
			getCorrespondingAllow(container, (Parameter) element);
	}

	public static Allow.Parameter getCorrespondingAllow(Node container, VarInit element) {
		FacetApply facetApply = areFacetVarInit(element);
		Collection<Allow> allowsOf = facetApply != null ? getAllows(container, facetApply.getType()) : TaraUtil.getAllowsOf(container);
		if (allowsOf == null) return null;
		List<Allow.Parameter> parametersAllowed = parametersAllowed(allowsOf);
		return findParameter(parametersAllowed, element.getName());
	}

	public static Allow.Parameter getCorrespondingAllow(Node container, Parameter parameter) {
		FacetApply facetApply = areFacetParameters(parameter);
		Collection<Allow> allowsOf = facetApply != null ? getAllows(container, facetApply.getType()) : TaraUtil.getAllowsOf(container);
		if (allowsOf == null) return null;
		List<Allow.Parameter> parametersAllowed = parametersAllowed(allowsOf);
		if (parametersAllowed.isEmpty() || parametersAllowed.size() <= parameter.getIndexInParent()) return null;
		return parameter.isExplicit() ? findParameter(parametersAllowed, parameter.getExplicitName()) : getParameterByIndex(parameter, parametersAllowed);
	}

	private static Allow.Parameter getParameterByIndex(Parameter parameter, List<Allow.Parameter> parametersAllowed) {
		for (Allow.Parameter allow : parametersAllowed)
			if (allow.position() == getIndexInParent(parameter)) return allow;
		return null;
	}

	private static int getIndexInParent(Parameter parameter) {
		return parameter.getIndexInParent();
	}


	private static Collection<Allow> getAllows(Node container, String facetApply) {
		Collection<Allow> allowsOf = TaraUtil.getAllowsOf(container);
		if (allowsOf == null) return Collections.EMPTY_LIST;
		for (Allow allow : allowsOf)
			if (allow instanceof Allow.Facet && ((Allow.Facet) allow).type().equals(facetApply))
				return ((Allow.Facet) allow).allows();
		return Collections.EMPTY_LIST;
	}

	private static FacetApply areFacetParameters(Parameter parameter) {
		NodeContainer contextOf = TaraPsiImplUtil.getContextOf(parameter);
		return contextOf instanceof FacetApply ? (FacetApply) contextOf : null;
	}

	private static FacetApply areFacetVarInit(VarInit varInit) {
		NodeContainer contextOf = TaraPsiImplUtil.getContextOf(varInit);
		return contextOf instanceof FacetApply ? (FacetApply) contextOf : null;
	}

	private static List<Allow.Parameter> parametersAllowed(Collection<Allow> allowsOf) {
		return allowsOf.stream().filter(allow -> allow instanceof Allow.Parameter).map(allow -> (Allow.Parameter) allow).collect(Collectors.toList());
	}

	private static Allow.Parameter findParameter(List<Allow.Parameter> parameters, String name) {
		for (Allow.Parameter variable : parameters)
			if (variable.name().equals(name))
				return variable;
		return null;
	}


	@NotNull
	public static List<Node> getMainNodesOfFile(TaraModel file) {
		List<Node> list = new ArrayList<>();
		Node[] nodes = PsiTreeUtil.getChildrenOfType(file, Node.class);
		if (nodes == null) return list;
		for (Node node : nodes) {
			list.add(node);
			list.addAll(node.getSubNodes());
		}
		Collection<? extends Node> mainNodes = findMainNodes(file);
		for (Node main : mainNodes) {
			if (list.contains(main)) continue;
			list.add(main);
		}
		return Collections.unmodifiableList(list);
	}

	@NotNull
	private static TaraModel[] getModuleFiles(PsiFile psiFile) {
		Module module = ModuleProvider.getModuleOf(psiFile);
		if (module == null) return new TaraModelImpl[0];
		List<TaraModel> taraFiles = getTaraFilesOfModule(module);
		return taraFiles.toArray(new TaraModel[taraFiles.size()]);
	}

	public static List<TaraModel> getTaraFilesOfModule(Module module) {
		List<TaraModel> taraFiles = new ArrayList<>();
		if (module == null) return taraFiles;
		Collection<VirtualFile> files = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.moduleScope(module));
		files.stream().filter(file -> file != null).forEach(file -> {
			TaraModel taraFile = (TaraModel) PsiManager.getInstance(module.getProject()).findFile(file);
			if (taraFile != null) taraFiles.add(taraFile);
		});
		return taraFiles;
	}

	@NotNull
	public static List<Node> getAllNodesOfFile(TaraModel model) {
		Set<Node> all = new HashSet<>();
		final Node[] nodes = PsiTreeUtil.getChildrenOfType(model, Node.class);
		if (nodes == null) return Collections.emptyList();
		final List<Node> includes = Arrays.asList(nodes);
		for (Node include : includes) all.addAll(include.getSubNodes());
		for (Node root : includes) getAllInnerOf(root, all);
		return new ArrayList<>(all);
	}

	@NotNull
	public static List<NodeContainer> getAllNodeContainersOfFile(TaraModel model) {
		Set<NodeContainer> all = new HashSet<>();
		final Node[] nodes = PsiTreeUtil.getChildrenOfType(model, Node.class);
		if (nodes == null) return Collections.emptyList();
		final List<Node> includes = Arrays.asList(nodes);
		for (Node include : includes) all.addAll(include.getSubNodes());
		for (Node root : includes) getAllNodeContainersOf(root, all);
		return new ArrayList<>(all);
	}

	private static void getAllNodeContainersOf(NodeContainer root, Set<NodeContainer> all) {
		all.add(root);
		for (Node include : root.getIncludes()) getAllNodeContainersOf(include, all);
		if (root instanceof Node) {
			for (FacetTarget facetTarget : ((Node) root).getFacetTargets()) {
				all.add(facetTarget);
				for (Node node : facetTarget.getIncludes()) getAllNodeContainersOf(node, all);
			}
			for (FacetApply facetApply : ((Node) root).getFacetApplies()) {
				all.add(facetApply);
				for (Node node : facetApply.getIncludes()) getAllNodeContainersOf(node, all);
			}
		}
	}

	private static void getAllInnerOf(Node root, Set<Node> all) {
		all.add(root);
		for (Node include : root.getIncludes()) getAllInnerOf(include, all);
		for (FacetTarget facetTarget : root.getFacetTargets())
			for (Node node : facetTarget.getIncludes()) getAllInnerOf(node, all);
		for (FacetApply facetApply : root.getFacetApplies())
			for (Node node : facetApply.getIncludes()) getAllInnerOf(node, all);
	}


	@NotNull
	public static List<Node> getInnerNodesOf(Node node) {
		return TaraPsiImplUtil.getInnerNodesOf(node);
	}

	@NotNull
	public static List<Node> getInnerNodesOf(FacetApply facetApply) {
		return TaraPsiImplUtil.getInnerNodesOf(facetApply);
	}

	@NotNull
	public static List<Node> getInnerNodesOf(FacetTarget facetTarget) {
		return TaraPsiImplUtil.getInnerNodesOf(facetTarget);
	}

	@NotNull
	public static List<Variable> getVariablesOf(FacetApply facetApply) {
		return TaraPsiImplUtil.getVariablesInBody(facetApply.getBody());
	}

	@Nullable
	public static Node findInner(Node node, String name) {
		for (Node include : node.getIncludes())
			if (include.getName() != null && include.getName().equals(name))
				return include;
		if (node.getParentNode() == null) return null;
		for (Node include : node.getParentNode().getIncludes())
			if (include.getName() != null && include.getName().equals(name))
				return include;
		return null;
	}

	public static TaraModel getOrCreateFile(String destiny, Project project) {
		TaraModel boxFile = (TaraModelImpl) PsiFileFactory.getInstance(project).
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

	public static VirtualFile getSrcRoot(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && "src".equals(file.getName())) return file;
		throw new TaraRuntimeException("src directory not found");
	}

	public static List<? extends Node> findMainNodes(TaraModel file) {
		return getAllNodesOfFile(file).stream().filter(Node::isAnnotatedAsMain).collect(Collectors.toList());
	}
}
