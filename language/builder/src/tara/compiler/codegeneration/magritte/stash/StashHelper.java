package tara.compiler.codegeneration.magritte.stash;

import tara.lang.model.*;
import tara.lang.semantics.Constraint;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.*;
import static tara.compiler.codegeneration.magritte.NameFormatter.getStashQn;
import static tara.lang.model.Primitive.*;

class StashHelper {

	private static final String BLOB_KEY = "%";


	static List<String> collectTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(withDollar(node.type()));
		final LinkedHashSet<String> facetTypes = node.facets().stream().map(Facet::type).collect(toCollection(LinkedHashSet::new));
		types.addAll(facetTypes.stream().map(type -> withDollar(type + "#" + node.type())).collect(toList()));
		return types;
	}

	static List<String> collectPrototypeTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(withDollar(node.type()));
		if (couldHaveLayer(node)) types.add(withDollar(node.qualifiedNameCleaned()));
		final Set<String> facetTypes = node.facets().stream().map(Facet::type).collect(toSet());
		if (couldHaveLayer(node)) types.addAll(facetTypes.stream().map(type -> withDollar(type + "#" + node.type())).collect(toList()));
		return types;
	}

	static List<String> collectTypes(FacetTarget target, List<Constraint> constraints) {
		final Constraint constraint = constraints.stream().filter(c -> c instanceof Constraint.MetaFacet).findFirst().orElse(null);
		final LinkedHashSet<String> facetTypes = target.owner().facets().stream().map(f -> f.type() + shortType(target)).collect(toCollection(LinkedHashSet::new));
		facetTypes.add((target.owner().type() + (constraint != null ? target.targetNode().simpleType() : "")).replace(":", ""));
		return new ArrayList<>(facetTypes);
	}

	private static String shortType(FacetTarget target) {
		final String type = target.owner().type();
		return type.contains(":") ? type.substring(0, type.indexOf(":")) : type;
	}

	static boolean hasToBeConverted(List<Object> values, Primitive type) {
		return ((values.get(0) instanceof String && !(type.equals(STRING))) || type.equals(WORD)) || type.equals(RESOURCE) || type.equals(BOOLEAN);
	}

	static List<Object> buildResourceValue(List<Object> values, String filePath) {
		return new ArrayList<>(values.stream().
			map(v -> BLOB_KEY + getPresentableName(new File(filePath).getName()) + v.toString()).
			collect(toList()));
	}

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}


	static String buildInstanceReference(Object o) {
		if (o instanceof Primitive.Reference) {
			Primitive.Reference reference = (Primitive.Reference) o;
			return reference.path() + "#" + withDollar(reference.get());
		}
		return "";
	}

	static boolean isInstance(Node node) {
		return !node.is(Tag.Prototype) && (node.is(Tag.Instance));
	}

	private static String withDollar(String name) {
		return name.replace(".", "$").replace(":", "");
	}

	static boolean couldHaveLayer(Node node) {
		return !node.qualifiedName().contains(Node.ANONYMOUS);
	}

	static String getLayerClass(Node node, String outDsl) {
		return node.name() != null && !node.name().isEmpty() ? getStashQn(node, outDsl) : null;
	}
}
