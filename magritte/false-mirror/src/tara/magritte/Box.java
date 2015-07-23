package tara.magritte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;


public abstract class Box {

    public static Map<String, Type> typeRecord = new HashMap<>();
    public Map<Object, Node> nodeRecord = new WeakHashMap<>();
    public Node current;
    private Node root;

    public void load(Node root) {
        typeRecord.clear();
        nodeRecord.clear();
        this.root = root;
        this.root.add("root");
        dependencies().forEach(d -> d.load(root));
        write();
    }

    public abstract void write();

    public abstract List<Box> dependencies();

    public Box def(String name, Class<? extends Morph> morphClass) {
        MorphFactory.register(name, morphClass.getName());
        current = getType(name);
        current.add(name);
        return this;
    }

    @SuppressWarnings("UnusedParameters")
    public Box abstractDef(String name, Class<? extends Morph> morphClass) {
        MorphFactory.registerAbstract(name);
        current = getType(name);
        current.add(name);
        return this;
    }

    public Box proto(int number) {
        return thing(number);
    }

    protected Box proto(String name, Class<? extends Morph> morph) {
        MorphFactory.register(name, morph.getName());
        getType(name);
        thing(name);
        current.add(name);
        return this;
    }

    public Box thing(int number) {
        current = getNode(number);
        return this;
    }

    public Box thing(String name) {
        current = getNode(name);
        return this;
    }

    public Box set(String parameter, Object object) {
        current.set(parameter, object);
        return this;
    }

    public Box type(String type) {
        if (current instanceof Type)
            type(((Type) current), type);
        else type(current, type);
        return this;
    }

    private void type(Type type, String metaType) {
        if (metaType.equalsIgnoreCase("concept")) return;
        type.add(getType(metaType));
        typeRecord.get(metaType).metaTypes().forEach(m -> type(type, m.name));
    }

    private void type(Node node, String type) {
        node.add(type);
        typeRecord.get(type).metaTypes().forEach(m -> node.add(m.name));
    }

    private void clonePrototypes() {
        current.types().forEach(t -> typeRecord.get(t).components().forEach(c -> current.add(new Node(c, current))));
    }

    public Box root() {
        root.add(current);
        return this;
    }

    public Box allowsMultiple(String... types) {
        for (String type : types) ((Type) current).allowsMultiple(getType(type));
        return this;
    }

    public Box allowsSingle(String... types) {
        for (String type : types) ((Type) current).allowsSingle(getType(type));
        return this;
    }

    public Box has(int number) {
        has(getNode(number));
        return this;
    }

    public Box has(String name) {
        has(getNode(name));
        return this;
    }

    private void has(Node node) {
        node.owner(current);
    }

    private Type getType(String name) {
        if (!typeRecord.containsKey(name))
            typeRecord.put(name, new Type(name));
        return typeRecord.get(name);
    }

    private Node getNode(Object object) {
        if (!nodeRecord.containsKey(object))
            nodeRecord.put(object, new Node(object.toString()));
        return nodeRecord.get(object);
    }


    public void end() {
        clonePrototypes();
        if (current.owner != null)
            current.owner.add(current);
    }
}
