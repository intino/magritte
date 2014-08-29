package siani.tara.lang;

import java.util.List;

public class LinkNode extends Node {

	transient DeclaredNode destiny;
	String destinyQN;
	String destinyBox;

	public LinkNode() {
	}

	public LinkNode(DeclaredNode destiny, DeclaredNode container) {
		this.destiny = destiny;
		this.container = container;
		this.destinyBox = destiny.getBox();
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
		this.destinyBox = destiny.getBox();
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
	public NodeObject getObject() {
		return destiny.getObject();
	}

	@Override
	public DeclaredNode getContainer() {
		return container;
	}

	@Override
	public boolean isSub() {
		return destiny.isSub();
	}

	@Override
	public String getName() {
		return "";
	}

	public String getDestinyBox() {
		return destinyBox;
	}

	public void setDestinyBox(String destinyBox) {
		this.destinyBox = destinyBox;
	}

	@Override
	protected String getNodePath() {
		String destiny = (getDestiny() != null) ? getDestiny().getQualifiedName() : getDestinyQN();
		String name = "[" + destiny + LINK + "]";
		return container.getQualifiedName() + "." + name;
	}

	public String toString() {
		return "LinkNode{" + qualifiedName + '}';
	}
}
