package io.intino.tara.lang.semantics.constraints.parameter;

import io.intino.tara.lang.model.Element;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.model.Node;

import java.util.List;

abstract class ParameterConstraint implements Constraint.Parameter {

	enum ParameterError {
		TYPE, NOT_FOUND, RECOVERABLE_ERROR, RULE, SIZE
	}

	protected ParameterError error = ParameterError.TYPE;

	static io.intino.tara.lang.model.Parameter findParameter(List<io.intino.tara.lang.model.Parameter> parameters, String facet, String name, int position) {
		for (io.intino.tara.lang.model.Parameter parameter : parameters)
			if (!parameter.name().isEmpty() && parameter.name().equals(name)) return parameter;
		return byPosition(parameters, facet, position);
	}

	private static io.intino.tara.lang.model.Parameter byPosition(List<io.intino.tara.lang.model.Parameter> parameters, String facet, int position) {
		for (io.intino.tara.lang.model.Parameter parameter : parameters) {
			if (parameter.name().isEmpty() && parameter.aspect().equals(facet) && parameter.position() == position)
				return parameter;
		}
		return null;
	}

	boolean isNotAbstractNode(Element element) {
		return element instanceof Node && !((Node) element).isAbstract() && !isInherited((Node) element);
	}

	private boolean isInherited(Node node) {
		Node parent = node.parent();
		while (parent != null) {
			final io.intino.tara.lang.model.Parameter parameter = findParameter(node.parent().parameters(), "", name(), position());
			if (parameter != null) return true;
			parent = node.parent();
		}
		return false;
	}


	protected abstract void error(Element element, io.intino.tara.lang.model.Parameter parameter, ParameterError errorType) throws SemanticException;
}
