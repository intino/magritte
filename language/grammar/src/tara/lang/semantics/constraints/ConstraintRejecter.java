package tara.lang.semantics.constraints;

import tara.lang.semantics.Constraint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintRejecter {


	public static List<String> componentConstrains(List<Constraint> constraints) {
		Set<String> types = new HashSet<>();
		final List<String> collect = constraints.stream().filter(c -> c instanceof Constraint.Component).map(c -> ((Constraint.Component) c).type()).collect(Collectors.toList());
		final List<List<Constraint.Component>> typeCollection = constraints.stream().filter(c -> c instanceof Constraint.OneOf).map(constraint -> ((Constraint.OneOf) constraint).components()).collect(Collectors.toList());
		for (List<Constraint.Component> components : typeCollection)
			types.addAll(components.stream().map(Constraint.Component::type).collect(Collectors.toList()));
		collect.addAll(types);
		return collect;
	}

	public static List<Constraint.Parameter> parameterConstrains(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList());
	}
}
