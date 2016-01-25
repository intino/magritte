package tara.magritte.modelwrappers;

import tara.magritte.Engine;
import tara.magritte.Instance;
import tara.magritte.Model;
import tara.magritte.ModelWrapper;
import tara.magritte.layers.MockLayer;

import java.util.ArrayList;
import java.util.List;

public class MockEngine extends ModelWrapper implements Engine {

	private final Model model;
	List<MockLayer> mockLayerList = new ArrayList<>();

	public MockEngine(Model model){
		this.model = model;
	}

	@Override
	public void init() {
		mockLayerList = model.components(MockLayer.class);
	}

	@Override
	public void execute() {

	}

	public List<MockLayer> mockLayerList() {
		return mockLayerList;
	}

	@Override
	protected void addInstance(Instance instance) {
		if(instance.is(MockLayer.class)) mockLayerList.add(instance.as(MockLayer.class));
	}

	@Override
	protected void removeInstance(Instance instance) {
		if(instance.is(MockLayer.class)) mockLayerList.remove(instance.as(MockLayer.class));
	}

	@Override
	public void update() {
		init();
	}
}
