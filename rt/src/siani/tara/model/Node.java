package siani.tara.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Node {

	protected String qualifiedName;
	protected String file;
	protected int line;
	protected List<String> instanceTypes;
	protected transient DeclaredNode container;
	protected ModelObject object;
	private List<String> imports = new ArrayList<>();
	private String modelOwner;
	private transient FacetTarget inFacetTargetParent = null;
	public transient static final String ANONYMOUS = "@anonymous";
	public transient static final String IN_FACET_TARGET = "@facetTarget";
	public transient static final String LINK = "@link";

	public Node() {
		instanceTypes = new ArrayList<>();
	}

	public abstract DeclaredNode getContainer();

	public void setContainer(DeclaredNode container) {
		this.container = container;
	}

	public abstract NodeObject getObject();

	public abstract NodeTree getInnerNodes();

	public abstract Annotation[] getAnnotations();

	protected abstract List<Annotation> getAnnotationList();

	public void add(Annotation annotation) {
		getAnnotationList().add(annotation);
	}

	public abstract boolean isSub();

	public DeclaredNode[] getSubNodes() {
		List<DeclaredNode> subs = new ArrayList<>();
		if (getInnerNodes() == null) return new DeclaredNode[0];
		for (Node child : getInnerNodes())
			if (child instanceof DeclaredNode && child.getObject().isSub()) subs.add((DeclaredNode) child);
		return subs.toArray(new DeclaredNode[subs.size()]);
	}

	public Collection<DeclaredNode> getDeepSubNodes() {
		List<DeclaredNode> subs = new ArrayList<>();
		if (getInnerNodes() == null) return subs;
		for (Node child : getInnerNodes())
			if (child.is(DeclaredNode.class) && child.isSub()) {
				subs.add((DeclaredNode) child);
				subs.addAll(child.getDeepSubNodes());
			}
		return subs;
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
		qualifiedName = getNodePath();
		return qualifiedName;
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
		if (!imports.isEmpty())
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

	public abstract boolean isAssociated();

	public abstract boolean isSingle();

	public abstract boolean isAbstract();

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

	public boolean isAnonymous() {
		return getName().contains(ANONYMOUS);
	}

	public String getMetaQN() {
		return container == null ? isSub() ? "" : getType() : container.getMetaQN() + (isSub() ? "" : "." + getType());
	}

	public void setFacetTargetParent(FacetTarget facetTarget) {
		this.inFacetTargetParent = facetTarget;
	}

	public FacetTarget getFacetTargetParent() {
		return inFacetTargetParent;
	}

	public boolean isInFacetTargetParent() {
		return this.inFacetTargetParent != null;
	}
}
