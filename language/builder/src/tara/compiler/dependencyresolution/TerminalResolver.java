package tara.compiler.dependencyresolution;

import tara.compiler.core.CompilerConfiguration.ModuleType;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.Node;
import tara.lang.model.Tag;

import static tara.compiler.core.CompilerConfiguration.ModuleType.Application;

public class TerminalResolver {

	private final Model model;
	private final ModuleType moduleType;

	public TerminalResolver(Model model, ModuleType moduleType) {
		this.model = model;
		this.moduleType = moduleType;
	}

	public void resolve() {
		resolveTerminals(model);
	}

	private void resolveTerminals(Node node) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference) continue;
			if (component.isTerminal()) propagateTerminalToInside(component);
			else if (Application.compareLevelWith(moduleType) > 0) resolveTerminals(component);
			else if (moduleType.compareLevelWith(Application) == 0) {
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
