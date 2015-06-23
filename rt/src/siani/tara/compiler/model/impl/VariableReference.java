package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;
import siani.tara.semantic.model.Tag;

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
	public Variable cloneIt(NodeContainer container) {
		VariableReference variable = new VariableReference(container, getType(), getName());
		variable.setMultiple(this.isMultiple());
		variable.setDefaultExtension(getDefaultExtension());
		variable.setContract(getContract());
		for (Tag tag : getFlags()) variable.addFlags(tag.name());
		variable.addAllowedValues(getAllowedValues().toArray(new Object[getAllowedValues().size()]));
		variable.addDefaultValues(getDefaultValues().toArray(new Object[getDefaultValues().size()]));
		variable.setDestiny(destiny);
		variable.setInherited(true);
		return variable;
	}
}
