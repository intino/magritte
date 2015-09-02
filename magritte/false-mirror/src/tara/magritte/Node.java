package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    public Node(String name, Node node, Node owner) {
        this.name = name;
        this.owner = owner;
        this.types.addAll(node.types);
        this.morphs.addAll(node.morphs.stream().map((morph) -> cloneMorph(morph, node)).collect(toList()));
        node.components().forEach(c -> morphs.forEach(m -> m._add(new Node(name + "." + c.shortName(), c, this))));
        PersistenceManager.registerClone(node.name, node, this);
    }

    public String shortName() {
        String shortName = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
        shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
        return shortName;
    }

    public String name() {
        return name;
    }

    public List<Type> types() {
        List<String> types = new ArrayList<>(this.types);
        Collections.reverse(types);
        return types.stream().map(PersistenceManager::type).collect(Collectors.toList());
    }

    private Morph cloneMorph(Morph morph, Node node) {
        try {
            return morph.getClass().getDeclaredConstructor(Node.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Node> components() {
        Set<Node> nodes = new LinkedHashSet<>();
        for (int i = morphs.size() - 1; i >= 0; i--) nodes.addAll(morphs.get(i)._components());
        return new ArrayList<>(nodes);
    }

    public Map<String, Object> variables() {
        Map<String, Object> variables = new HashMap<>();
        morphs.forEach(m -> variables.putAll(m._variables()));
        return variables;
    }

    public void add(Type type) {
        if (is(type)) return;
        removeSuperClassesMorph(type);
        Morph morph = MorphFactory.newInstance(type.name, this);
        if (morph != null) this.morphs.add(0, morph);
        types.add(type.name());
    }

    private void removeSuperClassesMorph(Type type) {
        if(type.isAbstract()) return;
        morphs.removeAll(morphs.stream().filter(m -> m.getClass().isAssignableFrom(type.morphClass())).collect(toList()));
    }

    public void remove(Morph morph) {
        morphs.remove(morph);
    }

    public void set(String parameter, Object value) {
        for (Morph morph : morphs) {
            morph._set(parameter, value);
        }
    }

    public void owner(Node owner) {
        this.owner = owner;
    }

    public Node owner() {
        return owner;
    }

    public <T extends Morph> T owner(Class<T> $Class) {
        if (owner == null) return null;
        if (owner.is($Class)) return owner.morph($Class);
        return owner.owner($Class);
    }

    public void add(Node component) {
        for (Morph morph : morphs) morph._add(component);
    }

    public boolean is(Type type) {
        return types().contains(type);
    }

    public boolean is(String type) {
        return types.contains(type);
    }

    public <T extends Morph> List<T> components(Class<T> aClass) {
        String name = morphType(aClass);
        return components().stream()
                .filter(c -> c.is(name))
                .map(c -> c.morph(aClass))
                .collect(toList());
    }

    public boolean is(Class<? extends Morph> morph) {
        return is(morphType(morph));
    }

    public List<Morph> _morphs() {
        return Collections.unmodifiableList(morphs);
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

    private static String morphType(Class<? extends Morph> aClass) {
        return MorphFactory.type(aClass);
    }
}
