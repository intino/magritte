package tara.compiler.codegeneration.magritte.morph;

import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.VariableReference;
import tara.language.model.*;
import tara.language.semantics.Allow;
import tara.language.semantics.Assumption;
import tara.language.semantics.constraints.allowed.ReferenceParameterAllow;

import java.util.*;
import java.util.stream.Collectors;

public final class TypesProvider implements TemplateTags {

	private TypesProvider() {
	}

	public static String[] getTypes(Node node, Language language) {
		List<String> types = node.flags().stream().map(Tag::name).collect(Collectors.toList());
		types.addAll(instanceAnnotations(node, language));
		return types.toArray(new String[types.size()]);
	}

	public static String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add(FACET);
		list.add(facet.type());
		return list.toArray(new String[list.size()]);
	}

	public static String[] getTypes(FacetTarget facetTarget) {
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
			String name = assumption.getClass().getInterfaces()[0].getName();
			if (name.endsWith("Instance")) instances.add(name);
		}
		return instances;
	}

	public static String[] getTypes(Variable variable, int level) {
		Set<String> list = new HashSet<>();
		list.add(variable.getClass().getSimpleName());
		if (level == 1) list.add(TERMINAL);
		list.add(VARIABLE);
		if (variable instanceof VariableReference) list.add(REFERENCE);
		list.add(variable.type());
		if (variable.isInherited()) list.add(INHERITED);
		if (variable.isOverriden()) list.add(OVERRIDEN);
		if (variable.isMultiple()) list.add(MULTIPLE);
		list.addAll(variable.flags().stream().map(Tag::name).collect(Collectors.toList()));
		return list.toArray(new String[list.size()]);
	}

	public static String[] getTypes(Allow.Parameter variable) {
		Set<String> list = new HashSet<>();
		list.add(variable.getClass().getSimpleName());
		list.add(VARIABLE);
		if (variable instanceof ReferenceParameterAllow && !variable.type().equals(Variable.WORD)) list.add(REFERENCE);
		list.add(variable.type());
		if (variable.multiple()) list.add(MULTIPLE);
		list.addAll(variable.flags().stream().collect(Collectors.toList()));
		return list.toArray(new String[list.size()]);
	}

	public static String[] getTypes(Parameter parameter) {
		Set<String> list = new HashSet<>();
		list.add(parameter.getClass().getSimpleName());
		list.add(VARIABLE);
		list.add(PARAMETER);
		list.add(parameter.inferredType());
		if (parameter.values().size() > 1) list.add(MULTIPLE);
		list.addAll(parameter.flags().stream().collect(Collectors.toList()));
		return list.toArray(new String[list.size()]);
	}

}
