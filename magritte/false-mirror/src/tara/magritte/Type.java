package tara.magritte;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class Type extends Node {

    private boolean isAbstract;
    private Class<? extends Morph> morphClass;
    private Set<Type> metaTypes = new LinkedHashSet<>();
    private Set<Type> subs = new LinkedHashSet<>();
    private Set<Type> allowsMultiple = new LinkedHashSet<>();
    private Set<Type> allowsSingle = new LinkedHashSet<>();
    private Set<Type> requiresMultiple = new LinkedHashSet<>();
    private Set<Type> requiresSingle = new LinkedHashSet<>();

    public Type(String name) {
        super(name);
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public Class<? extends Morph> morphClass() {
        return morphClass;
    }

    void setMorphClass(Class<? extends Morph> morphClass) {
        this.morphClass = morphClass;
    }

    public List<Type> metaTypes() {
        return new ArrayList<>(metaTypes);
    }

    @Override
    public void add(Type metaType) {
        super.add(metaType);
        if(metaType != this) metaTypes.add(metaType);
        if(metaType != this) metaType.sub(this);
    }

    void sub(Type type) {
        subs.add(type);
    }

    public List<Type> subs() {
        Set<Type> subs = new LinkedHashSet<>();
        subs.addAll(this.subs);
        this.subs.forEach(s -> subs.addAll(s.subs()));
        return new ArrayList<>(subs);
    }

    void allowsMultiple(Type type) {
        allowsMultiple.add(type);
    }

    public List<Type> allowsMultiple() {
        return new ArrayList<>(allowsMultiple);
    }

    public void allowsSingle(Type type) {
        allowsSingle.add(type);
    }

    public List<Type> allowsSingle() {
        return new ArrayList<>(allowsSingle);
    }

    public List<Type> requires(Class<? extends Morph> aClass) {
        String typeName = MorphFactory.type(aClass);
        List<Type> types = new ArrayList<>();
        types.addAll(requiresMultiple.stream().filter(r -> !r.isAbstract && r.is(typeName)).collect(toList()));
        types.addAll(requiresSingle.stream().filter(r -> !r.isAbstract && r.is(typeName)).collect(toList()));
        return types;
    }

    void requiresMultiple(Type type) {
        requiresMultiple.add(type);
    }

    public List<Type> requiresMultiple() {
        return new ArrayList<>(requiresMultiple);
    }

    public void requiresSingle(Type type) {
        requiresSingle.add(type);
    }

    public List<Type> requiresSingle() {
        return new ArrayList<>(requiresSingle);
    }

    public Node newInstance() {
        return newInstance("");
    }

    public Node newInstance(String name) {
        Node node = new Node(name);
        metaTypes().forEach(node::add);
        node.add(this);
        return node;
    }

    @Override
    public String toString() {
        return name + "{" +
                "metaTypes=" + metaTypes.stream().map(m -> m.name).collect(toList()) +
                ", subs=" + subs.stream().map(m -> m.name).collect(toList()) +
                ", allowsMultiple=" + allowsMultiple.stream().map(m -> m.name).collect(toList()) +
                ", allowsSingle=" + allowsSingle.stream().map(m -> m.name).collect(toList()) +
                ", requiresMultiple=" + requiresMultiple.stream().map(m -> m.name).collect(toList()) +
                ", requiresSingle=" + requiresSingle.stream().map(m -> m.name).collect(toList()) +
                '}';
    }

}
