package tara.compiler.model;

import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Primitive;
import tara.language.model.Variable;

public class VariableReference extends VariableImpl {

	private Node destiny;
	private String destinyName;

	public VariableReference(NodeContainer container, String type, String name) {
		super(container, Primitive.REFERENCE, name);
		this.destinyName = type;
	}

	public Node getDestiny() {
		return destiny;
	}

	public String getDestinyName() {
		return destinyName;
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

	@Override
	public Variable cloneIt(NodeContainer container) {
		VariableReference variable = new VariableReference(container, getDestinyName(), name());
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
