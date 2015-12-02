package tara.magritte;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Instance extends Predicate {

    protected final List<Layer> layers = new ArrayList<>();
    private Instance owner;

    public Instance() {
        this("");
    }

    public Instance(String name) {
        super(name);
    }

    @Override
    public List<Concept> types() {
        List<String> types = new ArrayList<>(this.typeNames);
        Collections.reverse(types);
        return types.stream().map(t -> model().getDefinition(t)).collect(toList());
    }

    public Model.Soil root() {
        Instance instance = this;
        while(instance.owner != null)
            instance = instance.owner;
        return (Model.Soil) instance;
    }

    @Override
    public List<Instance> components() {
        Set<Instance> instances = new LinkedHashSet<>();
        for (int i = layers.size() - 1; i >= 0; i--) instances.addAll(layers.get(i)._components());
        return new ArrayList<>(instances);
    }

    public void add(Instance component) {
        for (Layer layer : layers) layer._addComponent(component);
    }

    @Override
    public Map<String, Object> variables() {
        Map<String, Object> variables = new HashMap<>();
        layers.forEach(m -> variables.putAll(m._variables()));
        return variables;
    }

    @Override
    public <T extends Layer> List<T> findComponents(Class<T> aClass) {
        List<T> tList = new ArrayList<>();
        if (is(aClass))
            tList.add(as(aClass));
        components().forEach(c -> tList.addAll(c.findComponents(aClass)));
        return tList;
    }

    public Instance addLayer(Concept concept) {
        if (is(concept.name())) return this;
        putType(concept);
        createLayer(concept);
        removeParentMorph(concept);
        return this;
    }

    public Instance addLayer(Class<? extends Layer> layerClass) {
        createLayer(layerClass);
        return this;
    }

    public Instance removeLayer(Concept concept) {
        if (!is(concept.name())) return this;
        deleteType(concept);
        deleteLayer(concept);
        return this;
    }

    public Instance removeLayer(Class<? extends Layer> layerClass) {
        createLayer(layerClass);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Layer> T as(Class<T> layerClass) {
        for (Layer layer : layers)
            if (layerClass.isAssignableFrom(layer.getClass())) return (T) layer;
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

    public void load(String name, Object value) {
        for (Layer layer : layers) layer._load(name, value);
    }

    public void set(String name, Object value) {
        for (Layer layer : layers) layer._set(name, value);
    }

    public void owner(Instance owner) {
        this.owner = owner;
    }

    public Instance owner() {
        return owner;
    }

    public <T extends Layer> T ownerWith(Class<T> $Class) {
        if (owner == null) return null;
        if (owner.is($Class)) return owner.as($Class);
        return owner.ownerWith($Class);
    }

    private void createLayer(Concept concept) {
        Layer layer = LayerFactory.create(concept.name, this);
        if (layer != null) this.layers.add(0, layer);
    }

    private void deleteLayer(Concept concept) {
        layers.remove(as(concept.layerClass()));
    }

    private void createLayer(Class<? extends Layer> layerClass) {
        Layer layer = LayerFactory.create(layerClass, this);
        if (layer != null) this.layers.add(0, layer);
    }

    private void removeParentMorph(Concept concept) {
        if (concept.parent() == null || concept.parent().isAbstract()) return;
        layers.removeIf(m -> m.getClass() == concept.parent().layerClass());
    }

    public Model model() {
        return root().model();
    }
}