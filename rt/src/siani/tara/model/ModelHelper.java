package siani.tara.model;

import siani.tara.model.Model.SearchNode;

import java.util.*;

public class ModelHelper {
	private final Model model;

	public ModelHelper(Model model) {
		this.model = model;
	}

	public Node searchNode(String qn) {
		if (qn == null || qn.isEmpty()) return null;
		String[] path = qn.split("\\.");
		SearchNode searchTree = createSearchTree(path);
		return searchNode(searchTree);
	}

	public Node searchNode(SearchNode searchNode) {
		Node result;
		for (Node node : model.getNodeTree())
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

	public Model.SearchNode createSearchTree(Node instanceNode) {
		Model.SearchNode forward = null;
		Node forwardConcept = instanceNode.isSub() ? getParentOfSub(instanceNode) : instanceNode;
		Model.SearchNode previous = new Model.SearchNode(forwardConcept.getType());
		addProperties(forwardConcept, previous);
		while ((forwardConcept = forwardConcept.getContainer()) != null) {
			if (forwardConcept.isSub()) continue;
			forward = new Model.SearchNode(forwardConcept.getType());
			addProperties(forwardConcept, forward);
			forward.setNext(previous);
			previous.setPrevious(forward);
			previous = forward;
		}
		return forward != null ? forward : previous;
	}

	private SearchNode createSearchTree(String[] qn) {
		Model.SearchNode searchNode = null;
		Model.SearchNode previous = null;
		for (String name : qn) {
			SearchNode forward = new SearchNode(name);
			addProperties(forward);
			if (searchNode == null) {
				searchNode = forward;
				previous = forward;
			} else {
				previous.setNext(forward);
				forward.setPrevious(previous);
				previous = forward;
			}

		}
		return searchNode;
	}

	private void addProperties(SearchNode searchNode) {
		if (searchNode.getName().contains(Node.IN_FACET_TARGET))
			searchNode.setInFacetTarget(getFacetTargetContainer(searchNode.getName()));
	}

	private String getFacetTargetContainer(String name) {
		return name.substring(name.indexOf('('), name.indexOf(')'));
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
		for (Node node : model.getNodeTable()) {
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
			if (!contains(box + "." + path)) continue;
			Node node = get(box + "." + path);
			if (node != null) return (DeclaredNode) node;
			for (String importPath : context.getImports())
				if (contains(importPath) && importPath.equals(path))
					return (DeclaredNode) get(importPath);
		}
		return null;
	}

	DeclaredNode relativeSearch(String path, Node context) {
		DeclaredNode container = context.getContainer();
		while (container != null) {
			DeclaredNode node = (DeclaredNode) get(container.getQualifiedName() + "." + path);
			if (node != null && !node.equals(context)) return node;
			container = container.getContainer();
		}
		return (DeclaredNode) get(path);
	}

	private boolean contains(String qn) {
		for (Node node : model.getNodeTable())
			if (node.getQualifiedName().equals(qn)) return true;
		return false;
	}

	private Node get(String qn) {
		for (Node node : model.getNodeTable())
			if (node.getQualifiedName().equals(qn)) return node;
		return null;
	}
}