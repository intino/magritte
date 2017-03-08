package io.intino.tara.lang.semantics.constraints.parameter;

import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.variable.ReferenceRule;
import io.intino.tara.lang.model.rules.variable.VariableRule;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ReferenceParameter extends ParameterConstraint {

	private final String name;
	private final String type;
	private final String facet;
	private final Size size;
	private final int position;
	private final List<Tag> flags;
	private final String scope;
	private VariableRule rule;

	public ReferenceParameter(String name, String type, String facet, final Size size, int position, String scope, VariableRule rule, List<Tag> flags) {
		this.name = name;
		this.type = type;
		this.facet = facet;
		this.size = size;
		this.position = position;
		this.scope = scope;
		this.rule = rule;
		this.flags = flags;
	}

	@Override
	public void check(Element element) throws SemanticException {
		if (element instanceof Node && (((Node) element).isReference() || ((Node) element).isAbstract())) return;
		Parametrized parametrized = (Parametrized) element;
		io.intino.tara.lang.model.Parameter parameter = findParameter(parametrized.parameters(), this.facet, this.name, this.position);
		if (parameter == null) {
			if (size.isRequired() && (!(element instanceof Node) || isNotAbstractNode(element)))
				error(element, null, error = ParameterError.NOT_FOUND);
			return;
		}
		if (checkAsReference(parameter.values())) {
			parameter.name(name());
			parameter.type(type());
			parameter.facet(this.facet);
			parameter.flags(flags);
			parameter.rule(rule);
			parameter.scope(scope);
		} else error(element, parameter, error);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Primitive type() {
		return Primitive.REFERENCE;
	}

	@Override
	public String facet() {
		return this.facet;
	}

	public String referenceType() {
		return type;
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
		return Collections.unmodifiableList(flags);
	}

	public boolean isConstraintOf(io.intino.tara.lang.model.Parameter parameter) {
		io.intino.tara.lang.model.Parameter expected = findParameter(parameter.container().parameters(), this.facet, this.name, this.position);
		return parameter.equals(expected);
	}

	private boolean checkAsReference(List<Object> values) {
		final boolean size = this.size().accept(values);
		if (!size) error = ParameterError.SIZE;
		return size && checkReferences(values);
	}

	private boolean checkReferences(List<Object> values) {
		if (values.isEmpty()) return false;
		if (values.get(0) instanceof EmptyNode) return values.size() == 1;
		for (Object value : values)
			if (value instanceof Primitive.Reference && !areCompatibleReference(((Primitive.Reference) value).reference()) && !isCompatibleInstanceReference((Primitive.Reference) value)) {
				error = ParameterError.RULE;
				return false;
			} else if (!(value instanceof Node) && !(value instanceof Primitive.Reference)) return false;
		return true;
	}

	private boolean isCompatibleInstanceReference(Primitive.Reference value) {
		return !(rule() instanceof ReferenceRule) || value.isToInstance() && intersect(new ArrayList<>(value.instanceTypes()), new ArrayList<>(((ReferenceRule) rule).allowedReferences()));
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
		return node != null && rule.accept(Collections.singletonList(new Primitive.Reference(node)));
	}

	protected void error(Element element, io.intino.tara.lang.model.Parameter parameter, ParameterError errorType) throws SemanticException {
		switch (errorType) {
			case TYPE:
				throw new SemanticException(new SemanticNotification(SemanticNotification.Level.ERROR, "reject.parameter.in.context", parameter, Arrays.asList(parameter.name(), allowedValues(", "))));
			case NOT_FOUND:
				throw new SemanticException(new SemanticNotification(SemanticNotification.Level.ERROR, "required.parameter.in.context", element, Arrays.asList(this.name, "{" + allowedValues(", ") + "}")));
			case RULE:
				throw new SemanticException(new SemanticNotification(SemanticNotification.Level.ERROR, rule().errorMessage(), parameter, rule().errorParameters()));
		}
	}

	private String allowedValues(String delimiter) {
		return rule() instanceof ReferenceRule ? String.join(delimiter, ((ReferenceRule) rule).allowedReferences()) : "";
	}

	@Override
	public String toString() {
		return "Parameter{" + "ref" + "@" + name + "}";
	}
}
