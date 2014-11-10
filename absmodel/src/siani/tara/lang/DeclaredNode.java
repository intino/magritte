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

	public NodeObject getObject() {
		return (NodeObject) object;
	}

	public DeclaredNode getContainer() {
		return container;
	}

	public boolean add(Node node) {
		return innerNodes.add(node);
	}

	public void add(Node node, int index) {
		innerNodes.add(index, node);
	}

	public String getName() {
		return object.getName();
	}

	@Override
	public boolean isAggregated() {
		return getObject().is(Annotations.Annotation.AGGREGATED);
	}

	@Override
	protected String getNodePath() {
		String name = !getName().isEmpty() ? getName() :
			"[" + (getObject().getParentName() == null ? getObject().type : getObject().getParentName()) + ANONYMOUS + "]";
		if (container != null && !getObject().isSub())
			return container.getQualifiedName() +
				(isInFacetTargetParent() ? IN_FACET_TARGET + "(" + this.getFacetTargetParent().getDestinyName() + ")" : "") + "." + name;
		if (getObject().isSub())
			return getContainer().getQualifiedName().substring(0, getContainer().getQualifiedName().lastIndexOf(".")) + "." + name;
		else return getBox() + "." + name;
	}

	public boolean isSub() {
		return getObject().isSub();
	}

	public String toString() {
		return "DeclaredNode" + (isSub() ? "AsCase" : "") + "{" + qualifiedName + '}';
	}

	public boolean contains(String name) {
		for (Node innerNode : innerNodes)
			if (innerNode.getName().equals(name))
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
