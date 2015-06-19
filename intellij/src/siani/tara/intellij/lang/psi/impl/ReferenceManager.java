package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.codeinsight.JavaHelper;
import siani.tara.intellij.lang.lexer.TaraPrimitives;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;
import java.util.stream.Collectors;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContextOf;

public class ReferenceManager {

	private ReferenceManager() {
	}

	@Nullable
	public static PsiElement resolve(Identifier identifier) {
		PsiElement reference = internalResolve(identifier);
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

	private static PsiElement internalResolve(Identifier identifier) {
		if (identifier.getParent() instanceof IdentifierReference)
			return resolveNode(identifier, getIdentifiersOfReference(identifier));
		if (identifier.getParent() instanceof HeaderReference)
			return identifier.getParent().getParent() instanceof TaraDslDeclaration ?
				identifier : resolveHeaderReference(identifier);
		if (identifier.getParent() instanceof Signature) return identifier;
		return null;
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
		Node[] roots = getPossibleRoots(file, path.get(0));
		if (roots.length == 0) return null;
		if (roots.length == 1 && path.size() == 1) return roots[0];
		for (Node possibleRoot : roots) {
			if (possibleRoot.isEnclosed()) continue;
			Node node = resolvePathInNode(path, possibleRoot);
			if (node != null) return node;
		}
		return null;
	}

	protected static Node[] getPossibleRoots(TaraModel file, Identifier identifier) {
		Set<Node> set = new LinkedHashSet<>();
		addNodesInContext(identifier, set);
		addRootNodes(file, identifier, set);
//		addRoots(file, identifier, set, set);
		return set.toArray(new Node[set.size()]);
	}

	private static PsiElement tryToResolveAsQN(List<Identifier> path) {
		TaraModel resolve = resolveBoxPath(path.get(0));
		if (resolve == null || path.isEmpty()) return null;
		List<Identifier> qn = path.subList(1, path.size());
		if (qn.isEmpty()) return resolve;
		return tryToResolveInBox(resolve, qn);
	}


	private static void addRootNodes(TaraModel model, Identifier identifier, Set<Node> set) {
		Collection<Node> nodes = model.getIncludes();
		set.addAll(nodes.stream().filter(node -> areNamesake(identifier, node)).collect(Collectors.toList()));
	}

	private static void addNodesInContext(Identifier identifier, Set<Node> set) {
		Node container = getContainerNodeOf(identifier);
		if (isInFacetTarget(identifier)) addFacetTargetNodes(set, identifier);
		if (container != null && !isExtendsReference(identifier) && areNamesake(identifier, container))
			set.add(container);
		if (container != null) collectContextNodes(identifier, set, container);
	}

	private static boolean isInFacetTarget(Identifier identifier) {
		final PsiElement contextOf = getContextOf(identifier);
		return contextOf instanceof FacetTarget && ((FacetTarget) contextOf).getIdentifierReference() != null &&
			!((FacetTarget) contextOf).getIdentifierReference().getIdentifierList().contains(identifier);
	}

	private static void addFacetTargetNodes(Set<Node> set, Identifier identifier) {
		FacetTarget facetTarget = (FacetTarget) getContextOf(identifier);
		if (facetTarget == null) return;
		for (Node node : facetTarget.getIncludes()) {
			if (node.getName() == null) continue;
			set.add(node);
			if (node.isAbstract()) set.addAll(node.getSubNodes());
		}
	}

	private static void collectContextNodes(Identifier identifier, Set<Node> set, Node node) {
		Node container = node;
		while (container != null) {
			set.addAll(collectCandidates(container).stream().
				filter(sibling -> areNamesake(identifier, sibling) && !sibling.equals(getContainerNodeOf(identifier))).
				collect(Collectors.toList()));
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

	private static boolean isExtendsReference(Identifier reference) {
		return reference.getParent().getParent() instanceof Signature;
	}

	//
//	private static void addRoots(TaraModel file, Identifier identifier, Set<Node> set, Collection<Node> visited) {
//		List<Node> allNodesOfFile = TaraUtil.getAllNodesOfFile(file);
//		final List<Node> collect = allNodesOfFile.stream().
//			filter(node -> areNamesake(identifier, node) && isRoot(file, node, visited)).
//			collect(Collectors.toList());
//		set.addAll(collect);
//	}
//
	private static boolean areNamesake(Identifier identifier, Node node) {
		return identifier.getText().equals(node.getName());
	}
//
//	private static boolean isRoot(TaraModel file, Node node, Collection<Node> visited) {
//		if (visited.contains(node)) return false;
//		visited.add(node);
//		if (node.isAnnotatedAsMain() || isMetaRoot(node)) return true;
//		IdentifierReference parentReference = node.getSignature().getParentReference();
//		return parentReference != null && checkPossibleRoots(parentReference, getIncludes(file, parentReference, visited, new HashSet<>()));
//	}
//
//	private static boolean isMetaRoot(Node node) {
//		Collection<Assumption> assumptionsOf = TaraUtil.getAssumptionsOf(node);
//		if (assumptionsOf == null) return false;
//		for (Assumption assumption : assumptionsOf)
//			if (assumption instanceof Assumption.Root)
//				return true;
//		return false;
//	}
//
//	private static boolean checkPossibleRoots(IdentifierReference parentReference, Node[] roots) {
//		if (roots.length == 0) return false;
//		for (Node possibleRoot : roots) {
//			Node node = resolvePathInNode((List<Identifier>) parentReference.getIdentifierList(), possibleRoot);
//			if (node != null) return node.isAnnotatedAsMain();
//		}
//		return false;
//	}
//
//	private static Node[] getIncludes(TaraModel file, IdentifierReference parentReference, Collection<Node> visited, Set<Node> roots) {
//		Identifier identifier = getIdentifier(parentReference);
//		addNodesInContext(identifier, roots);
//		addRootNodes(file, identifier, roots);
//		visited.addAll(roots);
//		addRoots((TaraModel) identifier.getContainingFile(), identifier, roots, visited);
//		return roots.toArray(new Node[roots.size()]);
//	}

	private static Identifier getIdentifier(IdentifierReference reference) {
		List<? extends Identifier> identifierList = reference.getIdentifierList();
		return identifierList.get(identifierList.size() - 1);
	}

	private static Node resolvePathInNode(List<Identifier> path, Node node) {
		Node reference = null;
		for (Identifier identifier : path) {
			reference = (reference == null) ? (areNamesake(identifier, node) ? node : null) : TaraUtil.findInner(reference, identifier.getText());
			if (reference == null || (reference.isEnclosed() && !isLast(identifier, path))) return null;
		}
		return reference;
	}

	private static boolean isLast(Identifier identifier, List<Identifier> path) {
		return path.indexOf(identifier) == path.size() - 1;
	}

	private static TaraModel resolveBoxPath(Identifier identifier) {
		TaraModel containingFile = (TaraModel) identifier.getContainingFile().getOriginalFile();
		if (containingFile.getVirtualFile() == null) return null;
		Module moduleOfDocument = ModuleProvider.getModuleOf(containingFile);
		for (TaraModel taraBoxFile : TaraUtil.getTaraFilesOfModule(moduleOfDocument))
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
		nodes.addAll(containingFile.getIncludes());
//		addRoots(containingFile, path.get(0), nodes, nodes);
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

	public static PsiElement resolveContract(Contract contract) {
		if (isMeasure(contract))
			return resolveMetric(contract);
		else return resolveNativeClass(contract, contract.getProject());
	}

	private static boolean isMeasure(Contract contract) {
		PsiElement parent = contract.getParent();
		while (!(parent instanceof Variable)) parent = parent.getParent();
		return TaraPrimitives.MEASURE.equals(((Variable) parent).getType());
	}

	private static PsiElement resolveMetric(Contract contract) {
		final Module moduleOf = ModuleProvider.getModuleOf(contract);
		final TaraFacet taraFacetByModule = TaraFacet.getTaraFacetByModule(moduleOf);
		if (taraFacetByModule == null) return null;
		final String generatedDslName = taraFacetByModule.getConfiguration().getGeneratedDslName();
		return resolveJavaClassReference(contract.getProject(), generatedDslName.toLowerCase() + "." + "metrics" + "." + contract.getFormattedName());
	}

	private static PsiElement resolveNativeClass(Contract contract, Project project) {
		if (contract == null) return null;
		final TaraFacet taraFacetByModule = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(contract));
		if (taraFacetByModule == null) return null;
		String aPackage = taraFacetByModule.getConfiguration().getGeneratedDslName().toLowerCase() + '.' + "natives";
		return resolveJavaClassReference(project, aPackage.toLowerCase() + '.' + capitalize(contract.getFormattedName()));
	}

	private static String capitalize(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}