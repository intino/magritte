package io.intino.tara.magritte.layers;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.NodeLoader;
import io.intino.tara.magritte.tags.Terminal;

import java.util.*;

import static java.util.Collections.singletonList;

public class Super1MockLayer extends Super2MockLayer implements Terminal {

    private Super1MockLayer mockLayer;
	private List<Super1MockLayer> mockList = new ArrayList<>();

	public Super1MockLayer(Node _node) {
		super(_node);
	}

	public Super1MockLayer mockLayer() {
		return mockLayer;
	}

	public void mockLayer(Super1MockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public Super1MockLayer newMock() {
		return core$().graph().concept(Super1MockLayer.class).createNode(core$()).as(Super1MockLayer.class);
	}

	@Override
	public void addNode$(Node node) {
		if(node.is("Mock")) mockList.add(node.as(Super1MockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if(node.is("Mock")) mockList.remove(node.as(Super1MockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables$() {
		Map<String, List<?>> map = new LinkedHashMap<>();
		map.put("mockLayer", new ArrayList<>(singletonList(mockLayer)));
		return map;
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = NodeLoader.load(object, Super1MockLayer.class, this).get(0);
	}

	@Override
	public List<Node> componentList$() {
		Set<Node> components = new HashSet<>();
		new ArrayList<>(mockList).forEach(m -> components.add(m.core$()));
		return new ArrayList<>(components);
	}

	public Super1MockLayer mock(int index) {
		return mockList.get(index);
	}
}
