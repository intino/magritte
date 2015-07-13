package tara.compiler.codegeneration.magritte.morph;

import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Facet;
import tara.compiler.model.FacetTarget;
import tara.compiler.model.Node;
import tara.compiler.model.Variable;
import tara.compiler.model.impl.NodeImpl;
import tara.compiler.model.impl.VariableReference;
import tara.semantic.Allow;
import tara.semantic.Assumption;
import tara.semantic.constraints.ReferenceParameterAllow;
import tara.semantic.model.Tag;

import java.util.*;
import java.util.stream.Collectors;

public final class TypesProvider implements TemplateTags {

	private TypesProvider() {
	}

	public static String[] getTypes(NodeImpl node, Language language) {
		List<String> types = node.getAnnotations().stream().map(Tag::name).collect(Collectors.toList());
		types.addAll(instanceAnnotations(node, language));
		return types.toArray(new String[types.size()]);
	}

	public static String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add(FACET);
		list.add(facet.getFacetType());
		return list.toArray(new String[list.size()]);
	}

	public static String[] getTypes(FacetTarget facetTarget) {
		List<String> list = new ArrayList<>();
		list.add(FACET_TARGET);
		if (facetTarget.getTargetNode().getQualifiedName() != null)
			list.add(facetTarget.getTargetNode().getQualifiedName());
		return list.toArray(new String[list.size()]);
	}

	private static Collection<? extends String> instanceAnnotations(NodeImpl node, Language language) {
		List<String> instances = new ArrayList<>();
		Collection<Assumption> assumptions = language.assumptions(node.getType());
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
		list.add(variable.getType());
		if (variable.isMultiple()) list.add(MULTIPLE);
		list.addAll(variable.getFlags().stream().map(Tag::name).collect(Collectors.toList()));
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

	public static String[] getTypesOfReference(Node node) {
		Set<String> types = new HashSet<>();
		types.add("nodeReference");
		if (node.isSingle()) types.add("single");
		if (node.intoSingle()) types.add("into_single");
		if (node.isRequired()) types.add("required");
		if (node.isFeature()) types.add("feature");
		if (node.isFinal()) types.add("final");
		types.addAll(node.getFlags().stream().map((tag) -> tag.name().toLowerCase()).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}


}
