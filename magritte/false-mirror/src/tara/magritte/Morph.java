package tara.magritte;

import tara.util.WordGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static tara.magritte.loaders.NativeCodeLoader.nativeCodeOf;

public abstract class Morph {

    protected final Declaration _declaration;

    public Morph(Declaration _declaration) {
        this._declaration = _declaration;
    }

    public Declaration _declaration() {
        return _declaration;
    }

    public boolean is(String name) {
        return _declaration.is(name);
    }

    public boolean is(Class<? extends Morph> morphClass) {
        return _declaration.is(morphClass);
    }

    public boolean is(Definition definition) {
        return _declaration.is(definition.name());
    }

    public Declaration _owner() {
        return _declaration.owner();
    }

    public <T extends Morph> T _owner(Class<T> morphClass) {
        return _declaration.owner(morphClass);
    }

    public <T extends Morph> T as(Class<T> morphClass) {
        return _declaration.as(morphClass);
    }

    protected void _set(String name, Object object) {
    }

    protected void _load(String name, Object object) {
    }

    public void _createComponent(Definition definition) {
        _createComponent(definition, WordGenerator.generate());
    }

    public void _createComponent(Definition definition, String componentId) {
        _declaration.add(definition.create(componentId));
    }

    public List<Declaration> _components() {
        return Collections.emptyList();
    }

    protected void _addComponent(Declaration component) {
    }

    public Map<String, Object> _variables() {
        return Collections.emptyMap();
    }

    protected Object _link(NativeCode nativeCode) {
        if (nativeCode == null) return null;
        return defineContextOf(nativeCodeOf(nativeCode.getClass()));
    }

    private NativeCode defineContextOf(NativeCode nativeCode) {
        Morph morph = morphContextOf(nativeCode);
        nativeCode.$(morph == null ? this : morph);
        return nativeCode;
    }

    private Morph morphContextOf(NativeCode clone) {
        Declaration context = _declaration.is(clone.$Class()) ? _declaration : searchOwner(clone);
        return context == null ? this : context.as(clone.$Class());
    }

    private Declaration searchOwner(NativeCode nativeCode) {
        Morph ownerMorph = _declaration.owner(nativeCode.$Class());
        return ownerMorph != null ? ownerMorph._declaration : null;
    }

    public void save() {
        PersistenceManager.save(_declaration);
    }

    @Override
    public String toString() {
        return _declaration.name();
    }

}
