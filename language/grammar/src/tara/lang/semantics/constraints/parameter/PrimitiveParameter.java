package tara.lang.semantics.constraints.parameter;

import tara.lang.model.*;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.model.rules.variable.VariableRule;
import tara.lang.semantics.Constraint.Parameter;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static tara.lang.semantics.constraints.PrimitiveTypeCompatibility.checkCompatiblePrimitives;
import static tara.lang.semantics.constraints.PrimitiveTypeCompatibility.inferType;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public final class PrimitiveParameter extends ParameterConstraint implements Parameter {

	private final String name;
	private final Primitive type;
	private final Size size;
	private final int position;
	private final String scope;
	private final VariableRule rule;
	private final Object defaultValue;
	private final List<Tag> flags;

	public PrimitiveParameter(String name, Primitive type, Size size, Object defaultValue, int position, String scope, VariableRule rule, List<Tag> flags) {
		this.name = name;
		this.type = type;
		this.size = size;
		this.defaultValue = defaultValue;
		this.position = position;
		this.scope = scope;
		this.rule = rule;
		this.flags = flags;
	}

	@Override
	public void check(Element element) throws SemanticException {
		if (element instanceof Node && (((Node) element).isReference() || ((Node) element).isAbstract())) return;
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
	public String scope() {
		return this.scope;
	}

	@Override
	public VariableRule rule() {
		return rule;
	}

	@Override
	public List<Tag> flags() {
		return unmodifiableList(flags);
	}

	private void checkParameter(Element element, List<tara.lang.model.Parameter> parameters) throws SemanticException {
		tara.lang.model.Parameter parameter = findParameter(parameters, name, position);
		if (parameter == null) {
			if (size.isRequired()) error(element, null, error = ParameterError.NOT_FOUND);
			return;
		}
		if (isCompatible(parameter)) {
			parameter.name(name());
			parameter.type(type());
			parameter.scope(scope);
			if (parameter.rule() == null) parameter.rule(rule());
			else fillRule(parameter);
			if (compliesWithTheConstraints(parameter)) parameter.flags(flags());
			else error(element, parameter, error = ParameterError.RULE);
		} else error(element, parameter, error = ParameterError.TYPE);
	}

	private void fillRule(tara.lang.model.Parameter parameter) throws SemanticException {
		final VariableRule toFill = parameter.rule();
		if (this.rule() != null && toFill instanceof NativeRule) {
			NativeRule nativeRule = (NativeRule) this.rule();
			((NativeRule) toFill).interfaceClass(nativeRule.interfaceClass());
			((NativeRule) toFill).signature(nativeRule.signature());
			((NativeRule) toFill).imports(nativeRule.imports());
		}
	}

	private boolean isCompatible(tara.lang.model.Parameter parameter) {
		List<Object> values = parameter.values();
		if (values.isEmpty()) return true;
		Primitive inferredType = inferType(values.get(0));
		return inferredType != null && checkCompatiblePrimitives(type(), inferredType, parameter.isMultiple());
	}

	private boolean compliesWithTheConstraints(tara.lang.model.Parameter rejectable) {
		return checkRule(rejectable);
	}

	private boolean checkRule(tara.lang.model.Parameter parameter) {
		return rule == null || accept(parameter, rule);
	}

	private boolean accept(tara.lang.model.Parameter parameter, Rule rule) {
		return rule.accept(parameter.values(), parameter.metric());
	}

	protected void error(Element element, tara.lang.model.Parameter parameter, ParameterError errorType) throws SemanticException {
		switch (errorType) {
			case TYPE:
				throw new SemanticException(new SemanticNotification(ERROR, "reject.invalid.parameter.value.type", parameter, Arrays.asList(name(), type.getName())));
			case NOT_FOUND:
				throw new SemanticException(new SemanticNotification(ERROR, "required.parameter.in.context", element, Arrays.asList(name(), type.getName())));
			case RULE:
				throw new SemanticException(new SemanticNotification(rule.level(), rule().errorMessage(), parameter, rule().errorParameters()));
		}
	}

	@Override
	public String toString() {
		return "Parameter{" + type + "@" + name + "}";
	}
}
