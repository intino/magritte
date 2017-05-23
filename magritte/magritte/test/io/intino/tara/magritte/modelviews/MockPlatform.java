package io.intino.tara.magritte.modelviews;

import io.intino.tara.magritte.Graph;
import io.intino.tara.magritte.GraphWrapper;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.layers.MockLayer;

import java.util.ArrayList;
import java.util.List;

public class MockPlatform extends GraphWrapper {

	private final Graph graph;
	private List<MockLayer> mockLayerList = new ArrayList<>();

	@SuppressWarnings("WeakerAccess")
    public MockPlatform(Graph graph){
		this.graph = graph;
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
		if(node.is(MockLayer.class)) mockLayerList.add(node.as(MockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if(node.is(MockLayer.class)) mockLayerList.remove(node.as(MockLayer.class));
	}

	@Override
	public void update() {
		execute();
	}
}
