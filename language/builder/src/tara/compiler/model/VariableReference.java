package tara.compiler.model;

import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

public class VariableReference extends VariableImpl {

	private Node destiny;
	private String destinyName;

	public VariableReference(Node container, String type, String name, String scope) {
		super(container, Primitive.REFERENCE, name, scope);
		this.destinyName = type;
	}

	public Node getDestiny() {
		return destiny;
	}

	public String destinyName() {
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
	public Variable cloneIt(Node container) {
		VariableReference variable = new VariableReference(container, destinyName(), name(), scope());
		variable.size(this.size());
		variable.defaultMetric(defaultMetric());
		variable.rule(rule());
		flags().forEach(variable::addFlags);
		variable.values(this.values());
		variable.setDestiny(destiny);
		variable.setInherited(true);
		variable.file(file());
		variable.line(line());
		variable.column(column());
		return variable;
	}
}
