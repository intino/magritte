package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;

public class ModelUtils {


	public static FacetTarget findFacetTarget(Model model, Node target, String facet) {
		for (Node node : model.components()) if (facet.equals(node.name())) return correspondingTarget(node, target);
		return null;
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
