package siani.tara.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Node {

	protected transient static final String ANONYMOUS = "@anonymous";
	protected transient static final String IN_FACET_TARGET = "@facetTarget";
	protected transient static final String LINK = "@link";
	protected String qualifiedName;
	protected String file;
	protected int line;
	protected List<String> instanceTypes;
	protected transient DeclaredNode container;
	ModelObject object;
	private List<String> imports = new ArrayList<>();
	private String modelOwner;

	public Node() {
		instanceTypes = new ArrayList<>();
	}

	public abstract DeclaredNode getContainer();

	public void setContainer(DeclaredNode container) {
		this.container = container;
	}

	public abstract NodeObject getObject();

	public abstract List<Node> getInnerNodes();

	public abstract Annotation[] getAnnotations();

	public abstract boolean isSub();

	public DeclaredNode[] getSubConcepts() {
		List<DeclaredNode> subs = new ArrayList<>();
		for (Node child : getInnerNodes())
			if (child instanceof DeclaredNode && child.getObject().isSub()) subs.add((DeclaredNode) child);
		return subs.toArray(new DeclaredNode[subs.size()]);
	}

	public String getQualifiedName() {
		return qualifiedName == null ? calculateQualifiedName() : qualifiedName;
	}

	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	public abstract String getName();

	public String getType() {
		return object.getType();
	}

	public String calculateQualifiedName() {
		return qualifiedName = getNodePath();
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

	public void setImports(List<String> imports) {
		this.imports = imports;
	}

	public void addImports(Collection<String> imports) {
		if (imports.size() > 0)
			this.imports.addAll(imports);
	}

	public boolean is(Annotation annotation) {
		for (Annotation a : getAnnotations())
			if (annotation.equals(a)) return true;
		return false;
	}


	public boolean isPrime() {
		return getContainer() == null;
	}

	public abstract boolean isAggregated();

	protected abstract String getNodePath();

	public boolean is(Class type) {
		return type.isInstance(this);
	}

	public void setModelOwner(String modelOwner) {
		this.modelOwner = modelOwner;
	}

	public String getModelOwner() {
		return modelOwner;
	}

	public boolean addInstanceType(String inheritedType) {
		return instanceTypes.add(inheritedType);
	}
}
