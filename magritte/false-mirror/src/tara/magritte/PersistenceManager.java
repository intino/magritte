package tara.magritte;

import tara.io.*;
import tara.util.WordGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.*;

public class PersistenceManager {

    static Set<String> languages = new LinkedHashSet<>();
    static Map<String, Definition> definitionRecord = new HashMap<>();
    static Map<Object, Declaration> declarationRecord = new WeakHashMap<>();
    static Map<Declaration, List<Variable>> variableMap = new LinkedHashMap<>();
    static Declaration rootDeclaration;

    static{
        rootDeclaration = new Declaration();
        rootDeclaration.as(getDefinition("Root"));
    }

    @SuppressWarnings("unused")
    public static Declaration load(String source) {
        loadSource(source);
        return rootDeclaration;
    }

    @SuppressWarnings("unused")
    public static Declaration loadStashes(String... paths) {
        for (String path : paths) load(StashDeserializer.stashFrom(new File(path)));
        setVariables();
        variableMap.clear();
        return rootDeclaration;
    }

    public static Declaration loadDeclaration(Object id) {
        if(id instanceof String)
            return declarationRecord.get(id); //TODO Dynamic load
        else
            return cloneMap.get(((Morph) id)._declaration().name()).copy;
    }

    @SuppressWarnings("unused")
    public static List<Declaration> loadDeclarations(List ids) {
        List<Declaration> predicates = new ArrayList<>();
        ids.forEach(n -> predicates.add(loadDeclaration(n)));
        return predicates;
    }

    private static void loadSource(String source) {
        languages.add(source);
        for (String path : listStashes(source))
            load(StashDeserializer.stashFrom(PersistenceManager.class.getResourceAsStream(path)));
        setVariables();
        variableMap.clear();
    }

    private static void load(Stash stash) {
        if (!languages.contains(languageFile(stash.language))) loadLanguage(languageFile(stash.language));
        loadTypes(stash.types);
        loadCases(stash.cases);
    }

    private static String languageFile(String language) {
        return "/" + language + ".dsl";
    }

    private static void loadLanguage(String language) {
        if (language.contains("/Proteo.dsl")) return;
        loadSource(language);
    }

