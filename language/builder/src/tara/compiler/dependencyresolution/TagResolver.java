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
		resolveFlag(model, Tag.Prototype);
		resolveAnnotation(model, Tag.Prototype);
	}

	private void resolveFlag(Node node, Tag tag) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference || !component.flags().contains(tag)) continue;
			if (component.flags().contains(tag)) propagateFlag(component.components(), tag);
			else resolveFlag(component, tag);
		}
	}

	private void propagateFlag(List<Node> components, Tag tag) {
		components.stream().filter(node -> !node.flags().contains(tag) && !node.isReference()).forEach(n -> {
			n.addFlag(tag);
			propagateFlag(n.components(), tag);
		});
	}


	private void resolveAnnotation(Node node, Tag tag) {
		for (Node component : node.components()) {
			if (component instanceof NodeReference || !component.annotations().contains(tag)) continue;
			if (component.annotations().contains(tag)) propagateAnnotation(component.components(), tag);
			else resolveAnnotation(component, tag);
		}
	}

	private void propagateAnnotation(List<Node> components, Tag tag) {
		components.stream().filter(node -> !node.annotations().contains(tag) && !node.isReference()).forEach(n -> {
			n.addAnnotations(tag);
			propagateAnnotation(n.components(), tag);
		});
	}

}
