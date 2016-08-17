package tara.lang.semantics.constraints.parameter;

import tara.lang.model.*;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.NativeObjectRule;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.model.rules.variable.VariableRule;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static tara.lang.semantics.constraints.PrimitiveTypeCompatibility.checkCompatiblePrimitives;
import static tara.lang.semantics.constraints.PrimitiveTypeCompatibility.inferType;
import static tara.lang.semantics.constraints.parameter.ParameterConstraint.ParameterError.*;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public final class PrimitiveParameter extends ParameterConstraint {

	private final String name;
	private final Primitive type;
	private final String facet;
	private final Size size;
	private final int position;
	private final String scope;
	private final VariableRule rule;
	private final Set<Tag> flags;

	public PrimitiveParameter(String name, Primitive type, String facet, Size size, int position, String level, VariableRule rule, List<Tag> flags) {
		this.name = name;
		this.type = type;
		this.facet = facet;
		this.size = size;
		this.position = position;
		this.scope = level;
		this.rule = rule;
		this.flags = new HashSet<>(flags);
	}

	@Override
	public void check(Element element) throws SemanticException {
		if (element instanceof Node && ((Node) element).isReference()) return;
		checkParameter(element, ((Parametrized) element).parameters());
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
	public String facet() {
		return facet;
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
		return unmodifiableList(new ArrayList<>(flags));
	}

	private void checkParameter(Element element, List<tara.lang.model.Parameter> parameters) throws SemanticException {
		tara.lang.model.Parameter parameter = findParameter(parameters, facet, name, position);
		if (parameter == null) {
			if (size.isRequired() && (!(element instanceof Node) || isNotAbstractNode(element))) error(element, null, error = NOT_FOUND);
			return;
		}
		if (isCompatible(parameter)) {
			parameter.name(name());
			parameter.type(type());
			parameter.facet(this.facet);
			parameter.scope(this.scope);
			if (parameter.rule() == null) parameter.rule(rule());
			else fillRule(parameter);
			if (compliesWithTheConstraints(parameter)) parameter.flags(flags());
			else error(element, parameter, error = RULE);
			if (!size().accept(parameter.values())) error(element, parameter, error = SIZE);
			else parameter.multiple(!size().isSingle());
		} else error(element, parameter, error = TYPE);
	}

	private void fillRule(tara.lang.model.Parameter parameter) throws SemanticException {
		final VariableRule toFill = parameter.rule();
		if (toFill instanceof NativeRule && this.rule() instanceof NativeObjectRule)
			parameter.rule(new NativeObjectRule(((NativeObjectRule) this.rule()).declaredType()));
		else if (this.rule() != null && toFill instanceof NativeRule && this.rule() instanceof NativeRule) {
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
		try {
			return rule.accept(parameter.values(), parameter.metric());
		} catch (Exception e) {
			return false;
		}
	}

	protected void error(Element element, tara.lang.model.Parameter parameter, ParameterError errorType) throws SemanticException {
		switch (errorType) {
			case TYPE:
				throw new SemanticException(new SemanticNotification(ERROR, "reject.invalid.parameter.value.type", parameter, Arrays.asList(name(), type.getName())));
			case NOT_FOUND:
				throw new SemanticException(new SemanticNotification(ERROR, "required.parameter.in.context", element, Arrays.asList(name(), type.getName())));
			case SIZE:
				throw new SemanticException(new SemanticNotification(ERROR, size().errorMessage(), parameter, size().errorParameters()));
			case RULE:
				throw new SemanticException(new SemanticNotification(rule.level(), rule().errorMessage(), parameter, rule().errorParameters()));
		}
	}

	@Override
	public String toString() {
		return "Parameter{" + type + "@" + name + "}";
	}
}
