package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Tag;

public class InstanceAdder {
	private final Model model;

	public InstanceAdder(Model model) {
		this.model = model;
	}


	public void complete() {
		addTerminalInstance(model);
	}

	private void addTerminalInstance(Node node) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference) continue;
			if (!component.isDeclaration()) component.addFlag(Tag.Instance);
			addTerminalInstance(component);
		}
		for (Facet facet : node.facets())
			for (Node component : facet.components()) {
				if (!component.isDeclaration()) component.addFlag(Tag.Instance);
				addTerminalInstance(component);
			}
	}
}
