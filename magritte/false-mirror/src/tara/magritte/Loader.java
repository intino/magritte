package tara.magritte;

import tara.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Loader {

    Set<String> openedItems = new HashSet<>();
    List<String> source = new ArrayList<>();
    Map<String, tara.magritte.Type> typeRecord = new HashMap<>();
    Map<Object, Node> nodeRecord = new WeakHashMap<>();
    Node rootNode;

    public Loader(String... source) {
        Collections.addAll(this.source, source);
    }

    public void load(Node node) {
        this.rootNode = node;
        source.forEach(this::load);
    }

    private void load(String dsl) {
        for (String path : listStashes(dsl))
            load(StashDeserializer.stashFrom(this.getClass().getResourceAsStream(path)));
    }

    private void load(Stash stash) {
        if (!openedItems.contains(languageFile(stash.language))) loadLanguage(languageFile(stash.language));
        if (stash.types != null) loadTypes(stash.types);
        if (stash.prototypes != null) loadPrototypes(stash.prototypes);
        if (stash.cases != null) loadCases(stash.cases);
    }

    private String languageFile(String language) {
        return "/" + language + ".dsl";
    }

    private void loadLanguage(String language) {
        if(language.contains("/Proteo.dsl")) return;
        load(language);
    }

    private void loadTypes(List<tara.io.Type> types) {
        for (tara.io.Type type : types) {
            if (type.isAbstract) MorphFactory.registerAbstract(type.name);
            else MorphFactory.register(type.name, type.morph);
            loadType(type);
        }
    }

    private void loadType(tara.io.Type type) {
        Type mType = getType(type.name);
        if(type.types != null) addMetatypes(mType, Arrays.asList(type.types));
        if(type.prototypes != null) addPrototypes(mType, type.prototypes);
    }

    private void addMetatypes(Type mType, List<String> metatypes) {
        for (String name : metatypes) {
            if(name.equalsIgnoreCase("concept")) continue;
            mType.add(getType(name));
            typeRecord.get(name).metaTypes().forEach(m -> addMetatypes(mType, m.types()));
        }
    }

    private void addPrototypes(Type mType, List<Prototype> prototypes) {
        for (Node node : loadPrototypes(prototypes)) {
            mType.add(node);
            node.owner(mType);
        }
    }

    private List<Node> loadPrototypes(List<Prototype> prototypes) {
        List<Node> nodes = new ArrayList<>();
        for (Prototype prototype : prototypes) {
            if (prototype.name != null) {
                MorphFactory.register(prototype.name, prototype.morph);
                getType(prototype.name);
            }
            nodes.add(loadPrototype(prototype));
        }
        return nodes;
    }

    private Node loadPrototype(Prototype prototype) {
        Node node = prototype.name == null ? new Node() : getNode(prototype.name);
        addTypes(node, prototype.types);
        doSets(node, prototype.variables);
        addComponents(node, prototype.prototypes);
        return node;
    }

    private void addComponents(Node node, Prototype[] prototypes) {
        for (Prototype prototype : prototypes) {
            Node child = loadPrototype(prototype);
            child.owner(node);
            node.add(child);
        }
    }

    private void loadCases(List<Case> cases) {
        for (Case aCase : cases) loadCase(aCase);
    }

    private Node loadCase(Case aCase) {
        Node node = aCase.name == null ? new Node() : getNode(aCase.name);
        addTypes(node, aCase.types);
        doSets(node, aCase.variables);
        addComponents(node, aCase.cases);
        return node;
    }

    private void addComponents(Node node, List<Case> cases) {
        for (Case component : cases) {
            Node child = loadCase(component);
            child.owner(node);
            node.add(child);
        }
    }

    private void addTypes(Node node, String[] types) {
        for (String type : types) {
            node.add(type);
            typeRecord.get(type).metaTypes().forEach(m -> node.add(m.name));
        }
    }

    private void doSets(Node node, Variable[] variables) {
        for (Variable variable : variables)
            node.set(variable.n, variable.v);
    }

    private List<String> listStashes(String source) {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(source)));
        String line;
        try {
            while ((line = reader.readLine()) != null) lines.add(line);
        } catch (IOException ignored) {
        }
        return lines;
    }

    private Node getNode(Object object) {
        if (!nodeRecord.containsKey(object))
            nodeRecord.put(object, new Node(object.toString()));
        return nodeRecord.get(object);
    }

    private Type getType(String name) {
        if (!typeRecord.containsKey(name))
            typeRecord.put(name, new Type(name));
        return typeRecord.get(name);
    }
}
