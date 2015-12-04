package tara.magritte;

import tara.io.Stash;
import tara.magritte.stores.ResourcesStore;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Logger;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Model {

    private static final Logger LOG = Logger.getLogger(Model.class.getName());

    List<InstanceLoader> loaders = new ArrayList<>();
    List<VariableEntry> variables = new ArrayList<>();
    Soil soil = new Soil();
    private Set<String> languages = new LinkedHashSet<>();
    private Engine engine;
    private Domain domain;
    private Map<String, Concept> concepts = new HashMap<>();
    private Map<Object, Instance> instances = new HashMap<>();
    private long instanceIndex = 0;
    private Store store;

    public Model(Store store) {
        soil.addLayer(SoilLayer.class);
        soil.typeNames.add("Soil");
        this.store = store;
    }


    public static Model load() {
        return load(new ResourcesStore());
    }


    public static Model load(Store store) {
        Model model = new Model(store);
        model.init("Model");
        return model;
    }

    public Model init(Class<? extends Domain> domainClass, Class<? extends Engine> engineClass) {
        engine = create(engineClass, this);
        domain = create(domainClass, this);
        return this;
    }


    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Model clone() {
        Model clone = new Model(this.store);
        clone.loaders = new ArrayList<>(this.loaders);
        clone.languages = new HashSet<>(this.languages);
        clone.engine = this.engine;
        clone.domain = this.domain;
        soil.components().forEach(c -> clone.soil.add(c));
        clone.concepts = new HashMap<>(this.concepts);
        clone.instances = new HashMap<>(this.instances);
        return clone;
    }

    public List<String> languages() {
        return unmodifiableList(new ArrayList<>(languages));
    }


    public Model loadStashes(String... paths) {
        return loadStashes(asList(paths).stream().map(this::stashOf).toArray(Stash[]::new));
    }


    public Model loadStashes(Stash... stashes) {
        StashReader stashReader = new StashReader(this);
        for (Stash stash : stashes)
            doLoad(stashReader, stash);
        variables.forEach(vEntry -> vEntry.variables.forEach(vEntry.layer::_load));
        variables.clear();
        return this;
    }

    public Instance loadInstance(String name) {
        Instance instance = loadFromLoaders(name);
        if (instance == null) instance = instances.get(name);
        if (instance == null) instance = loadFromStash(name);
        return instance;
    }

    public <T extends Layer> List<T> find(Class<T> aClass) {
        return soil.findComponents(aClass);
    }

    public List<Instance> components() {
        return soil.components();
    }

    public <T extends Layer> List<T> components(Class<T> layerClass) {
        return soil.components(layerClass);
    }

    public void registerRoot(Instance root) {
        this.soil.add(root);
    }

    public void save(Instance instance) {
        //TODO
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

    public List<Concept> mainConceptsOf(String type) {
        return mainConceptsOf(concepts.get(type));
    }

    public List<Concept> mainConceptsOf(Class<? extends Layer> layerClass) {
        return mainConceptsOf(conceptOf(layerClass));
    }

    public List<Concept> mainConceptsOf(Concept type) {
        return concepts().stream().filter(t -> t.types().contains(type) && t.isMain()).collect(toList());
    }

    public Instance newRoot(Concept concept) {
        return newRoot(concept, newInstanceId());
    }

    public Instance newRoot(Concept concept, String id) {
        if (!concept.isMain()) {
            LOG.severe("Concept " + concept.name() + " is not main");
            return null;
        }
        Instance instance = concept.create(id, soil);
        register(instance);
        registerRoot(instance);
        return instance;
    }

    public <T extends Layer> T newRoot(Class<T> layerClass) {
        return newRoot(layerClass, newInstanceId());
    }

    public <T extends Layer> T newRoot(Class<T> layerClass, String id) {
        return newRoot(conceptOf(layerClass), id).as(layerClass);
    }

    public Instance newRoot(String type) {
        return newRoot(type, newInstanceId());
    }

    public Instance newRoot(String type, String id) {
        return newRoot(conceptOf(type), id);
    }

    public List<Instance> roots() {
        return unmodifiableList(soil.components());
    }

    public Engine engine() {
        return engine;
    }

    public Domain domain() {
        return domain;
    }

    public <T extends Engine> T engine(Class<T> class_) {
        return (T) engine;
    }

    public <T extends Domain> T domain(Class<T> class_) {
        return (T) domain;
    }

    private static <T> T create(Class<T> class_, Model model) {
        try {
            return class_.getConstructor(Model.class).newInstance(model);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Stash stashOf(String source) {
        Stash stash = store.stashFrom(source);
        if (stash == null) LOG.severe("Stash " + source + " does not exist or cannot be opened");
        return stash;
    }

    String newInstanceId() {
        return "i" + instanceIndex++;
    }

    void addVariableIn(Layer layer, Map<String, Object> variables) {
        this.variables.add(new VariableEntry(layer, variables));
    }

    Concept concept(String name) {
        if (name == null) return null;
        if (!concepts.containsKey(name)) register(new Concept(name));
        return concepts.get(name);
    }

    Instance instance(String name) {
        if (name == null) name = newInstanceId();
        if (!instances.containsKey(name)) register(new Instance(name));
        return instances.get(name);
    }

    private Instance loadFromStash(String id) {
        String stashPath = stashName(id);
        loadStashes(stashOf(stashPath));
        return instances.get(id);
    }

    private String stashName(String id) {
        return id.substring(0, id.indexOf("#"));
    }

    private void init(String language) {
        if (languages.contains(language)) return;
        if (language.contains("Proteo")) return;
        doInit(language);
    }

    private void doInit(String language) {
        this.languages.add(language);
        Stash stash = stashOf(language + ".stash");
        if (stash == null)
            throw new RuntimeException("Language or model not found: " + language);
        loadStashes(stash);
    }

    private Instance loadFromLoaders(String id) {
        for (InstanceLoader loader : loaders)
            if (loader.loadInstance(id) != null)
                return loader.loadInstance(id);
        return null;
    }

    private void doLoad(StashReader stashReader, Stash stash) {
        init(stash.language);
        stashReader.read(stash);
    }

    private void register(Concept concept) {
        concepts.put(concept.name, concept);
    }

    private void register(Instance instance) {
        instances.put(instance.name, instance);
    }

    static class VariableEntry {
        Layer layer;
        Map<String, Object> variables;

        public VariableEntry(Layer layer, Map<String, Object> variables) {
            this.layer = layer;
            this.variables = variables;
        }
    }

    public class Soil extends Instance {

        @Override
        public Model model() {
            return Model.this;
        }

        public Engine engine() {
            return engine;
        }

        public Domain domain() {
            return domain;
        }

        public <T extends Engine> T engine(Class<T> class_) {
            return (T) engine;
        }

        public <T extends Domain> T domain(Class<T> class_) {
            return (T) domain;
        }

    }
}
