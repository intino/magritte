package tara.magritte.layers;

import tara.magritte.DynamicModel;
import tara.magritte.Instance;
import tara.magritte.Layer;
import tara.magritte.Reference;
import tara.magritte.loaders.ReferenceLoader;
import tara.magritte.utils.ReferenceList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class DynamicMockLayer extends Layer implements tara.magritte.tags.Concept {

	public Reference mockLayer;
	public List<Reference> mockLayers = new ArrayList<>();
	public List<DynamicMockLayer> mockList = new ArrayList<>();

	public DynamicMockLayer(Instance _instance) {
		super(_instance);
	}

	public DynamicMockLayer mockLayer() {
		return mockLayer.instance().as(DynamicMockLayer.class);
	}

	public List<DynamicMockLayer> mockLayers() {
		return new ReferenceList<>(mockLayers, DynamicMockLayer.class);
	}

	public void mockLayer(DynamicMockLayer mockLayer) {
		this.mockLayer = new Reference(mockLayer._instance());
	}

	public DynamicMockLayer newMock() {
		return model().conceptOf(DynamicMockLayer.class).newInstance(_instance()).as(DynamicMockLayer.class);
	}

	@Override
	protected void _addInstance(Instance instance) {
		if(instance.is("Mock")) mockList.add(instance.as(DynamicMockLayer.class));
	}

	@Override
	protected void _removeInstance(Instance instance) {
		if(instance.is("Mock")) mockList.remove(instance.as(DynamicMockLayer.class));
	}

	@Override
	public Map<String, List<?>> _variables() {
		return Collections.singletonMap("mockLayer", new ArrayList<>(singletonList(mockLayer)));
	}

	@Override
	protected void _load(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = ReferenceLoader.load(object, (DynamicModel) model()).get(0);
	}

	@Override
	public List<Instance> _instances() {
		return new ArrayList<>(mockList.stream().map(Layer::_instance).collect(toList()));
	}

	public DynamicMockLayer mock(int index) {
		return mockList.get(index);
	}
}
