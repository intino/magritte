package siani.tara.lang;

import java.util.List;

public class DeclaredNode extends Node {

	private List<Node> innerNodes = new NodeTree();;

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
	protected String getNodePath() {
		String name = !getName().isEmpty() ? getName() : "[" + getObject().getParentName() + ANONYMOUS + "]";
		if (container != null && !getObject().isCase()) return container.getQualifiedName() + "." + name;
		if (getObject().isCase())
			return getContainer().getQualifiedName().substring(0, getContainer().getQualifiedName().lastIndexOf(".")) + "." + name;
		else return getBox() + "." + name;
	}


	public boolean isCase() {
		return getObject().isCase();
	}

	public String toString() {
		return "DeclaredNode{" + qualifiedName + '}';
	}
}
