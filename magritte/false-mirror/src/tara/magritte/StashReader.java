package tara.magritte;

import tara.io.Case;
import tara.io.Prototype;
import tara.io.Stash;
import tara.io.Type;
import tara.io.Variable;

import java.util.*;

import static java.util.stream.Collectors.toList;

class StashReader {

    private final Model model;
    public StashReader(Model model) {
        this.model = model;
    }

    public void read(Stash stash) {
        loadTypes(stash.types);
        loadCases(model._declaration(), stash.cases);
    }

    private void loadTypes(List<Type> types) {
        for (Type type : types) {
            LayerFactory.register(type.name, type.className);
            Definition definition = model.getDefinition(type.name);
            loadType(definition, type);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private Definition loadType(Definition definition, Type type) {
        definition.isAbstract(type.isAbstract);
        definition.isTerminal(type.isTerminal);
        definition.isMain(type.isMain);
        definition.layerClass(LayerFactory.layerClass(definition.name));
        definition.parent(model.getDefinition(type.parent));
        definition.types(metaTypesOf(type.types.stream().filter(t -> !t.equals("Concept")).map(name -> model.getDefinition(name)).collect(toList())));
        definition.allowsMultiple(type.allowsMultiple.stream().map(name -> model.getDefinition(name)).collect(toList()));
        definition.allowsSingle(type.allowsSingle.stream().map(name -> model.getDefinition(name)).collect(toList()));
        definition.requiresMultiple(type.requiresMultiple.stream().map(name -> model.getDefinition(name)).collect(toList()));
        definition.requiresSingle(type.requiresSingle.stream().map(name -> model.getDefinition(name)).collect(toList()));
        definition.components(type.cases.stream().map(c -> loadCase(model.getDeclaration(c.name), c)).collect(toList()));
        definition.prototypes(type.prototypes.stream().map(p -> loadPrototype(definition, p)).collect(toList()));
        definition.variables(asMap(type.variables));
        return definition;
    }

    private void loadCases(Declaration declaration, List<Case> cases) {
        for (Case aCase : cases) {
            Declaration component = model.getDeclaration(aCase.name);
            component.owner(declaration);
            loadCase(component, aCase);
            declaration.add(component);
        }
    }

    private Declaration loadCase(Declaration declaration, Case aCase) {
        addTypes(declaration, aCase.types);
        loadCases(declaration, aCase.cases);
        clonePrototypes(declaration);
        saveVariables(declaration, aCase.variables);
        return declaration;
    }

    private void addTypes(Declaration declaration, List<String> types) {
        List<Definition> definitions = metaTypesOf(types.stream().map(model::getDefinition).collect(toList()));
        definitions.forEach(declaration::addLayer);
        definitions.forEach(d -> d.variables().forEach((name, object) -> model.addVariableIn(declaration, name, object)));
    }

    private void saveVariables(Declaration declaration, List<Variable> variables) {
        variables.forEach(v -> model.addVariableIn(declaration, v.n, v.v));
    }

    private void clonePrototypes(Declaration declaration) {
        PrototypeCloner.clone(prototypesOf(declaration), declaration, model);
    }

    private List<Declaration> prototypesOf(Declaration declaration) {
        List<Declaration> prototypes = new ArrayList<>();
        declaration.types().forEach(t -> t.prototypes().forEach(prototypes::add));
        return prototypes;
    }

    private Declaration loadPrototype(Predicate parent, tara.io.Prototype prototype) {
        Declaration declaration = createPrototype(prototype);
        addTypes(declaration, prototype.types);
        declaration.variables(asMap(prototype.variables));
        addComponentPrototypes(declaration, prototype.prototypes);
        if(parent instanceof Declaration) ((Declaration)parent).add(declaration);
        return declaration;
    }

    private  Declaration createPrototype(Prototype prototype) {
        Declaration declaration = prototype.name == null ? new Declaration() : model.getDeclaration(prototype.name);
        if (prototype.className != null) {
            LayerFactory.register(declaration.name, prototype.className);
            declaration.addLayer(model.getDefinition(prototype.name));
        }
        return declaration;
    }

    private void addComponentPrototypes(Declaration aDeclaration, List<Prototype> prototypes) {
        for (Prototype prototype : prototypes) loadPrototype(aDeclaration, prototype);
    }

    private List<Definition> metaTypesOf(List<Definition> metaDefinitions) {
        List<Definition> definitions = new ArrayList<>();
        metaDefinitions.stream().forEach(d -> {
            definitions.addAll(metaTypesOf(d.types()));
            definitions.add(d);
        });
        return definitions;
    }

    private static Map<String, Object> asMap(List<Variable> variables) {
        Map<String, Object> variableMap = new LinkedHashMap<>();
        variables.forEach(v -> variableMap.put(v.n, v.v));
        return variableMap;
    }

}
