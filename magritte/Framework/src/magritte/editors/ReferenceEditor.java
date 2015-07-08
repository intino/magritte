package magritte.editors;

import magritte.Graph;
import magritte.Node;
import magritte.Reference;
import magritte.schema.ListSet;


import java.util.List;

import static magritte.Tag.Link.FanIn;
import static magritte.Tag.Link.FanOut;

public class ReferenceEditor {

    private final Node node;

    public static ReferenceEditor of(Node node) {
        return new ReferenceEditor(node);
    }

    private ReferenceEditor(Node node) {
        this.node = node;
    }

    public ReferenceEditor set(String var, Reference reference) {
        createReferences(node(reference));
        node.set(var, reference);
        return this;
    }

    public ReferenceEditor set(String var, Reference[] references) {
        createReferences(nodes(references));
        node.set(var, references);
        return this;
    }

    public ReferenceEditor unset(String var) {
        removeReferences(referencesIn(var));
        return this;
    }

    private Graph graph() {
        return node.graph();
    }

    private void createReferences(Node... references) {
        for (Node reference : references) {
            if (reference == null) continue;
            Node root = ownerOf(node);
            root.link(reference).as(FanOut);
            reference.link(root).as(FanIn);
        }
    }

    private void removeReferences(Node... references) {
        for (Node reference : references) {
            if (reference == null) continue;
            Node root = ownerOf(node);
            root.unlink(reference).as(FanOut);
            reference.unlink(root).as(FanIn);
        }
    }

    private Node ownerOf(Node node) {
        while (node.owner() != null) node = node.owner();
        return node;
    }

    private Node node(Reference reference) {
        check(reference);
        return graph().get(reference.value());
    }

    protected Node[] referencesIn(String var) {
        Object object = node.get(var);
        if(object == null) return new Node[0];
        return nodes(object.getClass().isArray() ? (Reference[])object : new Reference[] {(Reference)object});
    }

    private Node[] nodes(Reference[] references) {
        List<Node> nodes = ListSet.cast(references).map(this::node).asList();
        return nodes.toArray(new Node[nodes.size()]);
    }

    private void check(Reference reference) {
        if (graph().exists(reference.value())) return;
        Node node = graph().createNode();
        node.set(reference.value());
    }



}
