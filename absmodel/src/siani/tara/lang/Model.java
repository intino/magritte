package siani.tara.lang;

import java.util.*;

public class Model {
	private String name;
	private String parentModelName;
	private boolean terminal;
	private transient Model parentModel;
	private transient Map<String, Node> nodeTable = new HashMap<>();
	private NodeTree nodeTree = new NodeTree();
	private Set<String> identifiers = new HashSet<>();
	private boolean system;

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

	public List<Node> collectRequiredNodes() {
		List<Node> list = new ArrayList();
		for (Node node : nodeTable.values()) {
			if (node instanceof DeclaredNode && node.getObject().is(Annotations.Annotation.REQUIRED))
				list.add(node);
		}
		return list;
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
		Node result = relativeSearch(node.getDestinyQN(), node);
		if (result != null) return (DeclaredNode) result;
		return searchInImportReferences(node.getDestinyQN(), node);
	}

	public Node searchChildrenByName(Node parent, String childName) {
		for (Node node : nodeTable.values()) {
			boolean cond = parent.equals(searchAncestry(node));
			if (cond && (childName.equals(node.getName()) || ("").equals(node.getName())))
				return node;
		}
		return null;
	}

	public DeclaredNode searchDeclarationOfReference(String nodeName, Node context) {
		DeclaredNode result = null;
		if (nodeName == null || nodeName.isEmpty()) return null;
		if (context != null) result = relativeSearch(nodeName, context);
		if (result != null) return result;
		return searchInImportReferences(nodeName, context);
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
				} else
					currentNode = findInnerInList(findFacetApply(currentNode.getObject().getAllowedFacets(), getFacet(inFacet)).getInner(), name);
				inFacet = null;
			}
			if (currentNode == null) return null;
		}
		return currentNode;
	}

	private boolean searchInFacet(String inFacet) {
		return inFacet != null;
	}

	private boolean isInFacet(String nodeName) {
		return nodeName.contains("@");
	}

	private FacetTarget findFacetApply(Map<String, FacetTarget> facets, String facet) {
		for (Map.Entry<String, FacetTarget> facetApply : facets.entrySet()) {
			String[] key = facetApply.getKey().split("\\.");
			if (key[key.length - 1].equals(facet)) return facetApply.getValue();
		}
		return null;
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
				List<DeclaredNode> cases = new ArrayList();
				extractCases(node, cases);
				Node aCase = containsCase(cases, name);
				if (aCase != null) return aCase;
			}
		return null;
	}

	private Node searchInDestiny(LinkNode node, String name) {
		List<DeclaredNode> cases = new ArrayList();
		extractCases(node.getDestiny(), cases);
		Node aCase = containsCase(cases, name);
		return aCase != null ? aCase : null;
	}

	private Node containsCase(List<DeclaredNode> cases, String name) {
		for (DeclaredNode aCase : cases)
			if (name.equals(aCase.getName())) return aCase;
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
			extractCases(node, roots);
		}
		return roots;
	}

	private void extractCases(Node node, List<DeclaredNode> list) {
		List<DeclaredNode> cases = Arrays.asList(node.getSubConcepts());
		list.addAll(cases);
		for (DeclaredNode aCase : cases) {
			extractCases(aCase, list);
		}
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
		return (DeclaredNode) get(context.getBox() + "." + path);
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

	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}
}
