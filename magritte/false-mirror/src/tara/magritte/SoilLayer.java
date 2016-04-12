package tara.magritte;

import java.util.ArrayList;
import java.util.List;

public class SoilLayer extends Layer {

	private final List<Node> components = new ArrayList<>();

	public SoilLayer(Node _node) {
		super(_node);
	}

	@Override
	protected void addNode(Node node) {
		components.add(node);
	}

	@Override
	protected void removeNode(Node node) {
		components.remove(node);
	}

	@Override
	public List<Node> componentList() {
		return components;
	}

	@Override
	public List<Node> content() {
		java.util.Set<Node> nodes = new java.util.LinkedHashSet<>();
		components.forEach(nodes::add);
		return new ArrayList<>(nodes);
	}
}
