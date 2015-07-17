package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Variable;

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
		node.components().stream().
			filter(include -> include instanceof NodeImpl).forEach(include -> {
			resolveFacetTarget((NodeImpl) include);
			resolve(include);
			resolveVarsToFacetTargets((NodeImpl) include);
		});
	}

	private void resolveFacetTarget(NodeImpl node) {
		for (FacetTarget target : node.facetTargets()) {
			target.targetNode().addAllowedFacets(node.name());
			addToChildren(node, target);
		}
	}

	private void addToChildren(NodeImpl node, FacetTarget target) {
		for (Node children : target.targetNode().children()) children.addAllowedFacets(node.name());
	}

	private void resolveVarsToFacetTargets(NodeImpl node) {
		for (FacetTarget facetTarget : node.facetTargets())
			facetTarget.add(cloneVariables(facetTarget, node.variables()));
	}

	private Variable[] cloneVariables(NodeContainer container, Collection<Variable> variables) {
		List<Variable> clones = variables.stream().map(variable -> variable.cloneIt(container)).collect(Collectors.toList());
		return clones.toArray(new Variable[clones.size()]);
	}
}
