package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ReferenceManager {

	Model model;

	public ReferenceManager(Model model) {
		this.model = model;
	}

	public NodeImpl resolve(NodeReference reference) {
		return (NodeImpl) resolve(reference.getReference(), reference.container());
	}

	Node resolve(FacetTarget target, NodeContainer node) {
		Node result = resolve(target.target(), node);
		return result instanceof NodeReference ? ((NodeReference) result).getDestiny() : result;
	}

	NodeImpl resolve(VariableReference variable, NodeContainer container) {
		Node result = resolve(variable.getDestinyName(), container);
		return result instanceof NodeReference ? ((NodeReference) result).getDestiny() : (NodeImpl) result;
	}

	Node searchByQn(String qn) {
		return searchByQn(model, qn);
	}

	Node resolve(String reference, NodeContainer node) {
		String[] path = reference.split("\\.");
		Collection<Node> roots = searchPossibleRoots(node, path[0], false);
		if (roots.isEmpty()) return null;
		if (roots.size() == 1 && path.length == 1) return roots.iterator().next();
		for (Node root : roots) {
			Node candidate = resolvePathInNode(path, root);
			if (candidate != null) return candidate;
		}
		return null;
	}

	Node resolveParent(String reference, NodeContainer node) {
		String[] path = reference.split("\\.");
		Collection<Node> roots = searchPossibleRoots(node, path[0], true);
		if (roots.isEmpty()) return null;
		if (roots.size() == 1 && path.length == 1) return roots.iterator().next();
		for (Node root : roots) {
			Node candidate = resolvePathInNode(path, root);
			if (candidate != null) return candidate;
		}
		return null;
	}

	private Node resolvePathInNode(String[] path, Node node) {
		Node reference = null;
		for (String name : path) {
			if (reference == null) {
				reference = areNamesake(name, node) ? node : null;
				continue;
			}
			if (reference.component(name) == null && reference.parent() != null)
				reference = reference.parent().component(name);
			else reference = reference.component(name);
			if (reference == null) return null;
		}
		return reference;
	}

	private static boolean areNamesake(String name, Node node) {
		return name.equals(node.name());
	}

	private Collection<Node> searchPossibleRoots(NodeContainer node, String name, boolean parent) {
		Set<Node> set = new LinkedHashSet<>();
		namesake(name, set, node);
		addInContext(name, set, node, parent);
		if (node instanceof FacetTarget) addFacetRoots((FacetTarget) node, set);
		addNodeSiblings(name, node, set);
		addRoots(name, set);
		return set;
	}

	private static void addNodeSiblings(String identifier, NodeContainer container, Set<Node> set) {
		if (container == null) return;
		set.addAll(container.components().stream().filter(node -> areNamesake(identifier, node)).collect(Collectors.toList()));
	}

	private void addFacetRoots(FacetTarget facetTarget, Set<Node> set) {
		set.addAll(facetTarget.components());
	}

	private void addRoots(String name, Set<Node> set) {
		set.addAll(model.components().stream().
			filter(node -> areNamesake(name, node)).
			collect(Collectors.toList()));
	}

	private void addInContext(String name, Set<Node> set, NodeContainer node, boolean parent) {
		checkSiblings(name, set, node);
		NodeContainer container = node.container();
		while (container != null) {
			namesake(name, set, container);
			checkSiblings(name, set, container);
			container = container.container();
			if (parent) {
				final Node parentNode = ((Node) node).parent();
				if (parentNode != null) collectParentComponents(name, set, container, parentNode);
			}
		}
	}

	private static void collectParentComponents(String identifier, Set<Node> set, NodeContainer container, Node parent) {
		set.addAll(parent.components().stream().
			filter(sibling -> areNamesake(identifier, sibling) && !sibling.equals(container)).
			collect(Collectors.toList()));
	}

	private void checkSiblings(String name, Set<Node> set, NodeContainer container) {
		for (Node sibling : container.siblings()) namesake(name, set, sibling);
	}

	private void namesake(String name, Set<Node> set, NodeContainer container) {
		if (container instanceof NodeImpl && namesake((Node) container, name)) set.add((Node) container);
	}

	private boolean namesake(Node node, String name) {
		return areNamesake(name, node);
	}

	private Node searchByQn(Node node, String qn) {
		for (Node inner : node.components())
			if (node.qualifiedName().equals(qn)) return inner;
			else searchByQn(inner, qn);
		return null;
	}

}