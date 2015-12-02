package tara.magritte;

import java.util.ArrayList;
import java.util.List;

public class SoilLayer extends Layer {

	List<Instance> components = new ArrayList<>();

	public SoilLayer(Instance _instance) {
		super(_instance);
	}

	@Override
	protected void _addComponent(Instance component) {
		components.add(component);
	}

	@Override
	public List<Instance> _components() {
		return components;
	}
}
