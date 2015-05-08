package siani.tara.semantic.constraints;

import siani.tara.semantic.Rejectable;

import java.util.ArrayList;
import java.util.List;

abstract class ParameterAllow {

	protected static Rejectable.Parameter findParameter(List<? extends Rejectable> rejectables, String name, int position) {
		List<Rejectable.Parameter> parameters = getRejectableParameters(rejectables);
		for (Rejectable.Parameter parameter : parameters)
			if (!parameter.getParameter().getName().isEmpty() && parameter.getParameter().getName().equals(name))
				return parameter;
		for (Rejectable.Parameter parameter : parameters)
			if (parameter.getParameter().getPosition() == position && parameter.getParameter().getName().isEmpty())
				return parameter;
		return null;
	}


	protected static List<Rejectable.Parameter> getRejectableParameters(List<? extends Rejectable> rejectables) {
		List<Rejectable.Parameter> parameters = new ArrayList<>();
		for (Rejectable rejectable : rejectables)
			if (rejectable instanceof Rejectable.Parameter)
				parameters.add((Rejectable.Parameter) rejectable);
		return parameters;
	}
}
