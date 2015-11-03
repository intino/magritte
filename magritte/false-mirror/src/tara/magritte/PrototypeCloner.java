package tara.magritte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PrototypeCloner {

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
        prototypes.stream()
                .map(p -> clone(declaration.name() + "." + model.newDeclarationId(), p, declaration))
                .forEach(declaration::add);
        model.loaders.remove(loader);
    }

    private Declaration clone(String name, Declaration prototype, Declaration owner) {
        Declaration clone = new Declaration(name);
        clone.owner(owner);
        prototype.typeNames.forEach(n -> clone.addLayer(model.getDefinition(n)));
        prototype.components().forEach(c -> clone.add(clone(name + "." + c.simpleName(), c, clone)));
        cloneMap.put(prototype.name, clone);
        prototype.variables().entrySet().stream().filter(e -> e.getValue() != null).forEach(e -> clone.set(e.getKey(), e.getValue()));
        return clone;
    }

}
