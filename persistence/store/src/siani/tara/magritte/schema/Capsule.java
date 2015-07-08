package siani.tara.magritte.schema;

import magritte.*;
import siani.tara.magritte.*;
import siani.tara.magritte.Set.Filter;
import siani.tara.magritte.primitives.Resource;
import siani.tara.magritte.io.StashWriter;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.magritte.io.StashSerializer.serialize;
import static siani.tara.magritte.schema.ListSet.empty;


public class Capsule implements PersistentGraph {

    private final Store store;
    private final MemoryGraph model;
    private final List<Gate> gates;

    public Capsule(Store store) {
        this.store = store;
        this.model = new MemoryGraph();
        this.gates = new ArrayList<>();
    }

    public Capsule with(Graph graph) {
        for (Node type : graph.types()) index(type);
        for (Node root : graph.roots()) register(root);
        return this;
    }

    public Node register(String uid) {
        return register(GateReference.of(uid));
    }

    @Override
    public Node register(Reference reference) {
        return register(store.sourceOf(reference));
    }

    private Node register(Node node) {
        GateReference uid = GateReference.of(node);
        if (!store.exists(uid)) save(node);
        return register(uid.toString());
    }

    private Node register(Source source) {
        Gate gate = new Gate(this, source);
        this.gates.add(gate);
        return gate;
    }

    public Capsule open(String uid) {
        return open(GateReference.of(uid));
    }

    public Capsule open(Reference reference) {
        Capsule capsule = new Capsule(store).with(this);
        capsule.register(store.sourceOf(reference));
        return capsule;
    }

    public Capsule load() {
        for (Gate gate : gates) gate.load();
        return this;
    }

    @Override
    public void index(Node node) {
        model.index(node);
    }

    @Override
    public boolean exists(String name) {
        return existsGate(name) || model.exists(name);
    }

    private boolean existsGate(String name) {
        return findGate(name) != null;
    }

    private Gate findGate(String name) {
        for (Gate gate : gates) if (gate.name().equals(name)) return gate;
        return null;
    }

    @Override
    public Node get(String name) {
        return existsGate(name) ? findGate(name) : model.get(name);
    }

    @Override
    public Set<Node> types() {
        return model.types();
    }


    @Override
    public Set<Node> roots() {
        return empty();
    }

    @Override
    public Set<Node> find(Filter filter) {
        //TODO
        return null;
    }

    @Override
    public Node createNode() {
        return new MemoryNode(this);
    }

    @Override
    public Store store() {
        return store;
    }

    @Override
    public void save() {
        for (Gate gate : gates) {
            if (!gate.dirty()) continue;
            save(gate);
        }
    }

    public Set<Gate> gates() {
        return ListSet.cast(gates);
    }

    private void save(Node node) {
        Stash stash = StashWriter.stashOf(node);
        GateReference uid = GateReference.of(node);
        int index = 0;
        store.save(uid, serialize(stash.root));
        for (Resource resource : stash.resources)
            store.save(GateReference.of(node, resource(++index, resource.format())), resource.data());
    }

    private String resource(int index, String format) {
        return index + "." + format;
    }
}
