package io.intino.tara.magritte;

import io.intino.tara.io.Stash;
import io.intino.tara.magritte.stores.ResourcesStore;
import io.intino.tara.magritte.utils.StashHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class Graph extends GraphHandler {

    protected Graph(Store store) {
        super(store);
        model.graph = this;
        model.addLayer(M1.class);
        model.addLayer(M2.class);
        model.addLayer(M3.class);
        model.typeNames.add("Model");
    }

    public static GraphLoad load() {
        return load(new ResourcesStore());
    }

    public static GraphLoad load(Store store) {
        return load("Model", store);
    }

    public static GraphLoad load(String stash) {
        return load(stash, new ResourcesStore());
    }

    public static GraphLoad from(Stash... stashes) {
        return new Graph(new ResourcesStore()).loadStashes(stashes).modelLoad();
    }

    public static GraphLoad load(String stash, Store store) {
        Graph graph = new Graph(store);
        graph.init(stash);
        return graph.modelLoad();
    }

    public Graph loadStashes(String... paths) {
        return loadStashes(asList(paths).stream()
                .filter(p -> !openedStashes.contains(p))
                .map(this::stashOf).toArray(Stash[]::new));
    }

    public Graph loadStashes(Stash... stashes) {
        doLoadStashes(stashes);
        return this;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Graph clone() {
        return GraphCloner.doClone(this, new Graph(this.store));
    }

    public <T extends Layer> T first(Class<T> aClass) {
        List<T> nodes = find(aClass);
        return nodes.isEmpty() ? null : nodes.get(0);
    }

    public <T extends Layer> List<T> find(Class<T> aClass) {
        return model.findNode(aClass);
    }

    public M1 model() {
        return model.as(M1.class);
    }

    public M2 metamodel() {
        return model.as(M2.class);
    }

    public M3 metametamodel() {
        return model.as(M3.class);
    }

    public M1 m1() {
        return model.as(M1.class);
    }

    public M2 m2() {
        return model.as(M2.class);
    }

    public M3 m3() {
        return model.as(M3.class);
    }

    public List<Node> rootList() {
        return unmodifiableList(model.componentList());
    }

    public List<Node> rootList(java.util.function.Predicate<Node> predicate) {
        return unmodifiableList(model.componentList().stream().filter(predicate).collect(Collectors.toList()));
    }

    public <T extends Layer> List<T> rootList(Class<T> layerClass) {
        return model.componentList(layerClass);
    }

    public List<Concept> conceptList() {
        return unmodifiableList(new ArrayList<>(concepts.values()));
    }

    public List<Concept> conceptList(java.util.function.Predicate<Concept> predicate) {
        return concepts.values().stream().filter(predicate).collect(toList());
    }

    public Concept concept(String name) {
        return concepts.get(name);
    }

    public Concept concept(Class<? extends Layer> layerClass) {
        return concepts.get(layerFactory.names(layerClass).get(0));
    }

    public <T extends Layer> T createRoot(Class<T> layerClass) {
        return createRoot(layerClass, "Misc", createNodeName());
    }

    public Node createRoot(Concept concept, String stash) {
        return createRoot(concept, stash, createNodeName());
    }

    public <T extends Layer> T createRoot(Class<T> layerClass, String namespace) {
        return createRoot(layerClass, namespace, createNodeName());
    }

    public Node createRoot(String type, String namespace) {
        return createRoot(concept(type), namespace, createNodeName());
    }

    public <T extends Layer> T createRoot(Class<T> layerClass, String namespace, String name) {
        Node node = createRoot(concept(layerClass), namespace, name);
        return node != null ? node.as(layerClass) : null;
    }

    public Node createRoot(String type, String namespace, String name) {
        return createRoot(concept(type), namespace, name);
    }

    public Node createRoot(Concept concept, String namespace, String name) {
        Node newNode = createNode(concept, namespace, name);
        if (newNode != null) commit(newNode);
        return newNode;
    }

    Node createNode(Concept concept, String namespace, String name) {
        if (!concept.isMain()) {
            LOG.severe("Concept " + concept.id() + " is not main. The node could not be created.");
            return null;
        }
        if (concept.isAbstract()) {
            LOG.severe("Concept " + concept.id() + " is abstract. The node could not be created.");
            return null;
        }
        namespace = namespace == null ? "Misc" : namespace;
        loadStashes(StashHelper.stashWithExtension(namespace));
        if (name != null && nodes.containsKey(namespace + "#" + name)) {
            LOG.warning("Node with id " + namespace + "#" + name + " already exists");
            return null;
        }
        return concept.createNode(namespace, name == null ? createNodeName() : name, model);
    }

    void commit(Node node) {
        model.add(node);
        register(node);
        openedStashes.add(StashHelper.stashWithExtension(node.namespace()));
    }

    @Override
    protected void registerRoot(Node root) {
        this.model.add(root);
    }

    GraphLoad modelLoad() {
        return new GraphLoad();
    }

    public class GraphLoad {

        public GraphLoad loadStashes(String... paths) {
            Graph.this.loadStashes(paths);
            return this;
        }

        public GraphLoad loadStashes(Stash... stashes) {
            Graph.this.loadStashes(stashes);
            return this;
        }

        public <T extends Graph> T wrap(Class<? extends GraphWrapper> applicationClass, Class<? extends GraphWrapper> platformClass) {
            platform = create(platformClass, Graph.this);
            application = create(applicationClass, Graph.this);
            return (T) Graph.this;
        }

        public <T extends Graph> T wrap(Class<? extends GraphWrapper> applicationClass) {
            application = create(applicationClass, Graph.this);
            return (T) Graph.this;
        }

        public <T extends Graph> T platform(Class<? extends GraphWrapper> platformClass) {
            platform = create(platformClass, Graph.this);
            return (T) Graph.this;
        }
    }

}
