package io.intino.tara.magritte;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Layer {

	private final Node node;

	public Layer(Node node) {
		this.node = node;
	}

	public String id() {
		return node.id();
	}

	public String name() {
		return node.name();
	}

	public Node node() {
		return node;
	}

	public boolean is(String concept) {
		return node.is(concept);
	}

	public boolean is(Class<? extends Layer> layerClass) {
		return node.is(layerClass);
	}

	public boolean is(Concept concept) {
		return node.is(concept.id());
	}

	public Node owner() {
		return node.owner();
	}

	public <T extends Layer> T ownerAs(Class<T> layerClass) {
		return node.ownerAs(layerClass);
	}

	protected void _sync(Layer layer) {
	}

	protected void _set(String name, List<?> object) {
	}

	protected void _load(String name, List<?> object) {
	}

	public Map<String, List<?>> variables() {
		return Collections.emptyMap();
	}

	public void createNode(String name, Concept concept) {
		node.add(concept.createNode(name, node));
	}

	public List<Node> componentList() {
		return Collections.emptyList();
	}

	public Graph graph() {
		return node().graph();
	}

	public void delete() {
		node().delete();
	}

	public void save() {
		node().save();
	}

	public <T extends Layer> T as(Class<T> layerClass) {
		return node.as(layerClass);
	}

	public <T extends Layer> T addFacet(Class<T> layerClass) {
		return (T) addFacet(graph().layerFactory.names(layerClass).get(0));
	}

	public Layer addFacet(String concept) {
		return addFacet(graph().concept(concept));
	}

	public Layer addFacet(Concept concept) {
		concept.createLayersFor(node);
		node.syncLayers();
		return node.as(concept);
	}

	public void removeFacet(Class<? extends Layer> layerClass) {
		removeFacet(graph().layerFactory.names(layerClass).get(0));
	}

	public void removeFacet(String concept) {
		removeFacet(graph().concept(concept));
	}

	public void removeFacet(Concept concept) {
		node.removeLayer(concept).as(concept);
	}

	protected void addNode(Node node) {
	}

	protected void removeNode(Node node) {
	}

	@Override
	public String toString() {
		return node.id();
	}
}
