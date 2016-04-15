package tara.magritte;

import tara.io.Stash;
import tara.magritte.utils.I18n;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static tara.magritte.utils.StashHelper.stashWithExtension;

public abstract class ModelHandler {

	protected static final Logger LOG = Logger.getLogger(ModelHandler.class.getName());
	final Store store;
	final Model model = new Model();
	private final List<VariableEntry> variables = new ArrayList<>();
	protected ModelWrapper platform;
	protected ModelWrapper application;
	LayerFactory layerFactory = new LayerFactory();
	Set<String> openedStashes = new HashSet<>();
	Set<String> languages = new LinkedHashSet<>();
	Map<String, Concept> concepts = new HashMap<>();
	Map<String, Node> nodes = new HashMap<>();
	List<NodeLoader> loaders = new ArrayList<>();
	I18n i18n = new I18n();

	public ModelHandler(Store store) {
		this.store = store;
	}

	protected static <T> T create(Class<T> aClass, Graph graph) {
		try {
			return aClass.getConstructor(Graph.class).newInstance(graph);
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
		of(stashes).filter(s -> s != null).forEach(s -> doLoad(stashReader, s));
		new ArrayList<>(variables).forEach(vEntry -> vEntry.variables.forEach(vEntry.layer::_load));
		variables.clear();
	}

	public Node loadNode(String id) {
		Node node = loadFromLoaders(id);
		if (node == null) node = nodes.get(id);
		if (node == null) node = loadFromStash(id);
		if (node == null) LOG.warning("A reference to an node named as " + id + " has not been found");
		return node;
	}

	public I18n i18n() {
		return i18n;
	}

	@SuppressWarnings("unused")
	public URL loadResource(String path) {
		URL url = store.resourceFrom(path);
		if (url == null)
			LOG.severe("Resource at " + path + " not found");
		return url;
	}

	public Set<String> openedStashes() {
		return openedStashes;
	}

	protected abstract void registerRoot(Node root);

	public Store store() {
		return store;
	}

	@SuppressWarnings("UnusedParameters")
	public void save(Node node) {
		if (!store.allowWriting()) return;
		save(node.namespace());
	}

	private void save(String namespace) {
		save(namespace, model.graph.rootList().stream().filter(i -> i.namespace().equals(namespace)).collect(toList()));
	}

	private void save(String namespace, List<Node> nodes) {
		StashWriter.write(this, stashWithExtension(namespace), nodes);
	}

	@SuppressWarnings("UnusedParameters")
	public URL save(URL url, String path, URL oldUrl, Node node) {
		try {
			return store.writeResource(url.openConnection().getInputStream(), path, oldUrl, node);
		} catch (IOException e) {
			LOG.severe("Url at " + url.toString() + " could not be accessed");
			return null;
		}
	}

	@SuppressWarnings("UnusedParameters")
	public URL save(InputStream inputStream, String path, URL oldUrl, Node node) {
		return store.writeResource(inputStream, path, oldUrl, node);
	}

	protected Stash stashOf(String source) {
		openedStashes.add(source);
		Stash stash = store.stashFrom(source);
		if (stash == null) LOG.severe("Stash " + source + " does not exist or cannot be opened");
		return stash;
	}

	String createNodeId() {
		return UUID.randomUUID().toString();
	}

	void addVariableIn(Layer layer, Map<String, List<?>> variables) {
		this.variables.add(new VariableEntry(layer, variables));
	}

	Concept $concept(String name) {
		if (name == null) return null;
		if (!concepts.containsKey(name)) register(new Concept(name));
		return concepts.get(name);
	}

	Node $Node(String name) {
		if (name == null) name = createNodeId();
		if (nodes.containsKey(name)) return nodes.get(name);
		Node node = new Node(name);
		register(node);
		return node;
	}

	Node node(String name) {
		return nodes.get(name);
	}

	private Node loadFromStash(String id) {
		if (!openedStashes.contains(stashWithExtension(id)))
			doLoadStashes(stashOf(stashWithExtension(id)));
		return node(id);
	}

	protected void init(String language) {
		if (languages.contains(language)) return;
		if (language.contains("Proteo")) return;
		doInit(language);
	}

	private void doInit(String language) {
		this.languages.add(language);
		Stash stash = stashOf(stashWithExtension(language));
		if (stash == null)
			throw new RuntimeException("Language or model not found: " + language);
		doLoadStashes(stash);
	}

	private Node loadFromLoaders(String id) {
		return loaders.stream().map(l -> l.loadNode(id)).filter(i -> i != null).findFirst().orElse(null);
	}

	private void doLoad(StashReader stashReader, Stash stash) {
		init(stash.language);
		stashReader.read(stash);
	}

	protected void register(Concept concept) {
		concepts.put(concept.id, concept);
	}

	protected void register(Node node) {
		if (!node.name().equals("null"))
			nodes.put(node.id, node);
	}

	public <T extends Platform> T platform() {
		return (T) platform;
	}

	public <T extends Application> T application() {
		return (T) application;
	}

	public void remove(Node node) {
		node.owner().remove(node);
		unregister(node);
		if (store.allowWriting()) save(node.namespace());
	}

	public void reload() {
		Set<String> openedStashes = new HashSet<>(this.openedStashes);
		Set<String> languages = new HashSet<>(this.languages);
		clear();
		languages.forEach(this::init);
		openedStashes.forEach(s -> doLoadStashes(stashOf(s)));
		if (platform != null) platform.update();
		application.update();
	}

	public void clear() {
		model.componentList().forEach(model::remove);
		openedStashes.clear();
		languages.clear();
		concepts.clear();
		nodes.clear();
		loaders.clear();
		if (platform != null) platform.update();
		application.update();
		layerFactory.clear();
	}

	protected void unregister(Node node) {
		nodes.remove(node.id);
		if (platform != null) platform.removeNode(node);
		application.removeNode(node);
	}

	static class VariableEntry {
		final Layer layer;
		final Map<String, List<?>> variables;

		public VariableEntry(Layer layer, Map<String, List<?>> variables) {
			this.layer = layer;
			this.variables = variables;
		}
	}

}