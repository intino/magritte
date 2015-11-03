package tara.lang.semantics.constraints.parameter;

import tara.lang.model.Element;
import tara.lang.model.EmptyNode;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.ReferenceRule;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;
import tara.lang.semantics.constraints.component.Component;

import java.util.Collections;
import java.util.List;

import static tara.lang.model.Primitive.REFERENCE;

public class ReferenceParameter extends ParameterConstraint implements Component.Parameter {

	private final String name;
	private final Size size;
	private final int position;
	private final List<String> flags;
	private ReferenceRule rule;
	private Object defaultValue;

	public ReferenceParameter(String name, final Size size, Object defaultValue, int position, ReferenceRule rule, List<String> flags) {
		this.name = name;
		this.size = size;
		this.defaultValue = defaultValue;
		this.position = position;
		this.rule = rule;
		this.flags = flags;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node node = (Node) element;
		tara.lang.model.Parameter parameter = findParameter(node.parameters(), name(), position);
		if (parameter == null) return;
		if (checkAsReference(parameter.values())) {
			parameter.name(name());
			parameter.inferredType(type());
			parameter.flags(flags);
			parameter.rule(rule);
		} else
			throw new SemanticException(new SemanticError("reject.parameter.in.context", parameter, rule.getAllowedReferences()));
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
	public Size size() {
		return size;
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
	public List<String> annotations() {
		return Collections.unmodifiableList(flags);
	}

	private boolean checkAsReference(List<Object> values) {
		return checkReferences(values) && this.size().accept(values);
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
			if (rule.accept(type)) return true;
		return false;
	}
}
