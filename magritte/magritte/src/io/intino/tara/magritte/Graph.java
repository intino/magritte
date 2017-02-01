package io.intino.tara.magritte;

import io.intino.tara.io.Stash;
import io.intino.tara.magritte.stores.ResourcesStore;
import io.intino.tara.magritte.utils.PathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.intino.tara.magritte.utils.PathHelper.canonicalPath;
import static java.util.Arrays.stream;
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

    @SafeVarargs
    public static Graph use(Store store, Class<? extends GraphWrapper>... wrapperClasses) {
        Graph graph = new Graph(store);
        stream(wrapperClasses).filter(Objects::nonNull).forEach(c -> graph.wrappers.put(c, create(c, graph)));
        return graph;
    }

    @SafeVarargs
    public static Graph use(Class<? extends GraphWrapper>... wrapperClasses) {
        return use(new ResourcesStore(), wrapperClasses);
    }

    public Graph load(String... paths) {
        if (paths.length == 0) doLoadPath("Model");
        else doLoadPath(paths);
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

    public <T extends Layer> T createRoot(Class<T> layerClass, String path) {
        return createRoot(layerClass, path, createNodeName());
    }

    public Node createRoot(String type, String path) {
        return createRoot(concept(type), path, createNodeName());
    }

    public <T extends Layer> T createRoot(Class<T> layerClass, String path, String name) {
        Node node = createRoot(concept(layerClass), path, name);
        return node != null ? node.as(layerClass) : null;
    }

    public Node createRoot(String type, String path, String name) {
        return createRoot(concept(type), path, name);
    }

    public Node createRoot(Concept concept, String path, String name) {
        Node newNode = createNode(concept, path, name);
        if (newNode != null) commit(newNode);
        return newNode;
    }

    private Node createNode(Concept concept, String path, String name) {
        if (!concept.isMain()) {
            getGlobal().severe("Concept " + concept.id() + " is not main. The node could not be created.");
            return null;
        }
        if (concept.isAbstract()) {
            getGlobal().severe("Concept " + concept.id() + " is abstract. The node could not be created.");
            return null;
        }
        path = path == null || path.isEmpty() ? "Misc" : path;
        getGlobal().setUseParentHandlers(false);
        load(PathHelper.pathWithExtension(path));
        getGlobal().setUseParentHandlers(true);
        if (name != null && nodes.containsKey(path + "#" + name)) {
            getGlobal().warning("Node with id " + path + "#" + name + " already exists");
            return null;
        }
        return concept.createNode(canonicalPath(path), name == null ? createNodeName() : name, model);
    }

    private void commit(Node node) {
        model.add(node);
        register(node);
        openedStashes.add(PathHelper.pathWithExtension(node.path()));
    }

    @Override
    protected void registerRoot(Node root) {
        this.model.add(root);
    }

}
