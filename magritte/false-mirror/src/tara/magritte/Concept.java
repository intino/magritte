package tara.magritte;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Concept extends Predicate {

    private boolean isAbstract;
    private boolean isTerminal;
    private boolean isMain;
    private Class<? extends Layer> layerClass;
    private Concept parent;
    private Set<Concept> children = new LinkedHashSet<>();
    private Set<Concept> types = new LinkedHashSet<>();
    private Set<Concept> instances = new LinkedHashSet<>();
    private Set<Concept> allowsMultiple = new LinkedHashSet<>();
    private Set<Concept> allowsSingle = new LinkedHashSet<>();
    private Set<Concept> requiresMultiple = new LinkedHashSet<>();
    private Set<Concept> requiresSingle = new LinkedHashSet<>();
    private List<Instance> components = new ArrayList<>();
    private List<Instance> prototypes = new ArrayList<>();
    private Map<String, Object> variables = new LinkedHashMap<>();

    public Concept(String name) {
        super(name);
    }

    private static void addConcept(Instance instance, Concept concept) {
        instance.addLayer(concept);
        concept.variables.forEach(instance::load);
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    void isAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    void isTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    @SuppressWarnings("unused")
    public boolean isMain() {
        return isMain;
    }

    void isMain(boolean isMain) {
        this.isMain = isMain;
    }

    public Class<? extends Layer> layerClass() {
        return layerClass;
    }

    void layerClass(Class<? extends Layer> layerClass) {
        this.layerClass = layerClass;
    }

    public List<Concept> types() {
        return unmodifiableList(new ArrayList<>(types));
    }

    public Concept parent() {
        return parent;
    }

    public void parent(Concept parent) {
        if (parent == null) return;
        this.parent = parent;
        putType(parent);
        parent.children.add(this);
    }

    @SuppressWarnings("unused")
    public List<Concept> children() {
        return unmodifiableList(new ArrayList<>(children));
    }

    public void types(List<Concept> types) {
        types.forEach(this::putType);
    }

    @Override
    public void putType(Concept concept) {
        if (is(concept.name())) return;
        super.putType(concept);
        types.add(concept);
        concept.instances.add(this);
    }

    @SuppressWarnings("unused")
    public List<Concept> instances() {
        Set<Concept> instances = new LinkedHashSet<>();
        instances.addAll(this.instances);
        this.instances.forEach(s -> instances.addAll(s.instances()));
        return new ArrayList<>(instances);
    }

    public List<Concept> allowedConceptsInComponents() {
        Set concepts = new LinkedHashSet<>();
        concepts.addAll(allowsSingle());
        concepts.addAll(allowsMultiple());
        concepts.addAll(requiresSingle());
        concepts.addAll(requiresMultiple());
        return unmodifiableList(new ArrayList<>(concepts));
    }

    public List<Concept> allowsMultiple() {
        return unmodifiableList(new ArrayList<>(allowsMultiple));
    }

    void allowsMultiple(List<Concept> concepts) {
        allowsMultiple.addAll(concepts);
    }

    public List<Concept> allowsSingle() {
        return unmodifiableList(new ArrayList<>(allowsSingle));
    }

    public void allowsSingle(List<Concept> concepts) {
        allowsSingle.addAll(concepts);
    }

    @SuppressWarnings("unused")
    public List<Concept> requires(Class<? extends Layer> layerClass) {
        List<String> morphConcepts = LayerFactory.names(layerClass);
        List<Concept> concepts = new ArrayList<>();
        concepts.addAll(requiresMultiple.stream().filter(r -> !r.isTerminal() && r.isAnyOf(morphConcepts)).collect(toList()));
        concepts.addAll(requiresSingle.stream().filter(r -> !r.isTerminal() && r.isAnyOf(morphConcepts)).collect(toList()));
        return concepts;
    }

    void requiresMultiple(List<Concept> concepts) {
        requiresMultiple.addAll(concepts);
    }

    public List<Concept> requiresMultiple() {
        return unmodifiableList(new ArrayList<>(requiresMultiple));
    }

    public void requiresSingle(List<Concept> concepts) {
        requiresSingle.addAll(concepts);
    }

    public List<Concept> requiresSingle() {
        return unmodifiableList(new ArrayList<>(requiresSingle));
    }

    @Override
    public Map<String, Object> variables() {
        return Collections.unmodifiableMap(variables);
    }

    @Override
    public void variables(Map<String, Object> variables) {
        this.variables = variables;
    }

    @Override
    public List<Instance> components() {
        return unmodifiableList(components);
    }

    @Override
    public <T extends Layer> List<T> findComponents(Class<T> layerClass) {//TODO
        return null;
    }

    public void components(List<Instance> components) {
        this.components = components;
    }

    public List<Instance> prototypes() {
        return unmodifiableList(prototypes);
    }

    public void prototypes(List<Instance> prototypes) {
        this.prototypes.addAll(prototypes);
    }

    public Instance create(Instance owner) {
        return createInstance(owner.model().newInstanceId(), owner);
    }

    public Instance create(String name, Instance owner) {
        if (!isTerminal) {
            Logger.severe("Instance cannot be created. Concept " + this.name + " is not terminal");
            return null;
        }
        return createInstance(name, owner);
    }

    private Instance createInstance(String name, Instance owner) {
        Instance instance = new Instance(name);
        instance.owner(owner);
        types().forEach(t -> addConcept(instance, t));
        addConcept(instance, this);
        owner.add(instance);
        return instance;
    }

    @Override
    public String toString() {
        return name + "{" +
                "names=" + types.stream().map(m -> m.name).collect(toList()) +
                ", instances=" + instances.stream().map(m -> m.name).collect(toList()) +
                ", allowsMultiple=" + allowsMultiple.stream().map(m -> m.name).collect(toList()) +
                ", allowsSingle=" + allowsSingle.stream().map(m -> m.name).collect(toList()) +
                ", requiresMultiple=" + requiresMultiple.stream().map(m -> m.name).collect(toList()) +
                ", requiresSingle=" + requiresSingle.stream().map(m -> m.name).collect(toList()) +
                '}';
    }
}
