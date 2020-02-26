package io.intino.magritte.framework;

import java.util.Map;

class Model extends Node {

	Graph graph;
	private Map<Class<? extends GraphWrapper>, GraphWrapper> wrappers;

	Model(Graph graph, Map<Class<? extends GraphWrapper>, GraphWrapper> wrappers) {
		this.graph = graph;
		this.wrappers = wrappers;
		addLayer(M1.class);
		addLayer(M2.class);
		addLayer(M3.class);
		typeNames.add("Model");
	}

	@Override
	public Graph graph() {
		return graph;
	}

	@Override
	public void add(Node node) {
		super.add(node);
		wrappers.values().forEach(wrapper -> wrapper.addNode$(node));
	}

	@Override
	protected void remove(Node node) {
		super.remove(node);
		wrappers.values().forEach(wrapper -> wrapper.removeNode$(node));
	}
}
