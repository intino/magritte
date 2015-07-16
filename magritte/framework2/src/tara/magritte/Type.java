package tara.magritte;

import java.util.LinkedHashSet;
import java.util.Set;

public class Type extends Node {

    private Set<Type> metaTypes = new LinkedHashSet<>();
    private Set<Type> subs = new LinkedHashSet<>();
    private Set<Type> allowsMultiple = new LinkedHashSet<>();
    private Set<Type> allowsSingle = new LinkedHashSet<>();

    public Type(String name) {
        super(name);
    }

    void add(Type metaType) {
        super.add(metaType.name);
        metaTypes.add(metaType);
        metaType.sub(this);
    }

    void sub(Type type) {
        subs.add(type);
    }

    void allowsMultiple(Type type) {
        allowsMultiple.add(type);
    }

    public void allowsSingle(Type type) {
        allowsSingle.add(type);
    }

    public Set<Type> metaTypes() {
        return metaTypes;
    }

    public Set<Type> subs() {
        return subs;
    }

    public Set<Type> allowsMultiple() {
        return allowsMultiple;
    }

    public Set<Type> allowsSingle() {
        return allowsSingle;
    }
}
