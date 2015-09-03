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
    static Map<String, Type> typeRecord = new HashMap<>();
    static Map<Object, Case> nodeRecord = new WeakHashMap<>();
    static Map<Case, List<Variable>> variableMap = new LinkedHashMap<>();
    static Case rootCase;
    static Case currentRootCase;

    public static Case load(String source) {
        if(rootCase == null) {
            rootCase = new Case();
            rootCase.add(getType("Root"));
        }
        currentRootCase = rootCase;
        loadSource(source);
        return currentRootCase;
    }

    public static Case addStash(String... paths) {
        currentRootCase = rootCase;
        for (String path : paths) load(StashDeserializer.stashFrom(new File(path)));
        setVariables();
        variableMap.clear();
        return currentRootCase;
    }

    public static Case loadStash(String... paths) {
        currentRootCase = new Case();
        currentRootCase.add(getType("Root"));
        for (String path : paths) load(StashDeserializer.stashFrom(new File(path)));
        setVariables();
        variableMap.clear();
        return currentRootCase;
    }

    public static Case loadNode(Object nodeId) {
        if(nodeId instanceof String)
            return nodeRecord.containsKey(nodeId) ? nodeRecord.get(nodeId) : typeRecord.get(nodeId);
        else
            return cloneMap.get(((Facet) nodeId)._instance().name()).copy;
    }

    public static List<Case> loadNode(List nodeIds) {
        List<Case> aCases = new ArrayList<>();
        nodeIds.forEach(n -> aCases.add(loadNode(n)));
        return aCases;
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
            if (type.isAbstract) FacetFactory.registerAbstract(type.name, type.morph);
            else FacetFactory.register(type.name, type.morph);
            loadType(type);
        }
    }

    private static void loadType(tara.io.Type type) {
        Type mType = createType(type);
        type.allowsMultiple.forEach(a -> mType.allowsMultiple(getType(a)));
        type.allowsSingle.forEach(a -> mType.allowsSingle(getType(a)));
        type.requiresMultiple.forEach(r -> mType.requiresMultiple(getType(r)));
        type.requiresSingle.forEach(r -> mType.requiresSingle(getType(r)));
        type.prototypes.forEach(p -> loadPrototype(mType, p));
        mType.typeVariables(type.variables);
    }

    private static Type createType(tara.io.Type type) {
        Type mType = getType(type.name);
        mType.setAbstract(type.isAbstract);
        if(!mType.isAbstract()) mType.setMorphClass(FacetFactory.getClass(mType.name));
        addMetatypes(mType, type.types.stream().map(PersistenceManager::getType).collect(toList()));
        mType.add(getType(type.name));
        return mType;
    }

    private static void addMetatypes(Type mType, List<Type> metatypes) {
        for (Type type : metatypes) {
            if (type.name.equalsIgnoreCase("concept")) continue;
            mType.add(type);
            type.metaTypes().forEach(m -> addMetatypes(mType, m.metaTypes()));
        }
    }

    private static void loadPrototype(Case parent, Prototype prototype) {
        Case aCase = createNode(prototype, parent);
        addTypes(aCase, prototype.types);
        doSets(aCase, prototype.variables);
        addComponentPrototypes(aCase, prototype.prototypes);
        parent.add(aCase);
    }

    private static Case createNode(Prototype prototype, Case parent) {
        Case aCase = prototype.name == null ? new Case() : getNode(prototype.name);
        aCase.owner(parent);
        if (prototype.morph != null) {
            FacetFactory.register(prototype.name, prototype.morph);
            aCase.add(getType(prototype.name));
        }
        return aCase;
    }

    private static void doSets(Case aCase, List<Variable> variables) {
        for (Variable variable : variables)
            aCase.init(variable.n, variable.v);
    }

    private static void addComponentPrototypes(Case aCase, List<Prototype> prototypes) {
        for (Prototype prototype : prototypes) loadPrototype(aCase, prototype);
    }

    private static void loadCases(List<tara.io.Case> cases) {
        for (tara.io.Case aCase : cases) currentRootCase.add(loadCase(aCase));
    }

    private static Case loadCase(tara.io.Case aCase) {
        Case node = aCase.name == null ? new Case() : getNode(aCase.name);
        addTypes(node, aCase.types);
        saveVariables(node, aCase.variables);
        addComponentCases(node, aCase.cases);
        clonePrototypes(node);
        return node;
    }

    private static Map<String, Binomy> cloneMap = new HashMap<>();
    private static void clonePrototypes(Case parent) {
        parent.types().forEach(t -> t.components()
                .forEach(c -> parent.add(new Case(parent.name + "." + WordGenerator.generate(), c, parent))));
        cloneMap.forEach((k, v) -> v.original.variables()
                .forEach((var, val) -> { if(val != null) v.copy.set(var, val);}));
        cloneMap.clear();
    }

    private static void addComponentCases(Case aCase, List<tara.io.Case> cases) {
        for (tara.io.Case component : cases) {
            Case child = loadCase(component);
            child.owner(aCase);
            aCase.add(child);
        }
    }

    private static void addTypes(Case aCase, List<String> types) {
        for (String type : types) {
            getType(type).metaTypes().forEach(aCase::add);
            aCase.add(getType(type));
        }
    }

    private static void setVariables() {
        for (Case aCase : variableMap.keySet())
            for (Variable variable : variableMap.get(aCase)) aCase.init(variable.n, variable.v);
    }

    private static void saveVariables(Case aCase, List<Variable> variables) {
        variableMap.put(aCase, variables);
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

    private static Case getNode(Object object) {
        if (!nodeRecord.containsKey(object))
            nodeRecord.put(object, new Case(object.toString()));
        return nodeRecord.get(object);
    }

    private static Type getType(String name) {
        if (!typeRecord.containsKey(name))
            typeRecord.put(name, new Type(name));
        return typeRecord.get(name);
    }

    public static void clear() {
        languages.clear();
        typeRecord.clear();
        nodeRecord.clear();
        variableMap.clear();
    }

    public static Type type(String type) {
        return typeRecord.get(type);
    }

    public static List<String> languages() {
        return new ArrayList<>(languages).stream().map(s -> s.replace("/", "").replace(".dsl", "")).collect(toList());
    }

    public static void registerClone(String officialName, Case original, Case copy) {
        cloneMap.put(officialName, new Binomy(original, copy));
    }

    public static void save(Instance aCase) {
        //TODO
    }

    public static List<Type> types() {
        return Collections.unmodifiableList(new ArrayList<>(typeRecord.values()));
    }

    static class Binomy{
        Case original, copy;

        public Binomy(Case original, Case copy) {
            this.original = original;
            this.copy = copy;
        }
    }

}