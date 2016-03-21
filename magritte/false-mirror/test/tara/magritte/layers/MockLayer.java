package tara.magritte.layers;

import tara.magritte.Instance;
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

	public MockLayer(Instance _instance) {
		super(_instance);
	}

	public MockLayer mockLayer() {
		return mockLayer;
	}

	public void mockLayer(MockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public MockLayer newMock() {
		return model().conceptOf(MockLayer.class).newInstance(_instance()).as(MockLayer.class);
	}

	@Override
	protected void _addInstance(Instance instance) {
		if(instance.is("Mock")) mockList.add(instance.as(MockLayer.class));
	}

	@Override
	protected void _removeInstance(Instance instance) {
		if(instance.is("Mock")) mockList.remove(instance.as(MockLayer.class));
	}

	@Override
	public Map<String, List<?>> _variables() {
		return Collections.singletonMap("mockLayer", new ArrayList<>(singletonList(mockLayer)));
	}

	@Override
	protected void _load(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = InstanceLoader.load(object, model(), MockLayer.class).get(0);
	}

	@Override
	public List<Instance> _instances() {
		return new ArrayList<>(mockList.stream().map(Layer::_instance).collect(toList()));
	}

	public MockLayer mock(int index) {
		return mockList.get(index);
	}
}
