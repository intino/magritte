
package io.intino.tara.magritte.layers;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.NodeLoader;
import io.intino.tara.magritte.tags.Terminal;

import java.util.*;

import static java.util.Collections.singletonList;

public class Super5MockLayer extends Layer implements Terminal {

    private Super5MockLayer mockLayer;
	private List<Super5MockLayer> mockList = new ArrayList<>();

	public Super5MockLayer(Node _node) {
		super(_node);
	}

	public Super5MockLayer mockLayer() {
		return mockLayer;
	}

	public void mockLayer(Super5MockLayer mockLayer) {
		this.mockLayer = mockLayer;
	}

	public Super5MockLayer newMock() {
		return core$().graph().concept(Super5MockLayer.class).createNode(core$()).as(Super5MockLayer.class);
	}

	@Override
	public void addNode$(Node node) {
		if(node.is("Mock")) mockList.add(node.as(Super5MockLayer.class));
	}

	@Override
	protected void removeNode$(Node node) {
		if(node.is("Mock")) mockList.remove(node.as(Super5MockLayer.class));
	}

	@Override
	public Map<String, List<?>> variables$() {
		Map<String, List<?>> map = new LinkedHashMap<>();
		map.put("mockLayer", new ArrayList<>(singletonList(mockLayer)));
		return map;
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("mockLayer")) mockLayer = NodeLoader.load(object, Super5MockLayer.class, this).get(0);
	}

	@Override
	public List<Node> componentList$() {
		Set<Node> components = new HashSet<>();
		new ArrayList<>(mockList).forEach(m -> components.add(m.core$()));
		return new ArrayList<>(components);
	}

	public Super5MockLayer mock(int index) {
		return mockList.get(index);
	}
}
