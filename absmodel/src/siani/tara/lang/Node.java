package siani.tara.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Node {

	protected transient static final String ANONYMOUS = "@anonymous";
	protected transient static final String LINK = "@link";
	protected String qualifiedName;
	protected String file;
	protected String box;
	protected int line;
	protected transient DeclaredNode container;
	private List<String> imports = new ArrayList<>();

	public Node() {
	}

	public abstract DeclaredNode getContainer();

	public abstract NodeObject getObject();

	public abstract List<Node> getInnerNodes();

	public abstract boolean isCase();

	public DeclaredNode[] getCases() {
		List<DeclaredNode> cases = new ArrayList<>();
		for (Node child : getInnerNodes())
			if (child.getObject().isCase()) cases.add((DeclaredNode) child);
		return cases.toArray(new DeclaredNode[cases.size()]);
	}

	public String getQualifiedName() {
		return qualifiedName == null ? calculateQualifiedName() : qualifiedName;
	}

	public abstract String getName();

	public String calculateQualifiedName() {
		return qualifiedName = getNodeRoute();
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

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public List<String> getImports() {
		return imports;
	}

	public void addImports(Collection<String> imports) {
		if (imports.size() > 0)
			this.imports.addAll(imports);
	}

	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	public void setContainer(DeclaredNode container) {
		this.container = container;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}

	public boolean isPrime() {
		return getContainer() == null;
	}

	protected abstract String getNodeRoute();

}
