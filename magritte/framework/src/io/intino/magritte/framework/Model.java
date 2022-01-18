package io.intino.magritte.framework;

import java.util.Map;

public class Model extends Node {

	Graph graph;
	private final Map<Class<? extends GraphWrapper>, GraphWrapper> wrappers;

	public Model(Graph graph, Map<Class<? extends GraphWrapper>, GraphWrapper> wrappers) {
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
	public void remove(Node node) {
		super.remove(node);
		wrappers.values().forEach(wrapper -> wrapper.removeNode$(node));
	}

	public static class NoIndexedModel extends Model{

		public NoIndexedModel(Graph graph, Map<Class<? extends GraphWrapper>, GraphWrapper> wrappers) {
			super(graph, wrappers);
		}

		@Override
		public void add(Node node) {
		}

		@Override
		public void remove(Node node) {
		}
	}
}
