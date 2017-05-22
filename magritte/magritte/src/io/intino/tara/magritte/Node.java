package io.intino.tara.magritte;

import io.intino.tara.magritte.utils.PathHelper;

import java.util.*;

import static java.util.logging.Logger.getGlobal;
import static java.util.stream.Collectors.toList;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Node extends Predicate {

    final List<Layer> layers = new ArrayList<>();
    private Node owner;

    public Node() {
        this("");
    }

    public Node(String name) {
        super(name);
    }

    @Override
    public List<Concept> conceptList() {
        List<Concept> result = new ArrayList<>();
        Graph graph = graph();
        for (String typeName : typeNames) result.add(graph.concept(typeName));
        Collections.reverse(result);
        return result;
    }

    public Model model() {
        Node node = this;
        while (node.owner != null)
            node = node.owner;
        return (Model) node;
    }

    public Node root() {
        Node node = this;
        while (!(node.owner instanceof Model))
            node = node.owner;
        return node;
    }

    public void add(Node node) {
        for (Layer layer : layers) layer.addNode$(node);
    }

    @Override
    public Map<String, List<?>> variables() {
        Map<String, List<?>> variables = new HashMap<>();
        layers.forEach(m -> variables.putAll(m.variables$()));
        return variables;
    }

    @Override
    public <T extends Layer> List<T> findNode(Class<T> layerClass) {
        List<T> tList = new ArrayList<>();
        if (is(layerClass))
            tList.add(as(layerClass));
        componentList().forEach(c -> tList.addAll(c.findNode(layerClass)));
        return tList;
    }

    protected void remove(Node node) {
        layers.forEach(l -> l.removeNode$(node));
    }

    public void addLayers(List<Concept> concepts) {
        concepts.forEach(this::addLayer);
    }

    public Node addLayer(Concept concept) {
        if (is(concept.id)) return this;
        putType(concept);
        createLayer(concept);
        removeParentLayer(concept);
        return this;
    }

    public Node addLayer(Class<? extends Layer> layerClass) {
        createLayer(layerClass);
        return this;
    }

    public void removeLayers(List<Concept> concepts) {
        concepts.forEach(this::removeLayer);
    }

    @SuppressWarnings("unused")
    public Node removeLayer(Concept concept) {
        if (!is(concept.id())) return this;
        deleteType(concept);
        deleteLayer(concept.layerClass());
        return this;
    }

    public Node removeLayer(Class<? extends Layer> layerClass) {
        deleteLayer(layerClass);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Layer> T as(Class<T> layerClass) {
        for (Layer layer : layers)
            if (layerClass.isAssignableFrom(layer.getClass())) return (T) layer;
        return null;
    }

    public Layer as(String conceptName) {
        return as(graph().layerFactory.layerClass(conceptName));
    }

    @Override
    public List<Node> componentList() {
        Set<Node> nodes = new LinkedHashSet<>();
        reverseListOf(layers).forEach(l -> nodes.addAll(l.componentList$()));
        return new ArrayList<>(nodes);
    }

    @SuppressWarnings("unused")
    public <T extends Layer> List<T> componentList(Class<T> layerClass) {
        List<String> types = graph().layerFactory.names(layerClass);
        return componentList().stream()
                .filter(c -> c.isAnyOf(types))
                .map(c -> c.as(layerClass))
                .collect(toList());
    }

    public void owner(Node owner) {
        this.owner = owner;
    }

    public Node owner() {
        return owner;
    }

    public <T extends Layer> T ownerAs(Class<T> layerClass) {
        if (owner == null) return null;
        if (owner.is(layerClass)) return owner.as(layerClass);
        return owner.ownerAs(layerClass);
    }

    void load(String varName, List<?> value) {
        layers.forEach(l -> l.load$(varName, value));
    }

    public void load(Layer layer, String name, List<?> values) {
        if (layer.core$() == this)
            layer.load$(name, values);
        else
            getGlobal().severe("Layer does not belong to node " + name);
    }

    void set(String varName, List<?> value) {
        layers.forEach(l -> l.set$(varName, value));
    }

    public void set(Layer layer, String name, List<?> values) {
        if (layer.core$() == this)
            layer.set$(name, values);
        else
            getGlobal().severe("Layer does not belong to node " + name);
    }

    public void save() {
        graph().save(this);
    }

    public void createNode(String name, Concept concept) {
        add(concept.createNode(name, this));
    }

    public <T extends Layer> T addFacet(Class<T> layerClass) {
        return (T) addFacet(graph().layerFactory.names(layerClass).get(0));
    }

    public Layer addFacet(String concept) {
        return addFacet(graph().concept(concept));
    }

    public Layer addFacet(Concept concept) {
        concept.createLayersFor(this);
        syncLayers();
        return as(concept);
    }

    public void removeFacet(Class<? extends Layer> layerClass) {
        removeFacet(graph().layerFactory.names(layerClass).get(0));
    }

    public void removeFacet(String concept) {
        removeFacet(graph().concept(concept));
    }

    public void removeFacet(Concept concept) {
        removeLayer(concept);
    }

    private void createLayer(Concept concept) {
        Layer layer = graph().layerFactory.create(concept.id, this);
        if (layer != null) this.layers.add(0, layer);
    }

    private void deleteLayer(Class<? extends Layer> layerClass) {
        layers.remove(as(layerClass));
    }

    private void createLayer(Class<? extends Layer> layerClass) {
        Layer layer = graph().layerFactory.create(layerClass, this);
        if (layer != null) this.layers.add(0, layer);
    }

    private void removeParentLayer(Concept concept) {
        if (concept.parent() == null || concept.parent().isAbstract()) return;
        Layer toRemove = null;
        for (Layer layer : layers) {
            if (layer.getClass() != concept.parent().layerClass) continue;
            toRemove = layer;
            break;
        }
        if (toRemove != null) layers.remove(toRemove);
    }

    public Graph graph() {
        return model().graph();
    }

    public String path() {
        return PathHelper.pathOf(id);
    }

    private <T> List<T> reverseListOf(List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.reverse(result);
        return result;
    }

    public void delete() {
        graph().remove(this);
    }

    public boolean is(String concept) {
        return typeNames.contains(concept);
    }

    public boolean is(Concept concept) {
        return typeNames.contains(concept.name());
    }

    public boolean is(Class<? extends Layer> layer) {
        return isAnyOf(concepts(layer));
    }

    private List<String> concepts(Class<? extends Layer> layerClass) {
        return graph().layerFactory.names(layerClass);
    }

    boolean isAnyOf(List<String> concepts) {
        return concepts.stream().anyMatch(this::is);
    }

    public Layer as(Concept concept) {
        return as(concept.id);
    }

    void syncLayers() {
        layers.forEach(l -> layers.forEach(l::sync$));
    }
}