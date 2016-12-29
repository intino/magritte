package io.intino.tara.compiler.codegeneration.magritte.stash;

import io.intino.tara.lang.model.Facet;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.semantics.Constraint;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static io.intino.tara.lang.model.Primitive.*;

class StashHelper {

	private static final String BLOB_KEY = "%";


	static List<String> collectTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(withDollarAndHashtag(node.type()));
		final LinkedHashSet<String> facetTypes = node.facets().stream().map(Facet::type).collect(toCollection(LinkedHashSet::new));
		types.addAll(facetTypes.stream().map(type -> withDollarAndHashtag(type + "#" + node.type())).collect(toList()));
		return types;
	}

	static List<String> collectTypes(FacetTarget target, List<Constraint> constraints) {
		final Constraint constraint = constraints.stream().filter(c -> c instanceof Constraint.MetaFacet).findFirst().orElse(null);
		final LinkedHashSet<String> facetTypes = new LinkedHashSet<>();
		facetTypes.add((target.owner().type() + (constraint instanceof Constraint.MetaFacet || constraint == null ? "" : target.targetNode().simpleType())).
			replace(":", "#"));
		facetTypes.addAll(target.owner().facets().stream().map(f -> f.type() + "#" + shortType(target)).collect(toCollection(LinkedHashSet::new)));
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
			return reference.path() + "#" + withDollarAndHashtag(reference.get());
		}
		return "";
	}


	private static String withDollarAndHashtag(String name) {
		return name.replace(".", "$").replace(":", "#");
	}
}
