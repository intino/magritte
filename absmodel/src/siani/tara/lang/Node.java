package siani.tara.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

	protected static final String ANNONYMOUS = "@annonymous";
	private NodeObject object;
	private transient Node container;
	private List<Node> innerNodes;
	private Map<String, Node> innerAsReferences = new HashMap<>();
	private String qualifiedName;
	private int line;
	private String file;

	public Node(NodeObject object, Node container) {
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

	public Node getContainer() {
		return container;
	}

	public void setContainer(Node container) {
		this.container = container;
	}

	public boolean removeChild(Node node) {
		return innerNodes.remove(node);
	}

	public Node findChild(String name) {
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

	public void addInnerAsReference(String qualifiedName, Node element) {
		innerAsReferences.put(qualifiedName, element);
	}

	public Node[] getCases() {
		List<Node> cases = new ArrayList<>();
		for (Node child : getInnerNodes())
			if (child.getObject().isCase()) cases.add(child);
		return cases.toArray(new Node[cases.size()]);
	}

	public String getName() {
		return object.getName();
	}

	public String getQualifiedName() {
		return qualifiedName == null ? calculateQualifiedName() : qualifiedName;
	}

	public String calculateQualifiedName() {
		return qualifiedName = getNodeRoute();
	}

	private String getNodeRoute() {
		String name = !getName().isEmpty() ? getName() : "[" + getObject().getParentName() + ANNONYMOUS + "]";

		if (container != null && !getObject().isCase()) return container.getQualifiedName() + "." + name;
		if (getObject().isCase())
			return getContainer().getQualifiedName().substring(0, getContainer().getQualifiedName().lastIndexOf(".")) + "." + name;
		else return getObject().getBox() + "." + name;
	}

	public boolean isPrime() {
		return getContainer() == null;
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

	@Override
	public String toString() {
		return "Node{" + qualifiedName + '}';
	}


}
