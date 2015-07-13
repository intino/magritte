package wata._magritte.lite;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    protected final String name;
    private List<Node> components = new ArrayList<>();
    final ArrayList<String> abstractTypes = new ArrayList<>();
    final ArrayList<Morph> morphs = new ArrayList<>(1);
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
        node.components.forEach(c -> this.components.add(new Node(c, this)));
        node.abstractTypes.forEach(abstractTypes::add);
        node.morphs.forEach(m -> {
            try {
                morphs.add(m.getClass().getDeclaredConstructor(Morph.class, Node.class).newInstance(m, this));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        this.components.forEach(c -> morphs.forEach(m -> m.add(c)));
    }

    public void add(String type) {
        if (MorphFactory.isAbstract(type)) {
            abstractTypes.add(type);
            return;
        }
        this.morphs.add(MorphFactory.newInstance(type, this));
    }

    public void remove(Morph morph) {
        morphs.remove(morph);
    }

    public void set(String parameter, Object value) {
        for (Morph morph : morphs) {
            morph.set(parameter, value);
        }
    }

    <T extends Morph> T search(Class<T> $Class) {
        T morph = morph($Class);
        if(morph != null) return morph;
        if(owner == null) return null;
        return owner.search($Class);
    }

    private Node owner(Class<? extends Morph> superclass) {
        return null;
    }

    public void add(Node component) {
        components.add(component);
        for (Morph morph : morphs) morph.add(component);
    }

    public void owner(Node owner) {
        this.owner = owner;
    }

    public boolean is(String type) {
        for (Morph morph : morphs)
            if (morph.type.equalsIgnoreCase(type))
                return true;
        for (String abstractType : abstractTypes)
            if (abstractType.equalsIgnoreCase(type)) {
                return true;
            }
        return false;
    }

    //TODO
    public Object get(String step) {
        //TODO remove
        return null;
    }

    public <T extends Morph> List<T> components(Class<T> aClass) {
        String name = Morph.getClassName(aClass);
        return components.stream()
                .filter(c -> c.is(name))
                .map(c -> c.morph(aClass))
                .collect(Collectors.toList());
    }

    public boolean is(Class<? extends Morph> morph) {
        return is(Morph.getClassName(morph));
    }

    public <T extends Morph> T morph(Class<T> morph) {
        for (Morph morph1 : morphs) {
            if(morph.isAssignableFrom(morph1.getClass()))
                return (T) morph1;
        }
        return null;
    }

    public <T extends Morph> List<T> find(Class<T> aClass) {
        List<T> tList = new ArrayList<>();
        if (is(aClass.getSimpleName()))
            tList.add(morph(aClass));
        components.forEach(c -> tList.addAll(c.find(aClass)));
        return tList;
    }

}
