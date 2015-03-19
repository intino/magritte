package siani.tara.model;

import siani.tara.Language;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static siani.tara.model.Annotation.TERMINAL;

public class Model {
	private transient final ModelHelper modelHelper;
	private String name;
	private String parentModelName;
	private transient Language language;
	private transient List<Node> nodeTable = new ArrayList<>();
	private NodeTree nodeTree = new NodeTree();
	private Set<String> identifiers = new HashSet<>();
	private Map<String, List<SimpleEntry<String, String>>> metrics = new HashMap<>();
	private boolean terminal;
	private Locale locale;

	public Model(String name) {
		this.name = name;
		modelHelper = new ModelHelper(this);
	}

	public Model() {
		modelHelper = new ModelHelper(this);
	}

	public NodeTree getNodeTree() {
		return nodeTree;
	}

	public boolean addIdentifier(String identifier) {
		return !identifier.isEmpty() && identifiers.add(identifier);
	}

	public Set<String> getIdentifiers() {
		return identifiers;
	}

	public List<Node> getNodeTable() {
		return nodeTable;
	}

	public boolean add(Node node) {
		return nodeTree.add(node) && register(node);
	}

	public void putAllIdentifiers(Set<String> set) {
		identifiers.addAll(set);
	}

	public Node get(String qn) {
		return modelHelper.searchNode(qn);
	}

	public boolean register(Node... nodes) {
		for (Node node : nodes)
			if (!nodeTable.contains(node))
				nodeTable.add(node);
		return true;
	}

	public void addMetrics(Map<String, List<SimpleEntry<String, String>>> metrics) {
		this.metrics.putAll(metrics);
	}

	public Map<String, List<SimpleEntry<String, String>>> getMetrics() {
		return metrics;
	}

	public List<DeclaredNode> extractTerminals() {
		return getTerminalNodes(getNodeTree());
	}

	private List<DeclaredNode> getTerminalNodes(NodeTree tree) {
		List<DeclaredNode> terminals = new ArrayList<>();
		for (Node node : tree)
			if (node.is(DeclaredNode.class) && node.getObject().is(TERMINAL)) terminals.add((DeclaredNode) node);
			else if (node.is(DeclaredNode.class)) terminals.addAll(getTerminalNodes(node.getInnerNodes()));
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
		return result;
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

	public void sortNodeTable(Comparator<Node> comparator) {
		Collections.sort(nodeTable, comparator);
	}

	public String getParentModelName() {
		return parentModelName;
	}

	public void setParentModelName(String parentModelName) {
		this.parentModelName = parentModelName;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
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

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public static class SearchNode {
		String name;
		List<String> facets = new ArrayList<>();
		String inFacetApply;
		String inFacetTarget;
		SearchNode next;
		SearchNode previous;

		public String getName() {
			return name;
		}

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
