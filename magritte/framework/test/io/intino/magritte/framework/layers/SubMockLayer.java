package io.intino.magritte.framework.layers;

import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.NodeLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.*;

import static java.util.Collections.singletonList;

public class SubMockLayer extends Super1MockLayer implements Terminal {

	private SubMockLayer mockLayer;
	private List<SubMockLayer> varMockList = new ArrayList<>();
	private List<SubMockLayer> mockList = new ArrayList<>();

	public SubMockLayer(Node _node) {
		super(_node);
	}

	public SubMockLayer mockLayer() {
		return mockLayer;
	}

	public List<SubMockLayer> varMockList() {
		return varMockList;
	}

	public void mockLayer(SubMockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public SubMockLayer newMock() {
		return core$().graph().concept(SubMockLayer.class).createNode(core$()).as(SubMockLayer.class);
	}

	@Override
	public void addNode$(Node node) {
		if (node.is("Mock")) mockList.add(node.as(SubMockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if (node.is("Mock")) mockList.remove(node.as(SubMockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables$() {
		Map<String, List<?>> map = new LinkedHashMap<>();
		map.put("mockLayer", new ArrayList<>(singletonList(mockLayer)));
		map.put("varMockList", this.varMockList);
		return map;
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = NodeLoader.load(object, SubMockLayer.class, this).get(0);
	}

	@Override
	public List<Node> componentList$() {
		Set<Node> components = new HashSet<>();
		new ArrayList<>(mockList).forEach(m -> components.add(m.core$()));
		return new ArrayList<>(components);
	}

	public SubMockLayer mock(int index) {
		return mockList.get(index);
	}
}
