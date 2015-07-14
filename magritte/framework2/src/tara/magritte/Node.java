package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Node {

    protected final String name;
    final Set<String> types = new HashSet<>();
    final List<Morph> morphs = new ArrayList<>(1);
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
        node.types.forEach(types::add);
        node.morphs.forEach(m -> {
            try {
                morphs.add(m.getClass().getDeclaredConstructor(Morph.class, Node.class).newInstance(m, this));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        components().forEach(c -> morphs.forEach(m -> m.add(new Node(c, this))));
    }

    private List<Node> components() {
        Set<Node> nodes = new HashSet<>();
        morphs.forEach(m -> m.components().forEach(nodes::add));
        return new ArrayList<>(nodes);
    }

    public void add(String type) {
        if(is(type)) return;
        if (MorphFactory.isAbstract(type)) {
            types.add(type);
            return;
        }
        this.morphs.add(MorphFactory.newInstance(type, this));
    }

    public void remove(Morph morph) {
        morphs.remove(morph);
    }

    void set(String parameter, Object value) {
        for (Morph morph : morphs) {
            morph.set(parameter, value);
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
        for (Morph morph : morphs) morph.add(component);
    }

    public void owner(Node owner) {
        this.owner = owner;
    }

    public boolean is(String type) {
        for (Morph morph : morphs)
            if (morph.type.equalsIgnoreCase(type))
                return true;
        for (String abstractType : types)
            if (abstractType.equalsIgnoreCase(type)) {
                return true;
            }
        return false;
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
        for (Morph morph1 : morphs)
            if (morph.isAssignableFrom(morph1.getClass()))
                return (T) morph1;
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
