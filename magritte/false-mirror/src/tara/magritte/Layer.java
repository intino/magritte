package tara.magritte;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static tara.magritte.loaders.NativeCodeLoader.nativeCodeOf;

public abstract class Layer {

    protected final Declaration _declaration;

    public Layer(Declaration _declaration) {
        this._declaration = _declaration;
    }

    public Declaration _declaration() {
        return _declaration;
    }

    public boolean is(String name) {
        return _declaration.is(name);
    }

    public boolean is(Class<? extends Layer> layerClass) {
        return _declaration.is(layerClass);
    }

    public boolean is(Definition definition) {
        return _declaration.is(definition.name());
    }

    public Declaration _owner() {
        return _declaration.owner();
    }

    public <T extends Layer> T _owner(Class<T> layerClass) {
        return _declaration.ownerWith(layerClass);
    }

    public <T extends Layer> T as(Class<T> layerClass) {
        return _declaration.as(layerClass);
    }

    protected void _set(Layer layer) {
    }

    protected void _set(String name, Object object) {
    }

    protected void _load(String name, Object object) {
    }

    public Map<String, Object> _variables() {
        return Collections.emptyMap();
    }

    public void _createComponent(Definition definition) {
        _declaration.add(definition.create(_declaration));
    }

    public void _createComponent(Definition definition, String componentId) {
        _declaration.add(definition.create(componentId, _declaration));
    }

    public List<Declaration> _components() {
        return Collections.emptyList();
    }

    public Model _model() {
        return _declaration().model();
    }

    public String _name() {
        return _declaration.name();
    }

    public String _simpleName() {
        return _declaration.simpleName();
    }

    public void save() {
        _model().save(_declaration);
    }

    protected void _addComponent(Declaration component) {
    }

    protected Object _link(NativeCode nativeCode) {
        if (nativeCode == null) return null;
        NativeCode clone = nativeCodeOf(nativeCode.getClass());
        clone.$(morphContextOf(clone));
        return clone;
    }

    public Declaration _morphWith(Class<? extends Layer> layerClass) {
        return _declaration.addLayer(_model().definitionOf(layerClass));
    }

    public Declaration _morphWith(Definition definition) {
        return _declaration.addLayer(definition);
    }

    public Declaration _morphWith(String definition) {
        return _declaration.addLayer(_model().definitionOf(definition));
    }

    private Layer morphContextOf(NativeCode clone) {
        if (clone.$Class().isAssignableFrom(this.getClass()))
            return this;
        else if (_declaration.is(clone.$Class()))
            return _declaration.as(clone.$Class());
        else if (searchOwner(clone) != null)
            return searchOwner(clone).as(clone.$Class());
        return null;
    }

    private Declaration searchOwner(NativeCode nativeCode) {
        Layer ownerLayer = _declaration.ownerWith(nativeCode.$Class());
        return ownerLayer != null ? ownerLayer._declaration : null;
    }

    @Override
    public String toString() {
        return _declaration.name();
    }
}
