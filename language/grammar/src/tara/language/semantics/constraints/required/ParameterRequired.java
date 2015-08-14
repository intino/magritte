package tara.language.semantics.constraints.required;

import tara.language.model.Element;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.semantics.Constraint;
import tara.language.semantics.SemanticError;
import tara.language.semantics.SemanticException;
import tara.language.semantics.constraints.ConstraintHelper;

import java.util.Arrays;
import java.util.List;

public class ParameterRequired implements Constraint.Require.Parameter {
	private final String name;
	private final String type;
	private final boolean multiple;
	private final int position;
	private final String contract;
	private final String[] annotations;

	public ParameterRequired(String name, String type, boolean multiple, int position, String contract, String... annotations) {
		this.name = name;
		this.type = type;
		this.multiple = multiple;
		this.position = position;
		this.contract = contract;
		this.annotations = annotations;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public boolean multiple() {
		return multiple;
	}

	@Override
	public String[] allowedValues() {
		return new String[0];
	}

	@Override
	public int position() {
		return position;
	}

	@Override
	public String metric() {
		return contract;
	}

	@Override
	public String[] annotations() {
		return Arrays.copyOf(annotations, annotations.length);

	}

	@Override
	public void check(Element element) throws SemanticException {
		if (element instanceof Node && ((Node) element).isReference()) return;
		List<? extends tara.language.model.Parameter> parameters = (element instanceof Facet) ? ((Facet) element).parameters() : ((Node) element).parameters();
		if (ConstraintHelper.checkParameterExists(parameters, name(), position)) return;
		String elementType = (element instanceof Facet) ? ((Facet) element).type() : ((Node) element).type();
		throw new SemanticException(new SemanticError("required.parameter", element, Arrays.asList(elementType, type, name)));
	}
}
