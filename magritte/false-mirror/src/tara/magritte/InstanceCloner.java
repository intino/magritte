package tara.magritte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InstanceCloner {

    private final List<Instance> instances;
    private final Instance instance;
    private final ModelHandler model;
    private final Map<String, Instance> cloneMap = new HashMap<>();
    private final InstanceLoader loader = cloneMap::get;

    private InstanceCloner(List<Instance> instances, Instance instance, ModelHandler model) {
        this.instances = instances;
        this.instance = instance;
        this.model = model;
    }

    public static void clone(List<Instance> toClone, Instance instance, ModelHandler model) {
        new InstanceCloner(toClone, instance, model).execute();
    }

    private void execute() {
        model.loaders.add(loader);
        instances.stream()
            .map(p -> clone(instance.id() + "." + model.newInstanceId(), p, instance))
                .forEach(instance::add);
        model.loaders.remove(loader);
    }

    private Instance clone(String name, Instance toClone, Instance owner) {
        Instance clone = new Instance(name);
        clone.owner(owner);
        toClone.typeNames.forEach(n -> clone.addLayer(model.concept(n)));
        cloneComponents(toClone, clone, name);
        cloneMap.put(toClone.id, clone);
        copyVariables(toClone, clone);
        return clone;
    }

    private void cloneComponents(Instance toClone, Instance clone, String name) {
        toClone.layers.forEach(origin -> {
            Layer destination = getLayerFrom(clone, origin);
            toClone.instances().forEach(c -> destination.addInstance(clone(name + "." + c.name(), c, clone)));
        });
    }

    private void copyVariables(Instance toClone, Instance clone) {
        toClone.layers.forEach(origin -> {
            Layer destination = getLayerFrom(clone, origin);
            origin.variables().entrySet().stream()
                    .filter(e -> !e.getValue().isEmpty())
                    .forEach(e -> destination._set(e.getKey(), e.getValue()));
        });
    }

    private Layer getLayerFrom(Instance clone, Layer origin) {
        return clone.layers.stream().filter(l -> l.getClass() == origin.getClass()).findFirst().get();
    }

}
