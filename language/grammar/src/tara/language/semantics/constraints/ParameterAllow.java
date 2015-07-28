package tara.language.semantics.constraints;

import tara.language.semantics.Rejectable;

import java.util.List;
import java.util.stream.Collectors;

abstract class ParameterAllow {

	protected static Rejectable.Parameter findParameter(List<? extends Rejectable> rejectables, String name, int position) {
		List<Rejectable.Parameter> parameters = getRejectableParameters(rejectables);
		for (Rejectable.Parameter parameter : parameters)
			if (!parameter.getParameter().name().isEmpty() && parameter.getParameter().name().equals(name))
				return parameter;
		for (Rejectable.Parameter parameter : parameters)
			if (parameter.getParameter().position() == position && parameter.getParameter().name().isEmpty())
				return parameter;
		return null;
	}


	protected static List<Rejectable.Parameter> getRejectableParameters(List<? extends Rejectable> rejectables) {
		return rejectables.stream().
			filter(rejectable -> rejectable instanceof Rejectable.Parameter).
			map(rejectable -> (Rejectable.Parameter) rejectable).collect(Collectors.toList());
	}
}
