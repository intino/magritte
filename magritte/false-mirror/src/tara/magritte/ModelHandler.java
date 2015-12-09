package tara.magritte;

import tara.io.Stash;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import static java.util.Collections.unmodifiableList;

public abstract class ModelHandler {
    protected static final Logger LOG = Logger.getLogger(Model.class.getName());
    protected final Store store;
    protected final Soil soil = new Soil();
    private final List<VariableEntry> variables = new ArrayList<>();
    protected Engine engine;
    protected Domain domain;
    protected Map<String, Concept> concepts = new HashMap<>();
    protected Set<String> languages = new LinkedHashSet<>();
    protected Map<String, Instance> instances = new HashMap<>();
    protected long instanceIndex = 0;
    List<InstanceLoader> loaders = new ArrayList<>();

    public ModelHandler(Store store) {
        this.store = store;
    }

    protected static <T> T create(Class<T> class_, Model model) {
        try {
            return class_.getConstructor(Model.class).newInstance(model);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unused")
    public List<String> languages() {
        return unmodifiableList(new ArrayList<>(languages));
    }

    protected void doLoadStashes(Stash... stashes) {
        StashReader stashReader = new StashReader(this);
        for (Stash stash : stashes)
            doLoad(stashReader, stash);
        variables.forEach(vEntry -> vEntry.variables.forEach(vEntry.layer::_load));
        variables.clear();
    }

    public Instance loadInstance(String name) {
        Instance instance = loadFromLoaders(name);
        if (instance == null) instance = instances.get(name);
        if (instance == null) instance = loadFromStash(name);
        return instance;
    }

    @SuppressWarnings("unused")
    public URL loadResource(String path) {
        URL url = store.resourceFrom(path);
        if (url == null)
            LOG.severe("Resource at " + path + " not found");
        return url;
    }

    protected abstract void registerRoot(Instance root);

    @SuppressWarnings("UnusedParameters")
    public void save(Instance instance) {
        //TODO
    }

    protected Stash stashOf(String source) {
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
        doLoadStashes(stashOf(stashName(id)));
        return instances.get(id);
    }

    private String stashName(String id) {
        return id.substring(0, id.indexOf("#"));
    }

    protected void init(String language) {
        if (languages.contains(language)) return;
        if (language.contains("Proteo")) return;
        doInit(language);
    }

    private void doInit(String language) {
        this.languages.add(language);
        Stash stash = stashOf(language + ".stash");
        if (stash == null)
            throw new RuntimeException("Language or model not found: " + language);
        doLoadStashes(stash);
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

    protected void register(Instance instance) {
        instances.put(instance.name, instance);
    }

    @SuppressWarnings("unused")
    public <T extends Domain> T domain(Class<T> class_) {
        return (T) domain;
    }

    static class VariableEntry {
        final Layer layer;
        final Map<String, Object> variables;

        public VariableEntry(Layer layer, Map<String, Object> variables) {
            this.layer = layer;
            this.variables = variables;
        }
    }

}
