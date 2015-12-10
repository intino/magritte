package tara.magritte;

import java.util.ArrayList;
import java.util.List;

public class SoilLayer extends Layer {

	private final List<Instance> components = new ArrayList<>();

	public SoilLayer(Instance _instance) {
		super(_instance);
	}

	@Override
	protected void _addInstance(Instance instance) {
		components.add(instance);
	}

	@Override
	public List<Instance> _components() {
		return components;
	}
}
