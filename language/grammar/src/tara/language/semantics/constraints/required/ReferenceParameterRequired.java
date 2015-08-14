package tara.language.semantics.constraints.required;

import tara.language.model.Element;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.semantics.Constraint;
import tara.language.semantics.SemanticError;
import tara.language.semantics.SemanticException;
import tara.language.semantics.constraints.ConstraintHelper;

import java.util.List;

import static java.util.Arrays.asList;

public class ReferenceParameterRequired implements Constraint.Require.Parameter {
	private final String name;
	private final boolean multiple;
	private final String[] values;
	private final int position;
	private final String metric;
	private final String[] annotations;

	public ReferenceParameterRequired(String name, boolean multiple, String[] values, int position, String metric, String... annotations) {
		this.name = name;
		this.multiple = multiple;
		this.values = values;
		this.position = position;
		this.metric = metric;
		this.annotations = annotations;
	}

	@Override
	public String name() {
		return name.endsWith(":word") ? name.replace(":word", "") : name;
	}

	@Override
	public String type() {
		return name.endsWith(":word") ? "word" : "reference";
	}

	@Override
	public boolean multiple() {
		return multiple;
	}

	@Override
	public String[] allowedValues() {
		return values;
	}

	@Override
	public int position() {
		return position;
	}

	@Override
	public String metric() {
		return metric;
	}

	@Override
	public String[] annotations() {
		return annotations;
	}

	@Override
	public void check(Element element) throws SemanticException {
		if (!(element instanceof Node)) return;
		if (((Node) element).isReference()) return;
		List<? extends tara.language.model.Parameter> parameters = (element instanceof Facet) ?
			((Facet) element).parameters() :
			((Node) element).parameters();
		if (ConstraintHelper.checkParameterExists(parameters, name(), position)) return;
		String type = (element instanceof Facet) ? ((Facet) element).type() : ((Node) element).type();
		throw new SemanticException(new SemanticError("required.parameter", element, asList(type, "word", name)));
	}
}