    private static void loadTypes(List<tara.io.Type> types) {
        for (tara.io.Type type : types) {
            MorphFactory.register(type.name, type.morph);
            loadDefinition(type);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private static Definition loadDefinition(Type type) {
        Definition definition = getDefinition(type.name);
        definition.isAbstract(type.isAbstract);
        definition.isTerminal(type.isTerminal);
        definition.isMain(type.isMain);
        definition.morphClass(MorphFactory.morphClass(definition.name));
        definition.parent(getDefinition(type.parent));
        definition.types(metaTypesOf(type.types.stream().map(PersistenceManager::getDefinition).collect(toList())));
        definition.allowsMultiple(type.allowsMultiple.stream().map(a -> getDefinition(a)).collect(toList()));
        definition.allowsSingle(type.allowsSingle.stream().map(a -> getDefinition(a)).collect(toList()));
        definition.requiresMultiple(type.requiresMultiple.stream().map(r -> getDefinition(r)).collect(toList()));
        definition.requiresSingle(type.requiresSingle.stream().map(r -> getDefinition(r)).collect(toList()));
        definition.prototypes(type.prototypes.stream().map(p -> loadPrototype(definition, p)).collect(toList()));
        definition.variables(asMap(type.variables));
        return definition;
    }

    private static Map<String, Object> asMap(List<Variable> variables) {
        Map<String, Object> variableMap = new LinkedHashMap<>();
        variables.forEach(v -> variableMap.put(v.n, v.v));
        return variableMap;
    }

    private static List<Definition> metaTypesOf(List<Definition> metaDefinitions) {
        List<Definition> definitions = new ArrayList<>();
        metaDefinitions.forEach(d -> {
            definitions.add(d);
            definitions.addAll(metaTypesOf(d.types()));
        });
        return definitions;
    }

    private static Declaration loadPrototype(Predicate parent, tara.io.Prototype prototype) {
        Declaration declaration = createPrototype(prototype);
        addTypes(declaration, prototype.types);
        declaration.variables(asMap(prototype.variables));
        addComponentPrototypes(declaration, prototype.prototypes);
        parent.add(declaration);
        return declaration;
    }

    private static Declaration createPrototype(Prototype prototype) {
        Declaration declaration = prototype.name == null ? new Declaration() : getDeclaration(prototype.name);
        if (prototype.morph != null) {
            MorphFactory.register(declaration.name, prototype.morph);
            declaration.as(getDefinition(prototype.name));
        }
        return declaration;
    }

    private static void addComponentPrototypes(Declaration aDeclaration, List<Prototype> prototypes) {
        for (Prototype prototype : prototypes) loadPrototype(aDeclaration, prototype);
    }

    private static void loadCases(List<tara.io.Case> cases) {
        for (tara.io.Case aCase : cases) rootDeclaration.add(loadCase(aCase));
    }

    private static Declaration loadCase(tara.io.Case aCase) {
        Declaration declaration = aCase.name == null ? new Declaration() : getDeclaration(aCase.name);
        addTypes(declaration, aCase.types);
        saveVariables(declaration, aCase.variables);
        addComponentCases(declaration, aCase.cases);
        clonePrototypes(declaration);
        return declaration;
    }

    private static Map<String, Binomy> cloneMap = new HashMap<>();
    private static void clonePrototypes(Declaration parent) {
        parent.types().forEach(t -> t.components()
                .forEach(c -> parent.add(new Declaration(parent.name + "." + WordGenerator.generate(), c, parent))));
        cloneMap.forEach((k, v) -> v.original.variables()
                .forEach((var, val) -> { if(val != null) v.copy.set(var, val);}));
        cloneMap.clear();
    }

    private static void addComponentCases(Declaration aDeclaration, List<tara.io.Case> cases) {
        for (tara.io.Case component : cases) {
            Declaration child = loadCase(component);
            child.owner(aDeclaration);
            aDeclaration.add(child);
        }
    }

    private static void addTypes(Declaration aDeclaration, List<String> types) {
        for (String type : types) {
            getDefinition(type).types().forEach(aDeclaration::as);
            aDeclaration.as(getDefinition(type));
        }
    }

    private static void setVariables() {
        for (Declaration aDeclaration : variableMap.keySet())
            aDeclaration.variables(asMap(variableMap.get(aDeclaration)));
    }

    private static void saveVariables(Declaration aDeclaration, List<Variable> variables) {
        variableMap.put(aDeclaration, variables);
    }

    private static List<String> listStashes(String source) {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(PersistenceManager.class.getResourceAsStream(source)));
        String line;
        try {
            while ((line = reader.readLine()) != null) lines.add(line);
        } catch (IOException ignored) {
        }
        return lines;
    }

    private static Declaration getDeclaration(Object object) {
        if (!declarationRecord.containsKey(object))
            declarationRecord.put(object, new Declaration(object.toString()));
        return declarationRecord.get(object);
    }

    private static Definition getDefinition(String name) {
        if (!definitionRecord.containsKey(name))
            definitionRecord.put(name, new Definition(name));
        return definitionRecord.get(name);
    }

    public static void clear() {
        languages.clear();
        definitionRecord.clear();
        declarationRecord.clear();
        variableMap.clear();
    }

    public static Definition type(String type) {
        return definitionRecord.get(type);
    }

    public static List<String> languages() {
        return new ArrayList<>(languages).stream().map(s -> s.replace("/", "").replace(".dsl", "")).collect(toList());
    }

    public static void registerClone(String officialName, Declaration original, Declaration copy) {
        cloneMap.put(officialName, new Binomy(original, copy));
    }

    public static void save(Predicate aCase) {//TODO
    }

    public static List<Definition> types() {
        return Collections.unmodifiableList(new ArrayList<>(definitionRecord.values()));
    }

    static class Binomy{
        Declaration original, copy;

        public Binomy(Declaration original, Declaration copy) {
            this.original = original;
            this.copy = copy;
        }
    }

}