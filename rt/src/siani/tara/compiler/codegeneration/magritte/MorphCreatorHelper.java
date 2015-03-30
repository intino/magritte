package siani.tara.compiler.codegeneration.magritte;

import siani.tara.Language;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.semantic.Assumption;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class MorphCreatorHelper implements TemplateTags {

	private MorphCreatorHelper() {
	}

	public static NodeImpl getNodeContainer(NodeImpl node) {
		NodeContainer container = node.getContainer();
		while (container != null && !(container instanceof NodeImpl)) {
			container = container.getContainer();
		}
		return (NodeImpl) container;
	}

	public static String[] getTypes(NodeImpl node, Language language) {
		List<String> types = new ArrayList<>();
		for (Annotation annotation : node.getAnnotations()) types.add(annotation.getName());
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

	public static String[] getTypes(Variable variable) {
		List<String> list = new ArrayList<>();
		list.add(variable.getClass().getSimpleName());
		list.add(VARIABLE);
		list.add(variable.getType());
		if (variable.isMultiple()) list.add(MULTIPLE);
		for (Annotation annotation : variable.getAnnotations()) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}

}
