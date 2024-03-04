package io.intino.magritte.framework;

import java.util.ArrayList;
import java.util.List;

public class M1 extends Layer {

	private final List<Node> components = new ArrayList<>();

	public M1(Node _node) {
		super(_node);
	}

	@Override
	protected void addNode$(Node node) {
		components.add(node);
	}

	@Override
	protected void removeNode$(Node node) {
		components.remove(node);
	}

	@Override
	public List<Node> componentList$() {
		return new ArrayList<>(components);
	}

}
