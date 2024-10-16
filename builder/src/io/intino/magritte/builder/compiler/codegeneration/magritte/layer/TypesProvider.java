package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.language.semantics.Assumption;
import io.intino.tara.language.semantics.Constraint;
import io.intino.tara.model.*;
import io.intino.tara.model.rules.Size;
import io.intino.tara.processors.model.MogramImpl;
import io.intino.tara.processors.model.ReferenceProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class TypesProvider implements TemplateTags {


	private TypesProvider() {
	}

	static List<String> getTypes(Mogram mogram, Language language) {
		List<String> types = mogram.annotations().stream().map(Annotation::name).collect(Collectors.toList());
		types.add("Node");
		if (mogram instanceof MogramImpl) types.add("NodeImpl");
		final Size size = mogram.container().sizeOf(mogram);
		if (size != null && size.isSingle()) types.add(SINGLE);
		if (isOverriding(mogram)) types.add(OVERRIDEN);
		types.addAll(nodeAnnotations(mogram, language));
		return types;
	}

	private static boolean isOverriding(Mogram mogram) {
		return mogram.parent() != null && (mogram.container() instanceof Mogram c &&
				(c.parent() != null && mogram.parent().get().container().equals(c.parent().get()) || c.parent() != null && containerContainsParent(mogram)));
	}

	private static boolean containerContainsParent(Mogram mogram) {
		if (!(mogram.container() instanceof Mogram m)) return false;
		boolean contains = m.parent().get().contains(mogram.parent().get());
		return contains || hasReference(m.parent().get(), mogram.parent().get());
	}

	private static boolean hasReference(Mogram node, Mogram component) {
		for (Mogram candidate : node.components())
			if (candidate instanceof MogramReference r && r.target().equals(component)) return true;
		return false;
	}

	static String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add(ASPECT);
		list.add(facet.type());
		return list.toArray(new String[0]);
	}

	private static List<String> nodeAnnotations(Mogram node, Language language) {
		List<String> annotations = new ArrayList<>();
		List<Assumption> assumptions = language.assumptions(node.types().get(0));
		if (assumptions == null) return annotations;
		for (Assumption assumption : assumptions) {
			String name = assumption.getClass().getInterfaces()[0].getSimpleName();
			if (name.endsWith("Instance")) annotations.add(name);
		}
		return annotations;
	}

	public static Set<String> getTypes(Property prop) {
		Set<String> types = new HashSet<>();
		if (prop.values().isEmpty()) types.add(REQUIRED);
		if (!prop.values().isEmpty() && (prop.values().get(0) instanceof EmptyMogram || prop.values().get(0) == null))
			types.add((EMPTY));
		types.add(prop.getClass().getSimpleName());
		types.add(PROPERTY);
		if (prop instanceof ReferenceProperty) {
			types.add(REFERENCE);
		}
		if (prop.type().equals(Primitive.OBJECT)) types.add("objectVariable");
		types.add(prop.type().getName());
		if (Primitive.isJavaPrimitive(prop.type().getName())) types.add(PRIMITIVE);
//		if (prop.isInherited()) types.add(INHERITED);TODO
//		if (prop.isOverridden()) types.add(OVERRIDEN);
		if (prop.isMultiple()) types.add(MULTIPLE);
		types.addAll(prop.annotations().stream().map((tag) -> tag.name().toLowerCase()).toList());
		return types;
	}

	public static String[] getTypes(Constraint.Property parameter, boolean isRequired) {
		Set<String> types = new HashSet<>();
		types.add(parameter.getClass().getSimpleName());
		types.add(PROPERTY);
		if (parameter.type().equals(Primitive.REFERENCE) && !parameter.type().equals(Primitive.WORD))
			types.add(REFERENCE);
		types.add(parameter.type().getName());
//		if (isRequired && parameter.annotations().contains(Annotation.Terminal))
//			types.add(REQUIRED);TODO
		if (parameter.rules().stream().filter(r -> r instanceof Size).map(s -> (Size) s).findFirst().get().max() > 1)
			types.add(MULTIPLE);
		types.addAll(parameter.annotations().stream().map(Enum::name).toList());
		return types.toArray(new String[0]);
	}

	public static String[] getTypes(PropertyDescription parameter) {
		Set<String> types = new HashSet<>();
		types.add(parameter.getClass().getSimpleName());
		types.add(PROPERTY);
		types.add(PARAMETER);
		types.add(parameter.type().getName());
		if (parameter.values().size() > 1) types.add(MULTIPLE);
		types.addAll(parameter.definition().annotations().stream().map(Enum::name).toList());
		return types.toArray(new String[0]);
	}

}
