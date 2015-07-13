package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.FacetTarget;
import tara.compiler.model.Node;
import tara.compiler.model.NodeContainer;
import tara.compiler.model.Variable;
import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.NodeImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FacetTargetResolver {
	private final Model model;

	public FacetTargetResolver(Model model) {
		this.model = model;
	}

	public void resolve() throws DependencyException {
		resolve(this.model);
	}

	private void resolve(Node node) {
		node.getIncludedNodes().stream().
			filter(include -> include instanceof NodeImpl).forEach(include -> {
			resolveFacetTarget((NodeImpl) include);
			resolve(include);
			resolveVarsToFacetTargets((NodeImpl) include);
		});
	}

	private void resolveFacetTarget(NodeImpl node) {
		for (FacetTarget target : node.getFacetTargets()) {
			target.getTargetNode().addAllowedFacets(node.getName());
			addToChildren(node, target);
		}
	}

	private void addToChildren(NodeImpl node, FacetTarget target) {
		for (Node children : target.getTargetNode().getChildren()) children.addAllowedFacets(node.getName());
	}

	private void resolveVarsToFacetTargets(NodeImpl node) {
		for (FacetTarget facetTarget : node.getFacetTargets())
			facetTarget.addVariables(cloneVariables(facetTarget, node.getVariables()));
	}

	private Variable[] cloneVariables(NodeContainer container, Collection<Variable> variables) {
		List<Variable> clones = variables.stream().map(variable -> variable.cloneIt(container)).collect(Collectors.toList());
		return clones.toArray(new Variable[clones.size()]);
	}
}
