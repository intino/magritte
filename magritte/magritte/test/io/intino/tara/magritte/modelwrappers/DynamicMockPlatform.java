package io.intino.tara.magritte.modelwrappers;

import io.intino.tara.magritte.layers.DynamicMockLayer;
import io.intino.tara.magritte.Graph;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.GraphWrapper;
import io.intino.tara.magritte.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class DynamicMockPlatform extends GraphWrapper implements Platform {

	private final Graph graph;
	List<DynamicMockLayer> mockLayerList = new ArrayList<>();

	public DynamicMockPlatform(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void execute(String... args) {
		mockLayerList = graph.rootList(DynamicMockLayer.class);
	}

	@Override
	protected void addNode(Node node) {
		if (node.is(DynamicMockLayer.class)) mockLayerList.add(node.as(DynamicMockLayer.class));
	}

	@Override
	protected void removeNode(Node node) {
		if (node.is(DynamicMockLayer.class)) mockLayerList.remove(node.as(DynamicMockLayer.class));
	}

	@Override
	public void update() {
	}

	public List<DynamicMockLayer> mockLayerList() {
		return mockLayerList;
	}

	public List<DynamicMockLayer> mockLayerList(Predicate<? super DynamicMockLayer> predicate) {
		return mockLayerList.stream().filter(predicate).collect(toList());
	}
}
