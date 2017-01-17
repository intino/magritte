package io.intino.tara.magritte;

import io.intino.tara.io.Stash;
import io.intino.tara.magritte.stores.ResourcesStore;
import io.intino.tara.magritte.utils.StashHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static java.util.logging.Logger.getGlobal;
import static java.util.stream.Collectors.toList;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Graph extends GraphHandler {

    protected Graph(Store store) {
        super(store);
        model.graph = this;
        model.addLayer(M1.class);
        model.addLayer(M2.class);
        model.addLayer(M3.class);
        model.typeNames.add("Model");
    }

    public static Graph use(Store store, Class<? extends Application> application, Class<? extends Platform> platform) {
        try {
            Graph graph = new Graph(store);
            if (application != null) graph.application = create(application.asSubclass(GraphWrapper.class), graph);
            if (platform != null) graph.platform = create(platform.asSubclass(GraphWrapper.class), graph);
            return graph;
        } catch (ClassCastException e) {
            throw new MagritteException("Application and Platform classes must extend GraphWrapper");
        }
    }

    public static Graph use(Store store, Class<? extends Application> application) {
        return use(store, application, null);
    }

    public static Graph use(Class<? extends Application> application, Class<? extends Platform> platform) {
        return use(new ResourcesStore(), application, platform);
    }

    public static Graph use(Class<? extends Application> application) {
        return use(new ResourcesStore(), application, null);
    }


    public static Graph use() {
        return use(new ResourcesStore(), null, null);
    }

    public Graph load(String... namespaces) {
        if (namespaces.length == 0) doLoadNamespace("Model");
        else doLoadNamespace(namespaces);
        return this;
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

    private Node createNode(Concept concept, String namespace, String name) {
        if (!concept.isMain()) {
            getGlobal().severe("Concept " + concept.id() + " is not main. The node could not be created.");
            return null;
        }
        if (concept.isAbstract()) {
            getGlobal().severe("Concept " + concept.id() + " is abstract. The node could not be created.");
            return null;
        }
        namespace = namespace == null || namespace.isEmpty() ? "Misc" : namespace;
        getGlobal().setUseParentHandlers(false);
        load(StashHelper.stashWithExtension(namespace));
        getGlobal().setUseParentHandlers(true);
        if (name != null && nodes.containsKey(namespace + "#" + name)) {
            getGlobal().warning("Node with id " + namespace + "#" + name + " already exists");
            return null;
        }
        return concept.createNode(namespace, name == null ? createNodeName() : name, model);
    }

    private void commit(Node node) {
        model.add(node);
        register(node);
        openedStashes.add(StashHelper.stashWithExtension(node.namespace()));
    }

    @Override
    protected void registerRoot(Node root) {
        this.model.add(root);
    }

}
