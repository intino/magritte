package io.intino.magritte.framework.modelviews;

import io.intino.magritte.framework.Graph;
import io.intino.magritte.framework.GraphWrapper;
import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.layers.MockLayer;

import java.util.ArrayList;
import java.util.List;

public class MockPlatform extends GraphWrapper {

	protected final Graph graph;
	private List<MockLayer> mockLayerList = new ArrayList<>();

	@SuppressWarnings("WeakerAccess")
	public MockPlatform(Graph graph) {
		this.graph = graph;
		execute();
	}

	@SuppressWarnings("WeakerAccess")
	public MockPlatform(Graph graph, MockPlatform mockPlatform) {
		this.graph = graph;
		this.mockLayerList = new ArrayList<>(mockPlatform.mockLayerList);
		execute();
	}

	public void execute(String... args) {
		mockLayerList = graph.rootList(MockLayer.class);
	}

	public List<MockLayer> mockLayerList() {
		return mockLayerList;
	}

	@Override
	protected void addNode$(Node node) {
		if (node.is(MockLayer.class)) mockLayerList.add(node.as(MockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if (node.is(MockLayer.class)) mockLayerList.remove(node.as(MockLayer.class));
	}

	@Override
	public void update() {
		execute();
	}

	public Graph core$() {
		return graph;
	}
}
