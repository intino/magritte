package monet.tara.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASTWrapper {

	private AST ast = new AST();
	private Map<String, String> identifierMap = new HashMap<>();
	private Map<String, List<ASTNode>> nodeNameLookUpTable = new HashMap<>();

	public AST getAST() {
		return ast;
	}

	public void addIdentifier(String identifier, String context) {
		if (!identifierMap.containsKey(identifier) && !isUnName(identifier))
			identifierMap.put(identifier.toUpperCase() + "_" + context, identifier);
	}

	public Map<String, String> getIdentifiers() {
		return identifierMap;
	}

	public Map<String, List<ASTNode>> getNodeNameLookUpTable() {
		return nodeNameLookUpTable;
	}

	public boolean add(ASTNode astNode) {
		return ast.add(astNode);
	}

	public void putAllIdentifiers(Map<String, String> m) {
		identifierMap.putAll(m);
	}

	public void putAllInNodeNameTable(Map<String, List<ASTNode>> m) {
		for (Map.Entry<String, List<ASTNode>> entry : m.entrySet())
			if (!nodeNameLookUpTable.containsKey(entry.getKey()))
				nodeNameLookUpTable.put(entry.getKey(), entry.getValue());
			else nodeNameLookUpTable.get(entry.getKey()).addAll(entry.getValue());
	}

	public boolean add(String name, ASTNode astNode) {
		List<ASTNode> list = nodeNameLookUpTable.containsKey(name) ? nodeNameLookUpTable.get(name) : new ArrayList<ASTNode>();
		list.add(astNode);
		nodeNameLookUpTable.put(name, list);
		return true;
	}

	public boolean addAll(AST astNodes) {
		return ast.addAll(astNodes);
	}

	public ASTNode searchAncestry(ASTNode node) {
		if (node.getExtendFrom() == null) return null;
		ASTNode result = relativeSearch(node.getExtendFrom(), node);
		if (result != null) return result;
		return absoluteSearch(node.getExtendFrom(), node);
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
		return absoluteSearch(nodeName, context);
	}

	private boolean isUnName(String text) {
		return text.isEmpty();
	}

	private ASTNode absoluteSearch(String path, ASTNode context) {
		String[] tree = path.split("\\.");
		List<ASTNode> nodes = nodeNameLookUpTable.get(tree[tree.length - 1]);
		if (nodes == null) return null;
		for (ASTNode node : nodes)
			for (String importPath : context.getImports()) {
				String[] split = importPath.split("\\.");
				if (split[split.length - 1].equals(node.getIdentifier())) return node;
			}
		for (ASTNode node : nodes)
			if (context.getPackage().equals(node.getPackage()))
				return node;
		return null;
	}

	private ASTNode relativeSearch(String path, ASTNode context) {
		String[] tree = path.split("\\.");
		AST nodes;
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

	private ASTNode isInList(AST nodes, String nodeName) {
		for (ASTNode node : nodes)
			if (node.getIdentifier().equals(nodeName)) return node;
		return null;
	}

}
