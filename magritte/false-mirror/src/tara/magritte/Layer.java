package tara.magritte;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static tara.magritte.loaders.NativeCodeLoader.nativeCodeOf;

public abstract class Layer {

    protected final Instance _instance;

    public Layer(Instance _instance) {
        this._instance = _instance;
    }

    public Instance _declaration() {
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

    public Instance _owner() {
        return _instance.owner();
    }

    public <T extends Layer> T _owner(Class<T> layerClass) {
        return _instance.ownerWith(layerClass);
    }

    public <T extends Layer> T as(Class<T> layerClass) {
        return _instance.as(layerClass);
    }

    protected void _facet(Layer layer) {
    }

    protected void _set(String name, Object object) {
    }

    protected void _load(String name, Object object) {
    }

    public Map<String, Object> _variables() {
        return Collections.emptyMap();
    }

    public void _createComponent(Concept concept) {
        _instance.add(concept.create(_instance));
    }

    public void _createComponent(Concept concept, String componentId) {
        _instance.add(concept.create(componentId, _instance));
    }

    public List<Instance> _components() {
        return Collections.emptyList();
    }

    public Model _model() {
        return _declaration().model();
    }

    public String _name() {
        return _instance.name();
    }

    public String _simpleName() {
        return _instance.simpleName();
    }

    public void save() {
        _model().save(_instance);
    }

    protected void _addComponent(Instance component) {
    }

    protected Object _link(NativeCode nativeCode) {
        if (nativeCode == null) return null;
        NativeCode clone = nativeCodeOf(nativeCode.getClass());
        clone.$(morphContextOf(clone));
        return clone;
    }

    public Instance _morphWith(Class<? extends Layer> layerClass) {
        return _instance.addLayer(_model().definitionOf(layerClass));
    }

    public Instance _morphWith(Concept concept) {
        return _instance.addLayer(concept);
    }

    public Instance _morphWith(String definition) {
        return _instance.addLayer(_model().definitionOf(definition));
    }

    private Layer morphContextOf(NativeCode clone) {
        if (clone.$Class().isAssignableFrom(this.getClass()))
            return this;
        else if (_instance.is(clone.$Class()))
            return _instance.as(clone.$Class());
        else if (searchOwner(clone) != null)
            return searchOwner(clone).as(clone.$Class());
        return null;
    }

    private Instance searchOwner(NativeCode nativeCode) {
        Layer ownerLayer = _instance.ownerWith(nativeCode.$Class());
        return ownerLayer != null ? ownerLayer._instance : null;
    }

    @Override
    public String toString() {
        return _instance.name();
    }
}
