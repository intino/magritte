package tara.magritte;

import tara.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

class StashReader {

    private final Model model;
    public StashReader(Model model) {
        this.model = model;
    }

    public void read(Stash stash) {
        loadConcepts(stash.concepts);
        loadInstance(model.soil, stash.cases);
    }

    private void loadConcepts(List<tara.io.Concept> concepts) {
        for (tara.io.Concept taraConcept : concepts) {
            LayerFactory.register(taraConcept.name, taraConcept.className);
            loadConcept(model.getConcept(taraConcept.name), taraConcept);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private Concept loadConcept(Concept concept, tara.io.Concept taraConcept) {
        concept.isAbstract(taraConcept.isAbstract);
        concept.isTerminal(taraConcept.isTerminal);
        concept.isMain(taraConcept.isMain);
        concept.layerClass(LayerFactory.layerClass(concept.name));
        concept.parent(model.getConcept(taraConcept.parent));
        concept.types(metaTypesOf(taraConcept.types.stream().filter(t -> !t.equals("Concept") & !t.equals("Metaconcept")).map(name -> model.getConcept(name)).collect(toList())));
        concept.allowsMultiple(taraConcept.allowsMultiple.stream().map(name -> model.getConcept(name)).collect(toList()));
        concept.allowsSingle(taraConcept.allowsSingle.stream().map(name -> model.getConcept(name)).collect(toList()));
        concept.requiresMultiple(taraConcept.requiresMultiple.stream().map(name -> model.getConcept(name)).collect(toList()));
        concept.requiresSingle(taraConcept.requiresSingle.stream().map(name -> model.getConcept(name)).collect(toList()));
        concept.components(taraConcept.cases.stream().map(c -> loadInstance(model.getInstance(c.name), c)).collect(toList()));
        concept.prototypes(taraConcept.prototypes.stream().map(p -> loadPrototype(concept, p)).collect(toList()));
        concept.variables(taraConcept.variables.stream().collect(toMap(v -> v.n, v -> v.v, (oldK, newK) -> newK)));
        return concept;
    }

    private void loadInstance(Instance instance, List<tara.io.Instance> instances) {
        for (tara.io.Instance aCase : instances) {
            Instance component = model.getInstance(aCase.name);
            component.owner(instance);
            loadInstance(component, aCase);
            instance.add(component);
        }
    }

    private Instance loadInstance(Instance instance, tara.io.Instance taraInstance) {
        Map<String, Object> conceptVariables = addConcepts(instance, taraInstance.types);
        loadInstance(instance, taraInstance.cases);
        clonePrototypes(instance);
        saveVariables(instance, conceptVariables, taraInstance.variables);
        return instance;
    }

    private Map<String, Object> addConcepts(Instance instance, List<String> taraConcepts) {
        Map<String, Object> conceptVariables = new HashMap<>();
        List<Concept> concepts = metaTypesOf(taraConcepts.stream().map(model::getConcept).collect(toList()));
        concepts.forEach(instance::addLayer);
        concepts.forEach(d -> conceptVariables.putAll(d.variables()));
        instance.layers.forEach(l -> instance.layers.forEach(l::_facet));
        return conceptVariables;
    }

    private void saveVariables(Instance instance, Map<String, Object> conceptVariables, List<Variable> variables) {
        conceptVariables.putAll(variables.stream().collect(toMap(v -> v.n, v -> v.v, (oldK, newK) -> newK)));
        model.addVariableIn(instance, conceptVariables);
    }

    private void clonePrototypes(Instance instance) {
        PrototypeCloner.clone(prototypesOf(instance), instance, model);
    }

    private List<Instance> prototypesOf(Instance instance) {
        List<Instance> prototypes = new ArrayList<>();
        instance.types().forEach(t -> t.prototypes().forEach(prototypes::add));
        return prototypes;
    }

    private Instance loadPrototype(Predicate parent, tara.io.Prototype prototype) {
        Instance instance = createPrototype(prototype);
        if(parent instanceof Instance) instance.owner((Instance)parent);
        else instance.owner(model.soil);
        Map<String, Object> variables = addConcepts(instance, prototype.types);
        variables.putAll(prototype.variables.stream().collect(toMap(v -> v.n, v -> v.v, (oldK, newK) -> newK)));
        instance.variables(variables);
        addComponentPrototypes(instance, prototype.prototypes);
        if(parent instanceof Instance) ((Instance)parent).add(instance);
        return instance;//TODO refactor
    }

    private Instance createPrototype(Prototype prototype) {
        Instance instance = prototype.name == null ? new Instance() : model.getInstance(prototype.name);
        if (prototype.className != null) {
            LayerFactory.register(instance.name, prototype.className);
            instance.addLayer(model.getConcept(prototype.name));
        }
        return instance;
    }

    private void addComponentPrototypes(Instance aInstance, List<Prototype> prototypes) {
        for (Prototype prototype : prototypes) loadPrototype(aInstance, prototype);
    }

    private List<Concept> metaTypesOf(List<Concept> metaConcepts) {
        List<Concept> concepts = new ArrayList<>();
        metaConcepts.stream().forEach(d -> {
            concepts.addAll(metaTypesOf(d.types()));
            concepts.add(d);
        });
        return concepts;
    }

}
