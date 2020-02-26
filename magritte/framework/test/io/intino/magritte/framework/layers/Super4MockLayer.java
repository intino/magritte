package io.intino.magritte.framework.layers;

import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.NodeLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.*;

import static java.util.Collections.singletonList;

public class Super4MockLayer extends Super5MockLayer implements Terminal {

	private Super4MockLayer mockLayer;
	private List<Super4MockLayer> mockList = new ArrayList<>();

	public Super4MockLayer(Node _node) {
		super(_node);
	}

	public Super4MockLayer mockLayer() {
		return mockLayer;
	}

	public void mockLayer(Super4MockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public Super4MockLayer newMock() {
		return core$().graph().concept(Super4MockLayer.class).createNode(core$()).as(Super4MockLayer.class);
	}

	@Override
	public void addNode$(Node node) {
		if (node.is("Mock")) mockList.add(node.as(Super4MockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if (node.is("Mock")) mockList.remove(node.as(Super4MockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables$() {
		Map<String, List<?>> map = new LinkedHashMap<>();
		map.put("mockLayer", new ArrayList<>(singletonList(mockLayer)));
		return map;
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = NodeLoader.load(object, Super4MockLayer.class, this).get(0);
	}

	@Override
	public List<Node> componentList$() {
		Set<Node> components = new HashSet<>();
		new ArrayList<>(mockList).forEach(m -> components.add(m.core$()));
		return new ArrayList<>(components);
	}

	public Super4MockLayer mock(int index) {
		return mockList.get(index);
	}
}
