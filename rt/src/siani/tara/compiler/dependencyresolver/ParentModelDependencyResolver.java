package siani.tara.compiler.dependencyresolver;

import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.*;

import static siani.tara.lang.NodeObject.AnnotationType.TERMINAL;

public class ParentModelDependencyResolver {
	public ParentModelDependencyResolver(Model model, Model parentModel) {
		model.setParentModel(parentModel);
		Map<String, Node> terminals = collectParentTerminalNodes(parentModel);
		addTerminalNodes(terminals, model);
	}

	private Map<String, Node> collectParentTerminalNodes(Model parent) {
		Map<String, Node> terminals = new HashMap<>();
		for (Node node : parent.getNodeTable().values())
			if (node.getObject().is(TERMINAL))
				terminals.put(node.getName(), node);
		return terminals;
	}


	private void addTerminalNodes(Map<String, Node> terminals, Model model) {
		for (Node terminal : terminals.values()) {
			if (terminal.getContainer() == null) {
				model.add((DeclaredNode) terminal);
				model.add(terminal.getQualifiedName(), terminal);
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
