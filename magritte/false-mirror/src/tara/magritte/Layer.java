package tara.magritte;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Layer {

	private final Instance instance;

	public Layer(Instance instance) {
		this.instance = instance;
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

    @SuppressWarnings("unused")
	public Instance owner() {
		return instance.owner();
	}

    @SuppressWarnings("unused")
	public <T extends Layer> T owner(Class<T> layerClass) {
		return instance.ownerWith(layerClass);
	}

    @SuppressWarnings("unused")
    public <T extends Layer> T as(Class<T> layerClass) {
		return instance.as(layerClass);
	}

    @SuppressWarnings("UnusedParameters")
    protected void _facet(Layer layer) {
    }

    @SuppressWarnings("UnusedParameters")
    protected void _set(String name, List<?> object) {
    }

    @SuppressWarnings("UnusedParameters")
    protected void _load(String name, List<?> object) {
    }

	public Map<String, List<?>> variables() {
		return Collections.emptyMap();
    }

    @SuppressWarnings("unused")
	public void createComponent(String name, Concept concept) {
		instance.add(concept.newInstance(name, instance));
	}

    @SuppressWarnings("unused")
	public List<Instance> features() {
		return Collections.emptyList();
    }

	public List<Instance> components() {
		return Collections.emptyList();
    }

    @SuppressWarnings("unused")
	public List<Instance> instances() {
		return Collections.emptyList();
    }

	public Model model() {
		return instance().model();
	}

    @SuppressWarnings("unused")
	public String id() {
		return instance.id();
	}

    @SuppressWarnings("unused")
	public String name() {
		return instance.name();
	}

	public void remove() {
		instance().remove();
	}

    @SuppressWarnings("unused")
    public void save() {
		instance().save();
	}

	protected void addInstance(Instance instance) {
	}

	protected <T extends Layer> T _addFacet(Class<T> facetClass) {
		return (T) _addFacet(model().layerFactory.names(facetClass).get(0));
	}

	protected Layer _addFacet(String conceptName) {
		return _addFacet(model().conceptOf(conceptName));
	}

	protected Layer _addFacet(Concept concept) {
		Layer layer = instance.addLayer(concept).as(concept);
		instance.syncLayers();
		return layer;
	}

	protected void _removeFacet(Class<? extends Layer> facetClass) {
		_removeFacet(model().layerFactory.names(facetClass).get(0));
	}

	protected void _removeFacet(String conceptName) {
		_removeFacet(model().conceptOf(conceptName));
	}

	protected void _removeFacet(Concept concept) {
		instance.removeLayer(concept).as(concept);
	}

    @Override
    public String toString() {
		return instance.id();
	}

	protected void removeInstance(Instance instance) {
	}
}
