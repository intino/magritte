package tara.lang.semantics.constraints.required;

import tara.lang.model.Element;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.rules.ReferenceRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;
import tara.lang.semantics.constraints.ConstraintHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReferenceParameterRequired implements Constraint.Require.Parameter {
	private final String name;
	private final boolean multiple;
	private final Object defaultValue;
	private final int position;
	private final ReferenceRule rule;
	private final List<String> annotations;

	public ReferenceParameterRequired(String name, boolean multiple, Object defaultValue, int position, ReferenceRule rule, String... annotations) {
		this.name = name;
		this.multiple = multiple;
		this.defaultValue = defaultValue;
		this.position = position;
		this.rule = rule;
		this.annotations = Arrays.asList(annotations);
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
	public boolean multiple() {
		return multiple;
	}

	@Override
	public Object defaultValue() {
		return defaultValue;
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
		return Collections.unmodifiableList(annotations);
	}

	@Override
	public void check(Element element) throws SemanticException {
		if (!(element instanceof Node)) return;
		if (((Node) element).isReference()) return;
		List<? extends tara.lang.model.Parameter> parameters = (element instanceof Facet) ?
			((Facet) element).parameters() :
			((Node) element).parameters();
		if (ConstraintHelper.checkParameterExists(parameters, name(), position) != null) return;
		String type = (element instanceof Facet) ? ((Facet) element).type() : ((Node) element).type();
		throw new SemanticException(new SemanticError("required.parameter", element, Arrays.asList(type, Primitive.REFERENCE.getName(), name)));
	}
}
