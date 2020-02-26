package io.intino.magritte.framework;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Layer {

	private final Node node;

	public Layer(Node node) {
		this.node = node;
	}

	public String name$() {
		return node.name();
	}

	public <T extends Layer> T a$(Class<T> layerClass) {
		if (this.getClass() == layerClass) return (T) this;
		return node.as(layerClass);
	}

	public boolean i$(Class<? extends Layer> layerClass) {
		return node.is(layerClass);
	}

	public boolean i$(String concept) {
		return node.is(concept);
	}

	public boolean i$(Concept concept) {
		return node.is(concept.id());
	}

	public Node core$() {
		return node;
	}

	protected void sync$(Layer layer) {
	}

	protected void set$(String name, List<?> object) {
	}

	protected void load$(String name, List<?> object) {
	}

	protected Map<String, List<?>> variables$() {
		return Collections.emptyMap();
	}

	protected List<Node> componentList$() {
		return Collections.emptyList();
	}

	public void delete$() {
		node.delete();
	}

	public void save$() {
		node.save();
	}

	protected void addNode$(Node node) {
	}

	protected void removeNode$(Node node) {
	}

	@Override
	public String toString() {
		return node.id();
	}
}
