package tara.magritte;

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

	public Node instance() {
		return node;
	}

	public boolean is(String name) {
		return node.is(name);
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


	public <T extends Layer> T owner(Class<T> layerClass) {
		return node.ownerAs(layerClass);
	}

	protected void _facet(Layer layer) {
	}

	protected void _set(String name, List<?> object) {
	}

	protected void _load(String name, List<?> object) {
	}

	public Map<String, List<?>> variables() {
		return Collections.emptyMap();
	}

	public void createComponent(String name, Concept concept) {
		node.add(concept.newNode(name, node));
	}

	public List<Node> features() {
		return Collections.emptyList();
	}

	public List<Node> components() {
		return Collections.emptyList();
	}

	public List<Node> instances() {
		return Collections.emptyList();
	}

	public Model model() {
		return instance().model();
	}

	public void delete() {
		instance().delete();
	}

	public void save() {
		instance().save();
	}

	public <T extends Layer> T as(Class<T> layerClass) {
		return node.as(layerClass);
	}

	public <T extends Layer> T newFacet(Class<T> facetClass) {
		return (T) newFacet(model().layerFactory.names(facetClass).get(0));
	}

	public Layer newFacet(String conceptName) {
		return newFacet(model().conceptOf(conceptName));
	}

	public Layer newFacet(Concept concept) {
		Layer layer = node.addLayer(concept).as(concept);
		node.syncLayers();
		return layer;
	}

	public void deleteFacet(Class<? extends Layer> facetClass) {
		deleteFacet(model().layerFactory.names(facetClass).get(0));
	}

	public void deleteFacet(String conceptName) {
		deleteFacet(model().conceptOf(conceptName));
	}

	public void deleteFacet(Concept concept) {
		node.removeLayer(concept).as(concept);
	}

	protected void addInstance(Node node) {
	}

	protected void deleteInstance(Node node) {
	}


	@Override
	public String toString() {
		return node.id();
	}
}
