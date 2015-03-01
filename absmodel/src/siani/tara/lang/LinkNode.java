package siani.tara.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static siani.tara.lang.Annotation.ABSTRACT;
import static siani.tara.lang.Annotation.AGGREGATED;

public class LinkNode extends Node {

	transient DeclaredNode destiny;
	String destinyQN;
	boolean reference;
	List<Annotation> annotations = new ArrayList<>();

	public LinkNode() {
	}

	public LinkNode(DeclaredNode destiny, DeclaredNode container) {
		this.destiny = destiny;
		destinyQN = destiny.getQualifiedName();
		this.container = container;
	}

	public LinkNode(String destinyQN, DeclaredNode container) {
		this.destinyQN = destinyQN;
		this.container = container;
	}

	public DeclaredNode getDestiny() {
		return destiny;
	}

	public void setDestiny(DeclaredNode destiny) {
		this.destiny = destiny;
	}

	public boolean isReference() {
		return reference;
	}

	public void setReference(boolean reference) {
		this.reference = reference;
	}

	public String getDestinyQN() {
		return destinyQN;
	}

	public void setDestinyQN(String destinyQN) {
		this.destinyQN = destinyQN;
	}

	public String getDestinyName() {
		return destinyQN.substring(destinyQN.lastIndexOf('.') + 1);
	}

	@Override
	public NodeTree getInnerNodes() {
		return destiny != null ? destiny.getInnerNodes() : null;
	}

	@Override
	public Annotation[] getAnnotations() {
		return annotations.toArray(new Annotation[annotations.size()]);
	}

	@Override
	protected List<Annotation> getAnnotationList() {
		return annotations;
	}

	@Override
	public NodeObject getObject() {
		return destiny != null ? destiny.getObject() : null;
	}

	@Override
	public DeclaredNode getContainer() {
		return container;
	}

	public boolean isAggregated() {
		return annotations.contains(AGGREGATED);
	}

	public boolean addAll(Collection<? extends Annotation> c) {
		return annotations.addAll(c);
	}

	@Override
	public boolean isSub() {
		return destiny.isSub();
	}

	@Override
	public String getName() {
		return destiny != null ? destiny.getName() : null;
	}

	public boolean isAbstract() {
		return destiny.is(ABSTRACT);
	}

	@Override
	public boolean is(Annotation annotation) {
		for (Annotation linkAnnotation : annotations)
			if (linkAnnotation.equals(annotation)) return true;
		return destiny.is(annotation);
	}

	public String getType() {
		return destiny.getType();
	}

	@Override
	protected String getNodePath() {
		String destinyPath = (getDestiny() != null) ? getDestiny().getQualifiedName() : getDestinyQN();
		String name = "[" + destinyPath + LINK + (isAggregated() ? "{aggregated}" : "") + "]";
		return (container != null ? container.getQualifiedName() + '.' : "") + name;
	}

	public String toString() {
		return "LinkNode{" + qualifiedName + '}';
	}
}
