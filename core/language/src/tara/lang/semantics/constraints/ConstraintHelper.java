package tara.lang.semantics.constraints;

import tara.dsl.ProteoConstants;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Constraint.Component;

import java.util.*;
import java.util.stream.Collectors;

public class ConstraintHelper {


	public static List<String> componentConstrains(List<Constraint> constraints) {
		Set<String> types = new HashSet<>();
		if (constraints == null) return Collections.emptyList();
		types.addAll(components(constraints));
		for (List<Component> components : componentsOfOneOf(constraints))
			types.addAll(components.stream().map(Component::type).collect(Collectors.toList()));
		for (Constraint.Facet facet : constraints.stream().filter(c -> c instanceof Constraint.Facet).map(c -> (Constraint.Facet) c).collect(Collectors.toList())) {
			types.addAll(facetComponents(facet.type(), facet.constraints()));
			for (List<Component> components : componentsOfOneOf(facet.constraints())) types.addAll(typesOf((components)));
		}
		return new ArrayList<>(types);
	}

	private static List<List<Component>> componentsOfOneOf(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.OneOf).map(constraint -> ((Constraint.OneOf) constraint).components()).collect(Collectors.toList());
	}

	private static List<String> components(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Component && !((Component) c).type().isEmpty()).map(c -> ((Component) c).type()).collect(Collectors.toList());
	}

	private static List<String> facetComponents(String facet, List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Component && !((Component) c).type().isEmpty()).map(c -> facet + ProteoConstants.FACET_SEPARATOR + ((Component) c).type()).collect(Collectors.toList());
	}

	private static List<String> typesOf(List<Component> constraints) {
		return constraints.stream().filter(c -> c != null && !c.type().isEmpty()).map(Component::type).collect(Collectors.toList());
	}

	public static List<Constraint.Parameter> parameterConstrains(List<Constraint> constraints) {
		final List<Constraint.Parameter> parameters = filterParameters(constraints);
		for (Constraint.Facet facet : facetConstrains(constraints))
			parameters.addAll(filterParameters(facet.constraints()));
		return parameters;
	}

	private static List<Constraint.Parameter> filterParameters(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList());
	}

	public static List<Constraint.Facet> facetConstrains(List<Constraint> constraints) {
		return constraints.stream().filter(c -> c instanceof Constraint.Facet).map(c -> (Constraint.Facet) c).collect(Collectors.toList());
	}
}
