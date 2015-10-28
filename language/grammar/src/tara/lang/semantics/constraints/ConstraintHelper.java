package tara.lang.semantics.constraints;

import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Tag;
import tara.lang.semantics.Rejectable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConstraintHelper {

	private ConstraintHelper() {
	}

	public static List<Rejectable.Include> getRejectableIncludesBy(String type, List<? extends Rejectable> rejectables) {
		List<Rejectable.Include> rejectableIncludes = new ArrayList<>();
		for (Rejectable rejectable : rejectables) {
			if (!(rejectable instanceof Rejectable.Include)) continue;
			Rejectable.Include include = (Rejectable.Include) rejectable;
			if (checkType(type, include.getNode()) || checkFacets(type, include))
				rejectableIncludes.add(include);
		}
		return rejectableIncludes;
	}

	private static boolean checkFacets(String type, Rejectable.Include rejectable) {
		for (Facet facet : rejectable.getNode().facets())
			if (facet.type().equals(shortType(type))) return true;
		return false;
	}

	private static boolean checkType(String type, Node node) {
		return node != null && areCompatibles(node, type);
	}

	private static boolean areCompatibles(Node node, String type) {
		for (String nodeType : node.types())
			if (nodeType != null && nodeType.equals(type)) return true;
		return false;
	}


	public static String shortType(String absoluteType) {
		return absoluteType.contains(".") ? absoluteType.substring(absoluteType.lastIndexOf('.') + 1) : absoluteType;
	}

	public static Parameter checkParameterExists(List<? extends Parameter> parameters, String name, int position) {
		List<Parameter> signatureParameters = new ArrayList<>();
		for (Parameter parameter : parameters)
			if (name.equals(parameter.name())) return parameter;
			else if (!parameter.isVariableInit()) signatureParameters.add(parameter);
		removeNamedCandidates(signatureParameters);
		return signatureParameters.size() > position ? signatureParameters.get(position) : null;
	}

	public static void removeNamedCandidates(List<Parameter> signatureParameters) {
		List<Parameter> toRemove = signatureParameters.stream().filter(parameter -> parameter.name() != null && !parameter.name().isEmpty()).collect(Collectors.toList());
		remove(signatureParameters, toRemove);
	}

	public static void remove(List<Parameter> signatureParameters, List<Parameter> toRemove) {
		toRemove.forEach(signatureParameters::remove);
	}


	public static void addFlagsAndAnnotations(List<Rejectable.Include> includes, Tag[] tags) {
		for (Rejectable.Include include : includes)
			addFlags(include.getNode(), tags);
	}

	public static void addFlags(Node node, Tag[] tags) {
		List<Tag> flags = new ArrayList<>(node.flags());
		for (Tag flag : tags) {
			if (!flags.contains(flag)) node.addFlag(flag);
			flags.add(flag);
		}
	}

}
