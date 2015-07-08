package magritte.io;

import magritte.*;
import magritte.primitives.Date;
import magritte.editors.NodeEditor;
import magritte.primitives.Resource;
import magritte.PersistentGraph;
import magritte.schema.GateReference;
import magritte.schema.Stash;
import magritte.schema.Stash.Blob;
import magritte.schema.Stash.Pass;
import magritte.schema.ListSet;

import static magritte.Tag.Case;
import static magritte.Tag.Root;

public abstract class StashReader {

    public static StashReader read(Stash stash) {
        return new StashReader(stash) {
            @Override
            public Node into(PersistentGraph graph) {
                return into(graph.createNode());
            }

        };
    }

    public abstract Node into(PersistentGraph graph);

    private Stash stash;

    private StashReader(Stash stash) {
        this.stash = stash;
    }

    protected Node into(Node node) {
        if (stash.root == null) return node;
        update(node).with(stash.root);
        return node;
    }

    private NodeUpdater update(Node node) {
        return new NodeUpdater(node);
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    private class NodeUpdater extends NodeEditor {

        public NodeUpdater(Node node) {
            super(node);
            node.set(Case);
        }


        public Node with(String type) {
            node.set(type);
            return node;
        }

        public Node with(Stash.Root root) {
            node.set(root.name);
            node.set(Root, Case);
            types(root.types);
            variables(root.variables);
            components(root.components);
            fanIn(root.fanIn);
            fanOut(root.fanOut);
            return node;
        }

        public Node with(Stash.Case component) {
            node.set(component.name);
            node.set(Case);
            types(component.types);
            variables(component.variables);
            components(component.components);
            fanIn(component.fanIn);
            return node;
        }

        protected PersistentGraph graph() {
            return (PersistentGraph) super.graph();
        }

        private Store store() {
            return graph().store();
        }

        public void types(String... types) {
            for (String type : types)
                type(nodeOf(null));
        }

        public void variables(Stash.Variable[] variables) {
            if (variables == null) return;
            for (Stash.Variable variable : variables)
                node.set(variable.name, read(variable.value));
        }

        private Object read(Object value) {
            if (value instanceof Date) return read((Date) value);
            if (value instanceof Date[]) return read((Date[]) value);
            if (value instanceof Blob) return read((Blob) value);
            if (value instanceof Blob[]) return read((Blob[]) value);
            if (value instanceof Pass) return read((Pass) value);
            if (value instanceof Pass[]) return read((Pass[]) value);
            return value;
        }

        private Object[] read(Date[] dates) {
            return ListSet.cast(dates).map(this::read).asList().toArray(new Object[0]);
        }

        private Object read(Date date) {
            return new java.util.Date(date.timestamp());
        }

        private Resource[] read(Blob[] blobs) {
            return ListSet.cast(blobs).map(this::read).asList().toArray(new Resource[0]);
        }

        private Resource read(Blob blob) {
            return new Resource(sourceOf(blob.uid));
        }

        private Reference[] read(Pass[] passes) {
            return ListSet.cast(passes).map(this::read).asList().toArray(new Reference[0]);
        }

        private Reference read(Pass pass) {
            return GateReference.of(pass.uid);
        }

        public void components(Stash.Case... cases) {
            has(nodesOf(cases));
        }

        public void fanOut(Pass... passes) {
            fanOut(nodesOf(passes));
        }

        public void fanIn(Pass... passes) {
            fanIn(nodesOf(passes));
        }

        private Node[] nodesOf(Stash.Content[] contents) {
            return contents != null ? ListSet.cast(contents).map(this::nodeOf).asList().toArray(new Node[0]) : new Node[0];
        }

        private Node nodeOf(Stash.Content content) {
            if (content == null) return null;
            return nodeFor(content);
        }

        private Node nodeFor(Stash.Content content) {
            if (content instanceof Pass) return register((Pass) content);
            if (content instanceof Stash.Case) return nodeFor((Stash.Case) content);
            return null;
        }

        private Node register(Stash.Pass pass) {
            GateReference ref = GateReference.of(pass.uid);
            return exists(ref.value()) ? get(ref.value()) : graph().register(ref);
        }

        private Node nodeFor(String content) {
            return exists(content) ? get(content) : update(createNode()).with(content);
        }

        private Node nodeFor(Stash.Case content) {
            return exists(content.name) ? get(content.name) : update(createNode()).with(content);
        }


        public boolean exists(String name) {
            return graph().exists(name);
        }

        public Node get(String name) {
            return graph().get(name);
        }

        private Source sourceOf(String uid) {
            return store().sourceOf(GateReference.of(uid));
        }
    }
}
