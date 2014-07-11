package siani.tara.lang;

import java.util.List;

public class LinkNode extends Node {

	transient DeclaredNode destiny;
	String destinyQN;

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

	public String getDestinyQN() {
		return destinyQN;
	}

	public void setDestinyQN(String destinyQN) {
		this.destinyQN = destinyQN;
	}

	@Override
	public List<Node> getInnerNodes() {
		return destiny.getInnerNodes();
	}

	@Override
	public NodeObject getObject() {
		return destiny.getObject();
	}

	@Override
	public DeclaredNode getContainer() {
		return container;
	}

	@Override
	public boolean isCase() {
		return destiny.isCase();
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	protected String getNodeRoute() {
		String destiny = (getDestiny() != null) ? getDestiny().getQualifiedName() : getDestinyQN();
		String name = "[" + destiny + LINK + "]";
		return container.getQualifiedName() + "." + name;
	}

	public String toString() {
		return "LinkNode{" + qualifiedName + '}';
	}
}
