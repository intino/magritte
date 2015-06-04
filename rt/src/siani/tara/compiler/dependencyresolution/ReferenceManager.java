package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;

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
		return (NodeImpl) resolve(reference.getReference(), reference.getContainer());
	}

	Node resolve(FacetTarget target, NodeContainer node) {
		Node result = resolve(target.getTarget(), node);
		return result instanceof NodeReference ? ((NodeReference) result).getDestiny() : result;
	}

	NodeImpl resolve(Variable variable, NodeContainer container) {
		Node result = resolve(variable.getType(), container);
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
			reference = (reference == null) ? name.equals(node.getName()) ? node : null :
				reference.getInclude(name);
			if (reference == null) return null;
		}
		return reference;
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
		set.addAll(facetTarget.getIncludedNodes());
	}

	private void addRoots(String name, Set<Node> set) {
		set.addAll(model.getIncludedNodes().stream().
			filter(node -> name.equals(node.getName())).
			collect(Collectors.toList()));
	}

	private void addInContext(String name, Set<Node> set, NodeContainer node) {
		checkSiblings(name, set, node);
		NodeContainer container = node.getContainer();
		while (container != null) {
			namesake(name, set, container);
			checkSiblings(name, set, container);
			container = container.getContainer();
		}
	}

	private void checkSiblings(String name, Set<Node> set, NodeContainer container) {
		for (Node sibling : container.getNodeSiblings()) namesake(name, set, sibling);
	}

	private void namesake(String name, Set<Node> set, NodeContainer container) {
		if (container instanceof NodeImpl && namesake((Node) container, name)) set.add((Node) container);
	}

	private boolean namesake(Node node, String name) {
		return name.equals(node.getName());
	}

	private Node searchByQn(Node node, String qn) {
		for (Node inner : node.getIncludedNodes())
			if (node.getQualifiedName().equals(qn)) return inner;
			else searchByQn(inner, qn);
		return null;
	}

}