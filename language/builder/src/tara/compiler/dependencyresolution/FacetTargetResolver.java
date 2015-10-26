package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Variable;

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
			filter(include -> include instanceof NodeImpl).forEach(component -> {
			resolveVarsToFacetTargets(component);
			resolveComponentsToFacetTargets(component);
			resolveFacetTarget(component);
			resolve(component);
		});
	}

	private void resolveFacetTarget(Node node) {
		for (FacetTarget target : node.facetTargets()) {
			target.targetNode().addAllowedFacets(node.name());
			addToChildren(node, target);
		}
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

	private void resolveVarsToFacetTargets(Node node) {
		for (FacetTarget facetTarget : node.facetTargets())
			facetTarget.add(cloneVariables(facetTarget, node.variables()));
	}

	private void resolveComponentsToFacetTargets(Node node) {
		for (FacetTarget facetTarget : node.facetTargets())
			facetTarget.add(cloneComponents(facetTarget, node.components()));
	}

	private Node[] cloneComponents(FacetTarget facetTarget, List<Node> components) {
		List<Node> references = components.stream().map(component -> {
			NodeReference reference = new NodeReference((NodeImpl) (component.isReference() ? component.destinyOfReference() : component));
			reference.setHas(false);
			addTags(component, reference);
			reference.file(facetTarget.file());
			reference.line(facetTarget.line());
			reference.container(facetTarget);
			return reference;
		}).collect(Collectors.toList());
		return references.toArray(new Node[references.size()]);
	}

	private void addTags(Node component, NodeReference reference) {
		component.flags().stream().filter(tag -> !reference.flags().contains(tag)).forEach(reference::addFlags);
		component.annotations().stream().filter(tag -> !reference.annotations().contains(tag)).forEach(reference::addAnnotations);
	}

	private Variable[] cloneVariables(NodeContainer container, Collection<Variable> variables) {
		List<Variable> clones = variables.stream().map(variable -> variable.cloneIt(container)).collect(Collectors.toList());
		return clones.toArray(new Variable[clones.size()]);
	}
}
