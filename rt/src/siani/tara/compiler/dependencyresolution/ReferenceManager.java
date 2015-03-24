package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReferenceManager {

	Model model;

	ReferenceManager(Model model) {
		this.model = model;
	}

	NodeImpl resolve(NodeReference reference) {
		return (NodeImpl) resolve(reference.getReference(), getNodeContainer(reference.getContainer()));
	}

	Node resolve(FacetTarget target, NodeContainer container) {
		return resolve(target.getTarget(), getNodeContainer(container));
	}

	NodeImpl resolve(Variable variable, NodeContainer container) {
		return (NodeImpl) resolve(variable.getType(), getNodeContainer(container));
	}

	Node searchByQn(String qn) {
		return searchByQn(model, qn);
	}

	Node resolve(String reference, Node node) {
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

	private Collection<Node> searchPossibleRoots(Node node, String name) {
		Set<Node> set = new LinkedHashSet<>();
		checkName(name, set, node);
		addInContext(name, set, node);
		addRoots(name, set);
		return set;
	}

	private void addRoots(String name, Set<Node> set) {
		for (Node node : model.getIncludedNodes())
			if (name.equals(node.getName())) set.add(node);
	}

	private void addInContext(String name, Set<Node> set, NodeContainer node) {
		checkSiblings(name, set, node);
		NodeContainer container = node.getContainer();
		while (container != null) {
			checkName(name, set, container);
			checkSiblings(name, set, container);
			container = container.getContainer();
		}
	}

	private void checkSiblings(String name, Set<Node> set, NodeContainer container) {
		for (Node sibling : container.getNodeSiblings()) checkName(name, set, sibling);
	}

	private void checkName(String name, Set<Node> set, NodeContainer container) {
		if (container instanceof NodeImpl && checkName((Node) container, name))
			set.add((Node) container);
	}

	private boolean checkName(Node node, String name) {
		return name.equals(node.getName());
	}

	private Node searchByQn(Node node, String qn) {
		for (Node inner : node.getIncludedNodes())
			if (node.getQualifiedName().equals(qn)) return inner;
			else searchByQn(inner, qn);
		return null;
	}

	private NodeImpl getNodeContainer(NodeContainer reference) {
		NodeContainer container = reference;
		while (!(container instanceof NodeImpl))
			container = container.getContainer();
		return (NodeImpl) container;
	}
}
