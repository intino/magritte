package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;

public class VariableReference extends VariableImpl {

	private Node destiny;

	public VariableReference(NodeContainer container, String type, String name) {
		super(container, type, name);
	}

	public Node getDestiny() {
		return destiny;
	}

	public void setDestiny(Node destiny) {
		this.destiny = destiny;
	}

	@Override
	public Variable cloneIt(NodeContainer container) throws CloneNotSupportedException {
		VariableReference reference = new VariableReference(container, getType(), getName());
		reference.setDestiny(destiny);
		return reference;
	}
}
