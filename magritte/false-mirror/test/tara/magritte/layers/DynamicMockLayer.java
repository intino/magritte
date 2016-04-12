package tara.magritte.layers;

import tara.magritte.DynamicModel;
import tara.magritte.Node;
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

	public DynamicMockLayer(Node _node) {
		super(_node);
	}

	public DynamicMockLayer mockLayer() {
		return mockLayer.instance().as(DynamicMockLayer.class);
	}

	public List<DynamicMockLayer> mockLayers() {
		return new ReferenceList<>(mockLayers, DynamicMockLayer.class);
	}

	public void mockLayer(DynamicMockLayer mockLayer) {
		this.mockLayer = new Reference(mockLayer.instance());
	}

	public DynamicMockLayer newMock() {
		return model().conceptOf(DynamicMockLayer.class).newNode(instance()).as(DynamicMockLayer.class);
	}

	@Override
	protected void addInstance(Node node) {
		if(node.is("Mock")) mockList.add(node.as(DynamicMockLayer.class));
	}

	@Override
	protected void deleteInstance(Node node) {
		if(node.is("Mock")) mockList.remove(node.as(DynamicMockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables() {
		return Collections.singletonMap("mockLayer", new ArrayList<>(singletonList(mockLayer)));
	}

	@Override
	protected void _load(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = ReferenceLoader.load(object, this).get(0);
	}

	@Override
	public List<Node> instances() {
		return new ArrayList<>(mockList.stream().map(Layer::instance).collect(toList()));
	}

	public DynamicMockLayer mock(int index) {
		return mockList.get(index);
	}

	@Override
	public DynamicModel model() {
		return (DynamicModel) super.model();
	}
}
