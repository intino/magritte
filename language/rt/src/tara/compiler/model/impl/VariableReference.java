package tara.compiler.model.impl;

import tara.compiler.model.Node;
import tara.compiler.model.NodeContainer;
import tara.compiler.model.Variable;
import tara.semantic.model.Tag;

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
		VariableReference variable = new VariableReference(container, type(), name());
		variable.size(this.size());
		variable.defaultExtension(defaultExtension());
		variable.contract(contract());
		for (Tag tag : flags()) variable.addFlags(tag.name());
		variable.addAllowedValues(allowedValues().toArray(new Object[allowedValues().size()]));
		variable.addDefaultValues(getDefaultValues().toArray(new Object[getDefaultValues().size()]));
		variable.setDestiny(destiny);
		variable.setInherited(true);
		return variable;
	}
}
