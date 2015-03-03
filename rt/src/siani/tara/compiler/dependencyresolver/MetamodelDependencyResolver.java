package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.*;

import java.util.*;

import static siani.tara.lang.Annotation.*;


public class MetamodelDependencyResolver {
	private final Model model;
	private final Model parent;

	public MetamodelDependencyResolver(Model model, Model parentModel) {
		this.model = model;
		this.parent = parentModel;
		model.setParentModel(parentModel);
	}

	public void resolve() throws TaraException {
		addTerminalNodes(parent.extractTerminals());
		setValuesToNodes();
		addAnnotationsToInstances();
		completeFacetsInformation();
	}

	private void completeFacetsInformation() {
		for (Node node : model.getNodeTable()) {
			if (node.is(LinkNode.class)) continue;
			DeclaredNode declaredNode = (DeclaredNode) node;
			for (Facet facet : declaredNode.getObject().getFacets()) {
				Node parentNode = parent.searchNode(facet.getName());
				if (parentNode != null) facet.setIntention(parentNode.is(INTENTION));
			}
		}
	}

	private void addAnnotationsToInstances() {
		for (Node parentNode : this.parent.getNodeTable())
			for (Node instance : getInstancesOf(parentNode))
				addParentAnnotation(parentNode.getAnnotations(), instance);
	}

	private void addParentAnnotation(Annotation[] annotations, Node instance) {
		for (Annotation annotation : annotations)
			if (annotation.isMeta() && !instance.is(Annotation.getNormalAnnotationOfMeta(annotation)))
				instance.add(Annotation.getNormalAnnotationOfMeta(annotation));
			else if (!instance.is(annotation) && COMPONENT.equals(annotation) || AGGREGATED.equals(annotation))
				instance.add(annotation);
	}

	private void setValuesToNodes() throws TaraException {
		for (Node parentNode : this.parent.getNodeTable())
			for (Node instance : getDeclaredInstancesOf(parentNode)) {
				if (instance.is(LinkNode.class)) continue;
				addClassVariablesToInstance(parentNode.getObject().getVariables(), instance);
				setValuesFromParams(instance);
				setValuesFromVarInits(instance);
			}
	}

	private void setValuesFromParams(Node instance) {
		for (Map.Entry<String, Variable> entry : instance.getObject().getParameters().entrySet()) {
			List<Variable> variables = instance.getObject().getVariables();
			Object[] values = entry.getValue().getValues();
			try {
				int index = Integer.valueOf(entry.getKey());
				if (model.isTerminal()) variables.get(index).setValues(values);
				else variables.get(index).setDefaultValues(values);
			} catch (NumberFormatException ignored) {
				for (Variable variable : variables)
					setTextValues(entry, values, variable);
			}
		}
	}

	private void setValuesFromVarInits(Node instance) {
		for (Map.Entry<String, Variable> entry : instance.getObject().getVariableInits().entrySet())
			for (Variable variable : instance.getObject().getVariables())
				setTextValues(entry, entry.getValue().getValues(), variable);
	}

	private void setTextValues(Map.Entry<String, Variable> entry, Object[] values, Variable variable) {
		if (variable.getName().equals(entry.getKey()))
			if (model.isTerminal()) variable.setValues(values);
			else variable.setDefaultValues(values);
	}

	private void addClassVariablesToInstance(List<Variable> variables, Node instance) {
		List<Variable> clones = new ArrayList<>();
		for (Variable variable : variables)
			if (variable.getValues() == null || variable.getValues().length == 0) {
				Variable clone = variable.clone();
				clone.setInherited(true);
				clones.add(clone);
				if (variable instanceof Reference && variable.isTerminal())
					addInheritedTypes(parent.get(clone.getType()).getName(), (Reference) clone);
			}
		instance.getObject().getVariables().addAll(0, clones);
	}

	private void addInheritedTypes(String nodeType, Reference clone) {
		for (Node node : model.getNodeTable())
			if (node.getObject().getType().equals(nodeType))
				clone.addInheritedType(node.getObject().getName());
	}

