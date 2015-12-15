package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.Node;
import tara.lang.model.Tag;

import java.util.List;

public class TagResolver {

	private final Model model;

	public TagResolver(Model model) {
		this.model = model;
	}

	public void resolve() {
		resolveTags(model, Tag.Prototype);
	}

	private void resolveTags(Node node, Tag tag) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference || !component.annotations().contains(tag)) continue;
			if (component.annotations().contains(tag)) propagateTag(component.components(), tag);
			else resolveTags(component, tag);
		}
	}

	private void propagateTag(List<Node> components, Tag tag) {
		components.stream().filter(node -> !node.annotations().contains(tag) && !node.isReference()).forEach(n -> {
			n.addAnnotations(tag);
			propagateTag(n.components(), tag);
		});
	}

}
