package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.model.*;
import tara.language.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static tara.language.model.Primitives.WORD;

public class DependencyResolver {
	Model model;
	private final File wordsPath;
	ReferenceManager manager;

	public DependencyResolver(Model model, File wordsPath) throws DependencyException {
		this.model = model;
		this.wordsPath = wordsPath;
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
		resolveVariables(node);
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
		return value instanceof String && parameter.hasReferenceValue();
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
			resolveVariables(facet);
			resolveParametersReference(facet);
			for (Node include : facet.components()) resolve(include);
		}
	}

	private void resolveInTargets(Node node) throws DependencyException {
		for (FacetTarget facet : node.facetTargets()) {
			resolveVariables(facet);
			resolveFacetTarget(facet);
			for (Node include : facet.components())
				if (include instanceof NodeImpl) resolve(include);
				else resolveNodeReference((NodeReference) include);
			for (Node inner : node.components()) resolveFacets(inner);
		}
		for (Node inner : node.components()) resolveFacets(inner);

	}

	private void resolveVariables(NodeContainer container) throws DependencyException {
		for (Variable variable : container.variables())
			if (variable instanceof VariableReference)
				resolveVariables((VariableReference) variable, container);
			else if (WORD.equals(variable.type()) && variable.allowedValues().isEmpty())
				resolveOutDefinedWord(variable);
	}

	private void resolveOutDefinedWord(Variable variable) throws DependencyException {
		try {
			WordClassResolver resolver = new WordClassResolver(variable, wordsPath);
			if (wordsPath == null || !wordsPath.exists()) throw new TaraException("Words directory not found");
			variable.addAllowedValues(resolver.collectAllowedValues());
			((VariableImpl) variable).setOutDefined(true);
		} catch (TaraException e) {
			throw new DependencyException(e.getMessage(), variable, variable.type());
		}
	}

	private void resolveFacetTarget(FacetTarget facet) throws DependencyException {
		Node destiny = manager.resolve(facet, facet.container());
		if (destiny == null) throw new DependencyException("reject.facet.target.not.found", facet);
		else facet.targetNode(destiny);
	}

	private void resolveVariables(VariableReference variable, NodeContainer container) throws DependencyException {
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