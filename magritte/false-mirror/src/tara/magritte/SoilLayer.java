package tara.magritte;

import java.util.ArrayList;
import java.util.List;

public class SoilLayer extends Layer {

	private final List<Instance> components = new ArrayList<>();

	public SoilLayer(Instance _instance) {
		super(_instance);
	}

	@Override
	protected void addInstance(Instance instance) {
		components.add(instance);
	}

	@Override
	protected void removeInstance(Instance instance) {
		components.remove(instance);
	}

	@Override
	public List<Instance> components() {
		return components;
	}

	@Override
	public List<Instance> instances() {
		java.util.Set<tara.magritte.Instance> instances = new java.util.LinkedHashSet<>();
		components.forEach(instances::add);
		return new ArrayList<>(instances);
	}
}
