package tara.magritte;

import tara.io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Loader {

    static Set<String> languages = new LinkedHashSet<>();
    static Map<String, tara.magritte.Type> typeRecord = new HashMap<>();
    static Map<Object, Node> nodeRecord = new WeakHashMap<>();
    static Map<Node, Variable[]> variableMap = new LinkedHashMap<>();
    static Node rootNode;
    static Node currentRootNode;

    public static Node load(String source) {
        if(rootNode == null) {
            rootNode = new Node();
            rootNode.add("Root");
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
        currentRootNode.add("Root");
        for (String path : paths) load(StashDeserializer.stashFrom(new File(path)));
        setVariables();
        variableMap.clear();
        return currentRootNode;
    }

    public static Node loadNode(String nodeId) {
        return nodeRecord.containsKey(nodeId) ? nodeRecord.get(nodeId) : typeRecord.get(nodeId);
    }

    public static List<Node> loadNode(String[] nodeIds) {
        return Arrays.asList(nodeIds).stream().map(nodeRecord::get).collect(Collectors.toList());
    }

    private static void loadSource(String source) {
        languages.add(source);
        for (String path : listStashes(source))
            load(StashDeserializer.stashFrom(Loader.class.getResourceAsStream(path)));
        setVariables();
        variableMap.clear();
    }

    private static void load(Stash stash) {
        if (!languages.contains(languageFile(stash.language))) loadLanguage(languageFile(stash.language));
        if (stash.types != null) loadTypes(stash.types);
        if (stash.cases != null) loadCases(stash.cases);
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
            if (type.isAbstract) MorphFactory.registerAbstract(type.name);
            else MorphFactory.register(type.name, type.morph);
            loadType(type);
        }
    }

    private static void loadType(tara.io.Type type) {
        Type mType = getType(type.name);
        mType.add(type.name);
        if (type.types != null) addMetatypes(mType, Arrays.asList(type.types));
        if (type.prototypes != null) addPrototypes(mType, type.prototypes);
    }

    private static void addMetatypes(Type mType, List<String> metatypes) {
        for (String name : metatypes) {
            if (name.equalsIgnoreCase("concept")) continue;
            mType.add(getType(name));
            typeRecord.get(name).metaTypes().forEach(m -> addMetatypes(mType, m.types()));
        }
    }

    private static void addPrototypes(Type mType, List<Prototype> prototypes) {
        for (Prototype prototype : prototypes) loadPrototype(mType, prototype);
    }

    private static void loadPrototype(Node parent, Prototype prototype) {
        Node node = createNode(prototype);
        node.owner(parent);
        if (prototype.name != null) node.add(node.name);
        addTypes(node, prototype.types);
        doSets(node, prototype.variables);
        addComponents(node, prototype.prototypes);
        parent.add(node);
    }

    private static Node createNode(Prototype prototype) {
        if (prototype.name != null) {
            MorphFactory.register(prototype.name, prototype.morph);
            getType(prototype.name);
        }
        return prototype.name == null ? new Node() : getNode(prototype.name);
    }

    private static void doSets(Node node, Variable[] variables) {
        for (Variable variable : variables)
            node.set(variable.n, variable.v);
    }

    private static void addComponents(Node node, Prototype[] prototypes) {
        for (Prototype prototype : prototypes) loadPrototype(node, prototype);
    }

    private static void loadCases(List<Case> cases) {
        for (Case aCase : cases) currentRootNode.add(loadCase(aCase));
    }

    private static Node loadCase(Case aCase) {
        Node node = aCase.name == null ? new Node() : getNode(aCase.name);
        addTypes(node, aCase.types);
        saveVariables(node, aCase.variables);
        if (aCase.cases != null) addComponents(node, aCase.cases);
        clonePrototypes(node);
        return node;
    }

    private static void clonePrototypes(Node node) {
        node.types().forEach(t -> typeRecord.get(t).components().forEach(c -> node.add(new Node(c, node))));
    }

    private static void addComponents(Node node, List<Case> cases) {
        for (Case component : cases) {
            Node child = loadCase(component);
            child.owner(node);
            node.add(child);
        }
    }

    private static void addTypes(Node node, String[] types) {
        for (String type : types) {
            node.add(type);
            typeRecord.get(type).metaTypes().forEach(m -> node.add(m.name));
        }
    }

    private static void setVariables() {
        for (Node node : variableMap.keySet())
            for (Variable variable : variableMap.get(node)) node.set(variable.n, variable.v);
    }

    private static void saveVariables(Node node, Variable[] variables) {
        variableMap.put(node, variables);
    }

    private static List<String> listStashes(String source) {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(Loader.class.getResourceAsStream(source)));
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
        return new ArrayList<>(languages).stream().map(s -> s.replace("/", "").replace(".dsl", "")).collect(Collectors.toList());
    }
}
