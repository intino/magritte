package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.model.Tag;

public class TerminalInstanceAdder {
	private final Model model;

	public TerminalInstanceAdder(Model model) {
		this.model = model;
	}


	public void complete() {
		addTerminalInstance(model);
	}

	private void addTerminalInstance(Node node) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference) continue;
			if (!component.isTerminalInstance()) component.addFlags(Tag.TERMINAL_INSTANCE);
			addTerminalInstance(component);
		}
		for (Facet facet : node.facets())
			for (Node component : facet.components()) {
				if (!component.isTerminalInstance()) component.addFlags(Tag.TERMINAL_INSTANCE);
				addTerminalInstance(component);
			}
	}
}