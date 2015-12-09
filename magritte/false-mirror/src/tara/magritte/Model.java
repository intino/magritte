package tara.magritte;

import tara.io.Stash;
import tara.magritte.stores.ResourcesStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public final class Model extends ModelHandler {

    protected Model(Store store) {
        super(store);
        soil.model = this;
        soil.addLayer(SoilLayer.class);
        soil.typeNames.add("Soil");
    }

    @SuppressWarnings("unused")
    public static Model load() {
        return load(new ResourcesStore());
    }

    public static Model load(Store store) {
        Model model = new Model(store);
        model.init("Model");
        return model;
    }

    @SuppressWarnings("unused")
    public Model loadStashes(String... paths) {
        return loadStashes(asList(paths).stream().map(this::stashOf).toArray(Stash[]::new));
    }

    public Model loadStashes(Stash... stashes) {
        doLoadStashes(stashes);
        return this;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Model clone() {
        Model clone = new Model(this.store);
        clone.loaders = new ArrayList<>(this.loaders);
        clone.languages = new HashSet<>(this.languages);
        clone.engine = this.engine;
        clone.domain = this.domain;
        clone.concepts = new HashMap<>(this.concepts);
        clone.instances = new HashMap<>(this.instances);
        clone.instanceIndex = instanceIndex;
        soil.components().forEach(clone.soil::add);
        return clone;
    }

    @SuppressWarnings("unused")
    public Model init(Class<? extends Domain> domainClass, Class<? extends Engine> engineClass) {
        engine = create(engineClass, this);
        domain = create(domainClass, this);
        return this;
    }

    @SuppressWarnings("unused")
    public <T extends Layer> List<T> find(Class<T> aClass) {
        return soil.findComponents(aClass);
    }

    @SuppressWarnings("unused")
    public List<Instance> components() {
        return soil.components();
    }

    @SuppressWarnings("unused")
    public <T extends Layer> List<T> components(Class<T> layerClass) {
        return soil.components(layerClass);
    }

    public List<Concept> concepts() {
        return unmodifiableList(new ArrayList<>(concepts.values()));
    }

    public Concept conceptOf(String type) {
        return concepts.get(type);
    }

    public Concept conceptOf(Class<? extends Layer> layerClass) {
        return concepts.get(LayerFactory.names(layerClass).get(0));
    }

    @SuppressWarnings("unused")
    public List<Concept> mainConceptsOf(String type) {
        return mainConceptsOf(concepts.get(type));
    }

    @SuppressWarnings("unused")
    public List<Concept> mainConceptsOf(Class<? extends Layer> layerClass) {
        return mainConceptsOf(conceptOf(layerClass));
    }

    public List<Concept> mainConceptsOf(Concept type) {
        return concepts().stream().filter(t -> t.types().contains(type) && t.isMain()).collect(toList());
    }

    @SuppressWarnings("unused")
    public Instance newRoot(Concept concept) {
        return newRoot(concept, newInstanceId());
    }

    public Instance newRoot(Concept concept, String id) {
        if (!concept.isMain()) {
            LOG.severe("Concept " + concept.name() + " is not main. The instance could not be created.");
            return null;
        }
        Instance instance = concept.create(id, soil);
        register(instance);
        registerRoot(instance);
        return instance;
    }

    @SuppressWarnings("unused")
    public <T extends Layer> T newRoot(Class<T> layerClass) {
        return newRoot(layerClass, newInstanceId());
    }

    public <T extends Layer> T newRoot(Class<T> layerClass, String id) {
        Instance instance = newRoot(conceptOf(layerClass), id);
        return instance != null ? instance.as(layerClass) : null;
    }

    @SuppressWarnings("unused")
    public Instance newRoot(String type) {
        return newRoot(type, newInstanceId());
    }

    public Instance newRoot(String type, String id) {
        return newRoot(conceptOf(type), id);
    }

    public List<Instance> roots() {
        return unmodifiableList(soil.components());
    }

    @SuppressWarnings("unused")
    public Engine engine() {
        return engine;
    }

    @SuppressWarnings("unused")
    public Domain domain() {
        return domain;
    }

    @SuppressWarnings("unused")
    public <T extends Engine> T engine(Class<T> class_) {
        return (T) engine;
    }

    @Override
    protected void registerRoot(Instance root) {
        this.soil.add(root);
    }
}
