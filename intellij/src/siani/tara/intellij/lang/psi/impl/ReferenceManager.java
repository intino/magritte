package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.codeinsight.JavaHelper;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.semantic.Assumption;

import java.util.*;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

public class ReferenceManager {

	private ReferenceManager() {
	}

	@Nullable
	public static PsiElement resolve(Identifier identifier) {
		PsiElement reference = resolveInternal(identifier);
		return reference instanceof Node ? ((Node) reference).getIdentifierNode() : reference;
	}

	@Nullable
	public static Node resolveToNode(IdentifierReference identifierReference) {
		List<? extends Identifier> identifierList = identifierReference.getIdentifierList();
		return (Node) resolveNode(identifierList.get(identifierList.size() - 1), (List<Identifier>) identifierList);
	}

	@Nullable
	public static PsiElement resolve(IdentifierReference identifierReference) {
		List<? extends Identifier> identifierList = identifierReference.getIdentifierList();
		PsiElement reference = resolveNode(identifierList.get(identifierList.size() - 1), (List<Identifier>) identifierList);
		if (reference instanceof Node) reference = ((Node) reference).getIdentifierNode();
		return reference;
	}

	@Nullable
	public static PsiElement resolveJavaClassReference(Project project, String path) {
		if (project == null || path == null || path.isEmpty()) return null;
		return JavaHelper.getJavaHelper(project).findClass(path);
	}

	private static PsiElement resolveInternal(Identifier identifier) {
		if (identifier.getParent() instanceof IdentifierReference)
			return resolveNode(identifier, getIdentifiersOfReference(identifier));
		if (identifier.getParent() instanceof HeaderReference)
			return identifier.getParent().getParent() instanceof TaraDslDeclaration ?
				resolveMetamodelImport(identifier) : resolveHeaderReference(identifier);
		if (identifier.getParent() instanceof Signature)
			return identifier;
		return null;
	}

	private static PsiElement resolveMetamodelImport(Identifier identifier) {
		HeaderReference reference = (HeaderReference) identifier.getParent();
		List<? extends Identifier> identifiers = reference.getIdentifierList();
		if (identifiers.size() > 2) return null;
		Project project = identifier.getProject();
		if (identifier.equals(identifiers.get(0))) return getPsiFile(project.getProjectFile(), project);
		if (identifier.equals(identifiers.get(1))) {
			Module moduleByName = ModuleManager.getInstance(project).findModuleByName(identifier.getName());
			return moduleByName != null ? getPsiFile(moduleByName.getModuleFile(), project) : null;
		}
		return null;
	}

	private static PsiElement getPsiFile(VirtualFile file, Project project) {
		return PsiManager.getInstance(project).findFile(file);
	}

	private static PsiElement resolveHeaderReference(Identifier identifier) {
		return resolveBoxPath(identifier);
	}

	private static List<Identifier> getIdentifiersOfReference(Identifier identifier) {
		List<Identifier> path = (List<Identifier>) ((IdentifierReference) (identifier.getParent())).getIdentifierList();
		path = path.subList(0, path.indexOf(identifier) + 1);
		return path;
	}

	private static PsiElement resolveNode(PsiElement identifier, List<Identifier> path) {
		List<Identifier> subPath = path.subList(0, path.indexOf(identifier) + 1);
		PsiElement element = tryToResolveInBox((TaraModel) identifier.getContainingFile(), subPath);
		if (element != null) return element;
		element = tryToResolveOnImportedBoxes(subPath);
		if (element != null) return element;
		return tryToResolveAsQN(subPath);
	}

	private static PsiElement tryToResolveInBox(TaraModel file, List<Identifier> path) {
		Node[] possibleRoots = getPossibleRoots(file, path.get(0));
		if (possibleRoots.length == 0) return null;
		if (possibleRoots.length == 1 && path.size() == 1) return possibleRoots[0];
		for (Node possibleRoot : possibleRoots) {
			Node node = resolvePathInNode(path, possibleRoot);
			if (node != null) return node;
		}
		return null;
	}

	protected static Node[] getPossibleRoots(TaraModel file, Identifier identifier) {
		Set<Node> set = new LinkedHashSet<>();
		addNodesInContext(identifier, set);
		addRootNodes(file, identifier, set);
		addAggregated(file, identifier, set, toArrayList(set));
		return set.toArray(new Node[set.size()]);
	}

	private static PsiElement tryToResolveAsQN(List<Identifier> path) {
		TaraModelImpl resolve;
		resolve = resolveBoxPath(path.get(0));
		if (resolve == null || path.isEmpty()) return null;
		List<Identifier> qn = path.subList(1, path.size());
		if (qn.isEmpty()) return resolve;
		return tryToResolveInBox(resolve, qn);
	}

	private static List<Node> toArrayList(Set<Node> set) {
		List<Node> visited = new ArrayList<>();
		visited.addAll(set);
		return visited;
	}

	private static void addRootNodes(TaraModel file, Identifier identifier, Set<Node> set) {
		Collection<Node> nodes = file.getNodes();
		for (Node node : nodes)
			if (namesAreEqual(identifier, node))
				set.add(node);
	}

	private static void addNodesInContext(Identifier identifier, Set<Node> set) {
		Node container = getContainerNodeOf(identifier);
		if (container != null && !isExtendsReference((IdentifierReference) identifier.getParent()) &&
			namesAreEqual(identifier, container))
			set.add(container);
		while (container != null) {
			for (Node sibling : collectCandidates(container))
				if (namesAreEqual(identifier, sibling) && !sibling.equals(getContainerNodeOf(identifier)))
					set.add(sibling);
			container = container.getContainer();
		}
	}

