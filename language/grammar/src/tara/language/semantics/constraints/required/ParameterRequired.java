package tara.language.semantics.constraints.required;

import tara.language.model.Element;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.semantics.Constraint;
import tara.language.semantics.SemanticError;
import tara.language.semantics.SemanticException;
import tara.language.semantics.constraints.ConstraintHelper;
import tara.language.semantics.constraints.PrimitiveTypeCompatibility;

import java.util.Arrays;
import java.util.List;

public class ParameterRequired implements Constraint.Require.Parameter {
	private final String name;
	private final String type;
	private final boolean multiple;
	private final Object defaultValue;
	private final int position;
	private final String contract;
	private final String[] annotations;

	public ParameterRequired(String name, String type, boolean multiple, Object defaultValue, int position, String contract, String... annotations) {
		this.name = name;
		this.type = type;
		this.multiple = multiple;
		this.defaultValue = defaultValue;
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
	public Object defaultValue() {
		return defaultValue;
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
		final tara.language.model.Parameter parameter = ConstraintHelper.checkParameterExists(parameters, name(), position);
		if (parameter != null && checkParameter(parameter)) return;
		String elementType = (element instanceof Facet) ? ((Facet) element).type() : ((Node) element).type();
		throw new SemanticException(new SemanticError("required.parameter", element, Arrays.asList(elementType, type, name)));
	}

	private boolean checkParameter(tara.language.model.Parameter parameter) {
		List<Object> values = parameter.values();
		if (values.isEmpty()) return true;
		String inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return !inferredType.isEmpty() && PrimitiveTypeCompatibility.checkCompatiblePrimitives(type(), inferredType, parameter.isMultiple()) && checkCardinality(values.size());
	}

	private boolean checkCardinality(int size) {
		return size <= 1 || multiple();
	}
}
