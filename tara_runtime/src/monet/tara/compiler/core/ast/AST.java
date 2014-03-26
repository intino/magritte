package monet.tara.compiler.core.ast;

import java.util.*;

public class AST {

	private List<ASTNode> astRootNodes = new ArrayList<>();
	private Map<String, String> identifierMap = new HashMap<>();
	private Map<String, List<ASTNode>> lookUpTable = new HashMap<>();

	public ASTNode[] getAstRootNodes() {
		return astRootNodes.toArray(new ASTNode[astRootNodes.size()]);
	}

	public void addIdentifier(String identifier, String context) {
		if (!checkRepeated(identifier) && !isNoName(identifier))
			identifierMap.put(identifier.toUpperCase() + "_" + context, identifier);
	}

	public Map<String, String> getIdentifiers() {
		return identifierMap;
	}

	public Map<String, List<ASTNode>> getLookUpTable() {
		return lookUpTable;
	}

	private boolean isNoName(String text) {
		return text.isEmpty();
	}

	private boolean checkRepeated(String text) {
		return identifierMap.containsKey(text);
	}

	public boolean add(ASTNode astNode) {
		return astRootNodes.add(astNode);
	}

	public void putAllIdentifiers(Map<? extends String, ? extends String> m) {
		identifierMap.putAll(m);
	}

	public void putAllLookupTable(Map<? extends String, ? extends List<ASTNode>> m) {
		lookUpTable.putAll(m);
	}

	public boolean add(String name, ASTNode astNode) {
		List<ASTNode> list = lookUpTable.containsKey(name) ? lookUpTable.get(name) : new ArrayList<ASTNode>();
		list.add(astNode);
		lookUpTable.put(name, list);
		return true;
	}

	public boolean addAll(ASTNode[] c) {
		return Collections.addAll(astRootNodes, c);
	}

	public ASTNode searchAncestry(ASTNode node) {
		if (node.getExtendFrom() == null) return null;
		ASTNode result = relativeSearch(node.getExtendFrom(), node);
		if (result != null) return result;
		return absoluteSearch(node.getExtendFrom());
	}

	private ASTNode absoluteSearch(String path) {
		String[] tree = path.split("\\.");
		List<ASTNode> nodes = lookUpTable.get(tree[tree.length - 1]);
		if (nodes == null) return null;
		for (ASTNode node : nodes)
			if (path.equals(node.getAbsolutePath()))
				return node;
		return null;
	}

	private ASTNode relativeSearch(String path, ASTNode context) {
		String[] tree = path.split("\\.");
		ASTNode[] nodes;
		if (context.getParent() != null) nodes = context.getParent().getChildren();
		else return null;
		String nodeName = tree[0];
		ASTNode node = isInList(nodes, nodeName);
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

	private ASTNode isInList(ASTNode[] nodes, String nodeName) {
		for (ASTNode node : nodes)
			if (node.getIdentifier().equals(nodeName)) return node;
		return null;
	}

	public List<String> getKeys(String value) {
		List<String> keys = new ArrayList<>();
		for (Map.Entry<String, String> entry : identifierMap.entrySet()) {
			if (entry.getValue().equals(value))
				keys.add(entry.getKey());
		}
		return keys;
	}

	public ASTNode searchNode(String nodeName, ASTNode context) {
		ASTNode result = relativeSearch(nodeName, context);
		if (nodeName == null || nodeName.isEmpty()) return null;
		if (result != null) return result;
		return absoluteSearch(nodeName);
	}

}
