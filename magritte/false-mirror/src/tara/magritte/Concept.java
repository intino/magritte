package tara.magritte;

import java.util.*;
import java.util.logging.Logger;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Concept extends Predicate {

    private static final Logger LOG = Logger.getLogger(Concept.class.getName());
    boolean isAbstract;
    boolean isMetaConcept;
    boolean isMain;
    Class<? extends Layer> layerClass;
    private Concept parent;
    private final Set<Concept> children = new LinkedHashSet<>();
    private final Set<Concept> types = new LinkedHashSet<>();
    private final Set<Concept> concepts = new LinkedHashSet<>();
    Set<Content> contentRules = new LinkedHashSet<>();
    List<Instance> components = new ArrayList<>();
    List<Instance> prototypes = new ArrayList<>();
    Map<String, List<?>> variables = new LinkedHashMap<>();

    public Concept(String name) {
        super(name);
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isMetaConcept() {
        return isMetaConcept;
    }

    @SuppressWarnings("unused")
    public boolean isMain() {
        return isMain;
    }

    public Class<? extends Layer> layerClass() {
        return layerClass;
    }

    public List<Concept> types() {
        return unmodifiableList(new ArrayList<>(types));
    }

    public Concept parent() {
        return parent;
    }

    void parent(Concept parent) {
        if (parent == null) return;
        this.parent = parent;
        putType(parent);
        parent.children.add(this);
    }

    @SuppressWarnings("unused")
    public List<Concept> children() {
        return unmodifiableList(new ArrayList<>(children));
    }

    void types(List<Concept> types) {
        types.forEach(this::putType);
    }

    @Override
    protected void putType(Concept concept) {
        if (is(concept.name())) return;
        super.putType(concept);
        types.add(concept);
        concept.concepts.add(this);
    }

    @SuppressWarnings("unused")
    public List<Concept> instances() {
        Set<Concept> instances = new LinkedHashSet<>();
        instances.addAll(this.concepts);
        this.concepts.forEach(s -> instances.addAll(s.instances()));
        return new ArrayList<>(instances);
    }

    @SuppressWarnings("unused")
    public List<Concept> allowedConceptsInComponents() {
        Set concepts = new LinkedHashSet<>();
        concepts.addAll(allowsSingle());
        concepts.addAll(allowsMultiple());
        concepts.addAll(requiresSingle());
        concepts.addAll(requiresMultiple());
        return unmodifiableList(new ArrayList<>(concepts));
    }

    public List<Concept> allowsMultiple() {
        return unmodifiableList(contentRules.stream().filter(c -> c.max > 1).map(c -> c.concept).collect(toList()));
    }

    public List<Concept> allowsSingle() {
        return unmodifiableList(contentRules.stream().filter(c -> c.max == 1).map(c -> c.concept).collect(toList()));
    }

    public List<Concept> requiresMultiple() {
        return unmodifiableList(contentRules.stream().filter(c -> c.min == 1 && c.max > 1).map(c -> c.concept).collect(toList()));
    }

    public List<Concept> requiresSingle() {
        return unmodifiableList(contentRules.stream().filter(c -> c.min == 1 && c.max == 1).map(c -> c.concept).collect(toList()));
    }

    @Override
    public Map<String, List<?>> variables() {
        Map<String, List<?>> variables = new HashMap<>();
        types.forEach(t -> variables.putAll(t.variables()));
        variables.putAll(this.variables);
        return Collections.unmodifiableMap(variables);
    }

    @Override
    public List<Instance> components() {
        return unmodifiableList(components);
    }

    @Override
    public <T extends Layer> List<T> findInstance(Class<T> layerClass) {//TODO
        return null;
    }

    public List<Instance> prototypes() {
        return unmodifiableList(prototypes);
    }

    public Instance create(Instance owner) {
        return createInstance(owner.model().newInstanceId(), owner);
    }

    public Instance create(String name, Instance owner) {
        if (isMetaConcept) {
            LOG.severe("Instance cannot be created. Concept " + this.name + " is a MetaConcept");
            return null;
        }
        return createInstance(name, owner);
    }

    private Instance createInstance(String name, Instance owner) {
        Instance instance = new Instance(name);
        instance.owner(owner);
        createLayersFor(instance);
        owner.add(instance);
        return instance;
    }

    private void createLayersFor(Instance instance) {
        types().forEach(instance::addLayer);
        instance.addLayer(this);
        Layer layer = instance.as(this.name);
        variables().forEach(layer::_load);
    }

    @Override
    public String toString() {
        return name + "{" +
                "names=" + types.stream().map(m -> m.name).collect(toList()) +
                ", concepts=" + concepts.stream().map(m -> m.name).collect(toList()) +
                ", content=" + contentRules.stream().map(m -> m.concept.name).collect(toList()) +
                '}';
    }

    static class Content {

        Concept concept;
        int min, max;

        Content(Concept concept, int min, int max) {
            this.concept = concept;
            this.min = min;
            this.max = max;
        }
    }
}
