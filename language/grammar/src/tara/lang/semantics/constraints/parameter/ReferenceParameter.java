package tara.lang.semantics.constraints.parameter;

import tara.lang.model.*;
import tara.lang.model.Primitive.Reference;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.ReferenceRule;
import tara.lang.semantics.constraints.component.Component;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static tara.lang.model.Primitive.REFERENCE;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public final class ReferenceParameter extends ParameterConstraint implements Component.Parameter {

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
		if (element instanceof Node && ((Node) element).isReference()) return;
		Parametrized parametrized = (Parametrized) element;
		tara.lang.model.Parameter parameter = findParameter(parametrized.parameters(), name(), position);
		if (parameter == null) {
			if (size.isRequired()) error(element, null, error = ParameterError.NOT_FOUND);
			return;
		}
		if (checkAsReference(parameter.values())) {
			parameter.name(name());
			parameter.type(type());
			parameter.flags(flags);
			parameter.rule(rule);
		} else error(element, parameter, error);
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
			if (value instanceof Node && !areCompatibleReference((Node) value) ||
				value instanceof Reference && !isCompatibleDeclarationReference((Reference) value)) {
				error = ParameterError.RULE;
				return false;
			}
		return true;
	}

	private boolean isCompatibleDeclarationReference(Reference value) {
		return value.isToDeclaration() && intersect(new ArrayList<>(value.declarationTypes()), new ArrayList<>(rule.getAllowedReferences()));
	}

	private boolean intersect(List<String> declarationTypes, List<String> allowedReferences) {
		for (int i = declarationTypes.size() - 1; i > -1; --i) {
			String str = declarationTypes.get(i);
			if (!allowedReferences.remove(str))
				declarationTypes.remove(str);
		}
		return !declarationTypes.isEmpty();
	}

	private boolean areCompatibleReference(Node node) {
		for (String type : node.resolve().types())
			if (rule.accept(type)) return true;
		return false;
	}

	protected void error(Element element, tara.lang.model.Parameter parameter, ParameterError errorType) throws SemanticException {
		switch (errorType) {
			case TYPE:
				throw new SemanticException(new SemanticNotification(ERROR, "reject.parameter.in.context", parameter, Arrays.asList(parameter.name(), String.join(", ", rule.getAllowedReferences()))));
			case NOT_FOUND:
				throw new SemanticException(new SemanticNotification(ERROR, "required.parameter.in.context", element, Arrays.asList(this.name, "{" + String.join(",", rule.getAllowedReferences()) + "}")));
			case RULE:
				throw new SemanticException(new SemanticNotification(ERROR, rule().errorMessage(), parameter, rule().errorParameters()));
		}
	}

	@Override
	public String toString() {
		return "Parameter{" + "ref" + "@" + name + "}";
	}
}
