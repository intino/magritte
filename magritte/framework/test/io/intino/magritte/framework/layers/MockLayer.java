package io.intino.magritte.framework.layers;

import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.NodeLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.*;

import static java.util.Collections.singletonList;

public class MockLayer extends Layer implements Terminal {

	private MockLayer mockLayer;
	private List<MockLayer> varMockList = new ArrayList<>();
	private List<MockLayer> mockList = new ArrayList<>();

	public MockLayer(Node _node) {
		super(_node);
	}

	public MockLayer mockLayer() {
		return mockLayer;
	}

	public List<MockLayer> varMockList() {
		return varMockList;
	}

	public void mockLayer(MockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public MockLayer newMock() {
		return core$().graph().concept(MockLayer.class).createNode(core$()).as(MockLayer.class);
	}

	@Override
	public void addNode$(Node node) {
		if (node.is("Mock")) mockList.add(node.as(MockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if (node.is("Mock")) mockList.remove(node.as(MockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("mockLayer", new ArrayList<>(singletonList(mockLayer)));
		map.put("varMockList", this.varMockList);
		return map;
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = NodeLoader.load(object, MockLayer.class, this).get(0);
		if (name.equals("varMockList")) varMockList = NodeLoader.load(object, MockLayer.class, this);
	}

	@Override
	public List<Node> componentList$() {
		Set<Node> components = new HashSet<>();
		new ArrayList<>(mockList).forEach(m -> components.add(m.core$()));
		return new ArrayList<>(components);
	}

	public MockLayer mock(int index) {
		return mockList.get(index);
	}
}
