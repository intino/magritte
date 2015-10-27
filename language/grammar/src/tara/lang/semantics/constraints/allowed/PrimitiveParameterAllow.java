package tara.lang.semantics.constraints.allowed;

import tara.lang.model.Element;
import tara.lang.model.Metric;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.semantics.Allow;
import tara.lang.semantics.Rejectable;
import tara.lang.semantics.SemanticException;
import tara.lang.semantics.constraints.PrimitiveTypeCompatibility;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tara.lang.semantics.constraints.PrimitiveTypeCompatibility.checkCompatiblePrimitives;

public class PrimitiveParameterAllow extends ParameterAllow implements Allow.Parameter {

	private final String name;
	private final Primitive type;
	private final boolean multiple;
	private final int position;
	private final Rule rule;
	private final Object defaultValue;
	private final List<String> flags;


	public PrimitiveParameterAllow(String name, Primitive type, boolean multiple, Object defaultValue, int position, Rule rule, List<String> flags) {
		this.name = name;
		this.type = type;
		this.multiple = multiple;
		this.defaultValue = defaultValue;
		this.position = position;
		this.rule = rule;
		this.flags = flags;
	}

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) throws SemanticException {
		List<Rejectable> toRemove = new ArrayList<>();
		checkParameter(rejectables, toRemove);
		rejectables.removeAll(toRemove);
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
	public boolean multiple() {
		return multiple;
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
	public List<String> flags() {
		return flags;
	}

	private void checkParameter(List<? extends Rejectable> rejectables, List<Rejectable> toRemove) {
		Rejectable.Parameter parameter = findParameter(rejectables, name, position);
		if (parameter == null) return;
		if (checkParameter(parameter)) {
			toRemove.add(parameter);
			parameter.getParameter().name(name());
			parameter.getParameter().inferredType(type());
			parameter.getParameter().flags(flags);
			parameter.getParameter().multiple(multiple());
			parameter.getParameter().rule(rule());
		} else throwError(parameter);
	}

	private void throwError(Rejectable.Parameter parameter) {
		switch (error) {
			case TYPE:
				parameter.invalidType(type.getName());
				break;
			case METRIC:
				parameter.invalidMetric(((Metric) rule()).units());
				break;
			case RULE:
				parameter.invalidMetric(Collections.singletonList(rule().errorMessage()));//TODO
				break;
			case CARDINALITY:
				parameter.invalidCardinality();
				break;
		}
	}

	private boolean checkParameter(Rejectable.Parameter rejectable) {
		List<Object> values = rejectable.getParameter().values();
		if (values.isEmpty()) return true;
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null &&
			checkCompatiblePrimitives(type(), inferredType, rejectable.getParameter().isMultiple()) &&
			checkCardinality(values.size()) &&
			checkRule(rejectable.getParameter());
	}

	private boolean checkRule(tara.lang.model.Parameter parameter) {
		if (rule == null) return true;
		final boolean accept = accept(parameter, rule);
		if (!accept) error = ERROR.RULE;
		return accept;
	}

	private boolean accept(tara.lang.model.Parameter parameter, Rule rule) {
		for (Object o : parameter.values())
			if (!rule.accept(cast(o))) return false;
		return true;
	}

	private Object cast(Object o) {
		if (type.equals(Primitive.FILE))
			return new File(o.toString().substring(1, o.toString().length() - 1));
		return o;
	}

	private boolean checkCardinality(int size) {
		boolean check = size <= 1 || multiple();
		if (!check) error = ERROR.CARDINALITY;
		return check;
	}

	private static ERROR error = ERROR.TYPE;

	private enum ERROR {
		TYPE, CARDINALITY, METRIC, RULE
	}
}
