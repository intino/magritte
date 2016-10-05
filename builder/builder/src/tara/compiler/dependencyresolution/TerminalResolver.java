package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.compiler.shared.Configuration.Level;
import tara.lang.model.Node;
import tara.lang.model.Tag;

import static tara.compiler.shared.Configuration.Level.Application;

public class TerminalResolver {

	private final Model model;
	private final Level level;

	public TerminalResolver(Model model, Level level) {
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
			else if (Application.compareLevelWith(level) > 0) resolveTerminals(component);
			else if (level.compareLevelWith(Application) == 0) {
				component.addFlag(Tag.Terminal);
				propagateTerminalToInside(component);
			}
		}
	}

	private void propagateTerminalToInside(Node node) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference) continue;
			if (!component.isTerminal()) component.addFlag(Tag.Terminal);
			propagateTerminalToInside(component);
		}
		propagateTerminalToVariables(node);
	}

	private void propagateTerminalToVariables(Node node) {
		node.variables().stream().
			filter(variable -> !variable.isTerminal()).
			forEach(variable -> variable.addFlags(Tag.Terminal));
	}
}
