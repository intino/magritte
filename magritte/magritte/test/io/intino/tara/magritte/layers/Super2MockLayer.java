package io.intino.tara.magritte.layers;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.NodeLoader;
import io.intino.tara.magritte.tags.Terminal;

import java.util.*;

import static java.util.Collections.singletonList;

public class Super2MockLayer extends Super3MockLayer implements Terminal {

    private Super2MockLayer mockLayer;
	private List<Super2MockLayer> mockList = new ArrayList<>();

	public Super2MockLayer(Node _node) {
		super(_node);
	}

	public Super2MockLayer mockLayer() {
		return mockLayer;
	}

	public void mockLayer(Super2MockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public Super2MockLayer newMock() {
		return core$().graph().concept(Super2MockLayer.class).createNode(core$()).as(Super2MockLayer.class);
	}

	@Override
	public void addNode$(Node node) {
		if(node.is("Mock")) mockList.add(node.as(Super2MockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if(node.is("Mock")) mockList.remove(node.as(Super2MockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables$() {
		Map<String, List<?>> map = new LinkedHashMap<>();
		map.put("mockLayer", new ArrayList<>(singletonList(mockLayer)));
		return map;
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = NodeLoader.load(object, Super2MockLayer.class, this).get(0);
	}

	@Override
	public List<Node> componentList$() {
		Set<Node> components = new HashSet<>();
		new ArrayList<>(mockList).forEach(m -> components.add(m.core$()));
		return new ArrayList<>(components);
	}

	public Super2MockLayer mock(int index) {
		return mockList.get(index);
	}
}
