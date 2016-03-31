package tara.compiler.codegeneration.magritte.layer;

import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.VariableReference;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class TypesProvider implements TemplateTags {


	private TypesProvider() {
	}

	static String[] getTypes(Node node, Language language) {
		List<String> types = node.flags().stream().map(Tag::name).collect(Collectors.toList());
		final CompositionRule compositionRule = node.container().ruleOf(node);
		if (compositionRule != null && compositionRule.isSingle()) types.add(SINGLE);
		if (overrides(node)) types.add(OVERRIDEN);
		types.addAll(instanceAnnotations(node, language));
		return types.toArray(new String[types.size()]);
	}

	private static boolean overrides(Node node) {
		return node.parent() != null &&
			node.container() instanceof NodeImpl &&
			node.parent().container().equals(((NodeImpl) node.container()).parent());
	}

	static String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add(FACET);
		list.add(facet.type());
		return list.toArray(new String[list.size()]);
	}

	static String[] getTypes(FacetTarget facetTarget) {
		List<String> list = new ArrayList<>();
		list.add(FACET_TARGET);
		if (facetTarget.targetNode().qualifiedName() != null)
			list.add(facetTarget.targetNode().qualifiedName());
		return list.toArray(new String[list.size()]);
	}

	private static List<String> instanceAnnotations(Node node, Language language) {
		List<String> instances = new ArrayList<>();
		List<Assumption> assumptions = language.assumptions(node.type());
		if (assumptions == null) return instances;
		for (Assumption assumption : assumptions) {
			String name = assumption.getClass().getInterfaces()[0].getSimpleName();
			if (name.endsWith("Instance")) instances.add(name);
		}
		return instances;
	}

	public static String[] getTypes(Variable variable, int level) {
		Set<String> types = new HashSet<>();
		types.add(variable.getClass().getSimpleName());
		if (level == 1) types.add(TERMINAL);
		types.add(VARIABLE);
		if (variable instanceof VariableReference) {
			types.add(REFERENCE);
			if (variable.flags().contains(Tag.Concept)) types.add(CONCEPT);
		}
		if (variable.type().equals(Primitive.OBJECT))
			types.add("objectVariable");
		types.add(variable.type().getName());
		if (Primitive.isJavaPrimitive(variable.type().getName())) types.add(PRIMITIVE);
		if (variable.isInherited()) types.add(INHERITED);
		if (variable.isOverriden()) types.add(OVERRIDEN);
		if (variable.isMultiple()) types.add(MULTIPLE);
		types.addAll(variable.flags().stream().map(Tag::name).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}

	public static String[] getTypes(Constraint.Parameter variable) {
		Set<String> types = new HashSet<>();
		types.add(variable.getClass().getSimpleName());
		types.add(VARIABLE);
		if (variable instanceof ReferenceParameter && !variable.type().equals(Primitive.WORD)) types.add(REFERENCE);
		types.add(variable.type().getName());
		if (variable.size().max() > 1) types.add(MULTIPLE);
		types.addAll(variable.flags().stream().map(Enum::name).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}

	public static String[] getTypes(Parameter parameter) {
		Set<String> types = new HashSet<>();
		types.add(parameter.getClass().getSimpleName());
		types.add(VARIABLE);
		types.add(PARAMETER);
		types.add(parameter.type().getName());
		if (parameter.values().size() > 1) types.add(MULTIPLE);
		types.addAll(parameter.flags().stream().map(Enum::name).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}

}
