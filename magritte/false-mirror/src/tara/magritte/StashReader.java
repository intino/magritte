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
        loadTypes(stash.types);
        loadCases(model.soil, stash.cases);
    }

    private void loadTypes(List<Type> types) {
        for (Type type : types) {
            LayerFactory.register(type.name, type.className);
            Concept concept = model.getDefinition(type.name);
            loadType(concept, type);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private Concept loadType(Concept concept, Type type) {
        concept.isAbstract(type.isAbstract);
        concept.isTerminal(type.isTerminal);
        concept.isMain(type.isMain);
        concept.layerClass(LayerFactory.layerClass(concept.name));
        concept.parent(model.getDefinition(type.parent));
        concept.types(metaTypesOf(type.types.stream().filter(t -> !t.equals("Concept")).map(name -> model.getDefinition(name)).collect(toList())));
        concept.allowsMultiple(type.allowsMultiple.stream().map(name -> model.getDefinition(name)).collect(toList()));
        concept.allowsSingle(type.allowsSingle.stream().map(name -> model.getDefinition(name)).collect(toList()));
        concept.requiresMultiple(type.requiresMultiple.stream().map(name -> model.getDefinition(name)).collect(toList()));
        concept.requiresSingle(type.requiresSingle.stream().map(name -> model.getDefinition(name)).collect(toList()));
        concept.components(type.cases.stream().map(c -> loadCase(model.getDeclaration(c.name), c)).collect(toList()));
        concept.prototypes(type.prototypes.stream().map(p -> loadPrototype(concept, p)).collect(toList()));
        concept.variables(type.variables.stream().collect(toMap(v -> v.n, v -> v.v, (oldK, newK) -> newK)));
        return concept;
    }

    private void loadCases(Instance instance, List<Case> cases) {
        for (Case aCase : cases) {
            Instance component = model.getDeclaration(aCase.name);
            component.owner(instance);
            loadCase(component, aCase);
            instance.add(component);
        }
    }

    private Instance loadCase(Instance instance, Case aCase) {
        Map<String, Object> definitionVariables = addTypes(instance, aCase.types);
        loadCases(instance, aCase.cases);
        clonePrototypes(instance);
        saveVariables(instance, definitionVariables, aCase.variables);
        return instance;
    }

    private Map<String, Object> addTypes(Instance instance, List<String> types) {
        Map<String, Object> definitionVariables = new HashMap<>();
        List<Concept> concepts = metaTypesOf(types.stream().map(model::getDefinition).collect(toList()));
        concepts.forEach(instance::addLayer);
        concepts.forEach(d -> definitionVariables.putAll(d.variables()));
        instance.layers.forEach(l -> instance.layers.forEach(l::_facet));
        return definitionVariables;
    }

    private void saveVariables(Instance instance, Map<String, Object> definitionVariables, List<Variable> variables) {
        definitionVariables.putAll(variables.stream().collect(toMap(v -> v.n, v -> v.v, (oldK, newK) -> newK)));
        model.addVariableIn(instance, definitionVariables);
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
        Map<String, Object> variables = addTypes(instance, prototype.types);
        variables.putAll(prototype.variables.stream().collect(toMap(v -> v.n, v -> v.v, (oldK, newK) -> newK)));
        instance.variables(variables);
        addComponentPrototypes(instance, prototype.prototypes);
        if(parent instanceof Instance) ((Instance)parent).add(instance);
        return instance;//TODO refactor
    }

    private Instance createPrototype(Prototype prototype) {
        Instance instance = prototype.name == null ? new Instance() : model.getDeclaration(prototype.name);
        if (prototype.className != null) {
            LayerFactory.register(instance.name, prototype.className);
            instance.addLayer(model.getDefinition(prototype.name));
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
