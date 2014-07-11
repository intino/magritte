package siani.tara.lang;

import java.util.*;

public class Model {
	private String name;
	private Map<String, Node> nodeTable = new HashMap<>();
	private NodeTree nodeTree = new NodeTree();
	private Set<String> identifiers = new HashSet<>();

	public Model(String name) {
		this.name = name;
	}

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

	public boolean add(DeclaredNode node) {
		return nodeTree.add(node);
	}

	public void putAllIdentifiers(Set<String> set) {
		identifiers.addAll(set);
	}

	public void putAllInNodeTable(Map<String, Node> m) {
		for (Map.Entry<String, Node> entry : m.entrySet())
			if (!nodeTable.containsKey(entry.getKey()))
				nodeTable.put(entry.getKey(), entry.getValue());
	}

	public boolean add(String name, Node node) {
		nodeTable.put(name, node);
		return true;
	}

	public Node get(String qualifiedName) {
		if (!nodeTable.containsKey(qualifiedName)) return null;
		return nodeTable.get(qualifiedName);
	}

	public String getModelName() {
		return name;
	}

	public boolean addAll(NodeTree nodeTree) {
		return this.nodeTree.addAll(nodeTree);
	}

	public DeclaredNode searchAncestry(Node node) {
		if (node.getObject().isCase()) return node.getContainer();
		if (node.getObject().getParentName() == null) return null;
		String ancestry = node.getObject().getParentName();
		Node result = relativeSearch(ancestry, node);
		if (result != null) return (DeclaredNode) result;
		return (DeclaredNode) searchInImportReferences(ancestry, node);
	}

	public DeclaredNode searchDeclaredNodeOfLink(LinkNode node) {
		Node result = relativeSearch(node.getDestinyQN(), node);
		if (result != null) return (DeclaredNode) result;
		return (DeclaredNode) searchInImportReferences(node.getDestinyQN(), node);
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
		Node result = null;
		if (nodeName == null || nodeName.isEmpty()) return null;
		if (context != null) result = relativeSearch(nodeName, context);
		if (result != null) return result;
		return searchInImportReferences(nodeName, context);
	}

	public Node searchNode(String nodeName) {
		if (nodeName == null || nodeName.isEmpty()) return null;
		Node root = null;
		String[] split = nodeName.split("\\.");
		for (Node node : nodeTree) if (node.getName().equals(split[0])) root = node;
		if (root == null) return null;
		String subName = (split.length > 1) ? "." + nodeName.substring(nodeName.indexOf(".")) : "";
		return get(root.getQualifiedName() + subName);
	}

	private Node searchInImportReferences(String path, Node context) {
		for (String box : context.getImports()) {
			if (!nodeTable.containsKey(box + "." + path)) continue;
			Node node = nodeTable.get(box + "." + path);
			if (node != null) return node;
			for (String importPath : context.getImports())
				if (nodeTable.containsKey(importPath) && importPath.equals(path))
					return nodeTable.get(importPath);
		}
		return null;
	}


	private Node relativeSearch(String path, Node context) {
		DeclaredNode container = context.getContainer();
		while (container != null) {
			Node node = get(container.getQualifiedName() + "." + path);
			if (node != null) return node;
			container = container.getContainer();
		}
		return get(context.getBox() + "." + path);
	}

	public void sortNodeTable(Comparator<String> comparator) {
		TreeMap<String, Node> sorted_map = new TreeMap<>(comparator);
		sorted_map.putAll(nodeTable);
		nodeTable = sorted_map;
	}

}
