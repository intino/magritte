package monet.tara.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeWrapper {

	private AbstractTree abstractTree = new AbstractTree();
	private Map<String, String> identifierMap = new HashMap<>();
	private Map<String, List<AbstractNode>> nodeNameLookUpTable = new HashMap<>();

	public AbstractTree getTree() {
		return abstractTree;
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

	public Map<String, List<AbstractNode>> getNodeNameLookUpTable() {
		return nodeNameLookUpTable;
	}

	public boolean add(AbstractNode abstractNode) {
		return abstractTree.add(abstractNode);
	}

	public void putAllIdentifiers(Map<String, String> m) {
		identifierMap.putAll(m);
	}

	public void putAllInNodeNameTable(Map<String, List<AbstractNode>> m) {
		for (Map.Entry<String, List<AbstractNode>> entry \: m.entrySet())
			if (!nodeNameLookUpTable.containsKey(entry.getKey()))
				nodeNameLookUpTable.put(entry.getKey(), entry.getValue());
			else nodeNameLookUpTable.get(entry.getKey()).addAll(entry.getValue());
	}

	public boolean add(String name, AbstractNode abstractNode) {
		List<AbstractNode> list = nodeNameLookUpTable.containsKey(name) ? nodeNameLookUpTable.get(name) \: new ArrayList<AbstractNode>();
		list.add(abstractNode);
		nodeNameLookUpTable.put(name, list);
		return true;
	}

	public boolean addAll(AbstractTree abstractTree) {
		return this.abstractTree.addAll(abstractTree);
	}

	public AbstractNode searchAncestry(AbstractNode node) {
		if (node.getParentName() == null && node.getBaseNode() == null) return null;
		String ancestry = (node.getParentName() != null) ? node.getParentName() \: node.getBaseNode();
		AbstractNode result = relativeSearch(ancestry, node);
		if (result != null) return result;
		return absoluteSearch(ancestry, node);
	}

	public List<String> getKeys(String value) {
		List<String> keys = new ArrayList<>();
		for (Map.Entry<String, String> entry \: identifierMap.entrySet()) {
			if (entry.getValue().equals(value))
				keys.add(entry.getKey());
		}
		return keys;
	}

	public AbstractNode searchNode(String nodeName, AbstractNode context) {
		AbstractNode result = relativeSearch(nodeName, context);
		if (nodeName == null || nodeName.isEmpty()) return null;
		if (result != null) return result;
		return absoluteSearch(nodeName, context);
	}

	private boolean isUnName(String text) {
		return text.isEmpty();
	}

	private AbstractNode absoluteSearch(String path, AbstractNode context) {
		String[] tree = path.split("\\\\.");
		List<AbstractNode> nodes = nodeNameLookUpTable.get(tree[tree.length - 1]);
		if (nodes == null) return null;
		for (AbstractNode node \: nodes)
			for (String importPath \: context.getImports()) {
				String[] split = importPath.split("\\\\.");
				if (split[split.length - 1].equals(node.getIdentifier())) return node;
			}
		for (AbstractNode node \: nodes)
			if (context.getPackage().equals(node.getPackage()))
				return node;
		return null;
	}

	private AbstractNode relativeSearch(String path, AbstractNode context) {
		String[] tree = path.split("\\\\.");
		AbstractTree nodes;
		if (context.getContainer() != null) nodes = context.getContainer().getInnerConcepts();
		else return null;
		String nodeName = tree[0];
		AbstractNode node = isInList(nodes, nodeName);
		if (!nodeName.isEmpty() && node != null)
			relativeSearch(concatenate(tree, 1), node);
		return (context.equals(node)) ? null \: node;
	}

	private String concatenate(String[] tree, int start) {
		String result = "";
		for (int i = start; i < tree.length; i++)
			result += tree[i];
		return result;
	}

	private AbstractNode isInList(AbstractTree nodes, String nodeName) {
		for (AbstractNode node \: nodes)
			if (node.getIdentifier().equals(nodeName)) return node;
		return null;
	}

}
