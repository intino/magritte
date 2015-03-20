package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
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

	Node resolve(NodeReference reference) {
		return null;
	}

	Node resolve(String reference, NodeImpl node) {
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
				node.getInclude(name);
			if (reference == null) return null;
		}
		return reference;
	}

	private Collection<Node> searchPossibleRoots(Node node, String name) {
		Set<Node> set = new LinkedHashSet<>();
		addInContext(name, set, node.getContainer());
		addRoots(name, set);
		return set;
	}

	private void addRoots(String name, Set<Node> set) {
		for (Node node : model.getIncludedNodes())
			if (name.equals(node.getName())) set.add(node);
	}

	private void addInContext(String name, Set<Node> set, NodeContainer node) {
		NodeContainer container = node.getContainer();
		if (container instanceof NodeImpl && checkName((Node) node, name))
			set.add((Node) container);
		for (Node sibling : node.getNodeSiblings()) {
			if (sibling instanceof NodeImpl && checkName(sibling, name))
				set.add(sibling);
		}
	}

	private boolean checkName(Node node, String name) {
		return name.equals(node.getName());
	}

	public Node searchByQn(String qn) {
		return searchByQn(model, qn);
	}

	private Node searchByQn(Node node, String qn) {
		for (Node inner : node.getIncludedNodes())
			if (node.getQualifiedName().equals(qn)) return inner;
			else searchByQn(inner, qn);
		return null;
	}

}
