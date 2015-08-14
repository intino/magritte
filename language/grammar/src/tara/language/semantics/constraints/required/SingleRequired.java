package tara.language.semantics.constraints.required;

import tara.language.model.Element;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Tag;
import tara.language.semantics.Constraint;
import tara.language.semantics.SemanticError;
import tara.language.semantics.SemanticException;
import tara.language.semantics.constraints.ConstraintHelper;

import java.util.Arrays;

import static java.util.Arrays.asList;

public class SingleRequired implements Constraint.Require.Single {
	private final String type;
	private final Tag[] annotations;

	public SingleRequired(String type, Tag... annotations) {
		this.type = type;
		this.annotations = annotations;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public Tag[] annotations() {
		return Arrays.copyOf(annotations, annotations.length);
	}

	@Override
	public void check(Element element) throws SemanticException {
		NodeContainer container = (NodeContainer) element;
		if (container instanceof Node && ((Node) container).isReference()) return;
		for (Node inner : container.components())
			if (inner.type().equals(type)) {
				ConstraintHelper.addFlags(inner, annotations);
				return;
			}
		throw new SemanticException(new SemanticError("required.type.in.context", container, asList(type, container.type())));
	}
}
