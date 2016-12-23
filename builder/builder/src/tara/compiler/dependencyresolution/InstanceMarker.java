package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.Node;
import tara.lang.model.Tag;

public class InstanceMarker {
	private final Model model;

	public InstanceMarker(Model model) {
		this.model = model;
	}


	public void all() {
		asInstance(model);
	}

	private void asInstance(Node node) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference) continue;
			if (!component.is(Tag.Instance)) component.addFlag(Tag.Instance);
			asInstance(component);
		}
	}
}
