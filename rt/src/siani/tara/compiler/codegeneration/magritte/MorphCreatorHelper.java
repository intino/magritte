package siani.tara.compiler.codegeneration.magritte;

import siani.tara.Language;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.Allow;
import siani.tara.semantic.Assumption;
import siani.tara.semantic.constraints.ReferenceParameterAllow;
import siani.tara.semantic.model.Tag;

import java.util.*;
import java.util.stream.Collectors;

public final class MorphCreatorHelper implements TemplateTags {

	private MorphCreatorHelper() {
	}

	public static NodeImpl getNodeContainer(NodeContainer node) {
		NodeContainer container = node.getContainer();
		while (container != null && !(container instanceof NodeImpl)) {
			container = container.getContainer();
		}
		return (NodeImpl) container;
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
//		if (facetTarget.isAlways())
//			list.add(ALWAYS);
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

}
