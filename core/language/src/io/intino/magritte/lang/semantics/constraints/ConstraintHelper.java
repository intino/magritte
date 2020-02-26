package io.intino.magritte.lang.semantics.constraints;

import io.intino.magritte.lang.semantics.Constraint;

import java.util.*;
import java.util.stream.Collectors;

public class ConstraintHelper {


	public static List<String> componentConstrains(List<Constraint> constraints) {
		Set<String> types = new HashSet<>();
		if (constraints == null) return Collections.emptyList();
		types.addAll(components(constraints));
		for (List<Constraint.Component> components : componentsOfOneOf(constraints))
			types.addAll(components.stream().map(Constraint.Component::type).collect(Collectors.toList()));
		for (Constraint.Aspect aspect : constraints.stream().filter(c -> c instanceof Constraint.Aspect).map(c -> (Constraint.Aspect) c).collect(Collectors.toList())) {
			types.addAll(aspectComponents(aspect.constraints()));
			for (List<Constraint.Component> components : componentsOfOneOf(aspect.constraints())) types.addAll(typesOf((components)));
		}
		return new ArrayList<>(types);
	}

	private static List<List<Constraint.Component>> componentsOfOneOf(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.OneOf).map(constraint -> ((Constraint.OneOf) constraint).components()).collect(Collectors.toList());
	}

	private static List<String> components(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.Component && !((Constraint.Component) c).type().isEmpty()).map(c -> ((Constraint.Component) c).type()).collect(Collectors.toList());
	}

	private static List<String> aspectComponents(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.Component && !((Constraint.Component) c).type().isEmpty()).map(c -> ((Constraint.Component) c).type()).collect(Collectors.toList());
	}

	private static List<String> typesOf(List<Constraint.Component> constraints) {
		return constraints.stream().filter(c -> c != null && !c.type().isEmpty()).map(Constraint.Component::type).collect(Collectors.toList());
	}

	public static List<Constraint.Parameter> parameterConstrains(List<Constraint> constraints) {
		final List<Constraint.Parameter> parameters = filterParameters(constraints);
		for (Constraint.Aspect aspect : facetConstrains(constraints))
			parameters.addAll(filterParameters(aspect.constraints()));
		return parameters;
	}

	private static List<Constraint.Parameter> filterParameters(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList());
	}

	public static List<Constraint.Aspect> facetConstrains(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.Aspect).map(c -> (Constraint.Aspect) c).collect(Collectors.toList());
	}
}
