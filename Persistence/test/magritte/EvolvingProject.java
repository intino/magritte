package magritte;

import magritte.editors.GraphEditor;
import magritte.evolution.ModelEvolver;
import magritte.handlers.NodeProducer;
import magritte.schema.MemoryGraph;

import static magritte.Node.Member.Component;
import static magritte.Tag.Case;
import static magritte.Tag.Link.OwnedMember;
import static magritte.Tag.Link.Owner;

public class EvolvingProject extends GraphEditor {

    private EvolvingProject(Graph graph) {
        super(graph);
    }

    public static EvolvingProject start() {
        return new EvolvingProject(new MemoryGraph());
    }


    public EvolvingProject load(final Graph priorGraph) {
        ModelEvolver.evolve(graph).from(priorGraph);
        return this;
    }

    public EvolvingProject v1() {
        def("Folder").type("Concept").has("Document");
        def("Document").type("Concept");
        def("Students").type("Folder").set(Case);
        return this;
    }

    public EvolvingProject v2() {
        def("Folder").type("Concept").has("Document");
        def("Document").type("Concept").has("Document.FIeld");
        def("Document.Field").type("Concept").set("!date", "1");
        def("Students").type("Folder").set(Case);
        return this;
    }

    public EvolvingProject v3() {
        def("Folder").type("Concept").has($(2));
        def("Document").type("Concept").has($(3));
        def("Document.Field#1001").type("Concept").set("!date", "#");
        def("Students").type("Folder").set(Case);
        return this;
    }

    public EvolvingProject v4() {
        def("Folder").type("Concept").has($(2));
        def("Document").type("Concept").has($(3));
        def("Document.Section").type("Concept").has($(4));
        def("Document.Section.Field#1001").type("Concept").set("!date", "&");
        def("Students").type("Folder").set(Case);
        return this;
    }

    public EvolvingProject v5() {
        def("Folder").type("Concept").has($(2));
        def("Document").type("Concept").has($(3));
        def("Document.Field#1001").type("Concept").set("!date", "%");
        def("Students").type("Folder").set(Case);
        return this;
    }

    public EvolvingProject createDocuments(final String... values) {
        for (final String value : values) {
            createDocument(value.split("\\."));
        }
        return this;
    }


    private void createDocument(String[] data) {
        Node students = graph.get("Students");
        Node type = graph.get("Document");
        Node node = NodeProducer.produce(graph.createNode()).with(type).node();
        try {
            node.set("label", data[0]);
            Node component = node.members(Component).get(0);
            while (component != null) {
                if (component.type().title().contains("Field"))
                    component.set("linkSet", data[1]);
                component = component.members(Component).get(0);
            }
        }
        catch (Exception ignored) {
        }
        students.link(node).as(OwnedMember);
        node.link(students).as(Owner);
    }

    @Override
    public void write() {
    }

}
