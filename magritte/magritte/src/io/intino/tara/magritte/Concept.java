package io.intino.tara.magritte;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.logging.Logger.getGlobal;
import static java.util.stream.Collectors.toList;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Concept extends Predicate {

    final Set<Concept> concepts = new LinkedHashSet<>();
    private final Set<Concept> children = new LinkedHashSet<>();
    private final Set<Concept> instances = new LinkedHashSet<>();
    boolean isAbstract;
    boolean isMetaConcept;
    boolean isMain;
    Class<? extends Layer> layerClass;
    Concept metatype = null;
    Set<Content> contentRules = new LinkedHashSet<>();
    List<Node> nodes = new ArrayList<>();
    Map<String, List<?>> variables = new LinkedHashMap<>();
    Map<String, List<?>> parameters = new LinkedHashMap<>();
    private Concept parent;

    public Concept(String name) {
        super(name);
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isMetaConcept() {
        return isMetaConcept;
    }

    @SuppressWarnings("unused")
    public boolean isMain() {
        return isMain;
    }

    public Class<? extends Layer> layerClass() {
        return layerClass;
    }

    public List<Concept> conceptList() {
        return unmodifiableList(new ArrayList<>(concepts));
    }

    public Concept parent() {
        return parent;
    }

    void parent(Concept parent) {
        if (parent == null) return;
        this.parent = parent;
        putType(parent);
        parent.children.add(this);
    }

    @SuppressWarnings("unused")
    public List<Concept> children() {
        return unmodifiableList(new ArrayList<>(children));
    }

    void concepts(List<Concept> concepts) {
        concepts.forEach(this::putType);
    }

    public List<Node> nodes() {
        return nodes;
    }

    @Override
    protected void putType(Concept concept) {
        if (is(concept.id())) return;
        super.putType(concept);
        concepts.add(concept);
        concept.instances.add(this);
    }

    @SuppressWarnings("unused")
    public List<Concept> instanceList() {
        Set<Concept> instances = new LinkedHashSet<>();
        instances.addAll(this.instances);
        this.instances.forEach(s -> instances.addAll(s.instanceList()));
        return new ArrayList<>(instances);
    }

    public List<Concept> multipleAllowed() {
        return unmodifiableList(contentRules.stream().filter(c -> c.max > 1).map(c -> c.concept).collect(toList()));
    }

    public List<Concept> singleAllowed() {
        return unmodifiableList(contentRules.stream().filter(c -> c.max == 1).map(c -> c.concept).collect(toList()));
    }

    public List<Concept> multipleRequired() {
        return unmodifiableList(contentRules.stream().filter(c -> c.min == 1 && c.max > 1).map(c -> c.concept).collect(toList()));
    }

    public List<Concept> singleRequired() {
        return unmodifiableList(contentRules.stream().filter(c -> c.min == 1 && c.max == 1).map(c -> c.concept).collect(toList()));
    }

    @Override
    public Map<String, List<?>> variables() {
        return Collections.unmodifiableMap(variables);
    }

    @Override
    public <T extends Layer> List<T> findNode(Class<T> aClass) {
        // TODO
        return null;
    }

    public Map<String, List<?>> parameters() {
        return Collections.unmodifiableMap(parameters);
    }

    @Override
    public List<Node> componentList() {
        return unmodifiableList(nodes);
    }

    Node createNode(String path, String name, Node owner) {
        if (isMetaConcept) {
            getGlobal().severe("Node cannot be created. Concept " + this.id + " is a MetaConcept");
            return null;
        }
        return newNode(path + "#" + (name != null ? name : owner.graph().createNodeName()), owner);
    }

    public Node createNode(Node owner) {
        return createNode(owner.graph().createNodeName(), owner);
    }

    public Node createNode(String name, Node owner) {
        if (isMetaConcept) {
            getGlobal().severe("Node cannot be created. Concept " + this.id + " is a MetaConcept");
            return null;
        }
        return newNode(owner.path() + "#" + (name != null ? name : owner.graph().createNodeName()), owner);
    }

    private Node newNode(String name, Node owner) {
        Node node = owner.graph().$node(name);
        node.owner(owner);
        createLayersFor(node);
        if (!owner.is("Model")) owner.add(node);
        return node;
    }

    void createLayersFor(Node node) {
        conceptList().forEach(node::addLayer);
        node.addLayer(this);
        node.syncLayers();
        cloneNodes(node);
        fillVariables(node.as(this));
        fillParameters(node.as(this));
    }

    private void cloneNodes(Node node) {
        conceptList().forEach(t -> t.cloneNodes(node));
        NodeCloner.clone(componentList(), node, node.graph());
    }

    private void fillVariables(Layer layer) {
        conceptList().forEach(c -> c.fillVariables(layer));
        variables.forEach(layer::_load);
    }

    private void fillParameters(Layer layer) {
        conceptList().forEach(c -> c.fillParameters(layer));
        parameters.forEach(layer::_load);
    }

    @Override
    public String toString() {
        return id + "{names=" + concepts.stream().map(m -> m.id).collect(toList()) + '}';
    }

    public boolean is(String concept) {
        return typeNames.contains(concept);
    }

    static class Content {

        Concept concept;
        int min, max;

        Content(Concept concept, int min, int max) {
            this.concept = concept;
            this.min = min;
            this.max = max;
        }
    }
}
