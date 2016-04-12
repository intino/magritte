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
        graph.model = this;
        graph.addLayer(SoilLayer.class);
        graph.typeNames.add("Graph");
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
        return graph.findNode(aClass);
    }

    public List<Node> components() {
        return graph.components();
    }

    public <T extends Layer> List<T> components(Class<T> layerClass) {
        return graph.components(layerClass);
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
        return concepts().stream().filter(t -> t.concepts().contains(type) && t.isMain()).collect(toList());
    }

	public <T extends Layer> T newMain(Class<T> layerClass) {
		return newMain(layerClass, "Misc", newNodeId());
	}

	public Node newMain(Concept concept, String stash){
		return newMain(concept, stash, newNodeId());
	}

	public <T extends Layer> T newMain(Class<T> layerClass, String stash) {
		return newMain(layerClass, stash, newNodeId());
	}

	public Node newMain(String type, String stash) {
		return newMain(conceptOf(type), stash, newNodeId());
	}

    public <T extends Layer> T newMain(Class<T> layerClass, String stash, String id) {
        Node node = newMain(conceptOf(layerClass), stash, id);
        return node != null ? node.as(layerClass) : null;
    }

    public Node newMain(String type, String stash, String id) {
        return newMain(conceptOf(type), stash, id);
    }

	public Node newMain(Concept concept, String stash, String id){
		Node newNode = createInstance(concept, stash, id);
		if(newNode != null) commit(newNode);
		return newNode;
	}

	Node createInstance(Concept concept, String stash, String id) {
		if (!concept.isMain()) {
			LOG.severe("Concept " + concept.id() + " is not main. The newNode could not be created.");
			return null;
		}
        if (concept.isAbstract()) {
			LOG.severe("Concept " + concept.id() + " is abstract. The newNode could not be created.");
			return null;
        }
		return concept.newNode(stash, id, graph);
	}

	void commit(Node node) {
		graph.add(node);
		register(node);
		openedStashes.add(stashWithExtension(node.namespace()));
		if (platform != null) platform.addInstance(node);
		application.addInstance(node);
	}

	public List<Node> roots() {
        return unmodifiableList(graph.components());
    }

    @Override
    protected void registerRoot(Node root) {
        this.graph.add(root);
    }

}
