package tara.magritte;

import tara.util.WordGenerator;

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

    protected void _set(String name, Object object) {
    }

    protected void _load(String name, Object object) {
    }

    public Map<String, Object> _variables() {
        return Collections.emptyMap();
    }

    public void _createComponent(Definition definition) {
        _createComponent(definition, WordGenerator.generate());
    }

    public void _createComponent(Definition definition, String componentId) {
        _declaration.add(definition.create(componentId, _declaration));
    }

    public List<Declaration> _components() {
        return Collections.emptyList();
    }

    protected void _addComponent(Declaration component) {
    }

    public <T extends Model> T _model(Class<T> modelClass){
        return null;
    }

    protected Object _link(NativeCode nativeCode) {
        if (nativeCode == null) return null;
        return defineContextOf(nativeCodeOf(nativeCode.getClass()));
    }

    private NativeCode defineContextOf(NativeCode nativeCode) {
        Layer layer = morphContextOf(nativeCode);
        nativeCode.$(layer == null ? this : layer);
        return nativeCode;
    }

    private Layer morphContextOf(NativeCode clone) {
        Declaration context = _declaration.is(clone.$Class()) ? _declaration : searchOwner(clone);
        return context == null ? this : context.as(clone.$Class());
    }

    private Declaration searchOwner(NativeCode nativeCode) {
        Layer ownerLayer = _declaration.ownerWith(nativeCode.$Class());
        return ownerLayer != null ? ownerLayer._declaration : null;
    }

    public void save() {
        _declaration.ownerWith(Board.class).save(_declaration);
    }

    @Override
    public String toString() {
        return _declaration.name();
    }

}
