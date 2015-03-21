package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Node;

public class VariableReference extends VariableImpl {

	private Node destiny;

	public VariableReference(String type, String name) {
		super(type, name);
	}

	public Node getDestiny() {
		return destiny;
	}

	public void setDestiny(Node destiny) {
		this.destiny = destiny;
	}
}
