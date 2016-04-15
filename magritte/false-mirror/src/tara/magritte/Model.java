package tara.magritte;

import tara.io.Stash;
import tara.magritte.stores.ResourcesStore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public static ModelLoad load() {
		return load(new ResourcesStore());
	}

	public static ModelLoad load(Store store) {
		return load("Model", store);
	}

	public static ModelLoad load(String stash) {
		return load(stash, new ResourcesStore());
	}

	public static ModelLoad load(String stash, Store store) {
		Model model = new Model(store);
		model.init(stash);
		return model.modelLoad();
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

	public <T extends Layer> T first(Class<T> aClass) {
		List<T> nodes = find(aClass);
		return nodes.isEmpty() ? null : nodes.get(0);
	}

	public <T extends Layer> List<T> find(Class<T> aClass) {
		return graph.findNode(aClass);
	}

	public Graph graph() {
		return graph;
	}

	public List<Node> rootList() {
		return unmodifiableList(graph.componentList());
	}

	public List<Node> rootList(java.util.function.Predicate<Node> predicate) {
		return unmodifiableList(graph.componentList().stream().filter(predicate).collect(Collectors.toList()));
	}

	public <T extends Layer> List<T> rootList(Class<T> layerClass) {
		return graph.componentList(layerClass);
	}

	public List<Concept> conceptList() {
		return unmodifiableList(new ArrayList<>(concepts.values()));
	}

	public List<Concept> conceptList(java.util.function.Predicate<Concept> predicate) {
		return concepts.values().stream().filter(predicate).collect(toList());
	}

	public Concept concept(String name) {
		return concepts.get(name);
	}

	public Concept concept(Class<? extends Layer> layerClass) {
		return concepts.get(layerFactory.names(layerClass).get(0));
	}

	public <T extends Layer> T createRoot(Class<T> layerClass) {
		return createRoot(layerClass, "Misc", createNodeId());
	}

	public Node createRoot(Concept concept, String stash) {
		return createRoot(concept, stash, createNodeId());
	}

	public <T extends Layer> T createRoot(Class<T> layerClass, String namespace) {
		return createRoot(layerClass, namespace, createNodeId());
	}

	public Node createRoot(String type, String namespace) {
		return createRoot(concept(type), namespace, createNodeId());
	}

	public <T extends Layer> T createRoot(Class<T> layerClass, String namespace, String id) {
		Node node = createRoot(concept(layerClass), namespace == null ? "Misc" : namespace, id == null ? createNodeId() : id);
		return node != null ? node.as(layerClass) : null;
	}

	public Node createRoot(String type, String stash, String id) {
		return createRoot(concept(type), stash, id);
	}

	public Node createRoot(Concept concept, String stash, String id) {
		Node newNode = createNode(concept, stash, id);
		if (newNode != null) commit(newNode);
		return newNode;
	}

	Node createNode(Concept concept, String stash, String id) {
		if (!concept.isMain()) {
			LOG.severe("Concept " + concept.id() + " is not main. The node could not be created.");
			return null;
		}
		if (concept.isAbstract()) {
			LOG.severe("Concept " + concept.id() + " is abstract. The node could not be created.");
			return null;
		}
		return concept.newNode(stash, id, graph);
	}

	void commit(Node node) {
		graph.add(node);
		register(node);
		openedStashes.add(stashWithExtension(node.namespace()));
		if (platform != null) platform.addNode(node);
		application.addNode(node);
	}

	@Override
	protected void registerRoot(Node root) {
		this.graph.add(root);
	}

	ModelLoad modelLoad() {
		return new ModelLoad();
	}

	public class ModelLoad {

		public ModelLoad loadStashes(String... paths) {
			Model.this.loadStashes(paths);
			return this;
		}

		public ModelLoad loadStashes(Stash... stashes) {
			Model.this.loadStashes(stashes);
			return this;
		}

		public <T extends Model> T wrap(Class<? extends ModelWrapper> applicationClass, Class<? extends ModelWrapper> platformClass) {
			platform = create(platformClass, Model.this);
			application = create(applicationClass, Model.this);
			return (T) Model.this;
		}

		public <T extends Model> T wrap(Class<? extends ModelWrapper> applicationClass) {
			application = create(applicationClass, Model.this);
			return (T) Model.this;
		}
	}

}
