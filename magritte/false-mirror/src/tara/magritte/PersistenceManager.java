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
    static Map<Object, Node> nodeRecord = new WeakHashMap<>();
    static Map<Node, List<Variable>> variableMap = new LinkedHashMap<>();
    static Node rootNode;
    static Node currentRootNode;

    public static Node load(String source) {
        if(rootNode == null) {
            rootNode = new Node();
            rootNode.add(getType("Root"));
        }
        currentRootNode = rootNode;
        loadSource(source);
        return currentRootNode;
    }

    public static Node addStash(String... paths) {
        currentRootNode = rootNode;
        for (String path : paths) load(StashDeserializer.stashFrom(new File(path)));
        setVariables();
        variableMap.clear();
        return currentRootNode;
    }

    public static Node loadStash(String... paths) {
        currentRootNode = new Node();
        currentRootNode.add(getType("Root"));
        for (String path : paths) load(StashDeserializer.stashFrom(new File(path)));
        setVariables();
        variableMap.clear();
        return currentRootNode;
    }

    public static Node loadNode(Object nodeId) {
        if(nodeId instanceof String)
            return nodeRecord.containsKey(nodeId) ? nodeRecord.get(nodeId) : typeRecord.get(nodeId);
        else
            return cloneMap.get(((Morph) nodeId)._node().name()).copy;
    }

    public static List<Node> loadNode(List nodeIds) {
        List<Node> nodes = new ArrayList<>();
        nodeIds.forEach(n -> nodes.add(loadNode(n)));
        return nodes;
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
            if (type.isAbstract) MorphFactory.registerAbstract(type.name, type.morph);
            else MorphFactory.register(type.name, type.morph);
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
        if(!mType.isAbstract()) mType.setMorphClass(MorphFactory.getClass(mType.name));
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

    private static void loadPrototype(Node parent, Prototype prototype) {
        Node node = createNode(prototype, parent);
        addTypes(node, prototype.types);
        doSets(node, prototype.variables);
        addComponentPrototypes(node, prototype.prototypes);
        parent.add(node);
    }

    private static Node createNode(Prototype prototype, Node parent) {
        Node node = prototype.name == null ? new Node() : getNode(prototype.name);
        node.owner(parent);
        if (prototype.morph != null) {
            MorphFactory.register(prototype.name, prototype.morph);
            node.add(getType(prototype.name));
        }
        return node;
    }

    private static void doSets(Node node, List<Variable> variables) {
        for (Variable variable : variables)
            node.init(variable.n, variable.v);
    }

    private static void addComponentPrototypes(Node node, List<Prototype> prototypes) {
        for (Prototype prototype : prototypes) loadPrototype(node, prototype);
    }

    private static void loadCases(List<Case> cases) {
        for (Case aCase : cases) currentRootNode.add(loadCase(aCase));
    }

    private static Node loadCase(Case aCase) {
        Node node = aCase.name == null ? new Node() : getNode(aCase.name);
        addTypes(node, aCase.types);
        saveVariables(node, aCase.variables);
        addComponentCases(node, aCase.cases);
        clonePrototypes(node);
        return node;
    }

    private static Map<String, Binomy> cloneMap = new HashMap<>();
    private static void clonePrototypes(Node parent) {
        parent.types().forEach(t -> t.components()
                .forEach(c -> parent.add(new Node(parent.name + "." + WordGenerator.generate(), c, parent))));
        cloneMap.forEach((k, v) -> v.original.variables()
                .forEach((var, val) -> { if(val != null) v.copy.set(var, val);}));
        cloneMap.clear();
    }

    private static void addComponentCases(Node node, List<Case> cases) {
        for (Case component : cases) {
            Node child = loadCase(component);
            child.owner(node);
            node.add(child);
        }
    }

    private static void addTypes(Node node, List<String> types) {
        for (String type : types) {
            getType(type).metaTypes().forEach(node::add);
            node.add(getType(type));
        }
    }

    private static void setVariables() {
        for (Node node : variableMap.keySet())
            for (Variable variable : variableMap.get(node)) node.init(variable.n, variable.v);
    }

    private static void saveVariables(Node node, List<Variable> variables) {
        variableMap.put(node, variables);
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

    private static Node getNode(Object object) {
        if (!nodeRecord.containsKey(object))
            nodeRecord.put(object, new Node(object.toString()));
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

    public static void registerClone(String officialName, Node original, Node copy) {
        cloneMap.put(officialName, new Binomy(original, copy));
    }

    public static void save(Node node) {
        //TODO
    }

    public static List<Type> types() {
        return new ArrayList<>(typeRecord.values());
    }

    static class Binomy{
        Node original, copy;

        public Binomy(Node original, Node copy) {
            this.original = original;
            this.copy = copy;
        }
    }

}