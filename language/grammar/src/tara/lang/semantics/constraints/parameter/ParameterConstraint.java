package tara.lang.semantics.constraints.parameter;

import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticException;

import java.util.List;

abstract class ParameterConstraint implements Constraint.Parameter {

	enum ParameterError {
		TYPE, NOT_FOUND, RULE
	}

	protected ParameterError error = ParameterError.TYPE;

	static tara.lang.model.Parameter findParameter(List<tara.lang.model.Parameter> parameters, String facet, String name, int position) {
		for (tara.lang.model.Parameter parameter : parameters)
			if (!parameter.name().isEmpty() && parameter.name().equals(name)) return parameter;
		for (tara.lang.model.Parameter parameter : parameters) {
			if (parameter.name().isEmpty() && parameter.facet().equals(facet) && parameter.position() == position)
				return parameter;
		}
		return null;
	}

	boolean isNotAbstractNode(Element element) {
		return element instanceof Node && !((Node) element).isAbstract() && !isInherited((Node) element);
	}

	protected boolean isInherited(Node node) {
		Node parent = node.parent();
		while (parent != null) {
			final tara.lang.model.Parameter parameter = findParameter(node.parent().parameters(), "", name(), position());
			if (parameter != null) return true;
			parent = node.parent();
		}
		return false;
	}


	protected abstract void error(Element element, tara.lang.model.Parameter parameter, ParameterError errorType) throws SemanticException;
}
