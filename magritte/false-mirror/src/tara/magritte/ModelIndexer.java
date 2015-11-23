package tara.magritte;

import tara.Index;
import tara.Text;

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
        model.definitions().forEach(d -> index(index.edit(d.name), d));
        return index;
    }

    private void index(Index.Edition edit, Declaration declaration) {
        declaration.types().forEach(t -> edit.link("is", t.name));
        declaration.components().forEach(c -> edit.link("owner", c.name));
        declaration.variables().entrySet().stream()
                .filter(e -> e.getValue() != null)
                .filter(e -> e.getValue() instanceof Layer)
                .forEach(e -> edit.link(e.getKey(), ((Layer)e.getValue())._name()));
        declaration.variables().entrySet().stream()
                .filter(e -> e.getValue() != null)
                .filter(e -> !(e.getValue() instanceof Layer) && !(e.getValue() instanceof NativeCode))
                .forEach(e -> edit.set(e.getKey(), Text.of(e.getValue().toString())));
        declaration.components().forEach(c -> index(index.edit(c.name), c));
    }

    private void index(Index.Edition edit, Definition definition) {
        definition.types().forEach(t -> edit.link("is", t.name));
        definition.allowsMultiple().forEach(t -> edit.link("has", t.name));
        definition.allowsSingle().forEach(t -> edit.link("has", t.name));
        definition.requiresMultiple().forEach(t -> edit.link("has", t.name));
        definition.requiresSingle().forEach(t -> edit.link("has", t.name));
    }
}
