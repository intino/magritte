package tara.magritte;

import tara.io.Facet;
import tara.io.Prototype;
import tara.io.Stash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class StashReader {

    private final Model model;

    public StashReader(Model model) {
        this.model = model;
    }

    public void read(Stash stash) {
        loadConcepts(stash.concepts);
        loadInstances(model.soil, stash.cases);
    }

    private void loadConcepts(List<tara.io.Concept> rawConcepts) {
        for (tara.io.Concept rawConcept : rawConcepts) {
            LayerFactory.register(rawConcept.name, rawConcept.className);
            loadConcept(model.concept(rawConcept.name), rawConcept);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private void loadConcept(Concept concept, tara.io.Concept rawConcept) {
        concept.parent(model.concept(rawConcept.parent));
        concept.types(metaTypesOf(typesWithoutConcept(rawConcept).map(name -> model.concept(name))).collect(toList()));
        concept.isAbstract = rawConcept.isAbstract;
        concept.isMetaConcept = rawConcept.isMetaConcept;
        concept.isMain = rawConcept.isMain;
        concept.layerClass = LayerFactory.layerClass(concept.name);
        concept.allowsMultiple = rawConcept.allowsMultiple.stream().map(name -> model.concept(name)).collect(toSet());
        concept.allowsSingle = rawConcept.allowsSingle.stream().map(name -> model.concept(name)).collect(toSet());
        concept.requiresMultiple = rawConcept.requiresMultiple.stream().map(name -> model.concept(name)).collect(toSet());
        concept.requiresSingle = rawConcept.requiresSingle.stream().map(name -> model.concept(name)).collect(toSet());
        concept.components = rawConcept.instances.stream().map(c -> loadInstance(model.instance(c.name), c)).collect(toList());
        concept.prototypes = rawConcept.prototypes.stream().map(p -> loadPrototype(model.soil, p)).collect(toList());
        concept.variables = rawConcept.variables.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK));
    }

    private Stream<String> typesWithoutConcept(tara.io.Concept taraConcept) {
        return taraConcept.types.stream().filter(t -> !t.equals("Concept") && !t.equals("MetaConcept"));
    }

    private void loadInstances(Instance parent, List<tara.io.Instance> rawInstances) {
        for (tara.io.Instance rawInstance : rawInstances) {
            Instance instance = model.instance(rawInstance.name);
            instance.owner(parent);
            loadInstance(instance, rawInstance);
            parent.add(instance);
        }
    }

    private Instance loadInstance(Instance instance, tara.io.Instance rawInstance) {
        addConcepts(instance, rawInstance.facets);
        loadInstances(instance, rawInstance.facets.stream().flatMap(f -> f.instances.stream()).collect(toList()));
        clonePrototypes(instance);
        saveVariables(instance, rawInstance);
        return instance;
    }

    private void addConcepts(Instance instance, List<Facet> facets) {
        instance.addLayers(metaTypesOf(facets.stream().map(f -> model.concept(f.name))).collect(toList()));
        instance.layers.forEach(l -> instance.layers.forEach(l::_facet));
    }

    private void saveVariables(Instance instance, tara.io.Instance taraInstance) {
        taraInstance.facets.stream().forEach(f -> model.addVariableIn(instance.as(f.name), variablesOf(f)));
    }

    private Map<String, Object> variablesOf(Facet facet) {
        Map<String, Object> variables = new HashMap<>();
        variables.putAll(model.concept(facet.name).variables());
        variables.putAll(facet.variables.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK)));
        return variables;
    }

    private void clonePrototypes(Instance instance) {
        PrototypeCloner.clone(prototypesOf(instance), instance, model);
    }

    private List<Instance> prototypesOf(Instance instance) {
        List<Instance> prototypes = new ArrayList<>();
        instance.types().forEach(t -> t.prototypes().forEach(prototypes::add));
        return prototypes;
    }

    private Instance loadPrototype(Instance parent, tara.io.Prototype prototype) {
        Instance instance = createPrototype(prototype);
        instance.owner(parent);
        addConcepts(instance, prototype.facets);
        loadVariables(prototype, instance);
        addComponentPrototypes(instance, prototype.facets.stream().flatMap(f -> f.instances.stream()).collect(toList()));
        parent.add(instance);
        return instance;
    }

    private void loadVariables(Prototype prototype, Instance instance) {
        prototype.facets.forEach(f -> {
            Layer layer = instance.as(f.name);
            variablesOf(f).forEach(layer::_load);
        });
    }

    private Instance createPrototype(Prototype prototype) {
        Instance instance = prototype.name == null ? new Instance() : model.instance(prototype.name);
        if (prototype.className != null) {
            LayerFactory.register(instance.name, prototype.className);
            instance.addLayer(model.concept(prototype.name));
        }
        return instance;
    }

    private void addComponentPrototypes(Instance aInstance, List<tara.io.Instance> prototypes) {
        for (tara.io.Instance prototype : prototypes) loadPrototype(aInstance, (Prototype)prototype);
    }

    private Stream<Concept> metaTypesOf(Stream<Concept> metaConcepts) {
        List<Concept> concepts = new ArrayList<>();
        metaConcepts.forEach(d -> {
            concepts.addAll(metaTypesOf(d.types().stream()).collect(toList()));
            concepts.add(d);
        });
        return concepts.stream();
    }

}
