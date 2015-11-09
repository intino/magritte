package tara.lang.semantics.constraints.parameter;

import tara.lang.model.*;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint.Parameter;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;
import tara.lang.semantics.constraints.PrimitiveTypeCompatibility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class PrimitiveParameter extends ParameterConstraint implements Parameter {

	private final String name;
	private final Primitive type;
	private final Size size;
	private final int position;
	private final Rule rule;
	private final Object defaultValue;
	private final List<String> flags;

	public PrimitiveParameter(String name, Primitive type, Size size, Object defaultValue, int position, Rule rule, List<String> flags) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.defaultValue = defaultValue;
		this.position = position;
		this.rule = rule;
		this.flags = flags;
	}

	@Override
	public void check(Element element) throws SemanticException {
		if (element instanceof Node && ((Node) element).isReference()) return;
		Parametrized parametrized = (Parametrized) element;
		checkParameter(element, parametrized.parameters());
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
	public Object defaultValue() {
		return defaultValue;
	}

	@Override
	public Size size() {
		return size;
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
		return Collections.unmodifiableList(flags);
	}

	private void checkParameter(Element element, List<tara.lang.model.Parameter> parameters) throws SemanticException {
		tara.lang.model.Parameter parameter = findParameter(parameters, name, position);
		if (parameter == null) {
			if (size.isRequired()) {
				error = ERROR.NOT_FOUND;
				throwError(element, null);
			}
			return;
		}
		if (isCompatible(parameter)) {
			parameter.name(name());
			parameter.inferredType(type());
			parameter.rule(rule());
			parameter.flags(annotations());
			if (compliesWithTheConstraints(parameter)) fillParameterInfo(parameter);
			else throwError(element, parameter);
		} else {
			error = ERROR.TYPE;
			throwError(element, parameter);
		}
	}

	private boolean isCompatible(tara.lang.model.Parameter parameter) {
		List<Object> values = parameter.values();
		if (values.isEmpty()) return true;
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null &&
			PrimitiveTypeCompatibility.checkCompatiblePrimitives(type(), inferredType, parameter.isMultiple());
	}

	private void fillParameterInfo(tara.lang.model.Parameter parameter) {
		parameter.flags(flags);
	}

	private boolean compliesWithTheConstraints(tara.lang.model.Parameter rejectable) {
		return checkRule(rejectable);
	}

	private boolean checkRule(tara.lang.model.Parameter parameter) {
		if (rule == null) return true;
		final boolean accept = accept(parameter, rule);
		if (!accept) error = ERROR.RULE;
		return accept;
	}

	private boolean accept(tara.lang.model.Parameter parameter, Rule rule) {
		return rule.accept(parameter.values(), parameter.metric());
	}


	protected void throwError(Element element, tara.lang.model.Parameter parameter) throws SemanticException {
		switch (error) {
			case TYPE:
				throw new SemanticException(new SemanticError("invalid type", parameter, Collections.singletonList(type.getName())));
			case NOT_FOUND:
				throw new SemanticException(new SemanticError("required.parameter.type.in.context", element, Arrays.asList(this.name, this.type)));
			case RULE:
				throw new SemanticException(new SemanticError("rule fails", parameter, Collections.singletonList(type.getName())));
		}
	}


}
