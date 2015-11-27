package tara.magritte;

import tara.Index;
import tara.Text;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ModelIndexer {

    private final Index index;

    public static Index index(Model model){
        return new ModelIndexer().doIndex(model);
    }

    public ModelIndexer() {
        index = new Index();
    }

    private Index doIndex(Model model) {
        model.roots().forEach(d -> index(index.edit(d.name), d));
//        model.definitions().forEach(d -> index(index.edit(d.name), d));
        return index;
    }

    private void index(Index.Edition edit, Declaration declaration) {
        indexTypes(edit, declaration.types());
        indexVariables(edit, declaration.variables());
        indexComponents(edit, declaration.components());
    }

    private void indexTypes(Index.Edition edit, List<Definition> types) {
        types.forEach(t -> edit.link("is", t.name));
    }

    private void indexComponents(Index.Edition edit, List<Declaration> components) {
        components.forEach(c -> edit.link("has", c.name));
        components.forEach(c -> index(index.edit(c.name), c));
    }

    private void indexVariables(Index.Edition edit, Map<String, Object> variables) {
        variables.entrySet().stream()
                .filter(notNull()).filter(isLayer())
                .forEach(e -> linkReference(edit, e));
        variables.entrySet().stream()
                .filter(notNull()).filter(isString())
                .forEach(e -> edit.set(e.getKey(), Text.of(e.getValue().toString())));
        variables.entrySet().stream()
                .filter(notNull()).filter(isNotString()).filter(isNotLayer()).filter(isNotNative())
                .forEach(e -> edit.set(e.getKey(), Text.of(e.getValue().toString())));
    }

    private void linkReference(Index.Edition edit, Map.Entry<String, Object> entry) {
        Declaration declaration = layerOf(entry)._declaration;
        while(declaration.owner() != null){
            edit.link(entry.getKey(), declaration.name());
            declaration = declaration.owner();
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

//    private void index(Index.Edition edit, Definition definition) {
//        definition.types().forEach(t -> edit.link("is", t.name));
//        definition.allowsMultiple().forEach(t -> edit.link("has", t.name));
//        definition.allowsSingle().forEach(t -> edit.link("has", t.name));
//        definition.requiresMultiple().forEach(t -> edit.link("has", t.name));
//        definition.requiresSingle().forEach(t -> edit.link("has", t.name));
//    }
}
