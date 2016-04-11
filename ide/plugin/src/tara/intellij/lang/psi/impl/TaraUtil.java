package tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.*;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.io.refactor.Refactors;
import tara.lang.model.*;
import tara.lang.semantics.Constraint;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static org.jetbrains.jps.model.java.JavaResourceRootType.RESOURCE;
import static org.jetbrains.jps.model.java.JavaResourceRootType.TEST_RESOURCE;
import static tara.io.refactor.RefactorsDeserializer.refactorFrom;

public class TaraUtil {

	private TaraUtil() {
	}

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
		return LanguageManager.getLanguage(file.getVirtualFile() == null ? file.getOriginalFile() : file);
	}

	public static String getOutputDsl(@NotNull PsiElement element) {
		final TaraFacetConfiguration configuration = getFacetConfiguration(element);
		if (configuration == null) return "";
		return configuration.outputDsl();
	}

	public static int getLevel(@NotNull PsiElement element) {
		final TaraFacetConfiguration configuration = getFacetConfiguration(element);
		if (configuration == null) return -1;
		return configuration.getLevel();
	}

	public static TaraFacetConfiguration getFacetConfiguration(@NotNull PsiElement element) {
		return getFacetConfiguration(ModuleProvider.getModuleOf(element));
	}

	public static TaraFacetConfiguration getFacetConfiguration(@Nullable Module module) {
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return null;
		return facet.getConfiguration();
	}

	public static boolean isDefinitionFile(PsiFile file) {
		final Module moduleOf = ModuleProvider.getModuleOf(file);
		final VirtualFile definitions = getContentRoot(moduleOf, "definitions");
		return definitions != null && file.getVirtualFile().getPath().startsWith(definitions.getPath());
	}

	public static boolean isModelFile(PsiFile file) {
		final Module moduleOf = ModuleProvider.getModuleOf(file);
		final VirtualFile definitions = getContentRoot(moduleOf, "model");
		return definitions != null && file.getVirtualFile().getPath().startsWith(definitions.getPath());
	}

	public static boolean isTestModelFile(PsiFile file) {
		final Module moduleOf = ModuleProvider.getModuleOf(file);
		final VirtualFile definitions = getContentRoot(moduleOf, "test-model");
		return definitions != null && file.getVirtualFile().getPath().startsWith(definitions.getPath());
	}

	@Nullable
	public static List<Constraint> getConstraintsOf(Node node) {
		Language language = getLanguage((PsiElement) node);
		if (language == null) return null;
		return language.constraints(node.resolve().type());
	}

	@Nullable
	public static List<Constraint> getConstraintsOf(Facet facet) {
		final Node nodeOf = TaraPsiImplUtil.getContainerNodeOf((PsiElement) facet);
		final List<Constraint> allowsOf = getConstraintsOf(nodeOf);
		if (allowsOf == null || allowsOf.isEmpty()) return Collections.emptyList();
		return collectFacetConstrains(facet, allowsOf);
	}

	private static List<Constraint> collectFacetConstrains(Facet facet, List<Constraint> constraints) {
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.Facet && ((Constraint.Facet) constraint).type().equals(facet.type()))
				return ((Constraint.Facet) constraint).constraints();
		return Collections.emptyList();
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
		if (node.facetTarget() != null && node.facetTarget().targetNode() != null)
			for (Variable parentVar : node.facetTarget().targetNode().variables())
				if (isOverridden(variable, parentVar))
					return (TaraVariable) parentVar;
		return null;
	}

	private static boolean isOverridden(Variable variable, Variable parentVar) {
		return parentVar.type() != null && parentVar.type().equals(variable.type()) && parentVar.name() != null && parentVar.name().equals(variable.name());
	}

	public static Constraint.Parameter getConstraint(Node container, Parameter parameter) {
		Facet facet = areFacetParameters(parameter);
		List<Constraint> allowsOf = facet != null ? getConstraints(container, facet.type()) : TaraUtil.getConstraintsOf(container);
		if (allowsOf == null) return null;
		List<Constraint.Parameter> parametersAllowed = parametersAllowed(allowsOf);
		if (parametersAllowed.isEmpty() || parametersAllowed.size() <= parameter.position()) return null;
		return !parameter.name().isEmpty() || parameter instanceof TaraVarInit ?
			findParameter(parametersAllowed, parameter.name()) :
			getParameterByIndex(parameter, parametersAllowed);
	}

	private static Constraint.Parameter getParameterByIndex(Parameter parameter, List<Constraint.Parameter> parameterConstraints) {
		for (Constraint.Parameter constraint : parameterConstraints)
			if (constraint.position() == getIndexInParent(parameter)) return constraint;
		return null;
	}

	private static int getIndexInParent(Parameter parameter) {
		return parameter.position();
	}


	private static List<Constraint> getConstraints(Node container, String facetApply) {
		Collection<Constraint> allowsOf = TaraUtil.getConstraintsOf(container);
		if (allowsOf == null) return Collections.emptyList();
		for (Constraint constraint : allowsOf)
			if (constraint instanceof Constraint.Facet && ((Constraint.Facet) constraint).type().equals(facetApply))
				return ((Constraint.Facet) constraint).constraints();
		return Collections.emptyList();
	}

	private static Facet areFacetParameters(Parameter parameter) {
		NodeContainer contextOf = TaraPsiImplUtil.getContainerOf((PsiElement) parameter);
		return contextOf instanceof Facet ? (Facet) contextOf : null;
	}

	private static List<Constraint.Parameter> parametersAllowed(Collection<Constraint> allowsOf) {
		return allowsOf.stream().filter(constraint -> constraint instanceof Constraint.Parameter).map(constraint -> (Constraint.Parameter) constraint).collect(Collectors.toList());
	}

	private static Constraint.Parameter findParameter(List<Constraint.Parameter> parameters, String name) {
		for (Constraint.Parameter variable : parameters)
			if (variable.name().equals(name))
				return variable;
		return null;
	}

	public static List<Node> getMainNodesOfFile(TaraModel file) {
		Set<Node> list = new LinkedHashSet<>();
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

	public static List<Node> getAllNodesOfFile(TaraModel model) {
		Set<Node> all = new HashSet<>();
		final Node[] rootNodes = PsiTreeUtil.getChildrenOfType(model, TaraNode.class);
		if (rootNodes == null) return Collections.emptyList();
		final List<Node> nodes = Arrays.asList(rootNodes);
		for (Node include : nodes) all.addAll(include.subs());
		for (Node root : nodes) getAllInnerOf(root, all);
		return new ArrayList<>(all);
	}

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
		if (!all.add(root)) return;
		for (Node component : root.components()) getAllNodeContainersOf(component, all);
		if (root instanceof Node) {
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
		for (Facet facet : root.facets()) {
			components = PsiTreeUtil.getChildrenOfType(((TaraFacetApply) facet).getBody(), TaraNode.class);
			if (components != null) for (Node node : components) getAllInnerOf(node, all);
		}
	}

	@NotNull
	public static List<Node> getComponentsOf(NodeContainer container) {
		if (container instanceof Node) return TaraPsiImplUtil.getComponentsOf((Node) container);
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
			if (include.name() != null && include.name().equals(name)) return include;
		if (!(node instanceof Node) || ((Node) node).parent() == null) return null;
		return findInParentComponents((Node) node, name);
	}

	@Nullable
	private static Node findInParentComponents(Node node, String name) {
		for (Node include : node.parent().components())
			if (include.name() != null && include.name().equals(name))
				return include;
		return null;
	}

	public static Refactors[] getRefactors(Module module) {
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return new Refactors[2];
		final int level = facet.getConfiguration().getLevel();
		if (level == 2) return new Refactors[2];
		final File directory = LanguageManager.getRefactorsDirectory(module.getProject());
		return level == 1 ? new Refactors[]{refactorFrom(new File(directory, "engine")), null} :
			new Refactors[]{refactorFrom(new File(directory, "engine")), refactorFrom(new File(directory, "domain"))};
	}

	public static List<VirtualFile> getSourceRoots(@NotNull PsiElement foothold) {
		final Module module = ModuleUtilCore.findModuleForPsiElement(foothold);
		if (module != null) return getSourceRoots(module);
		return Collections.emptyList();
	}

	public static List<VirtualFile> getSourceRoots(@NotNull Module module) {
		final Set<VirtualFile> result = new LinkedHashSet<>();
		final ModuleRootManager manager = ModuleRootManager.getInstance(module);
		result.addAll(Arrays.asList(manager.getSourceRoots()));
		result.addAll(Arrays.asList(manager.getContentRoots()));
		return new ArrayList<>(result);
	}

	public static VirtualFile getResourcesRoot(PsiElement element) {
		final Module module = ModuleProvider.getModuleOf(element);
		return getResourcesRoot(module, isTestModelFile(element.getContainingFile()));
	}

	private static VirtualFile getResourcesRoot(Module module, boolean test) {
		if (module == null) return null;
		final List<VirtualFile> roots = ModuleRootManager.getInstance(module).getSourceRoots(test ? TEST_RESOURCE : RESOURCE);
		if (roots.isEmpty()) return null;
		return roots.stream().filter(r -> r.getName().equals(test ? "test-res" : "res")).findAny().orElseGet(null);
	}

	public static VirtualFile getSrcRoot(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && "src".equals(file.getName())) return file;
		return null;
	}

	private static VirtualFile getContentRoot(Module module, String name) {
		if (module == null) return null;
		final VirtualFile[] roots = ModuleRootManager.getInstance(module).getSourceRoots();
		for (VirtualFile file : roots)
			if (file.isDirectory() && name.equals(file.getName())) return file;
		return null;
	}

	private static List<Node> findMainNodes(TaraModel file) {
		final TaraNode[] childrenOfType = PsiTreeUtil.getChildrenOfType(file, TaraNode.class);
		if (childrenOfType == null) return Collections.emptyList();
		final List<Node> rootNodes = Arrays.asList(childrenOfType);
		return rootNodes.stream().filter((node) -> !TaraPsiImplUtil.isAnnotatedAsComponent(node)).collect(Collectors.toList());
	}
}