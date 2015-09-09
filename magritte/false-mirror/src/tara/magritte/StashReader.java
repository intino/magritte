package tara.magritte;

import tara.io.Case;
import tara.io.Prototype;
import tara.io.Stash;
import tara.io.Type;
import tara.io.Variable;
import tara.util.WordGenerator;

import java.util.*;

import static java.util.stream.Collectors.toList;

class StashReader {
    private final Board board;
    private Map<Declaration, List<Variable>> variables = new LinkedHashMap<>();

    public StashReader(Board board) {
        this.board = board;
    }

    public void read(Stash stash) {
        loadTypes(stash.types);
        loadRootCases(stash.cases);
    }

    private void loadTypes(List<Type> types) {
        for (Type type : types) {
            loadType(type);
        }
    }

    private void loadRootCases(List<Case> rootCases) {
        for (Case rootCase : rootCases) {
            loadRootCase(rootCase);
        }
    }

    private void loadRootCase(Case rootCase) {
        board.registerRoot(loadCase(rootCase));
    }

    @SuppressWarnings("Convert2MethodRef")
    private Definition loadType(Type type) {
        LayerFactory.register(type.name, type.className);
        Definition definition = board.getDefinition(type.name);
        definition.isAbstract(type.isAbstract);
        definition.isTerminal(type.isTerminal);
        definition.isMain(type.isMain);
        definition.layerClass(LayerFactory.layerClass(definition.name));
        definition.parent(board.getDefinition(type.parent));
        definition.types(metaTypesOf(type.types.stream().map(name -> board.getDefinition(name)).collect(toList())));
        definition.allowsMultiple(type.allowsMultiple.stream().map(name -> board.getDefinition(name)).collect(toList()));
        definition.allowsSingle(type.allowsSingle.stream().map(name -> board.getDefinition(name)).collect(toList()));
        definition.requiresMultiple(type.requiresMultiple.stream().map(name -> board.getDefinition(name)).collect(toList()));
        definition.requiresSingle(type.requiresSingle.stream().map(name -> board.getDefinition(name)).collect(toList()));
        definition.components(type.cases.stream().map(c -> loadCase(c)).collect(toList()));
        definition.prototypes(type.prototypes.stream().map(p -> loadPrototype(definition, p)).collect(toList()));
        definition.variables(asMap(type.variables));
        return definition;
    }

    private Declaration loadCase(Case aCase) {
        Declaration declaration = board.getDeclaration(aCase.name);
        addTypes(declaration, aCase.types);
        addComponents(declaration, aCase.cases);
        clonePrototypes(declaration);
        saveVariables(declaration, aCase.variables);
        return declaration;
    }

    private void addTypes(Declaration declaration, List<String> types) {
        List<Definition> definitions = types.stream().map(board::getDefinition).collect(toList());
        metaTypesOf(definitions).forEach(declaration::morphWith); //TODO parent is inside
//        for (String type : types) {
//            board.getDefinition(type).types().forEach(declaration::morphWith);
//            declaration.morphWith(board.getDefinition(type));
//        }
    }

    private void saveVariables(Declaration declaration, List<Variable> variables) {
        this.variables.put(declaration, variables);
    }

    private void addComponents(Declaration declaration, List<Case> cases) {
        for (Case aCase : cases) {
            Declaration component = loadCase(aCase);
            component.owner(declaration);
            declaration.add(component);
        }
    }

    private void clonePrototypes(Declaration declaration) {
        PrototypeCloner.clone(prototypesOf(declaration), declaration, board);
//        declaration.types().forEach(t -> t.prototypes()
//                .forEach(c -> declaration.add(new Declaration(declaration.name + "." + WordGenerator.generate(), c, declaration))));
//        cloneMap.forEach((k, v) -> v.original.variables()
//                .forEach((var, val) -> {
//                    if (val != null) v.copy.set(var, val);
//                }));
//        cloneMap.clear();
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
        Declaration declaration = prototype.name == null ? new Declaration() : board.getDeclaration(prototype.name);
        if (prototype.className != null) {
            LayerFactory.register(declaration.name, prototype.className);
            declaration.morphWith(board.getDefinition(prototype.name));
        }
        return declaration;
    }

    private void addComponentPrototypes(Declaration aDeclaration, List<Prototype> prototypes) {
        for (Prototype prototype : prototypes) loadPrototype(aDeclaration, prototype);
    }

    private List<Definition> metaTypesOf(List<Definition> metaDefinitions) {
        List<Definition> definitions = new ArrayList<>();
        metaDefinitions.forEach(d -> {
            definitions.add(d);
            definitions.addAll(metaTypesOf(d.types()));
        });
        return definitions;
    }

    private static Map<String, Object> asMap(List<Variable> variables) {
        Map<String, Object> variableMap = new LinkedHashMap<>();
        variables.forEach(v -> variableMap.put(v.n, v.v));
        return variableMap;
    }

}
