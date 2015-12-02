package tara.magritte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PrototypeCloner {

    private final List<Instance> prototypes;
    private final Instance instance;
    private final Model model;
    private final Map<String, Instance> cloneMap = new HashMap<>();
    private final InstanceLoader loader = cloneMap::get;

    private PrototypeCloner(List<Instance> prototypes, Instance instance, Model model) {
        this.prototypes = prototypes;
        this.instance = instance;
        this.model = model;
    }

    public static void clone(List<Instance> prototypes, Instance instance, Model model) {
        new PrototypeCloner(prototypes, instance, model).execute();
    }

    private void execute() {
        model.loaders.add(loader);
        prototypes.stream()
                .map(p -> clone(instance.name() + "." + model.newInstanceId(), p, instance))
                .forEach(instance::add);
        model.loaders.remove(loader);
    }

    private Instance clone(String name, Instance prototype, Instance owner) {
        Instance clone = new Instance(name);
        clone.owner(owner);
        prototype.typeNames.forEach(n -> clone.addLayer(model.getConcept(n)));
        prototype.components().forEach(c -> clone.add(clone(name + "." + c.simpleName(), c, clone)));
        cloneMap.put(prototype.name, clone);
        prototype.variables().entrySet().stream().filter(e -> e.getValue() != null).forEach(e -> clone.set(e.getKey(), e.getValue()));
        return clone;
    }

}
