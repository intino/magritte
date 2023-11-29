package io.intino.magritte.framework.layers;

import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.NodeLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.*;

import static java.util.Collections.singletonList;

public class Super3MockLayer extends Super4MockLayer implements Terminal {

	private Super3MockLayer mockLayer;
	private List<Super3MockLayer> mockList = new ArrayList<>();

	public Super3MockLayer(Node _node) {
		super(_node);
	}

	public Super3MockLayer mockLayer() {
		return mockLayer;
	}

	public void mockLayer(Super3MockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public Super3MockLayer newMock() {
		return core$().graph().concept(Super3MockLayer.class).createNode(core$()).as(Super3MockLayer.class);
	}

	@Override
	public void addNode$(Node node) {
		if (node.is("Mock")) mockList.add(node.as(Super3MockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if (node.is("Mock")) mockList.remove(node.as(Super3MockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables$() {
		Map<String, List<?>> map = new LinkedHashMap<>();
		map.put("mockLayer", new ArrayList<>(singletonList(mockLayer)));
		return map;
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = NodeLoader.load(object, Super3MockLayer.class, this).get(0);
	}

	@Override
	public List<Node> componentList$() {
		Set<Node> components = new HashSet<>();
		new ArrayList<>(mockList).forEach(m -> components.add(m.core$()));
		return new ArrayList<>(components);
	}

	public Super3MockLayer mock(int index) {
		return mockList.get(index);
	}
}
