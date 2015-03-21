package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;

public class FacetTargetResolver {
	private final Model model;

	public FacetTargetResolver(Model model) {
		this.model = model;
	}

	public void resolve() throws DependencyException {
		resolve(this.model);
	}

	private void resolve(Node node) {
		for (Node include : node.getIncludedNodes())
			if (include instanceof NodeImpl) {
				resolveFacetTarget((NodeImpl) include);
				resolve(include);
			}
	}

	private void resolveFacetTarget(NodeImpl node) {
		for (FacetTarget target : node.getFacetTargets())
			target.getTargetNode().addAllowedFacets(node.getName());
	}
}
