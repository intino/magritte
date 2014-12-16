package siani.tara.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		return destinyQN.substring(destinyQN.lastIndexOf(".") + 1);
	}

	@Override
	public List<Node> getInnerNodes() {
		return destiny.getInnerNodes();
	}

	@Override
	public Annotation[] getAnnotations() {
		return annotations.toArray(new Annotation[annotations.size()]);
	}

	@Override
	public NodeObject getObject() {
		return destiny.getObject();
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
		return "";
	}

	@Override
	protected String getNodePath() {
		String destiny = (getDestiny() != null) ? getDestiny().getQualifiedName() : getDestinyQN();
		String name = "[" + destiny + LINK + (isAggregated() ? "{aggregated}" : "") + "]";
		return container.getQualifiedName() + "." + name;
	}

	public String toString() {
		return "LinkNode{" + qualifiedName + '}';
	}
}
