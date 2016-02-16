package tara.magritte.modelwrappers;

import tara.magritte.Instance;
import tara.magritte.Model;
import tara.magritte.ModelWrapper;
import tara.magritte.Platform;
import tara.magritte.layers.DynamicMockLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class DynamicMockEngine extends ModelWrapper implements Platform {

	private final Model model;
	List<DynamicMockLayer> mockLayerList = new ArrayList<>();

	public DynamicMockEngine(Model model) {
		this.model = model;
	}

	@Override
	public void init(String... args) {
		mockLayerList = model.components(DynamicMockLayer.class);
	}

	@Override
	public void execute() {

	}

	@Override
	protected void addInstance(Instance instance) {
		if (instance.is(DynamicMockLayer.class)) mockLayerList.add(instance.as(DynamicMockLayer.class));
	}

	@Override
	protected void removeInstance(Instance instance) {
		if (instance.is(DynamicMockLayer.class)) mockLayerList.remove(instance.as(DynamicMockLayer.class));
	}

	@Override
	public void update() {
		init();
	}

	public List<DynamicMockLayer> mockLayerList() {
		return mockLayerList;
	}

	public List<DynamicMockLayer> mockLayerList(Predicate<? super DynamicMockLayer> predicate) {
		return mockLayerList.stream().filter(predicate).collect(toList());
	}
}
