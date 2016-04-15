package tara.magritte;

import tara.Index;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ModelIndexer {

    private final Index index;

    @SuppressWarnings("unused")
    public static Index index(Graph graph){
        return new ModelIndexer().doIndex(graph);
    }

    private ModelIndexer() {
        index = new Index();
    }

    private Index doIndex(Graph graph) {
        graph.rootList().forEach(d -> index(index.edit(d.id), d));
//        graph.conceptList().forEach(d -> index(index.edit(d.id), d));
        return index;
    }

    private void index(Index.Edition edit, Node node) {
        indexTypes(edit, node.conceptList());
        indexVariables(edit, node.variables());
        indexComponents(edit, node.componentList());
    }

    private void indexTypes(Index.Edition edit, List<Concept> types) {
        types.forEach(t -> edit.link("is", t.id));
    }

    private void indexComponents(Index.Edition edit, List<Node> components) {
        components.forEach(c -> edit.link("has", c.id));
        components.forEach(c -> index(index.edit(c.id), c));
    }

    private void indexVariables(Index.Edition edit, Map<String, List<?>> variables) {
//        variables.entrySet().stream()
//                .filter(notNull()).filter(isLayer())
//                .forEach(e -> linkReference(edit, e));
//        variables.entrySet().stream()
//                .filter(notNull()).filter(isString())
//                .forEach(e -> edit.set(e.getKey(), Text.of(e.getValue().toString())));
//        variables.entrySet().stream()
//                .filter(notNull()).filter(isNotString()).filter(isNotLayer()).filter(isNotNative())
//                .forEach(e -> edit.set(e.getKey(), Text.of(e.getValue().toString())));
    }

    private void linkReference(Index.Edition edit, Map.Entry<String, Object> entry) {
        Node node = layerOf(entry).node();
        while(node.owner() != null){
            edit.link(entry.getKey(), node.id());
            node = node.owner();
        }
    }

    private Layer layerOf(Map.Entry<String, Object> entry) {
        return (Layer) entry.getValue();
    }

    private Predicate<Map.Entry<String, Object>> isNotNative() {
        return e -> !(e.getValue() instanceof NativeCode);
    }

    private Predicate<Map.Entry<String, Object>> isNotLayer() {
        return e -> !(e.getValue() instanceof Layer);
    }

    private Predicate<Map.Entry<String, Object>> isNotString() {
        return e -> !(e.getValue() instanceof String);
    }

    private Predicate<Map.Entry<String, Object>> isString() {
        return e -> e.getValue() instanceof String;
    }

    private Predicate<Map.Entry<String, Object>> isLayer() {
        return e -> e.getValue() instanceof Layer;
    }

    private java.util.function.Predicate<Map.Entry<String, Object>> notNull() {
        return e -> e.getValue() != null;
    }

//    private void index(Index.Edition edit, Concept concept) {
//        concept.conceptList().forEach(t -> edit.link("is", t.id));
//        concept.multipleAllowed().forEach(t -> edit.link("has", t.id));
//        concept.singleAllowed().forEach(t -> edit.link("has", t.id));
//        concept.multipleRequired().forEach(t -> edit.link("has", t.id));
//        concept.singleRequired().forEach(t -> edit.link("has", t.id));
//    }
}
