package tara.lang.semantics.constraints.required;

import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Tag;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;
import tara.lang.semantics.constraints.ConstraintHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class Required implements Constraint.Has.Component {
	private final String type;
	private final Size size;
	private final List<Tag> annotations;

	public Required(String type, Size size, Tag... annotations) {
		this.type = type;
		this.size = size;
		this.annotations = Arrays.asList(annotations);
	}

	@Override
	public String type() {
		return this.type;
	}

	@Override
	public Size size() {
		return this.size;
	}

	@Override
	public List<Tag> annotations() {
		return Collections.unmodifiableList(annotations);
	}

	@Override
	public void check(Element element) throws SemanticException {
		NodeContainer container = (NodeContainer) element;
		if (container instanceof Node && ((Node) container).isReference()) return;
		for (Node include : container.components())
			if (include.type().equals(type)) {
				ConstraintHelper.addFlags(include, annotations);
				return;
			}
		throw new SemanticException(new SemanticError("required.type.in.context", container, asList(type, container.type().isEmpty() ? "Root" : container.type())));
	}
}
