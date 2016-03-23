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
    Concept metatype = null;
    private final Set<Concept> children = new LinkedHashSet<>();
    private final Set<Concept> types = new LinkedHashSet<>();
    private final Set<Concept> concepts = new LinkedHashSet<>();
    Set<Content> contentRules = new LinkedHashSet<>();
    List<Instance> components = new ArrayList<>();
    List<Instance> prototypes = new ArrayList<>();
    Map<String, List<?>> variables = new LinkedHashMap<>();
    Map<String, List<?>> parameters = new LinkedHashMap<>();

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
        return Collections.unmodifiableMap(variables);
    }

    @Override
    public List<Instance> components() {
        return unmodifiableList(components);
    }

    @Override
    public <T extends Layer> List<T> findInstance(Class<T> layerClass) {//TODO
        //TODO
        return null;
    }

	public List<Instance> prototypes() {
        return unmodifiableList(prototypes);
    }

	Instance newInstance(String stash, String name, Instance owner) {
		if (isMetaConcept) {
			LOG.severe("Instance cannot be created. Concept " + this.name + " is a MetaConcept");
			return null;
		}
		return createInstance(stash + "#" + (name != null ? name : owner.model().newInstanceId()), owner);
	}

    public Instance newInstance(Instance owner) {
        return newInstance(owner.model().newInstanceId(), owner);
    }

    public Instance newInstance(String name, Instance owner) {
        if (isMetaConcept) {
            LOG.severe("Instance cannot be created. Concept " + this.name + " is a MetaConcept");
            return null;
        }
        return createInstance(owner.stash() + "#" + (name != null ? name : owner.model().newInstanceId()), owner);
    }

    private Instance createInstance(String name, Instance owner) {
        Instance instance = owner.model().newInstance(name);
        instance.owner(owner);
        createLayersFor(instance);
        if(!owner.is("Soil")) owner.add(instance);
        return instance;
    }

    private void createLayersFor(Instance instance) {
        types().forEach(instance::addLayer);
        instance.addLayer(this);
        types().forEach(t -> t.fillVariables(instance.as(t)));
        types().stream().filter(t -> t.metatype != null).forEach(t -> t.fillParameters(instance.as(t.metatype)));
        fillVariables(instance.as(this));
    }

    private void fillVariables(Layer layer) {
        variables.forEach(layer::_load);
    }

    private void fillParameters(Layer layer) {
        parameters.forEach(layer::_load);
    }

    @Override
    public String toString() {
        return name + "{" +
                "names=" + types.stream().map(m -> m.name).collect(toList()) +
                ", concepts=" + concepts.stream().map(m -> m.name).collect(toList()) +
                ", content=" + contentRules.stream().map(m -> m.concept.name).collect(toList()) +
                '}';
    }

	public boolean is(String type) {
		return typeNames.contains(type);
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
