package io.intino.tara.compiler.dependencyresolution;

import io.intino.tara.compiler.core.errorcollection.DependencyException;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;

import java.util.List;

public class FacetTargetResolver {
	private final Model model;

	public FacetTargetResolver(Model model) {
		this.model = model;
	}

	public void resolve() throws DependencyException {
		resolve(this.model);
	}

	private void resolve(Node node) {
		node.components().stream().
			filter(component -> component instanceof NodeImpl).forEach(component -> {
			resolveFacetTarget(component);
			resolve(component);
		});
	}

	private void resolveFacetTarget(Node node) {
		if (node.facetTarget() != null) {
			node.facetTarget().targetNode().addAllowedFacets(node.name());
			addToChildren(node, node.facetTarget());
			addToFacetTargets(node.facetTarget().targetNode(), node.name());
		}
	}

	private void addToFacetTargets(Node node, String name) {
		node.container().components().stream().
			filter(component -> !component.isAnonymous() && component.name().equals(node.name()) && !component.equals(node) && component.facetTarget() != null).
			forEach(component -> component.addAllowedFacets(name));
	}

	private void addToChildren(Node node, FacetTarget target) {
		propagate(node, target.targetNode().children());
	}

	private void propagate(Node node, List<Node> children) {
		for (Node child : children) {
			child.addAllowedFacets(node.name());
			propagate(node, child.children());
		}
	}

}
