package io.intino.tara.lang.semantics.constraints.parameter;

import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.variable.NativeObjectRule;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.model.rules.variable.VariableRule;
import io.intino.tara.lang.semantics.constraints.PrimitiveTypeCompatibility;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.*;

import static java.util.Collections.unmodifiableList;

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

	private void checkParameter(Element element, List<io.intino.tara.lang.model.Parameter> parameters) throws SemanticException {
		io.intino.tara.lang.model.Parameter parameter = findParameter(parameters, facet, name, position);
		if (parameter == null) {
			if (size.isRequired() && (!(element instanceof Node) || isNotAbstractNode(element)))
				error(element, null, error = ParameterError.NOT_FOUND);
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
			else error(element, parameter, error = ParameterError.RULE);
			if (!size().accept(parameter.values())) error(element, parameter, error = ParameterError.SIZE);
			else parameter.multiple(!size().isSingle());
		} else error(element, parameter, error = ParameterError.TYPE);
	}

	private void fillRule(io.intino.tara.lang.model.Parameter parameter) throws SemanticException {
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

	private boolean isCompatible(io.intino.tara.lang.model.Parameter parameter) {
		List<Object> values = parameter.values();
		if (values.isEmpty()) return true;
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null && PrimitiveTypeCompatibility.checkCompatiblePrimitives(type(), inferredType, parameter.isMultiple());
	}

	private boolean compliesWithTheConstraints(io.intino.tara.lang.model.Parameter rejectable) {
		return rule == null || accept(rejectable, rule);
	}

	private boolean accept(io.intino.tara.lang.model.Parameter parameter, Rule rule) {
		try {
			return rule instanceof NativeRule || rule.accept(parameter.values(), parameter.metric());
		} catch (Exception e) {
			return false;
		}
	}

	protected void error(Element element, io.intino.tara.lang.model.Parameter parameter, ParameterError errorType) throws SemanticException {
		if (parameter == null) return;
		switch (errorType) {
			case TYPE:
				throw new SemanticException(new SemanticNotification(SemanticNotification.Level.ERROR, "reject.invalid.parameter.value.type", parameter, Arrays.asList(name(), type.getName())));
			case NOT_FOUND:
				throw new SemanticException(new SemanticNotification(SemanticNotification.Level.ERROR, "required.parameter.in.context", element, Arrays.asList(name(), type.getName())));
			case SIZE:
				throw new SemanticException(new SemanticNotification(SemanticNotification.Level.ERROR, size().errorMessage(), parameter, size().errorParameters()));
			case RULE:
				throw new SemanticException(new SemanticNotification(rule.level(), rule().errorMessage() == null || rule().errorMessage().isEmpty() ? "rule.not.complains" : rule().errorMessage(), parameter, rule().errorParameters()));
		}
	}

	@Override
	public String toString() {
		return "Parameter{" + type + "@" + name + "}";
	}
}
