package tara.magritte.modelwrappers;

import tara.magritte.Graph;
import tara.magritte.Node;
import tara.magritte.ModelWrapper;
import tara.magritte.Platform;
import tara.magritte.layers.MockLayer;

import java.util.ArrayList;
import java.util.List;

public class MockPlatform extends ModelWrapper implements Platform {

	private final Graph graph;
	List<MockLayer> mockLayerList = new ArrayList<>();

	public MockPlatform(Graph graph){
		this.graph = graph;
		execute();
	}

	@Override
	public void execute(String... args) {
		mockLayerList = graph.rootList(MockLayer.class);
	}

	public List<MockLayer> mockLayerList() {
		return mockLayerList;
	}

	@Override
	protected void addNode(Node node) {
		if(node.is(MockLayer.class)) mockLayerList.add(node.as(MockLayer.class));
	}

	@Override
	protected void removeNode(Node node) {
		if(node.is(MockLayer.class)) mockLayerList.remove(node.as(MockLayer.class));
	}

	@Override
	public void update() {
		execute();
	}
}
