package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.Node;
import tara.lang.model.Tag;

public class NodeCommitter {
	private final Model model;

	public NodeCommitter(Model model) {
		this.model = model;
	}


	public void complete() {
		addTerminalInstance(model);
	}

	private void addTerminalInstance(Node node) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference) continue;
			if (!component.is(Tag.Instance)) component.addFlag(Tag.Instance);
			addTerminalInstance(component);
		}
	}
}