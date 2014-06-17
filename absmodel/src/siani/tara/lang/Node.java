package siani.tara.lang;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private NodeObject object;
	private transient Node container;
	private List<Node> innerNodes;
	private String qualifiedName;
	private boolean inherited;
	private int line;
	private String file;

	public Node(NodeObject object, Node container, boolean inherited) {
		this.object = object;
		this.container = container;
		this.inherited = inherited;
		innerNodes = new NodeTree();
	}

	public List<Node> getInnerNodes() {
		return innerNodes;
	}

	public NodeObject getObject() {
		return object;
	}

	public Node getContainer() {
		return container;
	}

	public void setContainer(Node container) {
		this.container = container;
	}

	public boolean removeChild(Node node) {
		return innerNodes.remove(node);
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

	public void add(int index, Node element) {
		innerNodes.add(index, element);
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

	public String calculateQualifiedName() {
		return qualifiedName = getNodeRoute();
	}

	public String getName() {
		return object.getName();
	}

	private String getNodeRoute() {
		String name;
		name = !"".equals(getName()) ? getName() : "[" + getObject().getParentName() + "@annonymous]";
		if (container != null) return container.getNodeRoute() + (name.isEmpty() ? "" : "." + name);
		else {
			if ((getObject().getBaseName()) != null)
				return getObject().getBaseName().substring(0, getObject().getBaseName().lastIndexOf(".")) + "." + name;
			else {
				return name;
			}
		}
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

	public boolean isInherited() {
		return inherited;
	}

	@Override
	public String toString() {
		return "Node{" + qualifiedName + '}';
	}
}
