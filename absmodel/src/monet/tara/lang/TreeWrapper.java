package monet.tara.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeWrapper {

	private NodeTree nodeTree = new NodeTree();
	private transient Map<String, String> identifierMap = new HashMap<>();
	private Map<String, List<Node>> nodeNameLookUpTable = new HashMap<>();

	public NodeTree getTree() {
		return nodeTree;
	}

	public void addIdentifier(String identifier, String context) {
		if (!identifierMap.containsKey(identifier) && !isUnName(identifier))
			if (identifierMap.containsKey(identifier.toUpperCase() + "_KEY") && !identifierMap.containsValue(identifier))
				identifierMap.put(identifier.toUpperCase() + "_MAIN_" + context, identifier);
			else
				identifierMap.put(identifier.toUpperCase() + "_" + context, identifier);
	}

	public Map<String, String> getIdentifiers() {
		return identifierMap;
	}

	public Map<String, List<Node>> getNodeNameLookUpTable() {
		return nodeNameLookUpTable;
	}

	public boolean add(Node node) {
		return nodeTree.add(node);
	}

	public void putAllIdentifiers(Map<String, String> m) {
		identifierMap.putAll(m);
	}

	public void putAllInNodeNameTable(Map<String, List<Node>> m) {
		for (Map.Entry<String, List<Node>> entry : m.entrySet())
			if (!nodeNameLookUpTable.containsKey(entry.getKey()))
				nodeNameLookUpTable.put(entry.getKey(), entry.getValue());
			else nodeNameLookUpTable.get(entry.getKey()).addAll(entry.getValue());
	}

	public boolean add(String name, Node node) {
		List<Node> list = nodeNameLookUpTable.containsKey(name) ? nodeNameLookUpTable.get(name) : new ArrayList<Node>();
		list.add(node);
		nodeNameLookUpTable.put(name, list);
		return true;
	}

	public boolean addAll(NodeTree nodeTree) {
		return this.nodeTree.addAll(nodeTree);
	}

	public Node searchAncestry(Node node) {
		if (node.getObject().getParentName() == null && node.getObject().getBaseNode() == null) return null;
		String ancestry = (node.getObject().getParentName() != null) ? node.getObject().getParentName() : node.getObject().getBaseNode();
		Node result = relativeSearch(ancestry, node);
		if (result != null) return result;
		return absoluteSearch(ancestry, node);
	}

	public List<String> getKeys(String value) {
		List<String> keys = new ArrayList<>();
		for (Map.Entry<String, String> entry : identifierMap.entrySet()) {
			if (entry.getValue().equals(value))
				keys.add(entry.getKey());
		}
		return keys;
	}

	public Node searchNode(String nodeName, Node context) {
		Node result = relativeSearch(nodeName, context);
		if (nodeName == null || nodeName.isEmpty()) return null;
		if (result != null) return result;
		return absoluteSearch(nodeName, context);
	}

	private boolean isUnName(String text) {
		return text.isEmpty();
	}

	private Node absoluteSearch(String path, Node context) {
		String[] tree = path.split("\\.");
		List<Node> nodes = nodeNameLookUpTable.get(tree[tree.length - 1]);
		if (nodes == null) return null;
		for (Node node : nodes)
			for (String importPath : context.getObject().getImports()) {
				String[] split = importPath.split("\\.");
				if (split[split.length - 1].equals(node.getObject().getName())) return node;
			}
		for (Node node : nodes)
			if (context.getObject().getPackage().equals(node.getObject().getPackage()))
				return node;
		return null;
	}


	private Node relativeSearch(String path, Node context) {
		String[] tree = path.split("\\.");
		NodeTree nodes;
		if (context.getContainer() != null) nodes = context.getInnerNodes();
		else return null;
		String nodeName = tree[0];
		Node node = isInList(nodes, nodeName);
		if (!nodeName.isEmpty() && node != null)
			relativeSearch(concatenate(tree, 1), node);
		return (context.equals(node)) ? null : node;
	}

	private String concatenate(String[] tree, int start) {
		String result = "";
		for (int i = start; i < tree.length; i++)
			result += tree[i];
		return result;
	}

	private Node isInList(NodeTree nodes, String nodeName) {
		for (Node node : nodes)
			if (node.getObject().getName().equals(nodeName)) return node;
		return null;
	}

}
