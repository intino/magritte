package tara.compiler.codegeneration.magritte.stash;

import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.lang.model.*;
import tara.lang.semantics.Constraint;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.*;

public class StashHelper {

	private static final String BLOB_KEY = "%";


	public static List<String> collectTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(withDollar(node.type()));
		final LinkedHashSet<String> facetTypes = node.facets().stream().map(Facet::type).collect(Collectors.toCollection(LinkedHashSet::new));
		types.addAll(withHashTag(facetTypes.stream().map(type -> (type + node.type()).replace(":", "")).collect(Collectors.toList())));
		return types;
	}

	public static List<String> collectPrototypeTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(withDollar(node.type()));
		if (couldHaveLayer(node)) types.add(withDollar(node.qualifiedNameCleaned()));
		final Set<String> facetTypes = node.facets().stream().map(Facet::type).collect(Collectors.toSet());
		if (couldHaveLayer(node))
			types.addAll(withHashTag(facetTypes.stream().map(type -> (type + node.type()).replace(":", "")).collect(Collectors.toList())));
		return types;
	}

	public static List<String> collectTypes(FacetTarget target, List<Constraint> constraints) {
		final Constraint constraint = constraints.stream().filter(c -> c instanceof Constraint.MetaFacet).findFirst().orElse(null);
		return Collections.singletonList((target.owner().type() + (constraint != null ? target.targetNode().simpleType() : "")).replace(":", ""));
	}

	public static boolean hasToBeConverted(List<Object> values, Primitive type) {
		return ((values.get(0) instanceof String && !(type.equals(STRING))) || type.equals(WORD)) || type.equals(RESOURCE) || type.equals(BOOLEAN);
	}

	public static List<Object> buildResourceValue(List<Object> values, String filePath) {
		return new ArrayList<>(values.stream().
			map(v -> BLOB_KEY + getPresentableName(new File(filePath).getName()) + v.toString()).
			collect(Collectors.toList()));
	}

	public static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}


	public static String buildInstanceReference(Object o) {
		if (o instanceof Primitive.Reference) {
			Primitive.Reference reference = (Primitive.Reference) o;
			return reference.path() + "#" + withDollar(reference.get());
		}
		return "";
	}

	public static boolean isInstance(Node node) {
		return !node.is(Tag.Prototype) && (node.is(Tag.Instance));
	}

	public static List<String> withHashTag(List<String> names) {
		return names.stream().map(name -> name.replace(".", "#")).collect(Collectors.toList());
	}

	public static String withDollar(String name) {
		return name.replace(".", "$").replace(":", "");
	}

	public static boolean couldHaveLayer(Node node) {
		return !node.qualifiedName().contains(Node.ANNONYMOUS);
	}

	public static String getLayerClass(Node node, String generatedLanguage) {
		return node.name() != null && !node.name().isEmpty() ? NameFormatter.getJavaQN(generatedLanguage, node) : null;
	}
}
