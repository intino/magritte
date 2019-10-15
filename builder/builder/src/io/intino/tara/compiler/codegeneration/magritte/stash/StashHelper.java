package io.intino.tara.compiler.codegeneration.magritte.stash;

import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.lang.model.Aspect;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.semantics.Assumption;
import io.intino.tara.lang.semantics.Constraint;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

import static io.intino.tara.compiler.codegeneration.Format.noPackage;
import static io.intino.tara.compiler.codegeneration.Format.withDollar;
import static io.intino.tara.lang.model.Primitive.*;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class StashHelper {
	private static final String BLOB_KEY = "%";

	static List<String> collectTypes(Node node, Language language) {
		List<String> types = new ArrayList<>();
		types.add(node.type());
		types.addAll(node.appliedAspects().stream().map(Aspect::fullType).collect(toCollection(LinkedHashSet::new)));
		if (node.isAspect()) {
			Constraint.MetaAspect constraint = (Constraint.MetaAspect) language.constraints(node.type()).stream().filter(c -> c instanceof Constraint.MetaAspect).findFirst().orElse(null);
			if (constraint != null) types.add(constraint.type());
		}
		return types.stream().map(type -> {
			Assumption.StashNodeName nodeName = (Assumption.StashNodeName) language.assumptions(type).stream().
					filter(a -> a instanceof Assumption.StashNodeName).findFirst().orElse(null);
			return nodeName != null ? nodeName.stashNodeName() : null;
		}).filter(Objects::nonNull).collect(toList());
	}

	public static String name(io.intino.tara.lang.model.Node owner, String workingPackage) {
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
