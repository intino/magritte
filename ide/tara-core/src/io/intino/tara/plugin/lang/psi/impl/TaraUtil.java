package io.intino.tara.plugin.lang.psi.impl;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import io.intino.tara.plugin.lang.file.TaraFileType;
import io.intino.tara.plugin.project.configuration.ConfigurationManager;
import io.intino.tara.lang.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.intino.tara.Language;
import io.intino.tara.compiler.shared.Configuration;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.plugin.lang.LanguageManager;
import io.intino.tara.plugin.lang.psi.TaraModel;
import io.intino.tara.plugin.lang.psi.TaraNode;
import io.intino.tara.plugin.lang.psi.TaraVarInit;
import io.intino.tara.plugin.lang.psi.TaraVariable;
import io.intino.tara.plugin.project.module.ModuleProvider;
import io.intino.tara.lang.semantics.Constraint;

import java.util.*;
import java.util.stream.Collectors;

import static org.jetbrains.jps.model.java.JavaResourceRootType.RESOURCE;
import static org.jetbrains.jps.model.java.JavaResourceRootType.TEST_RESOURCE;

public class TaraUtil {
	private static final String FUNCTIONS = "functions";

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
		result.addAll(nodes.stream().filter(node -> identifier.equals(node.name())).collect(Collectors.toList()));
	}

	@Nullable
	public static Language getLanguage(PsiElement element) {
		if (element == null) return null;
		PsiFile file = element.getContainingFile();
		return LanguageManager.getLanguage(file.getVirtualFile() == null ? file.getOriginalFile() : file);
	}

	public static String workingPackage(@NotNull PsiElement element) {
		if (!(element.getContainingFile() instanceof TaraModel)) return "";
		final Configuration conf = configurationOf(element);
		if (conf == null) return "";
		return conf.workingPackage();
	}

	public static Level level(@NotNull PsiElement element) {
		final Configuration configuration = configurationOf(element);
		return configuration == null ? null : configuration.level();
	}

	public static Configuration configurationOf(@NotNull PsiElement element) {
		return configurationOf(ModuleProvider.moduleOf(element));
	}

	public static Configuration configurationOf(@Nullable Module module) {
		return ConfigurationManager.configurationOf(module);
	}

	private static boolean isTestModelFile(PsiFile file) {
		final Module moduleOf = ModuleProvider.moduleOf(file);
		final VirtualFile definitions = getContentRoot(moduleOf, "test");
		return definitions != null && file.getVirtualFile() != null && file.getVirtualFile().getPath().startsWith(definitions.getPath());
	}

	@Nullable
	public static List<Constraint> getConstraintsOf(Node node) {
		Language language = getLanguage((PsiElement) node);
		if (language == null) return null;
		return language.constraints(node.resolve().type());
	}

	@NotNull
	private static List<Constraint.Parameter> parameterConstraintsOf(Node node) {
		Language language = getLanguage((PsiElement) node);
		if (language == null) return Collections.emptyList();
		final List<Constraint> nodeConstraints = language.constraints(node.resolve().type());
		if (nodeConstraints == null) return Collections.emptyList();
		final List<Constraint> constraints = new ArrayList<>(nodeConstraints);
		List<Constraint.Parameter> parameters = new ArrayList<>();
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.Parameter) parameters.add((Constraint.Parameter) constraint);
			else if (constraint instanceof Constraint.Facet)
				parameters.addAll(((Constraint.Facet) constraint).constraints().stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList()));
		return parameters;
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

	public static Constraint.Parameter parameterConstraintOf(Parameter parameter) {
		List<Constraint.Parameter> parameters = parameterConstraintsOf(parameter.container());
		if (parameters.isEmpty() || parameters.size() <= parameter.position()) return null;
		return !parameter.name().isEmpty() || parameter instanceof TaraVarInit ?
			findParameter(parameters, parameter.name()) : getParameterByIndex(parameter, parameters);
	}

	private static Constraint.Parameter getParameterByIndex(Parameter parameter, List<Constraint.Parameter> parameterConstraints) {
		for (Constraint.Parameter constraint : parameterConstraints)
			if (constraint.position() == getIndexInParent(parameter)) return constraint;
		return null;
	}

	private static int getIndexInParent(Parameter parameter) {
		return parameter.position();
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
		Module module = ModuleProvider.moduleOf(psiFile);
		if (module == null) return new TaraModelImpl[0];
		List<TaraModel> taraFiles = getTaraFilesOfModule(module);
		return taraFiles.toArray(new TaraModel[taraFiles.size()]);
	}

	public static List<TaraModel> getTaraFilesOfModule(Module module) {
		List<TaraModel> taraFiles = new ArrayList<>();
		if (module == null) return taraFiles;
		Collection<VirtualFile> files = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.instance(), GlobalSearchScope.moduleScope(module));
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
		for (Node root : nodes) getRecursiveComponentsOf(root, all);
		return new ArrayList<>(all);
	}

	private static void getRecursiveComponentsOf(Node root, Set<Node> all) {
		all.add(root);
		TaraNode[] components = PsiTreeUtil.getChildrenOfType(((TaraNode) root).getBody(), TaraNode.class);
		if (components != null) for (Node include : components) getRecursiveComponentsOf(include, all);
	}

	@NotNull
	public static List<Node> getComponentsOf(NodeContainer container) {
		return TaraPsiImplUtil.getComponentsOf((Node) container);
	}

	@Nullable
	public static Node findComponent(NodeContainer node, String name) {
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

	@NotNull
	public static String importsFile(io.intino.tara.plugin.lang.psi.Valued valued) {
		return ModuleProvider.moduleOf(valued).getName() + LanguageManager.JSON;
	}

	public static String methodReference(PsiElement valued) {
		final PsiDirectory aPackage = valued.getContainingFile().getContainingDirectory();
		final PsiJavaFile file = (PsiJavaFile) aPackage.findFile(((TaraModel) valued.getContainingFile()).getPresentableName() + ".java");
		if (file == null) return "";
		return file.getClasses()[0].getQualifiedName();
	}

	public static PsiDirectory findFunctionsDirectory(Module module, String workingPackage) {
		return findOrCreateDirectory(module, workingPackage, FUNCTIONS);
	}

	private static PsiDirectory findOrCreateDirectory(Module module, String workingPackage, String dirName) {
		if (module == null) return null;
		final VirtualFile srcRoot = getSrcRoot(module);
		final PsiDirectory srcDirectory = srcRoot == null ? null : new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) PsiManager.getInstance(module.getProject()), srcRoot);
		final List<String> path = new ArrayList<>(Arrays.asList(workingPackage.toLowerCase().split("\\.")));
		path.add(dirName);
		PsiDirectory destinyDir = srcDirectory;
		if (destinyDir == null) return null;
		for (String name : path) {
			if (destinyDir == null) break;
			destinyDir = destinyDir.findSubdirectory(name) == null ? createDirectory(destinyDir, name) : destinyDir.findSubdirectory(name);
		}
		return destinyDir;
	}

	private static PsiDirectory createDirectory(final PsiDirectory basePath, final String name) {
		final com.intellij.openapi.application.Application application = ApplicationManager.getApplication();
		if (application.isWriteAccessAllowed())
			return application.<PsiDirectory>runWriteAction(() -> DirectoryUtil.createSubdirectories(name, basePath, "."));
		else {
			PsiDirectory[] directory = new PsiDirectory[1];
			application.invokeLater(() -> directory[0] = application.<PsiDirectory>runWriteAction(() -> DirectoryUtil.createSubdirectories(name, basePath, ".")));
			return directory[0];
		}
	}

	public static VirtualFile getResourcesRoot(PsiElement element) {
		final Module module = ModuleProvider.moduleOf(element);
		return getResourcesRoot(module, isTestModelFile(element.getContainingFile()));
	}

	private static VirtualFile getResourcesRoot(Module module, boolean test) {
		if (module == null) return null;
		final List<VirtualFile> roots = ModuleRootManager.getInstance(module).getSourceRoots(test ? TEST_RESOURCE : RESOURCE);
		if (roots.isEmpty()) return null;
		return roots.stream().filter(r -> r.getName().equals(test ? "test-res" : "res")).findAny().orElseGet(null);
	}

	public static VirtualFile getSrcRoot(Module module) {
		if (module == null) return null;
		for (VirtualFile file : getSourceRoots(module))
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