package siani.tara.lang;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static siani.tara.lang.Annotation.TERMINAL;

public class Model {
	private transient final ModelHelper modelHelper;
	private String name;
	private String parentModelName;
	private transient Model parentModel;
	private transient Map<String, Node> nodeTable = new HashMap<>();
	private NodeTree nodeTree = new NodeTree();
	private Set<String> identifiers = new HashSet<>();
	private Map<String, List<SimpleEntry<String, String>>> metrics = new HashMap<>();
	private boolean terminal;
	private Locale language;

	public Model(String name) {
		this.name = name;
		modelHelper = new ModelHelper(this);
	}

	public Model() {
		modelHelper = new ModelHelper(this);
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
		if (nodeTable == null || !nodeTable.containsKey(qualifiedName)) return null;
		return nodeTable.get(qualifiedName);
	}

	public void addMetrics(Map<String, List<SimpleEntry<String, String>>> metrics) {
		this.metrics.putAll(metrics);
	}

	public Map<String, List<SimpleEntry<String, String>>> getMetrics() {
		return metrics;
	}

	public Map<String, DeclaredNode> getTerminalNodes() {
		Map<String, DeclaredNode> terminals = new HashMap<>();
		for (Node node : getNodeTable().values())
			if (node.is(DeclaredNode.class) && node.getObject().is(TERMINAL))
				terminals.put(node.getName(), (DeclaredNode) node);
		return terminals;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean addAll(NodeTree nodeTree) {
		return this.nodeTree.addAll(nodeTree);
	}

	public DeclaredNode searchAncestry(Node node) {
		if (node.getObject().isSub()) return node.getContainer();
		if (node.getObject().getParentName() == null) return null;
		String ancestry = node.getObject().getParentName();
		Node result = modelHelper.relativeSearch(ancestry, node);
		if (result != null) return (DeclaredNode) result;
		return modelHelper.searchInImportReferences(ancestry, node);
	}

	public DeclaredNode searchDeclaredNodeOfLink(LinkNode node) {
		DeclaredNode result = modelHelper.relativeSearch(node.getDestinyQN(), node);
		if (result != null) return result;
		result = modelHelper.searchInImportReferences(node.getDestinyQN(), node);
		if (result != null) return result;
		if (get(node.getDestinyQN()) != null && get(node.getDestinyQN()).is(DeclaredNode.class))
			return (DeclaredNode) get(node.getDestinyQN());
		return null;
	}

	public DeclaredNode searchDeclarationOfReference(String referenceName, Node context) {
		DeclaredNode result = null;
		if (referenceName == null || referenceName.isEmpty()) return null;
		if (context != null) result = modelHelper.relativeSearch(referenceName, context);
		if (result != null) return result;
		return modelHelper.searchInImportReferences(referenceName, context);
	}

	public Node searchNode(String qn) {
		return modelHelper.searchNode(qn);
	}

	public Node searchNodeClass(Node instance) {
		return modelHelper.searchNode(modelHelper.createSearchTree(instance));
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

	public Node searchNode(SearchNode searchTree) {
		return modelHelper.searchNode(searchTree);
	}

	public Locale getLanguage() {
		return language;
	}

	public void setLanguage(Locale language) {
		this.language = language;
	}

	public static class SearchNode {
		String name;
		List<String> facets = new ArrayList<>();
		String inFacetApply;
		String inFacetTarget;
		SearchNode next;
		SearchNode previous;

		public SearchNode(String name) {
			this.name = name;
		}

		public boolean hasNext() {
			return next != null;
		}

		public List<String> getFacets() {
			return facets;
		}

		public boolean isInFacet() {
			return inFacetApply != null || inFacetTarget != null;
		}

		public String getFacet() {
			return isInFacet() ? inFacetApply != null ? inFacetApply : inFacetTarget : null;
		}

		public void setFacets(List<String> facets) {
			this.facets = facets;
		}

		public String getInFacetApply() {
			return inFacetApply;
		}

		public void setInFacetApply(String inFacetApply) {
			this.inFacetApply = inFacetApply;
		}

		public String getInFacetTarget() {
			return inFacetTarget;
		}

		public void setInFacetTarget(String inFacetTarget) {
			this.inFacetTarget = inFacetTarget;
		}

		public SearchNode getNext() {
			return next;
		}

		public void setNext(SearchNode next) {
			this.next = next;
		}

		public void setPrevious(SearchNode previous) {
			this.previous = previous;
		}
	}
}
