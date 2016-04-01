package tara.magritte;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Layer {

	private final Instance instance;

	public Layer(Instance instance) {
		this.instance = instance;
	}

	public String id() {
		return instance.id();
	}

	public String name() {
		return instance.name();
	}

	public Instance instance() {
		return instance;
	}

	public boolean is(String name) {
		return instance.is(name);
	}

	public boolean is(Class<? extends Layer> layerClass) {
		return instance.is(layerClass);
	}

	public boolean is(Concept concept) {
		return instance.is(concept.id());
	}

	public Instance owner() {
		return instance.owner();
	}


	public <T extends Layer> T owner(Class<T> layerClass) {
		return instance.ownerWith(layerClass);
	}

	protected void _facet(Layer layer) {
	}

	public void _set(String name, List<?> object) {
	}

	protected void _load(String name, List<?> object) {
	}

	public Map<String, List<?>> variables() {
		return Collections.emptyMap();
	}

	public void createComponent(String name, Concept concept) {
		instance.add(concept.newInstance(name, instance));
	}

	public List<Instance> features() {
		return Collections.emptyList();
	}

	public List<Instance> components() {
		return Collections.emptyList();
	}

	public List<Instance> instances() {
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
		return instance.as(layerClass);
	}

	public <T extends Layer> T newFacet(Class<T> facetClass) {
		return (T) newFacet(model().layerFactory.names(facetClass).get(0));
	}

	public Layer newFacet(String conceptName) {
		return newFacet(model().conceptOf(conceptName));
	}

	public Layer newFacet(Concept concept) {
		Layer layer = instance.addLayer(concept).as(concept);
		instance.syncLayers();
		return layer;
	}

	public void deleteFacet(Class<? extends Layer> facetClass) {
		deleteFacet(model().layerFactory.names(facetClass).get(0));
	}

	public void deleteFacet(String conceptName) {
		deleteFacet(model().conceptOf(conceptName));
	}

	public void deleteFacet(Concept concept) {
		instance.removeLayer(concept).as(concept);
	}

	protected void addInstance(Instance instance) {
	}

	protected void deleteInstance(Instance instance) {
	}


	@Override
	public String toString() {
		return instance.id();
	}
}
