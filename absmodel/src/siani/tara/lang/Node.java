package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private NodeObject object;
	private Node container;
	private NodeTree innerNodes;
	private String qualifiedName;
	private int line;
	private String file;

	public Node(NodeObject object, Node container) {
		this.object = object;
		this.container = container;
		innerNodes = new NodeTree();
	}

	public NodeTree getInnerNodes() {
		return innerNodes;
	}

	public NodeObject getObject() {
		return object;
	}

	public Node getContainer() {
		return container;
	}

	public Node getChildByName(String name) {
		for (Node child : innerNodes)
			if (child.getObject().getName().equals(name))
				return child;
		return null;
	}

	public boolean add(Node node) {
		return innerNodes.add(node);
	}

	public Node[] getCases() {
		List<Node> cases = new ArrayList<>();
		if (getObject().isBase()) {
			for (Node child : getInnerNodes())
				if (child.getObject().isCase()) cases.add(child);
			return cases.toArray(new Node[cases.size()]);
		} else return new Node[0];
	}

	public String getAbsolutePath() {
		return getObject().getPackage() + "." + getNodeRoute();
	}

	public String getQualifiedName() {
		return (qualifiedName == null) ? qualifiedName = getNodeRoute() : qualifiedName;
	}

	public String getName() {
		return object.getName();
	}

	private String getNodeRoute() {
		String name = !"".equals(getName()) ? getName() : getObject().getParentName();
		return container != null ? container.getNodeRoute() + "." + name : name;
	}

	public boolean isPrime() {
		return getContainer() == null;
	}

	public boolean isBase() {
		return object.isBase();
	}

	public boolean isCase() {
		return object.isCase();
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
