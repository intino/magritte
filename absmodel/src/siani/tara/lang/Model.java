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

	public void putAllInNodeTable(Map<String, DeclaredNode> m) {
		for (Map.Entry<String, DeclaredNode> entry : m.entrySet())
			if (!nodeTable.containsKey(entry.getKey()))
				nodeTable.put(entry.getKey(), entry.getValue());
	}

	public boolean add(String name, DeclaredNode node) {
		nodeTable.put(name, node);
		return true;
	}

	public Node get(String qualifiedName) {
		if (!nodeTable.containsKey(qualifiedName)) return null;
		return nodeTable.get(qualifiedName);
	}

	public String getName() {
		return name;
	}

	public boolean addAll(NodeTree nodeTree) {
		return this.nodeTree.addAll(nodeTree);
	}

	public DeclaredNode searchAncestry(DeclaredNode node) {
		if (node.getObject().isCase()) return node.getContainer();
		if (node.getObject().getParentName() == null) return null;
		String ancestry = node.getObject().getParentName();
		DeclaredNode result = relativeSearch(ancestry, node);
		if (result != null) return result;
		return absoluteSearchInContext(ancestry, node);
	}

	public Node searchChildrenByName(DeclaredNode parent, String childName) {
		for (Node node : nodeTable.values()) {
			boolean cond = parent.equals(searchAncestry(node));
			if (parent.equals(searchAncestry(node)) && (childName.equals(node.getName()) || ("").equals(node.getName())))
				return node;
		}
		return null;
	}

	public DeclaredNode searchNode(String nodeName, DeclaredNode context) {
		DeclaredNode result = null;
		if (nodeName == null || nodeName.isEmpty()) return null;
		if (context != null) result = relativeSearch(nodeName, context);
		if (result != null) return result;
		return absoluteSearchInContext(nodeName, context);
	}

	public DeclaredNode searchNode(String nodeName) {
		if (nodeName == null || nodeName.isEmpty()) return null;
		DeclaredNode root = null;
		String[] split = nodeName.split("\\.");
		for (Node node : nodeTree) if (node.getName().equals(split[0])) root = node;
		if (root == null) return null;
		String subName = (split.length > 1) ? "." + nodeName.substring(nodeName.indexOf(".")) : "";
		return get(root.getQualifiedName() + subName);
	}

	public boolean hasIdentifier(String identifier) {
		return identifiers.contains(identifier);
	}

	private boolean isUnName(String text) {
		return text.isEmpty();
	}

	private DeclaredNode absoluteSearchInContext(String path, DeclaredNode context) {
		for (String box : context.getImports()) {
			if (!nodeTable.containsKey(box + "." + path)) continue;
			DeclaredNode node = nodeTable.get(box + "." + path);
			if (node != null) return node;
			for (String importPath : context.getImports())
				if (nodeTable.containsKey(importPath) && importPath.equals(path))
					return nodeTable.get(importPath);
		}
		return null;
	}


	private DeclaredNode relativeSearch(String path, DeclaredNode context) {
		DeclaredNode container = context.getContainer();
		while (container != null) {
			DeclaredNode node = get(container.getQualifiedName() + "." + path);
			if (node != null) return node;
			container = container.getContainer();
		}
		return get(context.getBox() + "." + path);
	}

	public void sortNodeTable(Comparator<String> comparator) {
		TreeMap<String, DeclaredNode> sorted_map = new TreeMap<>(comparator);
		sorted_map.putAll(nodeTable);
		nodeTable = sorted_map;
	}

}
