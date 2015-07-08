package magritte.schema;

import magritte.*;

import static magritte.io.StashDeserializer.stashFrom;
import static magritte.io.StashReader.read;

public class Gate implements Node {
    private final PersistentGraph graph;
    private Node node;
    private Source source;
    private boolean dirty = false;

    public Gate(PersistentGraph graph, Source source) {
        this.graph = graph;
        this.node = null;
        this.source = source;
    }

    public Gate(PersistentGraph graph, Node node) {
        this.graph = graph;
        this.node = node;
        this.source = null;
    }

    public Gate load() {
        if (node != null) return this;
        node = read(stashFrom(source.data())).into(graph);
        graph.index(node);
        return this;
    }

    @Override
    public Reference ref() {
        return source.uid();
    }

    @Override
    public String name() {
        return node == null ? key() : node.name();
    }

    @Override
    public String title() {
        return node == null ? key() : node.title();
    }

    @Override
    public String key() {
        return node == null ? source.uid().value() : node.key();
    }

    @Override
    public Tag[] tags() {
        load();
        return node.tags();
    }

    @Override
    public boolean is(Tag tag) {
        load();
        return node.is(tag);
    }

    @Override
    public <T extends Node> T as(Class<T> class_) {
        return (T) this;
    }

    @Override
    public String[] vars() {
        load();
        return node.vars();
    }

    @Override
    public <T> T get(String name) {
        load();
        return node.get(name);
    }

    @Override
    public Node type() {
        load();
        return node.type();
    }

    @Override
    public Set<Node> types() {
        load();
        return node.types();
    }

    @Override
    public Node parent() {
        load();
        return node.parent();
    }

    @Override
    public Set<Node> children() {
        load();
        return node.children();
    }

    @Override
    public Node root() {
        load();
        return node.root();
    }

    @Override
    public Node owner() {
        load();
        return node.owner();
    }

    @Override
    public Set<Node> members(Member member) {
        load();
        return node.members(member);
    }

    @Override
    public Set<Node> fanIn() {
        load();
        return node.fanIn();
    }

    @Override
    public Set<Node> fanOut() {
        load();
        return node.fanOut();
    }

    @Override
    public void set(String name) {
        load();
        dirty = true;
        node.set(name);
    }

    @Override
    public void set(Tag... tags) {
        load();
        dirty = true;
        node.set(tags);
    }

    @Override
    public <T> void set(String name, T value) {
        load();
        dirty = true;
        node.set(name, value);
    }


    @Override
    public LinkAction link(Node node) {
        load();
        dirty = true;
        return this.node.link(node);
    }


    @Override
    public LinkAction unlink(Node node) {
        load();
        dirty = true;
        return this.node.unlink(node);
    }

    @Override
    public Graph graph() {
        return graph;
    }

    public boolean dirty() {
        return dirty || source == null;
    }

    @Override
    public String toString() {
        return node != null ? node.toString() : "Gate[" + source.uid().toString() + "]";
    }

}