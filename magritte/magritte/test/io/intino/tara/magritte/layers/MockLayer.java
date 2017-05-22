package io.intino.tara.magritte.layers;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.NodeLoader;
import io.intino.tara.magritte.tags.Terminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class MockLayer extends Layer implements Terminal {

    private MockLayer mockLayer;
	private List<MockLayer> mockList = new ArrayList<>();

	public MockLayer(Node _node) {
		super(_node);
	}

	public MockLayer mockLayer() {
		return mockLayer;
	}

	public void mockLayer(MockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public MockLayer newMock() {
		return core$().graph().concept(MockLayer.class).createNode(core$()).as(MockLayer.class);
	}

	@Override
	protected void addNode(Node node) {
		if(node.is("Mock")) mockList.add(node.as(MockLayer.class));
	}

	@Override
	protected void removeNode(Node node) {
		if(node.is("Mock")) mockList.remove(node.as(MockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables$() {
		return Collections.singletonMap("mockLayer", new ArrayList<>(singletonList(mockLayer)));
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = NodeLoader.load(object, MockLayer.class, this).get(0);
	}

	@Override
	public List<Node> componentList$() {
		return new ArrayList<>(mockList.stream().map(Layer::core$).collect(toList()));
	}

	public MockLayer mock(int index) {
		return mockList.get(index);
	}
}
