package io.intino.tara.magritte.layers;

import io.intino.tara.magritte.DynamicGraph;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.Reference;
import io.intino.tara.magritte.loaders.ReferenceLoader;
import io.intino.tara.magritte.tags.Terminal;
import io.intino.tara.magritte.utils.ReferenceList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class DynamicMockLayer extends Layer implements Terminal {

	public Reference mockLayer;
	public List<Reference> mockLayers = new ArrayList<>();
	public List<DynamicMockLayer> mockList = new ArrayList<>();

	public DynamicMockLayer(Node _node) {
		super(_node);
	}

	public DynamicMockLayer mockLayer() {
		return mockLayer.node().as(DynamicMockLayer.class);
	}

	public List<DynamicMockLayer> mockLayers() {
		return new ReferenceList<>(mockLayers, DynamicMockLayer.class);
	}

	public void mockLayer(DynamicMockLayer mockLayer) {
		this.mockLayer = new Reference(mockLayer.node());
	}

	public DynamicMockLayer newMock() {
		return graph().concept(DynamicMockLayer.class).createNode(node()).as(DynamicMockLayer.class);
	}

	@Override
	protected void addNode(Node node) {
		if(node.is("Mock")) mockList.add(node.as(DynamicMockLayer.class));
	}

	@Override
	protected void removeNode(Node node) {
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
	public List<Node> componentList() {
		return new ArrayList<>(mockList.stream().map(Layer::node).collect(toList()));
	}

	public DynamicMockLayer mock(int index) {
		return mockList.get(index);
	}

	@Override
	public DynamicGraph graph() {
		return (DynamicGraph) super.graph();
	}
}
