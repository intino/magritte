package tara.compiler.model;

import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Variable;

public class VariableReference extends VariableImpl {

	private Node destiny;
	private boolean type;

	public VariableReference(NodeContainer container, String type, String name, boolean isType) {
		super(container, type, name);
		this.type = isType;
	}

	public Node getDestiny() {
		return destiny;
	}

	public void setDestiny(Node destiny) {
		this.destiny = destiny;
	}

	@Override
	public Node destinyOfReference() {
		return getDestiny();
	}

	@Override
	public boolean isReference() {
		return true;
	}

	public boolean isType() {
		return type;
	}

	@Override
	public Variable cloneIt(NodeContainer container) {
		VariableReference variable = new VariableReference(container, type(), name(), type);
		variable.size(this.size());
		variable.defaultExtension(defaultExtension());
		variable.contract(contract());
		flags().forEach(variable::addFlags);
		variable.addAllowedValues(allowedValues().toArray(new Object[allowedValues().size()]));
		variable.addDefaultValues(defaultValues().toArray(new Object[defaultValues().size()]));
		variable.setDestiny(destiny);
		variable.setInherited(true);
		return variable;
	}
}
