package tara.lang.semantics.constraints.required;

import tara.lang.model.*;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;
import tara.lang.semantics.constraints.ConstraintHelper;
import tara.lang.semantics.constraints.PrimitiveTypeCompatibility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParameterRequired implements Constraint.Has.Parameter {
	private final String name;
	private final Primitive type;
	private final Size size;
	private final Object defaultValue;
	private final int position;
	private final Rule rule;
	private final List<String> annotations;

	public ParameterRequired(String name, Primitive type, Size size, Object defaultValue, int position, Rule rule, String... annotations) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.defaultValue = defaultValue;
		this.position = position;
		this.rule = rule;
		this.annotations = Arrays.asList(annotations);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Primitive type() {
		return type;
	}

	@Override
	public Size size() {
		return size;
	}

	@Override
	public Object defaultValue() {
		return defaultValue;
	}

	@Override
	public int position() {
		return position;
	}

	@Override
	public Rule rule() {
		return rule;
	}

	@Override
	public List<String> annotations() {
		return Collections.unmodifiableList(annotations);
	}

	@Override
	public void check(Element element) throws SemanticException {
		if (element instanceof Node && ((Node) element).isReference()) return;
		List<? extends tara.lang.model.Parameter> parameters = (element instanceof Facet) ? ((Facet) element).parameters() : ((Node) element).parameters();
		final tara.lang.model.Parameter parameter = ConstraintHelper.checkParameterExists(parameters, name(), position);
		if (parameter != null && checkParameter(parameter)) return;
		String elementType = (element instanceof Facet) ? ((Facet) element).type() : ((Node) element).type();
		throw new SemanticException(new SemanticError("required.parameter", element, Arrays.asList(elementType, type, name)));
	}

	private boolean checkParameter(tara.lang.model.Parameter parameter) {
		List<Object> values = parameter.values();
		if (values.isEmpty()) return true;
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null && PrimitiveTypeCompatibility.checkCompatiblePrimitives(type(), inferredType, size.max() > 1);
	}
}
