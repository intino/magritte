package tara.intellij.lang.psi.impl;

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
import tara.Language;
import tara.intellij.TaraRuntimeException;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.*;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.*;
import tara.language.semantics.Allow;

import java.util.*;
import java.util.stream.Collectors;

public class TaraUtil {

	private TaraUtil() {
	}

	@NotNull
	public static List<Node> findRootNode(PsiElement element, String identifier) {
		List<Node> result = new ArrayList<>();
		for (TaraModel taraFile : getModuleFiles(element.getContainingFile())) {
			Collection<Node> nodes = taraFile.components();
			extractNodesByName(identifier, result, nodes);
		}
		return result;
	}

	private static void extractNodesByName(String identifier, List<Node> result, Collection<Node> nodes) {
		result.addAll(nodes.stream().
			filter(node -> identifier.equals(node.name())).collect(Collectors.toList()));
	}

	@Nullable
	public static Language getLanguage(PsiElement element) {
		if (element == null) return null;
		PsiFile file = element.getContainingFile();
		return TaraLanguage.getLanguage(file.getVirtualFile() == null ? file.getOriginalFile() : file);
	}

	@Nullable
	public static List<Allow> getAllowsOf(Node node) {
		Language language = getLanguage((PsiElement) node);
		if (language == null) return null;
		return language.allows(node.resolve().type());
	}

	@NotNull
	public static List<String> getTypesOf(Node node) {
		Language language = getLanguage((PsiElement) node);
		if (language == null) return Collections.emptyList();
		final List<String> types = language.types(node.resolve().type());
		return types == null ? Collections.emptyList() : types;
	}

	@Nullable
	public static TaraVariable getOverriddenVariable(Variable variable) {
		Node node = TaraPsiImplUtil.getContainerNodeOf((PsiElement) variable);
		if (node == null) return null;
		Node parent = node.parent();
		while (parent != null) {
			for (Variable parentVar : parent.variables())
				if (isOverridden(variable, parentVar))
					return (TaraVariable) parentVar;
			parent = parent.parent();
		}
		return null;
	}

	private static boolean isOverridden(Variable variable, Variable parentVar) {
		return parentVar.type() != null && parentVar.type().equals(variable.type()) && parentVar.name() != null && parentVar.name().equals(variable.name());
	}

	public static Allow.Parameter getCorrespondingAllow(Node container, PsiElement element) {
		if (element instanceof Variable) return null;
		return getCorrespondingAllow(container, (Parameter) element);
	}

	public static Allow.Parameter getCorrespondingAllow(Node container, Parameter parameter) {
		Facet facet = areFacetParameters(parameter);
		Collection<Allow> allowsOf = facet != null ? getAllows(container, facet.type()) : TaraUtil.getAllowsOf(container);
		if (allowsOf == null) return null;
		List<Allow.Parameter> parametersAllowed = parametersAllowed(allowsOf);
		if (parametersAllowed.isEmpty() || parametersAllowed.size() <= parameter.position()) return null;
		return !parameter.name().isEmpty() || parameter instanceof TaraVarInit ?
			findParameter(parametersAllowed, parameter.name()) :
			getParameterByIndex(parameter, parametersAllowed);
	}

	private static Allow.Parameter getParameterByIndex(Parameter parameter, List<Allow.Parameter> parametersAllowed) {
		for (Allow.Parameter allow : parametersAllowed)
			if (allow.position() == getIndexInParent(parameter)) return allow;
		return null;
	}

	private static int getIndexInParent(Parameter parameter) {
		return parameter.position();
	}


	private static Collection<Allow> getAllows(Node container, String facetApply) {
		Collection<Allow> allowsOf = TaraUtil.getAllowsOf(container);
		if (allowsOf == null) return Collections.EMPTY_LIST;
		for (Allow allow : allowsOf)
			if (allow instanceof Allow.Facet && ((Allow.Facet) allow).type().equals(facetApply))
				return ((Allow.Facet) allow).allows();
		return Collections.EMPTY_LIST;
	}

	private static Facet areFacetParameters(Parameter parameter) {
		NodeContainer contextOf = TaraPsiImplUtil.getContainerOf((PsiElement) parameter);
		return contextOf instanceof Facet ? (Facet) contextOf : null;
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
		Set<Node> list = new HashSet<>();
		Node[] nodes = PsiTreeUtil.getChildrenOfType(file, TaraNode.class);
		if (nodes == null) return new ArrayList<>(list);
		for (Node node : nodes) {
			list.add(node);
			list.addAll(node.subs());
		}
		List<Node> mainNodes = findMainNodes(file);
		for (Node main : mainNodes) {
			if (list.contains(main)) continue;
			list.add(main);
		}
		return new ArrayList<>(list);
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
		final Node[] rootNodes = PsiTreeUtil.getChildrenOfType(model, TaraNode.class);
		if (rootNodes == null) return Collections.emptyList();
		final List<Node> nodes = Arrays.asList(rootNodes);
		for (Node include : nodes) all.addAll(include.subs());
		for (Node root : nodes) getAllInnerOf(root, all);
		return new ArrayList<>(all);
	}

