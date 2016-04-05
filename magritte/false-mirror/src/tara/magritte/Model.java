package tara.magritte;

import tara.io.Stash;
import tara.magritte.stores.ResourcesStore;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static tara.magritte.ModelCloner.doClone;
import static tara.magritte.utils.StashHelper.stashWithExtension;

@SuppressWarnings("unused")
public class Model extends ModelHandler {

    protected Model(Store store) {
        super(store);
        soil.model = this;
        soil.addLayer(SoilLayer.class);
        soil.typeNames.add("Soil");
    }

    public static Model load() {
        return load(new ResourcesStore());
    }

    public static Model load(Store store) {
        return load("Model", store);
    }

    public static Model load(String stash) {
        return load(stash, new ResourcesStore());
    }

    public static Model load(String stash, Store store) {
        Model model = new Model(store);
        model.init(stash);
        return model;
    }

    public Model loadStashes(String... paths) {
        return loadStashes(asList(paths).stream()
				.filter(p -> !openedStashes.contains(p))
				.map(this::stashOf).toArray(Stash[]::new));
    }

    public Model loadStashes(Stash... stashes) {
        doLoadStashes(stashes);
        return this;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Model clone() {
        return doClone(this, new Model(this.store));
    }

    public <T extends Model> T init(Class<? extends ModelWrapper> applicationClass, Class<? extends ModelWrapper> platformClass) {
		platform = create(platformClass, this);
		application = create(applicationClass, this);
		return (T) this;
    }

	public <T extends Model> T init(Class<? extends ModelWrapper> applicationClass) {
		application = create(applicationClass, this);
		return (T) this;
	}

    public <T extends Layer> T first(Class<T> aClass) {
		List<T> instances = find(aClass);
		return instances.isEmpty() ? null : instances.get(0);
	}

    public <T extends Layer> List<T> find(Class<T> aClass) {
        return soil.findInstance(aClass);
    }

    public List<Instance> components() {
        return soil.components();
    }

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
        return concepts.get(layerFactory.names(layerClass).get(0));
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

	public <T extends Layer> T newMain(Class<T> layerClass) {
		return newMain(layerClass, "Misc", newInstanceId());
	}

	public Instance newMain(Concept concept, String stash){
		return newMain(concept, stash, newInstanceId());
	}

	public <T extends Layer> T newMain(Class<T> layerClass, String stash) {
		return newMain(layerClass, stash, newInstanceId());
	}

	public Instance newMain(String type, String stash) {
		return newMain(conceptOf(type), stash, newInstanceId());
	}

    public <T extends Layer> T newMain(Class<T> layerClass, String stash, String id) {
        Instance instance = newMain(conceptOf(layerClass), stash, id);
        return instance != null ? instance.as(layerClass) : null;
    }

    public Instance newMain(String type, String stash, String id) {
        return newMain(conceptOf(type), stash, id);
    }

	public Instance newMain(Concept concept, String stash, String id){
		Instance newInstance = createInstance(concept, stash, id);
		if(newInstance != null) commit(newInstance);
		return newInstance;
	}

	Instance createInstance(Concept concept, String stash, String id) {
		if (!concept.isMain()) {
			LOG.severe("Concept " + concept.id() + " is not main. The newInstance could not be created.");
			return null;
		}
        if (concept.isAbstract()) {
			LOG.severe("Concept " + concept.id() + " is abstract. The newInstance could not be created.");
			return null;
        }
		return concept.newInstance(stash, id, soil);
	}

	void commit(Instance instance) {
		soil.add(instance);
		register(instance);
		openedStashes.add(stashWithExtension(instance.stash()));
		if (platform != null) platform.addInstance(instance);
		application.addInstance(instance);
	}

	public List<Instance> roots() {
        return unmodifiableList(soil.components());
    }

    @Override
    protected void registerRoot(Instance root) {
        this.soil.add(root);
    }

}
