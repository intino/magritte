package siani.tara.compiler.dependencyresolver;

import siani.tara.lang.*;

import java.util.*;

import static siani.tara.lang.Annotation.AGGREGATED;
import static siani.tara.lang.Annotation.COMPONENT;
import static siani.tara.lang.Annotation.FACET;


public class ParentModelDependencyResolver {
	private final Model model;
	private final Model parent;

	public ParentModelDependencyResolver(Model model, Model parentModel) {
		this.model = model;
		this.parent = parentModel;
		model.setParentModel(parentModel);
	}

	public void resolve() {
		addTerminalNodes(parent.getTerminalNodes());
		setValuesToNodes();
		addAnnotationsToInstances();
	}

	private void addAnnotationsToInstances() {
		for (Node parentNode : this.parent.getNodeTable().values())
			for (Node instance : getInstancesOf(parentNode))
				addParentAnnotation(parentNode.getAnnotations(), instance);
	}

	private void addParentAnnotation(Annotation[] annotations, Node instance) {
		for (Annotation annotation : annotations) {
			if (annotation.isMeta() && !instance.is(Annotation.getNormalAnnotationOfMeta(annotation)))
				instance.add(Annotation.getNormalAnnotationOfMeta(annotation));
			else if (!instance.is(annotation) && COMPONENT.equals(annotation) || AGGREGATED.equals(annotation))
				instance.add(annotation);
		}
	}

	private void setValuesToNodes() {
		for (Node parentNode : this.parent.getNodeTable().values())
			for (Node instance : getInstancesOf(parentNode)) {
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
					if (variable.getName().equals(entry.getKey()))
						if (model.isTerminal()) variable.setValues(values);
						else variable.setDefaultValues(values);
			}
		}
	}

	private void setValuesFromVarInits(Node instance) {
		for (Map.Entry<String, Variable> entry : instance.getObject().getVariableInits().entrySet())
			for (Variable variable : instance.getObject().getVariables())
				if (variable.getName().equals(entry.getKey()))
					if (model.isTerminal()) variable.setValues(entry.getValue().getValues());
					else variable.setDefaultValues(entry.getValue().getValues());
	}

	private void addClassVariablesToInstance(List<Variable> variables, Node instance) {
		List<Variable> clones = new ArrayList<>();
		for (Variable variable : variables)
			if (variable.values == null || variable.values.isEmpty()) {
				Variable clone = variable.clone();
				clones.add(clone);
				if (variable instanceof Reference && variable.isTerminal())
					addInheritedTypes(parent.get(clone.getType()).getName(), (Reference) clone);
			}
		instance.getObject().getVariables().addAll(0, clones);
	}

	private void addInheritedTypes(String nodeType, Reference clone) {
		for (Node node : model.getNodeTable().values())
			if (node.getObject().getType().equals(nodeType))
				clone.addInheritedType(node.getObject().getName());
	}

	private Collection<Node> getInstancesOf(Node node) {
		List<Node> instances = new ArrayList<>();
		for (Node instance : model.getNodeTable().values())
			if (instance.getObject().getType().equals(node.getName()))
				instances.add(instance);
		return instances;
	}

	private void addTerminalNodes(Map<String, Node> terminals) {
		for (Node terminal : terminals.values()) {
			if (terminal.getObject().is(FACET)) resolveTargets(terminal);
			model.add(terminal);
			addIdentifiers(terminal);

		}
	}

	private void addIdentifiers(Node terminal) {
		model.addIdentifier(terminal.getName());
		for (Node node : terminal.getInnerNodes())
			if (node.is(DeclaredNode.class)) addIdentifiers(node);
		for (Node node : terminal.getSubConcepts())
			addIdentifiers(node);
	}

	private void resolveTargets(Node terminal) {
		List<FacetTarget> newFacets = new ArrayList<>();
		if (terminal.getObject().getFacetTargets().isEmpty()) return;
		for (FacetTarget facetTarget : terminal.getObject().getFacetTargets())
			createFacetTargets(terminal, newFacets, findInstancesOf(facetTarget.getDestinyName()), facetTarget);
		terminal.getObject().getFacetTargets().clear();
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
		for (Node node : model.getNodeTable().values())
			if (node.is(DeclaredNode.class) && node.getObject().getType().equals(type)) {
				nodes.add(node);
				Collections.addAll(nodes, node.getSubConcepts());
			}
		return nodes;
	}
}
