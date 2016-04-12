package tara.magritte.modelwrappers;

import tara.magritte.Node;
import tara.magritte.Model;
import tara.magritte.ModelWrapper;
import tara.magritte.Platform;
import tara.magritte.layers.DynamicMockLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class DynamicMockPlatform extends ModelWrapper implements Platform {

	private final Model model;
	List<DynamicMockLayer> mockLayerList = new ArrayList<>();

	public DynamicMockPlatform(Model model) {
		this.model = model;
	}

	@Override
	public void execute(String... args) {
		mockLayerList = model.rootList(DynamicMockLayer.class);
	}

	@Override
	protected void addNode(Node node) {
		if (node.is(DynamicMockLayer.class)) mockLayerList.add(node.as(DynamicMockLayer.class));
	}

	@Override
	protected void removeNode(Node node) {
		if (node.is(DynamicMockLayer.class)) mockLayerList.remove(node.as(DynamicMockLayer.class));
	}

	@Override
	public void update() {
	}

	public List<DynamicMockLayer> mockLayerList() {
		return mockLayerList;
	}

	public List<DynamicMockLayer> mockLayerList(Predicate<? super DynamicMockLayer> predicate) {
		return mockLayerList.stream().filter(predicate).collect(toList());
	}
}
