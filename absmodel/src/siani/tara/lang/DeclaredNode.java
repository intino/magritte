package siani.tara.lang;

import java.util.List;

public class DeclaredNode extends Node {

	private NodeObject object;
	private List<Node> innerNodes;

	public DeclaredNode() {
	}

	public DeclaredNode(NodeObject object, DeclaredNode container) {
		super();
		this.object = object;
		this.container = container;
		innerNodes = new NodeTree();
	}

	public List<Node> getInnerNodes() {
		return innerNodes;
	}

	public NodeObject getObject() {
		return object;
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
	protected String getNodeRoute() {
		String name = !getName().isEmpty() ? getName() : "[" + getObject().getParentName() + ANNONYMOUS + "]";
		if (container != null && !getObject().isCase()) return container.getQualifiedName() + "." + name;
		if (getObject().isCase())
			return getContainer().getQualifiedName().substring(0, getContainer().getQualifiedName().lastIndexOf(".")) + "." + name;
		else return getBox() + "." + name;
	}


	public boolean isCase() {
		return object.isCase();
	}

	public String toString() {
		return "DeclaredNode{" + qualifiedName + '}';
	}
}
