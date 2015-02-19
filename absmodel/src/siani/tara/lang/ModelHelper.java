package siani.tara.lang;

import siani.tara.lang.Model.SearchNode;

import java.util.*;

public class ModelHelper {
	private final Model model;

	public ModelHelper(Model model) {
		this.model = model;
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

	public Node searchNode(SearchNode searchNode) {
		Node result;
		for (Node node : model.getTreeModel())
			if ((result = searchNode(node, searchNode)) != null) return result;
		return null;
	}

	private Node searchNode(Node node, SearchNode searchNode) {
		if (node == null) return null;
		Node nodeToSearch;
		if ((nodeToSearch = areCompatibles(searchNode, node)) != null) {
			if (!searchNode.hasNext()) return nodeToSearch;
			if (searchNode.next.isInFacet()) {
				if (searchNode.next.getInFacetApply() != null) {
					FacetTarget target = searchFacetApply(searchNode, nodeToSearch);
					if (target == null) return null;
					for (Node inner : target.getInner()) {
						Node result = searchNode(inner, searchNode.getNext());
						if (result != null) return result;
					}
				}
			} else {
				for (Node inner : nodeToSearch.getInnerNodes()) {
					Node result = searchNode(inner, searchNode.getNext());
					if (result != null) return result;
				}
			}
		}
		return null;
	}

	public Model.SearchNode createSearchTree(Node node) {
		Model.SearchNode forward = null;
		Node forwardConcept = node.isSub() ? getParentOfSub(node) : node;
		Model.SearchNode previous = new Model.SearchNode(forwardConcept.getType());
		addProperties(forwardConcept, previous);
		while ((forwardConcept = forwardConcept.getContainer()) != null) {
			forward = new Model.SearchNode(forwardConcept.getType());
			addProperties(forwardConcept, forward);
			forward.setNext(previous);
			previous.setPrevious(forward);
			previous = forward;
		}
		return forward != null ? forward : previous;
	}

	private static Node getParentOfSub(Node node) {
		Node container = node;
		while ((container = container.getContainer()) != null) if (!container.isSub()) return container;
		return null;
	}

	private static void addProperties(Node node, Model.SearchNode searchNode) {
		addGeneralProperties(node, searchNode);
		List<String> facets = new ArrayList<>();
		for (Facet facet : node.getObject().getFacets()) facets.add(facet.getName());
		searchNode.setFacets(facets);
	}

	private static void addGeneralProperties(Node element, Model.SearchNode searchNode) {
		if (element.getQualifiedName().contains(Node.IN_FACET_TARGET))
			searchNode.setInFacetTarget(getFacetTargetContainer(element));
	}

	private static String getFacetTargetContainer(Node node) {
		Node container = node;
		while ((container = container.getContainer()) != null)
			if (((DeclaredNode) container).getFacetTargetParent() != null)
				return ((DeclaredNode) container).getFacetTargetParent().getDestinyName();
		return null;
	}

	private FacetTarget searchFacetApply(SearchNode searchNode, Node nodeToSearch) {
		String facet = searchNode.next.getFacet();
		for (Map.Entry<String, List<FacetTarget>> entry : nodeToSearch.getObject().getAllowedFacets().entrySet()) {
			if (!entry.getKey().equals(facet)) continue;
			for (FacetTarget target : entry.getValue()) {
				if (target.getDestinyName().equals(searchNode.name))
					return target;//TODO solve Target whose destiny is parent of nodeToSearch, so, search also in hierarchy of searchNode.
			}
		}
		return null;
	}

	private Node areCompatibles(SearchNode searchNode, Node node) {
		if (searchNode.name.equals(node.getName()))
			return node;
		for (DeclaredNode sub : collectSubs(node))
			if (searchNode.name.equals(sub.getName()))
				return sub;
		for (String facet : searchNode.facets)
			if (facet.equals(node.getName()))
				return searchFacetedNode(searchNode.name, facet);
		return null;
	}

	private DeclaredNode searchFacetedNode(String name, String facet) {
		for (Node node : model.getNodeTable().values()) {
			if (node.is(LinkNode.class)) continue;
			DeclaredNode declaredNode = (DeclaredNode) node;
			if (name.equals(declaredNode.getName()) && canBe(declaredNode, facet))
				return declaredNode;
		}
		return null;
	}

	private boolean canBe(DeclaredNode declaredNode, String facet) {
		for (String facetKey : declaredNode.getObject().getAllowedFacets().keySet())
			if (facet.equals(facetKey)) return true;
		return false;
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
		return !nodeName.contains(Node.ANONYMOUS) && nodeName.contains("@");
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
				Node aSub = containsSub(collectSubs(node), name);
				if (aSub != null) return aSub;
			}
		return null;
	}

	private Node searchInDestiny(LinkNode node, String name) {
		Node aSub = containsSub(collectSubs(node.getDestiny()), name);
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
		for (Node node : model.getTreeModel()) {
			roots.add((DeclaredNode) node);
			roots.addAll(collectSubs(node));
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

	private List<DeclaredNode> collectSubs(Node node) {
		List<DeclaredNode> list = new ArrayList<>();
		if (node == null) return Collections.EMPTY_LIST;
		Collections.addAll(list, node.getSubNodes());
		for (DeclaredNode aSub : node.getSubNodes())
			list.addAll(collectSubs(aSub));
		return list;
	}

	DeclaredNode searchInImportReferences(String path, Node context) {
		for (String box : context.getImports()) {
			if (!model.getNodeTable().containsKey(box + "." + path)) continue;
			Node node = model.getNodeTable().get(box + "." + path);
			if (node != null) return (DeclaredNode) node;
			for (String importPath : context.getImports())
				if (model.getNodeTable().containsKey(importPath) && importPath.equals(path))
					return (DeclaredNode) model.getNodeTable().get(importPath);
		}
		return null;
	}

	DeclaredNode relativeSearch(String path, Node context) {
		DeclaredNode container = context.getContainer();
		while (container != null) {
			DeclaredNode node = (DeclaredNode) model.get(container.getQualifiedName() + "." + path);
			if (node != null && !node.equals(context)) return node;
			container = container.getContainer();
		}
		return (DeclaredNode) model.get(path);
	}
}