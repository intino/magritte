package tara.magritte;

import tara.Index;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ModelIndexer {

    private final Index index;

    @SuppressWarnings("unused")
    public static Index index(Model model){
        return new ModelIndexer().doIndex(model);
    }

    private ModelIndexer() {
        index = new Index();
    }

    private Index doIndex(Model model) {
        model.roots().forEach(d -> index(index.edit(d.id), d));
//        model.concepts().forEach(d -> index(index.edit(d.id), d));
        return index;
    }

    private void index(Index.Edition edit, Instance instance) {
        indexTypes(edit, instance.types());
        indexVariables(edit, instance.variables());
        indexComponents(edit, instance.components());
    }

    private void indexTypes(Index.Edition edit, List<Concept> types) {
        types.forEach(t -> edit.link("is", t.id));
    }

    private void indexComponents(Index.Edition edit, List<Instance> components) {
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
        Instance instance = layerOf(entry).instance();
        while(instance.owner() != null){
            edit.link(entry.getKey(), instance.id());
            instance = instance.owner();
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
//        concept.types().forEach(t -> edit.link("is", t.id));
//        concept.allowsMultiple().forEach(t -> edit.link("has", t.id));
//        concept.allowsSingle().forEach(t -> edit.link("has", t.id));
//        concept.requiresMultiple().forEach(t -> edit.link("has", t.id));
//        concept.requiresSingle().forEach(t -> edit.link("has", t.id));
//    }
}
