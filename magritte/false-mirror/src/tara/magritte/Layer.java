package tara.magritte;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Layer {

    private final Instance _instance;

    public Layer(Instance _instance) {
        this._instance = _instance;
    }

    public Instance _instance() {
        return _instance;
    }

    public boolean is(String name) {
        return _instance.is(name);
    }

    public boolean is(Class<? extends Layer> layerClass) {
        return _instance.is(layerClass);
    }

    public boolean is(Concept concept) {
        return _instance.is(concept.name());
    }

    @SuppressWarnings("unused")
    public Instance _owner() {
        return _instance.owner();
    }

    @SuppressWarnings("unused")
    public <T extends Layer> T _owner(Class<T> layerClass) {
        return _instance.ownerWith(layerClass);
    }

    @SuppressWarnings("unused")
    public <T extends Layer> T as(Class<T> layerClass) {
        return _instance.as(layerClass);
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

    public Map<String, List<?>> _variables() {
        return Collections.emptyMap();
    }

    @SuppressWarnings("unused")
    public void _createComponent(String name, Concept concept) {
        _instance.add(concept.newInstance(name, _instance));
    }

    @SuppressWarnings("unused")
    public List<Instance> _features() {
        return Collections.emptyList();
    }

    public List<Instance> _components() {
        return Collections.emptyList();
    }

    @SuppressWarnings("unused")
    public List<Instance> _instances() {
        return Collections.emptyList();
    }

	public Model model() {
		return _instance().model();
    }

    @SuppressWarnings("unused")
    public String _name() {
        return _instance.name();
    }

    @SuppressWarnings("unused")
    public String _simpleName() {
        return _instance.simpleName();
    }

	public void remove() {
		_instance().remove();
	}

    @SuppressWarnings("unused")
    public void save() {
        _instance().save();
    }

    protected void _addInstance(Instance instance) {
    }

	public <T extends Layer> T _addFacet(Class<T> facetClass){
		return (T) _addFacet(model().layerFactory.names(facetClass).get(0));
	}

	public Layer _addFacet(String conceptName){
		return _addFacet(model().conceptOf(conceptName));
	}

	public Layer _addFacet(Concept concept){
		return _instance.addLayer(concept).as(concept);
	}

	public void _removeFacet(Class<? extends Layer> facetClass){
		_removeFacet(model().layerFactory.names(facetClass).get(0));
	}

	public void _removeFacet(String conceptName){
		_removeFacet(model().conceptOf(conceptName));
	}

	public void _removeFacet(Concept concept){
		_instance.removeLayer(concept).as(concept);
	}

    @SuppressWarnings("unused")
    public Instance _morphWith(Class<? extends Layer> layerClass) {
		return _instance.addLayer(model().conceptOf(layerClass));
	}

    @SuppressWarnings("unused")
    public Instance _morphWith(Concept concept) {
        return _instance.addLayer(concept);
    }

    @SuppressWarnings("unused")
    public Instance _morphWith(String concept) {
		return _instance.addLayer(model().conceptOf(concept));
	}

    @Override
    public String toString() {
        return _instance.name();
    }

	protected void _removeInstance(Instance instance){}
}
