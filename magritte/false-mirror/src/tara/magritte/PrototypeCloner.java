package tara.magritte;

import tara.util.WordGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrototypeCloner {

    private final List<Declaration> prototypes;
    private final Declaration declaration;
    private final Model model;
    private final Map<String, Declaration> cloneMap = new HashMap<>();
    private final DeclarationLoader loader = cloneMap::get;

    private PrototypeCloner(List<Declaration> prototypes, Declaration declaration, Model model) {
        this.prototypes = prototypes;
        this.declaration = declaration;
        this.model = model;
    }

    public static void clone(List<Declaration> prototypes, Declaration declaration, Model model) {
        new PrototypeCloner(prototypes, declaration, model).execute();
    }

    private void execute() {
        model.loaders.add(loader);
        prototypes.forEach(p -> clone(declaration.name() + "." + WordGenerator.generate(), p, declaration));
        model.loaders.remove(loader);
    }

    private Declaration clone(String name, Declaration prototype, Declaration owner) {
        Declaration clone = new Declaration(name);
        clone.owner(owner);
        prototype.types().forEach(clone::morphWith);
        prototype.components().forEach(c -> clone.add(clone(name + "." + c.shortName(), c, clone)));
        cloneMap.put(prototype.name, clone);
        owner.add(clone);
        return clone;
    }

}
