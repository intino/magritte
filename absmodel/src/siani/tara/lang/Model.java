package siani.tara.lang;

import java.util.*;

import static siani.tara.lang.Annotation.TERMINAL;

public class Model {
	private String name;
	private String parentModelName;
	private transient Model parentModel;
	private transient Map<String, Node> nodeTable = new HashMap<>();
	private NodeTree nodeTree = new NodeTree();
	private Set<String> identifiers = new HashSet<>();
	private Map<String, List<Map.Entry<String, String>>> metrics = new HashMap<>();
	private boolean terminal;

	public Model(String name) {
		this.name = name;
	}

	public NodeTree getTreeModel() {
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

	public void setNodeTable(Map<String, Node> nodeTable) {
		this.nodeTable = nodeTable;
	}

	public boolean add(Node node) {
		return nodeTree.add(node) && add(node.getQualifiedName(), node);
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

	public void addMetrics(Map<String, List<Map.Entry<String, String>>> metrics) {
		this.metrics.putAll(metrics);
	}

	public Map<String, List<Map.Entry<String, String>>> getMetrics() {
		return metrics;
	}

	public Map<String, Node> getTerminalNodes() {
		Map<String, Node> terminals = new HashMap<>();
		for (Node node : getNodeTable().values())
			if (node.getObject().is(TERMINAL))
				terminals.put(node.getName(), node);
		return terminals;
	}

	public String getModelName() {
		return name;
	}

	public boolean addAll(NodeTree nodeTree) {
		return this.nodeTree.addAll(nodeTree);
	}

	public DeclaredNode searchAncestry(Node node) {
		if (node.getObject().isSub()) return node.getContainer();
		if (node.getObject().getParentName() == null) return null;
		String ancestry = node.getObject().getParentName();
		Node result = relativeSearch(ancestry, node);
		if (result != null) return (DeclaredNode) result;
		return searchInImportReferences(ancestry, node);
	}

	public DeclaredNode searchDeclaredNodeOfLink(LinkNode node) {
		DeclaredNode result = relativeSearch(node.getDestinyQN(), node);
		if (result != null) return result;
		result = searchInImportReferences(node.getDestinyQN(), node);
		if (result != null) return result;
		if (get(node.getDestinyQN()) != null && get(node.getDestinyQN()).is(DeclaredNode.class))
			return (DeclaredNode) get(node.getDestinyQN());
		return null;
	}

	public DeclaredNode searchDeclarationOfReference(String referenceName, Node context) {
		DeclaredNode result = null;
		if (referenceName == null || referenceName.isEmpty()) return null;
		if (context != null) result = relativeSearch(referenceName, context);
		if (result != null) return result;
		return searchInImportReferences(referenceName, context);
	}

	public Node searchNode(String qn) {
		if (qn == null || qn.isEmpty()) return null;
		String[] path = qn.split("\\.");
		Node currentNode = null;
		String inFacet = null;
		for (String nodeName : path) {
			String name = isInFacet(nodeName) ? nodeName.substring(0, nodeName.indexOf("@")) : nodeName;
			if (!searchInFacet(inFacet)) {
				currentNode = (currentNode == null) ? findNodeInList(getModelRoots(), name) : findInnerIn(currentNode, name);
				if (isInFacet(nodeName)) inFacet = nodeName.substring(nodeName.indexOf("@"));
			} else {
				if (isInFacetTarget(inFacet)) {
					currentNode = findNodeInList(getModelRoots(), name);
					inFacet = null;
					continue;
				} else {
					String context = getContext(getFacet(inFacet));
					if (context.isEmpty()) context = getContainer(nodeName, path);
					FacetTarget allowedFacetByContext = currentNode.getObject().getAllowedFacetByContext(getFacetName(getFacet(inFacet)), context);
					if (allowedFacetByContext != null)
						currentNode = findInnerInList(allowedFacetByContext.getInner(), name);
					else return null;
				}
				inFacet = null;
			}
			if (currentNode == null) return null;
		}
		return currentNode;
	}

	private String getContainer(String nodeName, String[] path) {
		List<String> nodes = Arrays.asList(path);
		String name = nodes.get(nodes.indexOf(nodeName) - 1);
		return name.substring(0, name.indexOf("@"));
	}

	private String getFacetName(String inFacet) {
		return inFacet.contains("$") ? inFacet.substring(inFacet.lastIndexOf("$") + 1) : inFacet;
	}

	private String getContext(String inFacet) {
		return inFacet.contains("$") ? inFacet.substring(0, inFacet.lastIndexOf("$")) : "";
	}

	private boolean searchInFacet(String inFacet) {
		return inFacet != null;
	}

	private boolean isInFacet(String nodeName) {
		return nodeName.contains("@");
	}

	private String getFacet(String nodeName) {
		return nodeName.substring(nodeName.indexOf("(") + 1, nodeName.indexOf(")"));
	}

	private boolean isInFacetTarget(String rootName) {
		return rootName.contains(Node.IN_FACET_TARGET);
	}

	private Node findInnerIn(Node parent, String name) {
		return findInnerInList(parent.getInnerNodes(), name);
	}

	private Node findInnerInList(List<Node> innerNodes, String name) {
		for (Node node : innerNodes)
			if (node instanceof LinkNode) {
				if (((LinkNode) node).getDestinyName().equals(name)) return node;
				else {
					Node destiny = searchInDestiny((LinkNode) node, name);
					if (destiny != null) return destiny;
				}
			} else if (node.getName().equals(name)) {
				return node;
			} else {
				List<DeclaredNode> subs = new ArrayList();
				extractSubs(node, subs);
				Node aSub = containsSub(subs, name);
				if (aSub != null) return aSub;
			}
		return null;
	}

	private Node searchInDestiny(LinkNode node, String name) {
		List<DeclaredNode> subs = new ArrayList();
		extractSubs(node.getDestiny(), subs);
		Node aSub = containsSub(subs, name);
		return aSub != null ? aSub : null;
	}

	private Node containsSub(List<DeclaredNode> subs, String name) {
		for (DeclaredNode sub : subs)
			if (name.equals(sub.getName())) return sub;
		return null;
	}

	private DeclaredNode findNodeInList(List<DeclaredNode> nodeList, String name) {
		for (DeclaredNode root : nodeList) if (name.equals(root.getName())) return root;
		return null;
	}

	private List<DeclaredNode> getModelRoots() {
		List<DeclaredNode> roots = new ArrayList<>();
		for (Node node : nodeTree) {
			roots.add((DeclaredNode) node);
			extractSubs(node, roots);
			extractAggregatedNodes(node, roots);
		}
		return roots;
	}

	private void extractAggregatedNodes(Node node, List<DeclaredNode> roots) {
		if (node == null) return;
		for (Node inner : node.getInnerNodes())
			if (inner.is(DeclaredNode.class)) {
				if (inner.isAggregated()) roots.add((DeclaredNode) inner);
				extractAggregatedNodes(inner, roots);
			}
	}

	private void extractSubs(Node node, List<DeclaredNode> list) {
		if (node == null) return;
		List<DeclaredNode> subs = Arrays.asList(node.getSubConcepts());
		list.addAll(subs);
		for (DeclaredNode aSub : subs)
			extractSubs(aSub, list);
	}

	private DeclaredNode searchInImportReferences(String path, Node context) {
		for (String box : context.getImports()) {
			if (!nodeTable.containsKey(box + "." + path)) continue;
			Node node = nodeTable.get(box + "." + path);
			if (node != null) return (DeclaredNode) node;
			for (String importPath : context.getImports())
				if (nodeTable.containsKey(importPath) && importPath.equals(path))
					return (DeclaredNode) nodeTable.get(importPath);
		}
		return null;
	}

	private DeclaredNode relativeSearch(String path, Node context) {
		DeclaredNode container = context.getContainer();
		while (container != null) {
			DeclaredNode node = (DeclaredNode) get(container.getQualifiedName() + "." + path);
			if (node != null && !node.equals(context)) return node;
			container = container.getContainer();
		}
		return (DeclaredNode) get(path);
	}

	public void sortNodeTable(Comparator<String> comparator) {
		Map<String, Node> newMap;
		newMap = comparator == null ? new HashMap<String, Node>() : new TreeMap<String, Node>(comparator);
		newMap.putAll(nodeTable);
		nodeTable = newMap;
	}

	public String getParentModelName() {
		return parentModelName;
	}

	public void setParentModelName(String parentModelName) {
		this.parentModelName = parentModelName;
	}

	public Model getParentModel() {
		return parentModel;
	}

	public void setParentModel(Model parentModel) {
		this.parentModel = parentModel;
	}

	public boolean isTerminal() {
		return terminal;
	}

	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
}
