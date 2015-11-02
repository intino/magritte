package tara.lang.semantics.constraints.parameter;

import tara.lang.model.Parameter;

import java.util.List;

abstract class ParameterConstraint {

	protected static Parameter findParameter(List<Parameter> parameters, String name, int position) {
		for (Parameter parameter : parameters)
			if (!parameter.name().isEmpty() && parameter.name().equals(name)) return parameter;
		for (Parameter parameter : parameters)
			if (parameter.position() == position && parameter.name().isEmpty()) return parameter;
		return null;
	}
}
