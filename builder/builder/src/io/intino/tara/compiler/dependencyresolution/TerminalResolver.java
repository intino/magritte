package io.intino.tara.compiler.dependencyresolution;

import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Tag;

import static io.intino.tara.compiler.shared.Configuration.Level.Product;

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
			else if (Product.compareLevelWith(level) > 0) resolveTerminals(component);
			else if (level.compareLevelWith(Product) == 0) {
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
