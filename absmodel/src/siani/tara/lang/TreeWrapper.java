package siani.tara.lang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TreeWrapper {

	private NodeTree nodeTree = new NodeTree();
	private Set<String> identifiers = new HashSet<>();
	private Map<String, Node> nodeTable = new HashMap<>();

	public NodeTree getTree() {
		return nodeTree;
	}

	public boolean addIdentifier(String identifier) {
		return !identifier.isEmpty() && identifiers.add(identifier);
	}

	public Set<String> getIdentifiers() {
		return identifiers;
	}

	public Map<String, Node> getNodeTable() {
		return nodeTable;
	}

	public boolean add(Node node) {
		return nodeTree.add(node);
	}

	public void putAllIdentifiers(Set<String> set) {
		identifiers.addAll(set);
	}

	public void putAllInNodeNameTable(Map<String, Node> m) {
		for (Map.Entry<String, Node> entry : m.entrySet())
			if (!nodeTable.containsKey(entry.getKey()))
				nodeTable.put(entry.getKey(), entry.getValue());
	}

	public boolean add(String name, Node node) {
		nodeTable.put(name, node);
		return true;
	}

	public Node get(String qualifiedName) {
		return nodeTable.get(qualifiedName);
	}

	public boolean addAll(NodeTree nodeTree) {
		return this.nodeTree.addAll(nodeTree);
	}

	public Node searchAncestry(Node node) {
		if (node.getObject().isCase()) return node.getContainer();
		if (node.getObject().getParentName() == null) return null;
		String ancestry = node.getObject().getParentName();
		Node result = relativeSearch(ancestry, node);
		if (result != null) return result;
		return absoluteSearch(ancestry, node);
	}

	public Node searchChildrenByName(Node parent, String childName) {
		for (Node node : nodeTable.values()) {
			boolean cond = parent.equals(searchAncestry(node));
			if (parent.equals(searchAncestry(node)) && (childName.equals(node.getName()) || ("").equals(node.getName())))
				return node;
		}
		return null;
	}

	public Node searchNode(String nodeName, Node context) {
		if (nodeName == null || nodeName.isEmpty()) return null;
		Node result = relativeSearch(nodeName, context);
		if (result != null) return result;
		return absoluteSearch(nodeName, context);
	}

	public boolean hasIdentifier(String identifier) {
		return identifiers.contains(identifier);
	}

	private boolean isUnName(String text) {
		return text.isEmpty();
	}

	private Node absoluteSearch(String path, Node context) {
		Node node = nodeTable.get(path);
		if (node != null) return node;
		for (String importPath : context.getObject().getImports())
			if (nodeTable.containsKey(importPath) && importPath.equals(path))
				return nodeTable.get(importPath);
		return null;
	}


	private Node relativeSearch(String path, Node context) {
		return (context.getContainer() != null) ? get(context.getContainer().getQualifiedName() + "." + path) : null;
	}

}
