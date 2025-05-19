package io.intino.magritte.builder.compiler.codegeneration.magritte.stash;

import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.model.Mogram;
import io.intino.tara.model.Primitive;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

import static io.intino.tara.builder.utils.Format.noPackage;
import static io.intino.tara.builder.utils.Format.withDollar;
import static io.intino.tara.model.Primitive.*;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class StashHelper {
	private static final String BLOB_KEY = "%";

	static List<String> collectTypes(Mogram mogram) {
		List<String> types = new ArrayList<>();
		types.addAll(mogram.metaMograms().stream().map(NameFormatter::layerQualifiedName).toList());
		types.addAll(mogram.appliedFacets().stream().map(facet -> NameFormatter.layerQualifiedName(facet.definition().get())).collect(toCollection(LinkedHashSet::new)));
		return types.stream()
				.filter(Objects::nonNull)
				.distinct()
				.collect(toList());
	}

	public static String name(io.intino.tara.model.Mogram owner, String workingPackage) {
		return withDollar().format(noPackage().format(NameFormatter.getQn(owner, workingPackage))).toString();
	}

	static boolean hasToBeConverted(List<Object> values, Primitive type) {
		if ((values.get(0) instanceof String && !(type.equals(STRING))) || type.equals(WORD)) return true;
		if (type.equals(INSTANT) || type.equals(RESOURCE)) return true;
		return type.equals(LONG) && values.get(0) instanceof Integer;
	}

	static List<Object> buildResourceValue(List<Object> values, String filePath) {
		return values.stream().
				map(v -> BLOB_KEY + getPresentableName(new File(filePath).getName()) + v.toString()).collect(toList());
	}

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	static String buildInstanceReference(Object o) {
		return o instanceof Reference reference ?
				getPath(reference) + "#" + NameFormatter.layerQualifiedName(reference.get().get()) :
				"";
	}

	private static String getPath(Reference reference) {
		String source = new File(reference.get().get().source()).getName();
		return source.substring(0, source.lastIndexOf("."));
	}
}
