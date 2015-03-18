package siani.tara.model;

import java.util.List;

import static siani.tara.model.Annotation.AGGREGATED;
import static siani.tara.model.Annotation.SINGLE;

public class DeclaredNode extends Node {

	private NodeTree innerNodes = new NodeTree();

	public DeclaredNode() {
	}

	public DeclaredNode(NodeObject object, DeclaredNode container) {
		this.object = object;
		this.container = container;
	}

	public NodeTree getInnerNodes() {
		return innerNodes;
	}

	@Override
	public Annotation[] getAnnotations() {
		return getObject().getAnnotations();
	}

	@Override
	protected List<Annotation> getAnnotationList() {
		return getObject().annotations;
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
		return !object.getName().isEmpty() ? object.getName() :
			"[" + (getObject().getParentName() == null ? getObject().type : getObject().getParentName()) + ANONYMOUS + "]";
	}

	@Override
	public boolean isAggregated() {
		return is(AGGREGATED);
	}

	@Override
	public boolean isAssociated() {
		return is(AGGREGATED);
	}

	@Override
	public boolean isSingle() {
		return is(SINGLE);
	}

	@Override
	public boolean isAbstract() {
		return getObject().is(Annotation.ABSTRACT) || hasSubs();
	}

	@Override
	protected String getNodePath() {
		String name = getName();
		if (container != null && !getObject().isSub())
			return container.getQualifiedName() +
				(isInFacetTargetParent() ? IN_FACET_TARGET + '(' + this.getFacetTargetParent().getDestinyName() + ')' : "") + '.' + name;
		if (getObject().isSub()) {
			String containerName = getContainer().getQualifiedName();
			return (containerName.contains(".") ? containerName.substring(0, containerName.lastIndexOf('.')) + '.' : "") + name;
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
			if (innerNode.getName().equals(name) && !innerNode.isSub())
				return true;
		return false;
	}

	public boolean contains(String type, String name, boolean aggregated) {
		for (Node innerNode : innerNodes)
			if (innerNode.getType().equals(type) && innerNode.getName().equals(name) && innerNode.isAggregated() == aggregated)
				return true;
		return false;
	}

}
