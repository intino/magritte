package io.intino.magritte.builder.compiler.dependencyresolution;

import io.intino.Configuration;
import io.intino.magritte.builder.compiler.model.Model;
import io.intino.magritte.builder.compiler.model.NodeReference;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.Tag;

import static io.intino.Configuration.Artifact.Model.Level.Product;


public class TerminalResolver {

	private final Model model;
	private final Configuration.Artifact.Model.Level level;

	public TerminalResolver(Model model, Configuration.Artifact.Model.Level level) {
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
			else if (level.equals(Product)) {
				component.addFlags(Tag.Terminal);
				propagateTerminalToInside(component);
			}
		}
	}

	private void propagateTerminalToInside(Node node) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference) continue;
			if (!component.isTerminal()) component.addFlags(Tag.Terminal);
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
