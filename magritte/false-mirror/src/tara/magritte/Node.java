package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class Node {

    protected final String name;
    protected final Set<String> types = new LinkedHashSet<>();
    protected final List<Morph> morphs = new ArrayList<>();
    Node owner;

    public Node() {
        this("");
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(Node node, Node owner) {
        this.name = node.name;
        this.owner = owner;
        this.types.addAll(node.types);
        this.morphs.addAll(node.morphs.stream().map((morph) -> cloneMorph(morph, node)).collect(Collectors.toList()));
        node.components().forEach(c -> morphs.forEach(m -> m._add(new Node(c, this))));
    }

    public String name() {
        return name;
    }

    public List<String> types() {
        List<String> types = new ArrayList<>(this.types);
        Collections.reverse(types);
        return types;
    }

    private Morph cloneMorph(Morph morph, Node node) {
        try {
            Morph instance = morph.getClass().getDeclaredConstructor(Morph.class, Node.class).newInstance(morph, this);
            instance._add(node.components());
            return instance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Node> components() {
        Set<Node> nodes = new LinkedHashSet<>();
        morphs.forEach(m -> nodes.addAll(m._components()));
        return new ArrayList<>(nodes);
    }

    public Map<String, Object> variables() {
        Map<String, Object> variables = new HashMap<>();
        morphs.forEach(m -> variables.putAll(m._variables()));
        return variables;
    }

    public void add(String type) {
        if (is(type)) return;
        if (!MorphFactory.isAbstract(type)) this.morphs.add(0, MorphFactory.newInstance(type, this));
        types.add(type);
    }

    public void remove(Morph morph) {
        morphs.remove(morph);
    }

    void set(String parameter, Object value) {
        for (Morph morph : morphs) {
            morph._set(parameter, value);
        }
    }

    Node search(Class<? extends Morph> $Class) {
        if (is($Class)) return this;
        if (owner == null) return null;
        return owner.search($Class);
    }

    public Node owner(Class<? extends Morph> superclass) {
        //TODO
        return null;
    }

    public void add(Node component) {
        for (Morph morph : morphs) morph._add(component);
    }

    public void owner(Node owner) {
        this.owner = owner;
    }

    public boolean is(String type) {
        return types.contains(type);
    }

    public <T extends Morph> List<T> components(Class<T> aClass) {
        String name = Morph.getClassName(aClass);
        return components().stream()
                .filter(c -> c.is(name))
                .map(c -> c.morph(aClass))
                .collect(Collectors.toList());
    }

    public boolean is(Class<? extends Morph> morph) {
        return is(Morph.getClassName(morph));
    }

    @SuppressWarnings("unchecked")
    public <T extends Morph> T morph(Class<T> morph) {
        for (Morph aMorph : morphs)
            if (morph.isAssignableFrom(aMorph.getClass()))
                return (T) aMorph;
        return null;
    }

    public <T extends Morph> List<T> find(Class<T> aClass) {
        List<T> tList = new ArrayList<>();
        if (is(aClass.getSimpleName()))
            tList.add(morph(aClass));
        components().forEach(c -> tList.addAll(c.find(aClass)));
        return tList;
    }

}
