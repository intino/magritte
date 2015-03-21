package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;

public class DependencyResolver {
	Model model;
	ReferenceManager manager;

	public DependencyResolver(Model model) throws DependencyException {
		this.model = model;
		manager = new ReferenceManager(this.model);
	}

	public void resolve() throws DependencyException {
		for (Node node : model.getIncludedNodes())
			resolve(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveParent(node);
		resolveInnerReferenceNodes(node);
		resolveVariableReference(node);
		for (Node include : node.getIncludedNodes())
			resolve(include);
		resolveInFacets(node);
		resolveInTargets(node);
	}

	private void resolveParent(Node node) throws DependencyException {
		if (node.getParent() == null && node.getParentName() != null) {
			Node parent = manager.resolve(node.getParentName(), getNodeContainer(node.getContainer()));
			if (parent == null) throw new DependencyException("Parent not found", (Element) node);
			else {
				((NodeImpl) node).setParent(parent);
				parent.addChild(node);
			}
		}
	}

	private void resolveInnerReferenceNodes(Node node) throws DependencyException {
		for (NodeReference nodeReference : node.getInnerNodeReferences()) {
			if (nodeReference.getDestiny() != null) continue;
			NodeImpl destiny = manager.resolve(nodeReference);
			if (destiny == null) throw new DependencyException("Reference destiny not found", nodeReference);
			else nodeReference.setDestiny(destiny);
		}
	}

	private void resolveInFacets(Node node) throws DependencyException {
		for (Facet facet : node.getFacets()) {
			resolveVariableReference(facet);
			for (Node include : facet.getIncludedNodes())
				resolve(include);
		}
	}

	private void resolveInTargets(Node node) throws DependencyException {
		for (FacetTarget facet : node.getFacetTargets()) {
			resolveVariableReference(facet);
			resolveFacetTarget(facet);
			for (Node include : facet.getIncludedNodes())
				resolve(include);
		}
	}

	private void resolveVariableReference(NodeContainer container) throws DependencyException {
		for (Variable variable : container.getVariables())
			if (variable instanceof VariableReference)
				resolveVariableReference((VariableReference) variable, container);
	}

	private void resolveFacetTarget(FacetTarget facet) throws DependencyException {
		NodeImpl destiny = manager.resolve(facet, facet.getContainer());
		if (destiny == null) throw new DependencyException("Facet Target not found", (Element) facet);
		else facet.setTargetNode(destiny);
	}

	private void resolveVariableReference(VariableReference variable, NodeContainer container) throws DependencyException {
		NodeImpl destiny = manager.resolve(variable, container);
		if (destiny == null)
			throw new DependencyException("variable of type " + variable.getType() + " not found", (Element) container);
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