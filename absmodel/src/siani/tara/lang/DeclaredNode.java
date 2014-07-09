package siani.tara.lang;

import java.util.*;

public class DeclaredNode extends Node {

	private String box;
	private List<String> imports = new ArrayList<>();
	private NodeObject object;
	private transient Node container;
	private transient Map<String, Node> innerAsReferences = new HashMap<>();
	private List<Node> innerNodes;
	private String qualifiedName;
	private int line;
	private String file;

	public DeclaredNode(NodeObject object, Node container) {
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

	public boolean add(DeclaredNode node) {
		return innerNodes.add(node);
	}

	public void add(int index, DeclaredNode element) {
		innerNodes.add(index, element);
	}

	public void addInnerAsReference(String qualifiedName, DeclaredNode element) {
		innerAsReferences.put(qualifiedName, element);
	}

	public DeclaredNode[] getCases() {
		List<DeclaredNode> cases = new ArrayList<>();
		for (Node child : getInnerNodes())
			if (child.getObject().isCase()) cases.add(child);
		return cases.toArray(new DeclaredNode[cases.size()]);
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
		else return getBox() + "." + name;
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

	public List<String> getImports() {
		return imports;
	}

	public void setImports(String[] imports) {
		if (imports.length > 0)
			Collections.addAll(this.imports, imports);
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	@Override
	public String toString() {
		return "Node{" + qualifiedName + '}';
	}


}
