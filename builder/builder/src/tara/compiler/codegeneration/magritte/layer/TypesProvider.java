package tara.compiler.codegeneration.magritte.layer;

import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.core.CompilerConfiguration;
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

import static tara.compiler.core.CompilerConfiguration.Level.Application;

public final class TypesProvider implements TemplateTags {


	private TypesProvider() {
	}

	static String[] getTypes(Node node, Language language) {
		List<String> types = node.flags().stream().map(Tag::name).collect(Collectors.toList());
		final CompositionRule compositionRule = node.container().ruleOf(node);
		if (compositionRule != null && compositionRule.isSingle()) types.add(SINGLE);
		if (isOverriding(node)) types.add(OVERRIDEN);
		types.addAll(nodeAnnotations(node, language));
		return types.toArray(new String[types.size()]);
	}

	private static boolean isOverriding(Node node) {
		return node.parent() != null &&
			((node.container() instanceof NodeImpl &&
				node.parent().container().equals(node.container().parent())) || node.container().parent() != null && containerContainsParent(node));
	}

	private static boolean containerContainsParent(Node node) {
		boolean contains = node.container().parent().contains(node.parent());
		return contains || hasReference(node.container().parent(), node.parent());
	}

	private static boolean hasReference(Node node, Node component) {
		for (Node candidate : node.components())
			if (candidate.isReference() && candidate.destinyOfReference().equals(component)) return true;
		return false;
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

	private static List<String> nodeAnnotations(Node node, Language language) {
		List<String> annotations = new ArrayList<>();
		List<Assumption> assumptions = language.assumptions(node.type());
		if (assumptions == null) return annotations;
		for (Assumption assumption : assumptions) {
			String name = assumption.getClass().getInterfaces()[0].getSimpleName();
			if (name.endsWith("Instance")) annotations.add(name);
		}
		return annotations;
	}

	public static String[] getTypes(Variable variable, CompilerConfiguration.Level type) {
		Set<String> types = new HashSet<>();
		if (variable.values().isEmpty()) types.add(REQUIRED);
		if (!variable.values().isEmpty() && (variable.values().get(0) instanceof EmptyNode || variable.values().get(0) == null))
			types.add((EMPTY));
		types.add(variable.getClass().getSimpleName());
		if (type.equals(Application)) types.add(TERMINAL);
		types.add(VARIABLE);
		if (variable instanceof VariableReference) {
			types.add(REFERENCE);
			if (variable.flags().contains(Tag.Concept)) types.add(CONCEPT);
		}
		if (variable.type().equals(Primitive.OBJECT)) types.add("objectVariable");
		types.add(variable.type().getName());
		if (Primitive.isJavaPrimitive(variable.type().getName())) types.add(PRIMITIVE);
		if (variable.isInherited()) types.add(INHERITED);
		if (variable.isOverriden()) types.add(OVERRIDEN);
		if (variable.isMultiple()) types.add(MULTIPLE);
		types.addAll(variable.flags().stream().map((tag) -> tag.name().toLowerCase()).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}

	public static String[] getTypes(Constraint.Parameter parameter) {
		Set<String> types = new HashSet<>();
		types.add(parameter.getClass().getSimpleName());
		types.add(VARIABLE);
		if (parameter instanceof ReferenceParameter && !parameter.type().equals(Primitive.WORD)) types.add(REFERENCE);
		types.add(parameter.type().getName());
		if (parameter.size().isRequired() || (parameter.size().into() != null && parameter.size().into().isRequired())) types.add(REQUIRED);
		if (parameter.size().max() > 1) types.add(MULTIPLE);
		types.addAll(parameter.flags().stream().map(Enum::name).collect(Collectors.toList()));
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
