package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Tag;

public class TerminalResolver {

	private final Model model;
	private final int level;

	public TerminalResolver(Model model, int level) {
		this.model = model;
		this.level = level;
	}

	public void resolve() {
		resolveTerminals(model);
	}

	private void resolveTerminals(Node node) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference) continue;
			if (component.isTerminal()) propagateTerminalToInside(component);
			else if (level > 1) resolveTerminals(component);
			else if (level == 1) {
				component.addFlag(Tag.Terminal);
				propagateTerminalToInside(component);
			}
		}
	}

	private void propagateTerminalToInside(Node node) {
		for (Node inner : node.components()) {
			if (inner instanceof NodeReference) continue;
			if (!inner.isTerminal()) inner.addFlag(Tag.Terminal);
			propagateTerminalToInside(inner);
		}
		propagateTerminalToVariables(node);
	}

	private void propagateTerminalToVariables(NodeContainer node) {
		node.variables().stream().
			filter(variable -> !variable.isTerminal()).
			forEach(variable -> variable.addFlags(Tag.Terminal));
	}
}
