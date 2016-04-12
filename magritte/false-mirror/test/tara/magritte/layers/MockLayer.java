package tara.magritte.layers;

import tara.magritte.Node;
import tara.magritte.Layer;
import tara.magritte.loaders.InstanceLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class MockLayer extends Layer implements tara.magritte.tags.Concept {

	MockLayer mockLayer;
	List<MockLayer> mockList = new ArrayList<>();

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
		return model().conceptOf(MockLayer.class).newNode(instance()).as(MockLayer.class);
	}

	@Override
	protected void addInstance(Node node) {
		if(node.is("Mock")) mockList.add(node.as(MockLayer.class));
	}

	@Override
	protected void deleteInstance(Node node) {
		if(node.is("Mock")) mockList.remove(node.as(MockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables() {
		return Collections.singletonMap("mockLayer", new ArrayList<>(singletonList(mockLayer)));
	}

	@Override
	protected void _load(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = InstanceLoader.load(object, MockLayer.class, this).get(0);
	}

	@Override
	public List<Node> instances() {
		return new ArrayList<>(mockList.stream().map(Layer::instance).collect(toList()));
	}

	public MockLayer mock(int index) {
		return mockList.get(index);
	}
}
