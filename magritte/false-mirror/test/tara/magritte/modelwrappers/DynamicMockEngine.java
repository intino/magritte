package tara.magritte.modelwrappers;

import tara.magritte.Engine;
import tara.magritte.Instance;
import tara.magritte.Model;
import tara.magritte.ModelWrapper;
import tara.magritte.layers.DynamicMockLayer;

import java.util.ArrayList;
import java.util.List;

public class DynamicMockEngine extends ModelWrapper implements Engine {

	private final Model model;
	List<DynamicMockLayer> mockLayerList = new ArrayList<>();

	public DynamicMockEngine(Model model) {
		this.model = model;
	}

	@Override
	public void init() {
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

	public List<DynamicMockLayer> mockLayerList() {
		return mockLayerList;
	}
}