	private static Collection<Node> collectCandidates(Node container) {
		List<Node> nodes = new ArrayList<>();
		Collection<Node> siblings = container.getNodeSiblings();
		nodes.addAll(siblings);
		for (Node node : siblings) nodes.addAll(node.getSubNodes());
		return nodes;
	}

	private static boolean isExtendsReference(IdentifierReference reference) {
		return reference.getParent() instanceof Signature;
	}

	private static void addAggregated(TaraModel file, Identifier identifier, Set<Node> set, List<Node> visited) {
		List<Node> allNodesOfFile = TaraUtil.getAllNodesOfFile(file);
		for (Node node : allNodesOfFile)
			if (namesAreEqual(identifier, node) && isAggregated(file, node, visited))
				set.add(node);
	}

	private static boolean namesAreEqual(Identifier identifier, Node node) {
		return identifier.getText().equals(node.getName());
	}

	private static boolean isAggregated(TaraModel file, Node node, List<Node> visited) {
		if (visited.contains(node)) return false;
		visited.add(node);
		if (node.isAnnotatedAsAggregated() || isMetaAggregated(node)) return true;
		IdentifierReference parentReference = node.getSignature().getParentReference();
		return parentReference != null && checkPossibleAggregates(parentReference, getRootNodes(file, parentReference, visited, new HashSet<Node>()));
	}

	private static boolean isMetaAggregated(Node node) {
		Collection<Assumption> assumptionsOf = TaraUtil.getAssumptionsOf(node);
		for (Assumption assumption : assumptionsOf)
			if (assumption instanceof Assumption.Aggregated)
				return true;
		return false;
	}

	private static boolean checkPossibleAggregates(IdentifierReference parentReference, Node[] roots) {
		if (roots.length == 0) return false;
		for (Node possibleRoot : roots) {
			Node aggregated = resolvePathInNode((List<Identifier>) parentReference.getIdentifierList(), possibleRoot);
			if (aggregated != null) return aggregated.isAnnotatedAsAggregated();
		}
		return false;
	}

	private static Node[] getRootNodes(TaraModel file, IdentifierReference parentReference, List<Node> visited, Set<Node> roots) {
		Identifier identifier = getIdentifier(parentReference);
		addNodesInContext(identifier, roots);
		addRootNodes(file, identifier, roots);
		visited.addAll(roots);
		addAggregated((TaraModel) identifier.getContainingFile(), identifier, roots, visited);
		return roots.toArray(new Node[roots.size()]);
	}

	private static Identifier getIdentifier(IdentifierReference reference) {
		List<? extends Identifier> identifierList = reference.getIdentifierList();
		return identifierList.get(identifierList.size() - 1);
	}

	private static Node resolvePathInNode(List<Identifier> path, Node node) {
		Node reference = null;
		for (Identifier identifier : path) {
			reference = (reference == null) ? namesAreEqual(identifier, node) ? node : null :
				TaraUtil.findInner(reference, identifier.getText());
			if (reference == null) return null;
		}
		return reference;
	}

	private static TaraModelImpl resolveBoxPath(Identifier identifier) {
		TaraModel containingFile = (TaraModel) identifier.getContainingFile().getOriginalFile();
		if (containingFile.getVirtualFile() == null) return null;
		Module moduleOfDocument = ModuleProvider.getModuleOf(containingFile);
		List<TaraModelImpl> taraFilesOfModule = TaraUtil.getTaraFilesOfModule(moduleOfDocument);
		for (TaraModelImpl taraBoxFile : taraFilesOfModule)
			if (taraBoxFile.getPresentableName().equals(identifier.getText())) return taraBoxFile;
		return null;
	}

	private static PsiElement tryToResolveOnImportedBoxes(List<Identifier> path) {
		TaraModel context = (TaraModel) path.get(0).getContainingFile();
		Collection<Import> imports = context.getImports();
		return searchInImport(path, imports);
	}

	private static Node searchInImport(List<Identifier> path, Collection<Import> imports) {
		for (Import anImport : imports) {
			PsiElement resolve = resolveImport(anImport);
			if (resolve == null || !TaraModel.class.isInstance(resolve.getContainingFile())) continue;
			TaraModel containingFile = (TaraModel) resolve.getContainingFile();
			Node node = resolvePathInBox(containingFile, path);
			if (node != null) return node;
		}
		return null;
	}

	private static Node resolvePathInBox(TaraModel containingFile, List<Identifier> path) {
		Set<Node> nodes = new HashSet<>();
		nodes.addAll(containingFile.getNodes());
		addAggregated(containingFile, path.get(0), nodes, toArrayList(nodes));
		for (Node node : nodes) {
			Node solution = resolvePathInNode(path, node);
			if (solution != null) return solution;
		}
		return null;
	}

	private static PsiElement resolveImport(Import anImport) {
		List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
		return resolve(importIdentifiers.get(importIdentifiers.size() - 1));
	}

	public static PsiElement resolveMeasure(MeasureType element) {
		Project project = element.getProject();
		String path = project.getName().toLowerCase() + "." + "metrics" + "." + element.getText();
		return resolveJavaClassReference(project, path);
	}
}