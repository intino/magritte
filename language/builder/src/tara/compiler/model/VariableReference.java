package tara.compiler.model;

import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

public class VariableReference extends VariableImpl {

	private Node destiny;
	private String destinyName;

	public VariableReference(NodeContainer container, String type, String name, String scope) {
		super(container, Primitive.REFERENCE, name, scope);
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
		VariableReference variable = new VariableReference(container, getDestinyName(), name(), scope());
		variable.size(this.size());
		variable.defaultMetric(defaultMetric());
		variable.rule(rule());
		flags().forEach(variable::addFlags);
		variable.values(this.values());
		variable.setDestiny(destiny);
		variable.setInherited(true);
		return variable;
	}
}
