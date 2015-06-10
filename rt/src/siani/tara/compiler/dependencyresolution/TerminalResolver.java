package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;

public class TerminalResolver {

	private final Model model;

	public TerminalResolver(Model model) {
		this.model = model;
	}

	public void resolve() {
		resolveTerminals(model);
	}

	private void resolveTerminals(Node node) {
		for (Node include : node.getIncludedNodes()) {
			if (include instanceof NodeReference) continue;
			if (!include.isTerminal()) resolveTerminals(include);
			else propagateTerminalToInside(include);
		}
	}

	private void propagateTerminalToInside(Node node) {
		for (Node include : node.getIncludedNodes()) {
			if (include instanceof NodeReference) continue;
			if (!include.isTerminal()) include.addAnnotations("terminal");
			propagateTerminalToInside(include);
		}
		node.getVariables().stream().filter(variable -> !variable.isTerminal()).forEach(variable -> variable.addFlags("terminal"));
	}
}
