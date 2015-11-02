package tara.lang.semantics.constraints.parameter;

import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint.Parameter;
import tara.lang.semantics.SemanticException;
import tara.lang.semantics.constraints.PrimitiveTypeCompatibility;

import java.util.Collections;
import java.util.List;

public class PrimitiveParameter extends ParameterConstraint implements Parameter {

	private final String name;
	private final Primitive type;
	private final Size size;
	private final int position;
	private final Rule rule;
	private final Object defaultValue;
	private final List<String> flags;
	private ERROR error = ERROR.TYPE;

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
		Node node = (Node) element;
		checkParameter(node.parameters());
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

	private void checkParameter(List<tara.lang.model.Parameter> parameters) {
		tara.lang.model.Parameter parameter = findParameter(parameters, name, position);
		if (parameter == null) return;
		if (isCompatible(parameter)) {
			parameter.name(name());
			parameter.inferredType(type());
			parameter.rule(rule());
			if (compliesWithTheConstraints(parameter)) fillParameterInfo(parameter);
			else throwError(parameter);
		} else {
			error = ERROR.TYPE;
			throwError(parameter);
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

	//
	private boolean checkRule(tara.lang.model.Parameter parameter) {
		if (rule == null) return true;
		final boolean accept = accept(parameter, rule);
		if (!accept) error = ERROR.RULE;
		return accept;
	}

	private boolean accept(tara.lang.model.Parameter parameter, Rule rule) {
		for (Object o : parameter.values())
			if (!rule.accept(o, parameter.metric())) return false;
		return true;
	}


	private void throwError(tara.lang.model.Parameter parameter) {
//		switch (error) {
//			case TYPE:
//				parameter.invalidType(type.getName());
//				break;
//			case RULE:
//				parameter.ruleFails();
//				break;
//			case CARDINALITY:
//				parameter.invalidCardinality();
//				break;
//		}
	}

	private enum ERROR {
		TYPE, CARDINALITY, RULE
	}
}
