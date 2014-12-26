package siani.tara.lang;

import java.util.List;

public class DeclaredNode extends Node {

	private List<Node> innerNodes = new NodeTree();
	private transient FacetTarget inFacetTargetParent = null;

	public DeclaredNode() {
	}

	public DeclaredNode(NodeObject object, DeclaredNode container) {
		this.object = object;
		this.container = container;
	}

	public List<Node> getInnerNodes() {
		return innerNodes;
	}

	@Override
	public Annotation[] getAnnotations() {
		return getObject().getAnnotations();
	}

	public NodeObject getObject() {
		return (NodeObject) object;
	}

	public DeclaredNode getContainer() {
		return container;
	}

	public boolean add(Node node) {
		return innerNodes.add(node);
	}

	public void add(int index, Node node) {
		innerNodes.add(index, node);
	}

	public String getName() {
		return object.getName();
	}

	@Override
	public boolean isAggregated() {
		return getObject().is(Annotation.AGGREGATED);
	}

	@Override
	protected String getNodePath() {
		String name = !getName().isEmpty() ? getName() :
			"[" + (getObject().getParentName() == null ? getObject().type : getObject().getParentName()) + ANONYMOUS + "]";
		if (container != null && !getObject().isSub())
			return container.getQualifiedName() +
				(isInFacetTargetParent() ? IN_FACET_TARGET + "(" + this.getFacetTargetParent().getDestinyName() + ")" : "") + "." + name;
		if (getObject().isSub()) {
			String containerName = getContainer().getQualifiedName();
			return (containerName.contains(".") ? containerName.substring(0, containerName.lastIndexOf(".")) + "." : "") + name;
		} else return name;
	}

	public boolean isSub() {
		return getObject().isSub();
	}

	public boolean hasSubs() {
		for (Node innerNode : innerNodes)
			if (innerNode.isSub()) return true;
		return false;
	}

	public String toString() {
		return "DeclaredNode" + (isSub() ? "AsSub" : "") + "{" + qualifiedName + '}';
	}

	public boolean contains(String name) {
		for (Node innerNode : innerNodes)
			if (innerNode.getName().equals(name))
				return true;
		return false;
	}

	public boolean contains(String type, String name, boolean aggregated) {
		for (Node innerNode : innerNodes)
			if (innerNode.getType().equals(type) && innerNode.getName().equals(name) && innerNode.isAggregated() == aggregated)
				return true;
		return false;
	}

	public FacetTarget getFacetTargetParent() {
		return inFacetTargetParent;
	}

	public void setFacetTargetParent(FacetTarget facetTarget) {
		this.inFacetTargetParent = facetTarget;
	}

	public boolean isInFacetTargetParent() {
		return this.inFacetTargetParent != null;
	}
}
