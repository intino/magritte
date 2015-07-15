package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.language.model.*;

import java.util.ArrayList;
import java.util.List;

public class DependencyResolver {
	Model model;
	ReferenceManager manager;

	public DependencyResolver(Model model) throws DependencyException {
		this.model = model;
		manager = new ReferenceManager(this.model);
	}

	public void resolve() throws DependencyException {
		resolveInNodes(model);
		resolveFacets(model);
	}

	private void resolveInNodes(Node node) throws DependencyException {
		for (Node inner : node.components())
			resolve(inner);
	}

	private void resolveFacets(Node node) throws DependencyException {
		if (node instanceof NodeReference) return;
		resolveInFacets(node);
		resolveInTargets(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveParent(node);
		resolveInnerReferenceNodes(node);
		resolveVariableReference(node);
		resolveParametersReference(node);
		for (Node include : node.components())
			resolve(include);
	}

	private void resolveParametersReference(Parametrized parametrized) throws DependencyException {
		for (Parameter parameter : parametrized.parameters())
			resolveParameterValue((NodeContainer) parametrized, parameter);
	}

	private void resolveParameterValue(NodeContainer node, Parameter parameter) throws DependencyException {
		if (!areReferenceValues(parameter)) return;
		List<Node> nodes = new ArrayList<>();
		for (Object value : parameter.values()) {
			Node reference = resolveParameter(node, (String) value);
			if (reference != null) nodes.add(reference);
//			else //TODO WORDS
//				throw new DependencyException("Parameter reference " + value.toString() + " not found", (Element) node);
		}
		if (!nodes.isEmpty()) {
			parameter.inferredType(Parameter.REFERENCE);
			parameter.substituteValues(nodes);
		}
	}

	private Node resolveParameter(NodeContainer node, String reference) throws DependencyException {
		return manager.resolve(reference, node);
	}

	private boolean areReferenceValues(Parameter parameter) {
		Object value = parameter.values().iterator().next();
		return (value instanceof String && parameter.hasReferenceValue());
	}

	private void resolveParent(Node node) throws DependencyException {
		if (node.parent() == null && node.parentName() != null) {
			Node parent = manager.resolve(node.parentName(), getNodeContainer(node.container()));
			if (parent == null)
				throw new DependencyException("reject.dependency.parent.node.not.found", node);
			else {
				((NodeImpl) node).setParent(parent);
				parent.addChild(node);
			}
		}
	}

	private void resolveInnerReferenceNodes(Node node) throws DependencyException {
		for (Node nodeReference : node.referenceComponents())
			resolveNodeReference((NodeReference) nodeReference);
	}

	private void resolveNodeReference(NodeReference nodeReference) throws DependencyException {
		if (nodeReference.getDestiny() != null) return;
		NodeImpl destiny = manager.resolve(nodeReference);
		if (destiny == null) throw new DependencyException("reject.dependency.reference.node.not.found", nodeReference);
		else nodeReference.setDestiny(destiny);
	}

	private void resolveInFacets(Node node) throws DependencyException {
		facets(node);
		for (Node inner : node.components()) resolveFacets(inner);
	}

	private void facets(Node node) throws DependencyException {
		for (Facet facet : node.facets()) {
			resolveVariableReference(facet);
			resolveParametersReference(facet);
			for (Node include : facet.components()) resolve(include);
		}
	}

	private void resolveInTargets(Node node) throws DependencyException {
		for (FacetTarget facet : node.facetTargets()) {
			resolveVariableReference(facet);
			resolveFacetTarget(facet);
			for (Node include : facet.components())
				if (include instanceof NodeImpl) resolve(include);
				else resolveNodeReference((NodeReference) include);
			for (Node inner : node.components()) resolveFacets(inner);
		}
		for (Node inner : node.components()) resolveFacets(inner);

	}

	private void resolveVariableReference(NodeContainer container) throws DependencyException {
		for (Variable variable : container.variables())
			if (variable instanceof VariableReference)
				resolveVariableReference((VariableReference) variable, container);
	}

	private void resolveFacetTarget(FacetTarget facet) throws DependencyException {
		Node destiny = manager.resolve(facet, facet.container());
		if (destiny == null) throw new DependencyException("reject.facet.target.not.found", facet);
		else facet.targetNode(destiny);
	}

	private void resolveVariableReference(VariableReference variable, NodeContainer container) throws DependencyException {
		NodeImpl destiny = manager.resolve(variable, container);
		if (destiny == null)
			throw new DependencyException("reject.variable.not.found", container, variable.type());
		else variable.setDestiny(destiny);
	}

	private Node getNodeContainer(NodeContainer reference) {
		NodeContainer container = reference;
		while (!(container instanceof NodeImpl)) {
			if (container.container() == null) break;
			container = container.container();
		}
		return (Node) container;
	}
}