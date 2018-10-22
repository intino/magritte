package io.intino.tara.magritte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NodeCloner {

	private final List<Node> nodes;
	private final Node node;
	private final Graph model;
	private final Map<String, Node> cloneMap = new HashMap<>();
	private final NodeLoader loader = cloneMap::get;
	private String stash;

	private NodeCloner(List<Node> nodes, Node node, Graph model) {
		this.nodes = nodes;
		this.node = node;
		this.model = model;
	}

	private NodeCloner(List<Node> nodes, Model m1, String stash, Graph model) {
		this.nodes = nodes;
		this.node = m1;
		this.stash = stash;
		this.model = model;
	}

	static void clone(List<Node> toClone, Node node, Graph model) {
		if (toClone.isEmpty()) return;
		new NodeCloner(toClone, node, model).execute();
	}

	static void clone(List<Node> toClone, Model m1, String stash, Graph model) {
		if (toClone.isEmpty()) return;
		new NodeCloner(toClone, m1, stash, model).execute();
	}

	private void execute() {
		model.loaders.add(loader);
		nodes.stream()
				.map(toClone -> clone(cloneId(toClone), toClone, node))
				.forEach(node::add);
		nodes.forEach(this::copyVariables);
		model.loaders.remove(loader);
	}

	private String cloneId(Node toClone) {
		return node instanceof Model ? stash + "#" + toClone.name() : node.id() + "." + toClone.name();
	}

	private void copyVariables(Node original) {
		copyVariables(original, cloneMap.get(original.id()));
		original.componentList().forEach(this::copyVariables);
	}

	private Node clone(String name, Node toClone, Node owner) {
		Node clone = new Node(name);
		clone.owner(owner);
		toClone.typeNames.forEach(n -> clone.addLayer(model.concept$(n)));
		clone.syncLayers();
		cloneComponents(toClone, clone, name);
		register(toClone, clone);
		return clone;
	}

	private void register(Node toClone, Node clone) {
		cloneMap.put(toClone.id, clone);
		model.register(clone);
	}

	private void cloneComponents(Node toClone, Node clone, String name) {
		toClone.layers.forEach(origin -> {
			Layer destination = getLayerFrom(clone, origin);
			toClone.componentList().forEach(c -> destination.addNode$(clone(name + "." + c.name(), c, clone)));
		});
	}

	private void copyVariables(Node toClone, Node clone) {
		toClone.layers.forEach(origin -> {
			Layer destination = getLayerFrom(clone, origin);
			origin.variables$().entrySet().stream()
					.filter(e -> !e.getValue().isEmpty())
					.forEach(e -> destination.set$(e.getKey(), e.getValue()));
		});
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	private Layer getLayerFrom(Node clone, Layer origin) {
		return clone.layers.stream().filter(l -> l.getClass() == origin.getClass()).findFirst().get();
	}

}
