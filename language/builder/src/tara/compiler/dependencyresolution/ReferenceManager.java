package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Variable;

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

	NodeImpl resolve(Variable variable, NodeContainer container) {
		Node result = resolve(variable.type(), container);
		return result instanceof NodeReference ? ((NodeReference) result).getDestiny() : (NodeImpl) result;
	}

	Node searchByQn(String qn) {
		return searchByQn(model, qn);
	}

	Node resolve(String reference, NodeContainer node) {
		String[] path = reference.split("\\.");
		Collection<Node> roots = searchPossibleRoots(node, path[0]);
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
				reference = areNamesake(node, name) ? node : null;
				continue;
			}
			if (reference.component(name) == null && reference.parent() != null)
				reference = reference.parent().component(name);
			else reference = reference.component(name);
			if (reference == null) return null;
		}
		return reference;
	}

	private boolean areNamesake(Node node, String name) {
		return name.equals(node.name());
	}

	private Collection<Node> searchPossibleRoots(NodeContainer node, String name) {
		Set<Node> set = new LinkedHashSet<>();
		namesake(name, set, node);
		addInContext(name, set, node);
		if (node instanceof FacetTarget) addFacetRoots((FacetTarget) node, set);
		addRoots(name, set);
		return set;
	}

	private void addFacetRoots(FacetTarget facetTarget, Set<Node> set) {
		set.addAll(facetTarget.components());
	}

	private void addRoots(String name, Set<Node> set) {
		set.addAll(model.components().stream().
			filter(node -> areNamesake(node, name)).
			collect(Collectors.toList()));
	}

	private void addInContext(String name, Set<Node> set, NodeContainer node) {
		checkSiblings(name, set, node);
		NodeContainer container = node.container();
		while (container != null) {
			namesake(name, set, container);
			checkSiblings(name, set, container);
			container = container.container();
		}
	}

	private void checkSiblings(String name, Set<Node> set, NodeContainer container) {
		for (Node sibling : container.siblings()) namesake(name, set, sibling);
	}

	private void namesake(String name, Set<Node> set, NodeContainer container) {
		if (container instanceof NodeImpl && namesake((Node) container, name)) set.add((Node) container);
	}

	private boolean namesake(Node node, String name) {
		return areNamesake(node, name);
	}

	private Node searchByQn(Node node, String qn) {
		for (Node inner : node.components())
			if (node.qualifiedName().equals(qn)) return inner;
			else searchByQn(inner, qn);
		return null;
	}

}