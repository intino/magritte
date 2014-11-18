package siani.tara.compiler.dependencyresolver;

import siani.tara.lang.*;

import java.util.*;

import static siani.tara.lang.Annotations.Annotation.TERMINAL;


public class ParentModelDependencyResolver {
	private final Model model;
	private final Model parent;

	public ParentModelDependencyResolver(Model model, Model parentModel) {
		this.model = model;
		this.parent = parentModel;
		model.setParentModel(parentModel);
	}

	public void resolve() {
		addTerminalNodes(collectParentTerminalNodes());
		setValuesToNodes();
	}

	private void setValuesToNodes() {
		for (Node parentNode : this.parent.getNodeTable().values())
			for (Node instance : getInstancesOf(parentNode)) {
				addClassVariablesToInstance(parentNode.getObject().getVariables(), instance);
				setValuesFromParams(instance);
				setValuesFromVarInits(instance);
			}
	}

	private void setValuesFromParams(Node instance) {
		for (Map.Entry<String, Variable> entry : instance.getObject().getParameters().entrySet())
			try {
				Integer index = Integer.valueOf(entry.getKey());
				instance.getObject().getVariables().get(index).setValues(entry.getValue().getValues());
			} catch (NumberFormatException ignored) {
				for (Variable variable : instance.getObject().getVariables())
					if (variable.getName().equals(entry.getKey()))
						variable.setValues(entry.getValue().getValues());
			}
	}

	private void setValuesFromVarInits(Node instance) {
		for (Map.Entry<String, Variable> entry : instance.getObject().getVariableInits().entrySet())
			for (Variable variable : instance.getObject().getVariables())
				if (variable.getName().equals(entry.getKey()))
					variable.setValues(entry.getValue().getValues());
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

	private Map<String, Node> collectParentTerminalNodes() {
		Map<String, Node> terminals = new HashMap<>();
		for (Node node : parent.getNodeTable().values())
			if (node.getObject().is(TERMINAL))
				terminals.put(node.getName(), node);
		return terminals;
	}

	private void addTerminalNodes(Map<String, Node> terminals) {
		for (Node terminal : terminals.values())
			if (terminal.getContainer() == null) {
				model.add(terminal);
				model.add(terminal.getQualifiedName(), terminal);
				model.addIdentifier(terminal.getName());
				terminal.getObject().setParentObject(null);
				terminal.getObject().setParentName(null);
			} else addInnerTerminal(terminal);
	}

	private void addInnerTerminal(Node terminal) {
		Collection<Node> nodes = getInstancesOf(terminal.getContainer());
		if (nodes.isEmpty()) return;
		for (Node node : nodes)
			if (node instanceof DeclaredNode) {
				DeclaredNode declaredNode = (DeclaredNode) node;
				declaredNode.add(terminal, 0); // Possible StackOverFlow maybe clone needed
				terminal.getObject().setParentObject(null); //Possible loss of info
				terminal.getObject().setParentName(null);
				terminal.setContainer(declaredNode);
				model.add(terminal.getQualifiedName(), terminal);
				model.addIdentifier(terminal.getName());
			}
	}

}
