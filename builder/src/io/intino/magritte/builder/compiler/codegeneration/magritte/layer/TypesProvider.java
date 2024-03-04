package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.builder.core.CompilerConfiguration.Level;
import io.intino.tara.builder.model.MogramImpl;
import io.intino.tara.builder.model.VariableReference;
import io.intino.tara.language.model.*;
import io.intino.tara.language.model.rules.Size;
import io.intino.tara.language.semantics.Assumption;
import io.intino.tara.language.semantics.Constraint;
import io.intino.tara.language.semantics.constraints.parameter.ReferenceParameter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class TypesProvider implements TemplateTags {


	private TypesProvider() {
	}

	static List<String> getTypes(Mogram mogram, Language language) {
		List<String> types = mogram.flags().stream().map(Tag::name).collect(Collectors.toList());
		types.add("Node");
		if (mogram instanceof MogramImpl) types.add("NodeImpl");
		final Size size = mogram.container().sizeOf(mogram);
		if (size != null && size.isSingle()) types.add(SINGLE);
		if (isOverriding(mogram)) types.add(OVERRIDEN);
		types.addAll(nodeAnnotations(mogram, language));
		return types;
	}

	private static boolean isOverriding(Mogram node) {
		return node.parent() != null &&
				((node.container() instanceof MogramImpl &&
						node.parent().container().equals(node.container().parent())) || node.container().parent() != null && containerContainsParent(node));
	}

	private static boolean containerContainsParent(Mogram node) {
		boolean contains = node.container().parent().contains(node.parent());
		return contains || hasReference(node.container().parent(), node.parent());
	}

	private static boolean hasReference(Mogram node, Mogram component) {
		for (Mogram candidate : node.components())
			if (candidate.isReference() && candidate.targetOfReference().equals(component)) return true;
		return false;
	}

	static String[] getTypes(Facet aspect) {
		List<String> list = new ArrayList<>();
		list.add(ASPECT);
		list.add(aspect.type());
		return list.toArray(new String[0]);
	}

	private static List<String> nodeAnnotations(Mogram node, Language language) {
		List<String> annotations = new ArrayList<>();
		List<Assumption> assumptions = language.assumptions(node.type());
		if (assumptions == null) return annotations;
		for (Assumption assumption : assumptions) {
			String name = assumption.getClass().getInterfaces()[0].getSimpleName();
			if (name.endsWith("Instance")) annotations.add(name);
		}
		return annotations;
	}

	public static Set<String> getTypes(Variable variable, Level type) {
		Set<String> types = new HashSet<>();
		if (variable.values().isEmpty()) types.add(REQUIRED);
		if (!variable.values().isEmpty() && (variable.values().get(0) instanceof EmptyMogram || variable.values().get(0) == null))
			types.add((EMPTY));
		types.add(variable.getClass().getSimpleName());
		if (type.equals(Level.MetaModel)) types.add(TERMINAL);
		types.add(VARIABLE);
		if (variable instanceof VariableReference) {
			types.add(REFERENCE);
			if (variable.flags().contains(Tag.Concept)) types.add(CONCEPT);
		}
		if (variable.type().equals(Primitive.OBJECT)) types.add("objectVariable");
		types.add(variable.type().getName());
		if (Primitive.isJavaPrimitive(variable.type().getName())) types.add(PRIMITIVE);
		if (variable.isInherited()) types.add(INHERITED);
		if (variable.isOverridden()) types.add(OVERRIDEN);
		if (variable.isMultiple()) types.add(MULTIPLE);
		types.addAll(variable.flags().stream().map((tag) -> tag.name().toLowerCase()).toList());
		return types;
	}

	public static String[] getTypes(Constraint.Parameter parameter, boolean isRequired) {
		Set<String> types = new HashSet<>();
		types.add(parameter.getClass().getSimpleName());
		types.add(VARIABLE);
		if (parameter instanceof ReferenceParameter && !parameter.type().equals(Primitive.WORD)) types.add(REFERENCE);
		types.add(parameter.type().getName());
		if (isRequired && parameter.flags().contains(Tag.Terminal))
			types.add(REQUIRED);
		if (parameter.size().max() > 1) types.add(MULTIPLE);
		types.addAll(parameter.flags().stream().map(Enum::name).toList());
		return types.toArray(new String[0]);
	}

	public static String[] getTypes(Parameter parameter) {
		Set<String> types = new HashSet<>();
		types.add(parameter.getClass().getSimpleName());
		types.add(VARIABLE);
		types.add(PARAMETER);
		types.add(parameter.type().getName());
		if (parameter.values().size() > 1) types.add(MULTIPLE);
		types.addAll(parameter.flags().stream().map(Enum::name).toList());
		return types.toArray(new String[0]);
	}

}
