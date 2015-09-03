package tara.magritte;

import tara.io.Variable;

import java.util.*;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

public class Type extends Case {

    private static final Logger LOG = Logger.getLogger(Type.class.getName());

    private boolean isAbstract;
    private boolean isTerminal;
    private boolean isMain;
    private Class<? extends Facet> morphClass;
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

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean isMain) {
        this.isMain = isMain;
    }

    public Class<? extends Facet> morphClass() {
        return morphClass;
    }

    void setMorphClass(Class<? extends Facet> morphClass) {
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

    public List<Type> requires(Class<? extends Facet> aClass) {
        List<String> typeName = FacetFactory.type(aClass);
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

    public Case newCase(String name) {
        if(!isTerminal){
            LOG.severe("Case cannot be created. Type " + name + " is not terminal");
            return null;
        }
        return createCase(name);
    }

    private Case createCase(String name) {
        Case aCase = new Case(name);
        metaTypes().forEach(aCase::add);
        for (Type type : metaTypes()) addType(aCase, type);
        addType(aCase, this);
        return aCase;
    }

    private static void addType(Case aCase, Type type) {
        aCase.add(type);
        type.variables.forEach(v -> aCase.set(v.n, v.v));
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
