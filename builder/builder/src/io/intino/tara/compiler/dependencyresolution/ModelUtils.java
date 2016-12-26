package io.intino.tara.compiler.dependencyresolution;

import io.intino.tara.compiler.model.Model;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;

import java.util.List;
import java.util.stream.Collectors;

public class ModelUtils {


	public static FacetTarget findFacetTarget(Model model, Node target, String facet) {
		for (Node node : model.components())
			if (facet.equals(node.name())) {
				final FacetTarget facetTarget = correspondingTarget(node, target);
				if (facetTarget != null) return facetTarget;
			}
		return null;
	}

	public static Node findFacetTargetNode(Model model, Node target, String facet) {
		List<Node> candidates = model.components().stream().filter(node -> facet.equals(node.name()) && correspondingTarget(node, target) != null).collect(Collectors.toList());
		return candidates.isEmpty() ? null : selectCandidate(target, candidates);
	}

	private static Node selectCandidate(Node target, List<Node> candidates) {
		if (candidates.size() == 1) return candidates.get(0);
		Node candidate = target;
		while (candidate != null) {
			for (Node node : candidates) if (node.facetTarget().targetNode().equals(candidate)) return node;
			candidate = candidate.parent();
		}
		return candidates.get(0);
	}

	private static FacetTarget correspondingTarget(Node node, Node target) {
		final FacetTarget facetTarget = node.facetTarget();
		return facetTarget != null && (facetTarget.targetNode().equals(target) || isChild(facetTarget.targetNode(), target)) ? facetTarget : null;
	}

	private static boolean isChild(Node parent, Node target) {
		if (parent.children().contains(target)) return true;
		for (Node node : parent.children()) {
			boolean isChild = isChild(node, target);
			if (isChild) return true;
		}
		return false;
	}
}
