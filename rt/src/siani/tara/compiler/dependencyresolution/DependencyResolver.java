package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;

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
		for (Node inner : node.getIncludedNodes())
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
		for (Node include : node.getIncludedNodes())
			resolve(include);
	}

	private void resolveParametersReference(Parametrized parametrized) throws DependencyException {
		for (Parameter parameter : parametrized.getParameters())
			resolveParameterValue((NodeContainer) parametrized, parameter);
	}

	private void resolveParameterValue(NodeContainer node, Parameter parameter) throws DependencyException {
		if (!areReferenceValues(parameter)) return;
		List<Node> nodes = new ArrayList<>();
		for (Object value : parameter.getValues()) {
			Node reference = resolveParameter(node, (String) value);
			if (reference != null) nodes.add(reference);
//			else //TODO WORDS
//				throw new DependencyException("Parameter reference " + value.toString() + " not found", (Element) node);
		}
		if (!nodes.isEmpty()) {
			parameter.setInferredType(Parameter.REFERENCE);
			parameter.substituteValues(nodes);
		}
	}

	private Node resolveParameter(NodeContainer node, String reference) throws DependencyException {
		return manager.resolve(reference, node);
	}

	private boolean areReferenceValues(Parameter parameter) {
		Object value = parameter.getValues().iterator().next();
		return (value instanceof String && parameter.hasReferenceValue());
	}

	private void resolveParent(Node node) throws DependencyException {
		if (node.getParent() == null && node.getParentName() != null) {
			Node parent = manager.resolve(node.getParentName(), getNodeContainer(node.getContainer()));
			if (parent == null)
				throw new DependencyException("reject.dependency.parent.node.not.found", (Element) node);
			else {
				((NodeImpl) node).setParent(parent);
				parent.addChild(node);
			}
		}
	}

	private void resolveInnerReferenceNodes(Node node) throws DependencyException {
		for (NodeReference nodeReference : node.getInnerNodeReferences())
			resolveNodeReference(nodeReference);
	}

	private void resolveNodeReference(NodeReference nodeReference) throws DependencyException {
		if (nodeReference.getDestiny() != null) return;
		NodeImpl destiny = manager.resolve(nodeReference);
		if (destiny == null) throw new DependencyException("reject.dependency.reference.node.not.found", nodeReference);
		else nodeReference.setDestiny(destiny);
	}

	private void resolveInFacets(Node node) throws DependencyException {
		facets(node);
		for (Node inner : node.getIncludedNodes()) resolveFacets(inner);
	}

	private void facets(Node node) throws DependencyException {
		for (Facet facet : node.getFacets()) {
			resolveVariableReference(facet);
			resolveParametersReference(facet);
			for (Node include : facet.getIncludedNodes()) resolve(include);
		}
	}

	private void resolveInTargets(Node node) throws DependencyException {
		for (FacetTarget facet : node.getFacetTargets()) {
			resolveVariableReference(facet);
			resolveFacetTarget(facet);
			for (Node include : facet.getIncludedNodes())
				if (include instanceof NodeImpl) resolve(include);
				else resolveNodeReference((NodeReference) include);
			for (Node inner : node.getIncludedNodes()) resolveFacets(inner);
		}
		for (Node inner : node.getIncludedNodes()) resolveFacets(inner);

	}

	private void resolveVariableReference(NodeContainer container) throws DependencyException {
		for (Variable variable : container.getVariables())
			if (variable instanceof VariableReference)
				resolveVariableReference((VariableReference) variable, container);
	}

	private void resolveFacetTarget(FacetTarget facet) throws DependencyException {
		Node destiny = manager.resolve(facet, facet.getContainer());
		if (destiny == null) throw new DependencyException("reject.facet.target.not.found", (Element) facet);
		else facet.setTargetNode(destiny);
	}

	private void resolveVariableReference(VariableReference variable, NodeContainer container) throws DependencyException {
		NodeImpl destiny = manager.resolve(variable, container);
		if (destiny == null)
			throw new DependencyException("reject.variable.not.found", (Element) container, variable.getType());
		else variable.setDestiny(destiny);
	}

	private Node getNodeContainer(NodeContainer reference) {
		NodeContainer container = reference;
		while (!(container instanceof NodeImpl)) {
			if (container.getContainer() == null) break;
			container = container.getContainer();
		}
		return (Node) container;
	}
}