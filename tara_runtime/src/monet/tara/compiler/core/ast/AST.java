package monet.tara.compiler.core.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AST {

	private ArrayList<ASTNode> astRootNodes = new ArrayList<>();
	private HashMap<String, String> identifierMap = new HashMap<>();
	private HashMap<String, ArrayList<ASTNode>> lookUpTable = new HashMap<>();

	public ASTNode[] getAstRootNodes() {
		return astRootNodes.toArray(new ASTNode[astRootNodes.size()]);
	}

	public void addIdentifier(String identifier, String context) {
		if (!checkRepeated(identifier) && !isNoName(identifier))
			identifierMap.put(identifier.toUpperCase() + "_" + context, identifier);
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

	public boolean add(String name, ASTNode astNode) {
		ArrayList<ASTNode> list = lookUpTable.containsKey(name) ? lookUpTable.get(name) : new ArrayList<ASTNode>();
		list.add(astNode);
		lookUpTable.put(name, list);
		return true;
	}

	public ASTNode searchAncestry(ASTNode node) {
		ASTNode result;
		if (node.getExtendFrom() == null) return null;
		if ((result = relativeSearch(node.getExtendFrom(), node)) != null) return result;
		return absoluteSearch(node.getExtendFrom());
	}

	private ASTNode absoluteSearch(String path) {
		String[] tree = path.split("\\.");
		ArrayList<ASTNode> nodes;
		if ((nodes = lookUpTable.get(tree[tree.length - 1])) == null) return null;
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
		ASTNode node = context;
		String nodeName = tree[0];
		if (!nodeName.isEmpty() && (node = isInList(nodes, nodeName)) != null)
			relativeSearch(concatenate(tree, 1), node);
		return (node == context) ? null : node;
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

	public ArrayList<String> getKeys(String value) {
		ArrayList<String> keys = new ArrayList<>();
		for (Map.Entry<String, String> entry : identifierMap.entrySet()) {
			if (entry.getValue().equals(value))
				keys.add(entry.getKey());
		}
		return keys;
	}
}
