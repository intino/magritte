package tara.lang.semantics.constraints.allowed;

import tara.lang.model.Element;
import tara.lang.model.EmptyNode;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.rules.ReferenceRule;
import tara.lang.semantics.Allow;
import tara.lang.semantics.Rejectable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tara.lang.model.Primitive.REFERENCE;

public class ReferenceParameterAllow extends ParameterAllow implements Allow.Parameter {

	private final String name;
	private final boolean multiple;
	private final int position;
	private final List<String> flags;
	private ReferenceRule rule;
	private Object defaultValue;

	public ReferenceParameterAllow(String name, boolean multiple, Object defaultValue, int position, ReferenceRule rule, List<String> flags) {
		this.name = name;
		this.multiple = multiple;
		this.defaultValue = defaultValue;
		this.position = position;
		this.rule = rule;
		this.flags = flags;
	}

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) {
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
		return REFERENCE;
	}

	@Override
	public Object defaultValue() {
		return this.defaultValue;
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
	public ReferenceRule rule() {
		return rule;
	}

	@Override
	public List<String> flags() {
		return Collections.unmodifiableList(flags);
	}

	private void checkParameter(List<? extends Rejectable> rejectables, List<Rejectable> toRemove) {
		Rejectable.Parameter parameter = findParameter(rejectables, name(), position);
		if (parameter == null) return;
		if (checkAsReference(parameter.getParameter().values())) {
			parameter.getParameter().name(name());
			parameter.getParameter().inferredType(type());
			parameter.getParameter().flags(flags);
			parameter.getParameter().multiple(multiple());
			parameter.getParameter().rule(rule);
			toRemove.add(parameter);
		} else parameter.invalidValue(rule.getAllowedReferences());
	}

	private boolean checkAsReference(List<Object> values) {
		return checkReferences(values) && checkCardinality(values.size());
	}

	private boolean checkReferences(List<Object> values) {
		if (values.isEmpty()) return false;
		if (values.get(0) instanceof EmptyNode) return values.size() == 1;
		for (Object value : values)
			if (!(value instanceof Node) || !areCompatibleReference((Node) value)) return false;
		return true;
	}

	private boolean areCompatibleReference(Node node) {
		for (String type : node.types())
			if (rule.getAllowedReferences().contains(type)) return true;
		return false;
	}

	private boolean checkCardinality(int size) {
		return size <= 1 || multiple();
	}

}
