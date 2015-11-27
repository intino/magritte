package tara.lang.semantics.constraints.parameter;

import tara.lang.model.Element;
import tara.lang.model.Parameter;
import tara.lang.semantics.errorcollector.SemanticException;

import java.util.List;

abstract class ParameterConstraint {

	protected enum PARAMETER_ERROR {
		TYPE, NOT_FOUND, RULE
	}

	protected PARAMETER_ERROR error = PARAMETER_ERROR.TYPE;

	protected static Parameter findParameter(List<Parameter> parameters, String name, int position) {
		for (Parameter parameter : parameters)
			if (!parameter.name().isEmpty() && parameter.name().equals(name)) return parameter;
		for (Parameter parameter : parameters)
			if (parameter.position() == position && parameter.name().isEmpty()) return parameter;
		return null;
	}

	protected abstract void throwError(Element element, tara.lang.model.Parameter parameter) throws SemanticException;
}
