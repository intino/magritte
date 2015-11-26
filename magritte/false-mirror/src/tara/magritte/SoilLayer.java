package tara.magritte;

import java.util.ArrayList;
import java.util.List;

public class SoilLayer extends Layer {

	List<Declaration> components = new ArrayList<>();

	public SoilLayer(Declaration _declaration) {
		super(_declaration);
	}

	@Override
	protected void _addComponent(Declaration component) {
		components.add(component);
	}

	@Override
	public List<Declaration> _components() {
		return components;
	}
}
