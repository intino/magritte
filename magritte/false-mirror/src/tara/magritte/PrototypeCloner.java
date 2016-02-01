package tara.magritte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PrototypeCloner {

    private final List<Instance> prototypes;
    private final Instance instance;
    private final ModelHandler model;
    private final Map<String, Instance> cloneMap = new HashMap<>();
    private final InstanceLoader loader = cloneMap::get;

    private PrototypeCloner(List<Instance> prototypes, Instance instance, ModelHandler model) {
        this.prototypes = prototypes;
        this.instance = instance;
        this.model = model;
    }

    public static void clone(List<Instance> prototypes, Instance instance, ModelHandler model) {
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
        prototype.typeNames.forEach(n -> clone.addLayer(model.concept(n)));
        cloneComponents(prototype, clone, name);
        cloneMap.put(prototype.name, clone);
        copyVariables(prototype, clone);
        return clone;
    }

    private void cloneComponents(Instance prototype, Instance clone, String name) {
        prototype.layers.forEach(origin -> {
            Layer destination = getLayerFrom(clone, origin);
            prototype.instances().forEach(c -> destination._addInstance(clone(name + "." + c.simpleName(), c, clone)));
        });
    }

    private void copyVariables(Instance prototype, Instance clone) {
        prototype.layers.forEach(origin -> {
            Layer destination = getLayerFrom(clone, origin);
            origin._variables().entrySet().stream()
                    .filter(e -> !e.getValue().isEmpty())
                    .forEach(e -> destination._set(e.getKey(), e.getValue()));
        });
    }

    private Layer getLayerFrom(Instance clone, Layer origin) {
        return clone.layers.stream().filter(l -> l.getClass() == origin.getClass()).findFirst().get();
    }

}
