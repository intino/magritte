package tara.lang.semantics.constraints.parameter;

import tara.lang.model.Element;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Rejectable;
import tara.lang.semantics.SemanticException;
import tara.lang.semantics.constraints.PrimitiveTypeCompatibility;
import tara.lang.semantics.constraints.component.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tara.lang.semantics.constraints.PrimitiveTypeCompatibility.checkCompatiblePrimitives;

public class PrimitiveParameter extends ParameterConstraint implements Component.Parameter {

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
		List<Rejectable> toRemove = new ArrayList<>();
//		checkParameter(rejectables, toRemove);
//		rejectables.removeAll(toRemove);
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

	private void checkParameter(List<? extends Rejectable> rejectables, List<Rejectable> toRemove) {
		Rejectable.Parameter parameter = findParameter(rejectables, name, position);
		if (parameter == null) return;
		if (isCompatible(parameter)) {
			parameter.getParameter().name(name());
			parameter.getParameter().inferredType(type());
			parameter.getParameter().rule(rule());
			if (compliesWithTheConstraints(parameter))
				fillParameterInfo(toRemove, parameter);
			else throwError(parameter);
		} else {
			error = ERROR.TYPE;
			throwError(parameter);
		}
	}

	private boolean isCompatible(Rejectable.Parameter rejectable) {
		List<Object> values = rejectable.getParameter().values();
		if (values.isEmpty()) return true;
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null &&
			checkCompatiblePrimitives(type(), inferredType, rejectable.getParameter().isMultiple());
	}

	private void fillParameterInfo(List<Rejectable> toRemove, Rejectable.Parameter parameter) {
		parameter.getParameter().flags(flags);
		toRemove.add(parameter);
	}

	private boolean compliesWithTheConstraints(Rejectable.Parameter rejectable) {
		return checkRule(rejectable.getParameter());
	}

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


	private void throwError(Rejectable.Parameter parameter) {
		switch (error) {
			case TYPE:
				parameter.invalidType(type.getName());
				break;
			case RULE:
				parameter.ruleFails();
				break;
			case CARDINALITY:
				parameter.invalidCardinality();
				break;
		}
	}

	private enum ERROR {
		TYPE, CARDINALITY, RULE
	}
}