	private Collection<Node> getDeclaredInstancesOf(Node metaNode) throws TaraException {
		if (metaNode.getName() == null) return Collections.EMPTY_LIST;
		List<Node> instances = new ArrayList<>();
		for (Node instance : model.getNodeTable())
			if (instance.getObject().getType().equals(metaNode.getName()) && isInstance(metaNode, instance))
				instances.add(instance);
		return instances;
	}

	private boolean isInstance(Node metaNode, Node instance) throws TaraException {
		Node node = parent.searchNodeClass(instance);
		if (node == null)
			throw new TaraException("Node in metamodel not found: " + instance.getMetaQN());
		return node.is(DeclaredNode.class) ? node.equals(metaNode) : ((LinkNode) node).getDestiny().equals(metaNode);
	}

	private Collection<Node> getInstancesOf(Node metaNode) {
		if (metaNode.getName() == null) return Collections.EMPTY_LIST;
		List<Node> instances = new ArrayList<>();
		for (Node instance : model.getNodeTable())
			if (instance.getObject().getType().equals(metaNode.getName()) && parent.searchNodeClass(instance).equals(metaNode))
				instances.add(instance);
		return instances;
	}

	private void addTerminalNodes(List<DeclaredNode> terminals) throws TaraException {
		for (DeclaredNode terminal : terminals) {
			if (terminal.getObject().is(FACET)) resolveTargets(terminal);
			model.add(terminal);
			addIdentifiers(terminal);
			propagateLinks(findLinksOfTerminal(terminal), terminal);
		}
	}

	private List<LinkNode> findLinksOfTerminal(DeclaredNode terminal) {
		List<LinkNode> linkNodes = new ArrayList<>();
		for (Node node : parent.getNodeTable()) {
			if (node.is(DeclaredNode.class) || !((LinkNode) node).getDestiny().equals(terminal) || !((LinkNode) node).isReference())
				continue;
			linkNodes.add((LinkNode) node);
		}
		return linkNodes;
	}

	private void propagateLinks(List<LinkNode> linkNodes, DeclaredNode terminal) throws TaraException {
		for (LinkNode linkNode : linkNodes) {
			Collection<Node> instancesOf = getDeclaredInstancesOf(linkNode.getContainer());
			for (Node container : instancesOf)
				if (container.is(DeclaredNode.class)) {
					LinkNode node = new LinkNode(terminal, (DeclaredNode) container);
					node.setReference(true);
					((DeclaredNode) container).add(node);
				}
		}
	}

	private void addIdentifiers(Node terminal) {
		model.addIdentifier(terminal.getName());
		for (Node node : terminal.getInnerNodes())
			if (node.is(DeclaredNode.class)) addIdentifiers(node);
		for (Node node : terminal.getSubNodes())
			addIdentifiers(node);
	}

	private void resolveTargets(Node terminal) {
		List<FacetTarget> newFacets = new ArrayList<>();
		if (terminal.getObject().getFacetTargets().isEmpty()) return;
		for (FacetTarget facetTarget : terminal.getObject().getFacetTargets())
			createFacetTargets(terminal, newFacets, findInstancesOf(facetTarget.getDestinyName()), facetTarget);
		terminal.getObject().getFacetTargets().addAll(newFacets);
	}

	private void createFacetTargets(Node facetNode, List<FacetTarget> newFacets, Collection<Node> nodes, FacetTarget facetTarget) {
		for (Node node : nodes) {
			FacetTarget target = new FacetTarget(node.getName());
			target.setDestiny(node.getObject());
			target.setDestinyQN(node.getQualifiedName());
			for (Node inner : facetTarget.getInner()) target.add(inner);
			for (Variable var : facetTarget.getVariables()) target.add(var);
			newFacets.add(target);
			node.getObject().addAllowedFacet(facetNode.getQualifiedName(), target);
		}
	}

	private Collection<Node> findInstancesOf(String type) {
		Set<Node> nodes = new HashSet<>();
		for (Node node : model.getNodeTable())
			if (node.is(DeclaredNode.class) && node.getObject().getType().equals(type)) {
				nodes.add(node);
				Collections.addAll(nodes, node.getSubNodes());
			}
		return nodes;
	}
}
