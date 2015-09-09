package tara.magritte;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Declaration extends Predicate {

    protected final List<Layer> layers = new ArrayList<>();
    private Declaration owner;

    public Declaration() {
        this("");
    }

    public Declaration(String name) {
        super(name);
    }

    @Override
    public List<Definition> types() {
        List<String> types = new ArrayList<>(this.typeNames);
        Collections.reverse(types);
        return types.stream().map(t -> ownerWith(Board.class).getDefinition(t)).collect(toList());
    }

    @Override
    public List<Declaration> components() {
        Set<Declaration> declarations = new LinkedHashSet<>();
        for (int i = layers.size() - 1; i >= 0; i--) declarations.addAll(layers.get(i)._components());
        return new ArrayList<>(declarations);
    }

    public void add(Declaration component) {
        for (Layer layer : layers) layer._addComponent(component);
    }

    @Override
    public Map<String, Object> variables() {
        Map<String, Object> variables = new HashMap<>();
        layers.forEach(m -> variables.putAll(m._variables()));
        return variables;
    }

    @Override
    public <T extends Layer> List<T> findComponent(Class<T> aClass) {
        List<T> tList = new ArrayList<>();
        if (is(aClass.getSimpleName()))
            tList.add(as(aClass));
        components().forEach(c -> tList.addAll(c.findComponent(aClass)));
        return tList;
    }

    public Declaration morphWith(Definition definition) {
        if (is(definition.name())) return this;
        putType(definition);
        createLayer(definition);
        removeParentMorph(definition);
        return this;
    }

    public Declaration morphWith(Class<? extends Layer> layerClass) {
        createLayer(layerClass);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Layer> T as(Class<T> layerClass) {
        for (Layer layer : layers)
            if (layerClass.isAssignableFrom(layer.getClass()))
                return (T) layer;
        return null;
    }

    @SuppressWarnings("unused")
    public <T extends Layer> List<T> components(Class<T> layerClass) {
        List<String> types = LayerFactory.names(layerClass);
        return components().stream()
                .filter(c -> c.isAnyOf(types))
                .map(c -> c.as(layerClass))
                .collect(toList());
    }

    @Override
    public void variables(Map<String, Object> variables) {
        variables.forEach((k, v) -> layers.forEach(m -> m._load(k, v)));
    }

    public void set(String name, Object value) {
        for (Layer layer : layers) layer._set(name, value);
    }

    private void createLayer(Definition definition) {
        Layer layer = LayerFactory.create(definition.name, this);
        if (layer != null) this.layers.add(0, layer);
    }

    private void createLayer(Class<? extends Layer> layerClass) {
        Layer layer = LayerFactory.create(layerClass, this);
        if (layer != null) this.layers.add(0, layer);
    }

    private void removeParentMorph(Definition definition) {
        if (definition.parent() == null || definition.parent().isAbstract()) return;
        layers.removeIf(m -> m.getClass() == definition.parent().layerClass());
    }

    public void owner(Declaration owner) {
        this.owner = owner;
    }

    public Declaration owner() {
        return owner;
    }

    public <T extends Layer> T ownerWith(Class<T> $Class) {
        if (owner == null) return null;
        if (owner.is($Class)) return owner.as($Class);
        return owner.ownerWith($Class);
    }


}
