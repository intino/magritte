package tara.compiler.dependencyresolution;

import tara.compiler.model.Node;
import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.NodeReference;
import tara.semantic.model.Tag;

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
			if (!include.isTerminal()) include.addFlags(Tag.TERMINAL.name());
			propagateTerminalToInside(include);
		}
		node.getVariables().stream().filter(variable -> !variable.isTerminal()).forEach(variable -> variable.addFlags(Tag.TERMINAL.name()));
	}
}
