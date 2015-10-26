package tara.lang.semantics.constraints.required;

import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Tag;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.constraints.ConstraintHelper;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticException;

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