	@NotNull
	public static List<NodeContainer> getAllNodeContainersOfFile(TaraModel model) {
		Set<NodeContainer> all = new HashSet<>();
		final Node[] nodes = PsiTreeUtil.getChildrenOfType(model, TaraNode.class);
		if (nodes == null) return Collections.emptyList();
		final List<Node> includes = Arrays.asList(nodes);
		for (Node include : includes) all.addAll(include.subs());
		for (Node root : includes) getAllNodeContainersOf(root, all);
		return new ArrayList<>(all);
	}

	private static void getAllNodeContainersOf(NodeContainer root, Set<NodeContainer> all) {
		all.add(root);
		for (Node include : root.components()) getAllNodeContainersOf(include, all);
		if (root instanceof Node) {
			for (FacetTarget facetTarget : ((Node) root).facetTargets()) {
				all.add(facetTarget);
				for (Node node : facetTarget.components()) getAllNodeContainersOf(node, all);
			}
			for (Facet facetApply : ((Node) root).facets()) {
				all.add(facetApply);
				for (Node node : facetApply.components()) getAllNodeContainersOf(node, all);
			}
		}
	}

	private static void getAllInnerOf(Node root, Set<Node> all) {
		all.add(root);
		TaraNode[] components = PsiTreeUtil.getChildrenOfType(((TaraNode) root).getBody(), TaraNode.class);
		if (components != null) for (Node include : components) getAllInnerOf(include, all);
		for (FacetTarget facetTarget : root.facetTargets()) {
			components = PsiTreeUtil.getChildrenOfType(((TaraFacetTarget) facetTarget).getBody(), TaraNode.class);
			if (components != null) for (Node node : components) getAllInnerOf(node, all);
		}
		for (Facet facet : root.facets()) {
			components = PsiTreeUtil.getChildrenOfType(((TaraFacetApply) facet).getBody(), TaraNode.class);
			if (components != null) for (Node node : components) getAllInnerOf(node, all);
		}
	}

	@NotNull
	public static List<Node> getComponentsOf(NodeContainer container) {
		if (container instanceof Node) return TaraPsiImplUtil.getComponentsOf((Node) container);
		else if (container instanceof FacetTarget) return TaraPsiImplUtil.getComponentsOf((FacetTarget) container);
		else return TaraPsiImplUtil.getComponentsOf((Facet) container);
	}

	@NotNull
	public static List<Node> getComponentsOf(Facet facet) {
		return TaraPsiImplUtil.getComponentsOf(facet);
	}

	@NotNull
	public static List<Variable> getVariablesOf(Facet facet) {
		return TaraPsiImplUtil.getVariablesInBody(((TaraFacetApply) facet).getBody());
	}

	@Nullable
	public static Node findInner(NodeContainer node, String name) {
		for (Node include : node.components())
			if (include.name() != null && include.name().equals(name))
				return include;
		if (!(node instanceof Node) || ((Node) node).parent() == null) return null;
		for (Node include : ((Node) node).parent().components())
			if (include.name() != null && include.name().equals(name))
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
	public static List<VirtualFile> getSourceRoots(@NotNull PsiElement foothold) {
		final Module module = ModuleUtilCore.findModuleForPsiElement(foothold);
		if (module != null) return getSourceRoots(module);
		return Collections.emptyList();
	}

	@NotNull
	public static List<VirtualFile> getSourceRoots(@NotNull Module module) {
		final Set<VirtualFile> result = new LinkedHashSet<>();
		final ModuleRootManager manager = ModuleRootManager.getInstance(module);
		result.addAll(Arrays.asList(manager.getSourceRoots()));
		result.addAll(Arrays.asList(manager.getContentRoots()));
		return new ArrayList<>(result);
	}

	public static VirtualFile getSrcRoot(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && "src".equals(file.getName())) return file;
		throw new TaraRuntimeException("src directory not found");
	}

	public static List<Node> findMainNodes(TaraModel file) {
		return getAllNodesOfFile(file).stream().filter(TaraPsiImplUtil::isAnnotatedAsMain).collect(Collectors.toList());
	}


}