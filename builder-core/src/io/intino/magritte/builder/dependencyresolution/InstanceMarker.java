package io.intino.magritte.builder.dependencyresolution;

import io.intino.magritte.builder.model.Model;
import io.intino.magritte.builder.model.NodeReference;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.Tag;

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
			if (!component.is(Tag.Instance)) component.addFlags(Tag.Instance);
			asInstance(component);
		}
	}
}
