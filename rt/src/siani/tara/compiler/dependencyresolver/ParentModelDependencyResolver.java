package siani.tara.compiler.dependencyresolver;

import siani.tara.lang.*;

import java.util.*;

import static siani.tara.lang.Annotations.Annotation.TERMINAL;


public class ParentModelDependencyResolver {
	private final Model model;
	private final Model parentModel;

	public ParentModelDependencyResolver(Model model, Model parentModel) {
		this.model = model;
		this.parentModel = parentModel;
		model.setParentModel(parentModel);
	}

	public void resolve() {
		Map<String, Node> terminals = collectParentTerminalNodes(parentModel);
		addTerminalNodes(terminals);
		Map<Node, List<Variable>> terminalVariables = collectParentTerminalVariables(parentModel);
		addTerminalVariables(terminalVariables);
	}

	private void addTerminalVariables(Map<Node, List<Variable>> nodesWithTerminal) {
		for (Map.Entry<Node, List<Variable>> terminal : nodesWithTerminal.entrySet()) {
			Collection<Node> nodes = collectNodesByType(model, terminal.getKey().getContainer().getObject().getType());
			if (nodes.isEmpty()) return;
			for (Node node : nodes)
				if (node instanceof DeclaredNode) {
					DeclaredNode declaredNode = (DeclaredNode) node;
					for (Variable variable : terminal.getValue())
						declaredNode.getObject().add(variable.clone());
				}
		}
	}

	private Map<Node, List<Variable>> collectParentTerminalVariables(Model parentModel) {
		Map<Node, List<Variable>> terminalVariables = new HashMap<>();
		for (Node node : parentModel.getNodeTable().values()) {
			List<Variable> variables = getTerminalVariables(node);
			if (!variables.isEmpty()) terminalVariables.put(node, variables);
		}
		return terminalVariables;
	}

	private Map<String, Node> collectParentTerminalNodes(Model parent) {
		Map<String, Node> terminals = new HashMap<>();
		for (Node node : parent.getNodeTable().values())
			if (node.getObject().is(TERMINAL))
				terminals.put(node.getName(), node);
		return terminals;
	}

	private List<Variable> getTerminalVariables(Node node) {
		List<Variable> list = new ArrayList();
		for (Variable variable : node.getObject().getVariables()) {
			if (variable.isTerminal()) list.add(variable);
		}
		return list;
	}


	private void addTerminalNodes(Map<String, Node> terminals) {
		for (Node terminal : terminals.values()) {
			if (terminal.getContainer() == null) {
				model.add(terminal);
				model.add(terminal.getQualifiedName(), terminal);
				model.addIdentifier(terminal.getName());
			} else {
				addInnerTerminal(model, terminal);
			}
		}
	}

	private void addInnerTerminal(Model model, Node terminal) {
		Collection<Node> nodes = collectNodesByType(model, terminal.getContainer().getObject().getType());
		if (nodes.isEmpty()) return;
		for (Node node : nodes) {
			if (node instanceof DeclaredNode) {
				DeclaredNode declaredNode = (DeclaredNode) node;
				declaredNode.add(terminal, 0);
				terminal.setContainer(declaredNode);
				model.add(terminal.getQualifiedName(), terminal);
				model.addIdentifier(terminal.getName());
			}
		}
	}

	private Collection<Node> collectNodesByType(Model model, String type) {
		List<Node> list = new ArrayList<>();
		for (Node node : model.getNodeTable().values())
			if (node.getObject().getType().equals(type))
				list.add(node);
		return list;
	}
}