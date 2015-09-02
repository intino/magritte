package tara.magritte;

import tara.io.Variable;

import java.util.*;

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
    private List<Variable> variables = new ArrayList<>();

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
        List<String> typeName = MorphFactory.type(aClass);
        List<Type> types = new ArrayList<>();
        types.addAll(requiresMultiple.stream().filter(r -> !r.isAbstract && r.isAnyOf(typeName)).collect(toList()));
        types.addAll(requiresSingle.stream().filter(r -> !r.isAbstract && r.isAnyOf(typeName)).collect(toList()));
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

    void typeVariables(List<Variable> variables){
        this.variables = new ArrayList<>(variables);
    }

    List<Variable> typeVariables() {
        return variables;
    }

    public Node newInstance() {
        return newInstance("");
    }

    public Node newInstance(String name) {
        Node node = new Node(name);
        metaTypes().forEach(node::add);
        for (Type type : metaTypes()) addType(node, type);
        addType(node, this);
        return node;
    }

    private static void addType(Node node, Type type) {
        node.add(type);
        type.variables.forEach(v -> node.set(v.n, v.v));
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
