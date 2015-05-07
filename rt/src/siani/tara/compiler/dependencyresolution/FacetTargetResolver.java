package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;

import java.util.ArrayList;
import java.util.Collection;
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
		for (Node include : node.getIncludedNodes())
			if (include instanceof NodeImpl) {
				resolveFacetTarget((NodeImpl) include);
				resolve(include);
				resolveVarsToFacetTargets((NodeImpl) include);
			}
	}

	private void resolveFacetTarget(NodeImpl node) {
		for (FacetTarget target : node.getFacetTargets())
			target.getTargetNode().addAllowedFacets(node.getName());
	}

	private void resolveVarsToFacetTargets(NodeImpl node) {
		for (FacetTarget facetTarget : node.getFacetTargets())
			facetTarget.addVariables(cloneVariables(facetTarget, node.getVariables()));
		if (node.isFacet()) node.getVariables().clear();
	}

	private Variable[] cloneVariables(NodeContainer container, Collection<Variable> variables) {
		List<Variable> clones = new ArrayList<>();
		for (Variable variable : variables)
			clones.add(variable.cloneIt(container));
		return clones.toArray(new Variable[clones.size()]);
	}
}
