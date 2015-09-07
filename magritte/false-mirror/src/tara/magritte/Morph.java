package tara.magritte;

import tara.util.WordGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static tara.magritte.loaders.NativeCodeLoader.nativeCodeOf;

public abstract class Morph {

    protected final Declaration declaration;

    public Morph(Declaration declaration) {
        this.declaration = declaration;
    }

    public Declaration _declaration() {
        return declaration;
    }

    public boolean is(String name) {
        return declaration.is(name);
    }

    public boolean is(Class<? extends Morph> morphClass) {
        return declaration.is(morphClass);
    }

    public boolean is(Definition definition) {
        return declaration.is(definition.name());
    }

    public Declaration _owner() {
        return declaration.owner();
    }

    public <T extends Morph> T _owner(Class<T> morphClass) {
        return declaration.owner(morphClass);
    }

    public <T extends Morph> T as(Class<T> morphClass) {
        return declaration.as(morphClass);
    }

    protected void _set(String name, Object object) {
    }

    protected void _load(String name, Object object) {
    }

    public void _createComponent(Definition definition) {
        _createComponent(definition, WordGenerator.generate());
    }

    public void _createComponent(Definition definition, String componentId) {
        declaration.add(definition.create(componentId));
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
        Declaration context = declaration.is(clone.$Class()) ? declaration : searchOwner(clone);
        return context == null ? this : context.as(clone.$Class());
    }

    private Declaration searchOwner(NativeCode nativeCode) {
        Morph ownerMorph = declaration.owner(nativeCode.$Class());
        return ownerMorph != null ? ownerMorph.declaration : null;
    }

    public void save() {
        PersistenceManager.save(declaration);
    }

    @Override
    public String toString() {
        return declaration.name();
    }

}
