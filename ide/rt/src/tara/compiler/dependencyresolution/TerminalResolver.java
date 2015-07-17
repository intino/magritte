package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.language.model.Node;
import tara.language.model.Tag;
import tara.language.model.Variable;

public class TerminalResolver {

	private final Model model;

	public TerminalResolver(Model model) {
		this.model = model;
	}

	public void resolve() {
		resolveTerminals(model);
	}

	private void resolveTerminals(Node node) {
		for (Node include : node.components()) {
			if (include instanceof NodeReference) continue;
			if (!include.isTerminal()) resolveTerminals(include);
			else propagateTerminalToInside(include);
		}
	}

	private void propagateTerminalToInside(Node node) {
		for (Node include : node.components()) {
			if (include instanceof NodeReference) continue;
			if (!include.isTerminal()) include.addFlags(Tag.TERMINAL);
			propagateTerminalToInside(include);
		}
		for (Variable variable : node.variables()) {
			if (!variable.isTerminal())
				variable.addFlags(Tag.TERMINAL);
		}
	}
}
