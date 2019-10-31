package io.intino.tara.magritte;

import io.intino.tara.dsl.Meta;
import io.intino.tara.dsl.Proteo;
import io.intino.tara.io.Stash;
import io.intino.tara.magritte.stores.ResourcesStore;
import io.intino.tara.magritte.utils.I18n;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.intino.tara.magritte.utils.StashHelper.stashWithExtension;
import static java.util.Arrays.stream;
import static java.util.logging.Logger.getGlobal;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Graph {

	Model model;
	Store store;
	Map<String, Node> nodes = new HashMap<>();
	Map<String, Concept> concepts = new HashMap<>();
	Map<Class<? extends GraphWrapper>, GraphWrapper> wrappers = new HashMap<>();
	List<NodeLoader> loaders = new ArrayList<>();
	LayerFactory layerFactory = new LayerFactory();
	Set<String> languages = new LinkedHashSet<>();
	Set<String> openedStashes = new HashSet<>();
	private Map<Node, Map<String, List<?>>> variables = new HashMap<>();
	private I18n i18n = new I18n();

	public Graph() {
		this(new ResourcesStore());
	}

	public Graph(Store store) {
		this.store = store;
		model = new Model(this, wrappers);
	}

	public Graph loadStashes(String... stashes) {
		if (stashes.length == 0) return this;
		doLoadStashes(stashes);
		return this;
	}

	public Graph loadStashes(Stash... stashes) {
		doLoadStashes(stashes);
		return this;
	}

	public Node load(String id) {
		return load(id, true);
	}

	public Node load(String id, boolean logFail) {
		Node node = loadFromLoaders(id);
		if (node == null) node = nodes.get(id);
		if (node == null) node = loadFromStash(id, logFail);
		if (node == null && logFail)
			getGlobal().warning("A reference to a node named as " + id + " has not been found");
		return node;
	}

	public List<String> languages() {
		return new ArrayList<>(languages);
	}

	public I18n i18n() {
		return i18n;
	}

	public URL loadResource(String path) {
		return loadResource(path, true);
	}

	@SuppressWarnings("WeakerAccess")
	public URL loadResource(String path, boolean logFail) {
		URL url = store.resourceFrom(path);
		if (url == null && logFail)
			getGlobal().severe("Resource at " + path + " not found");
		return url;
	}

	public String[] openedStashes() {
		return new HashSet<>(openedStashes).toArray(new String[openedStashes.size()]);
	}

	public Store store() {
		return store;
	}

	@SuppressWarnings("UnusedParameters")
	void save(Node node) {
		save(node.stash());
	}

	public synchronized void save(String... stashes) {
		if (!store.allowWriting()) return;
		GraphHelper.saveStashes(this, stashes);
	}

	public synchronized void saveAll(String... excludedStashes) {
		if (!store.allowWriting()) return;
		GraphHelper.saveAll(this, excludedStashes);
	}

	@SuppressWarnings("UnusedParameters")
	public synchronized URL save(URL url, String path, URL oldUrl, Node node) {
		try {
			return store.writeResource(url.openConnection().getInputStream(), path, oldUrl, node);
		} catch (IOException e) {
			getGlobal().severe("Url at " + url.toString() + " could not be accessed");
			return null;
		}
	}

	@SuppressWarnings("UnusedParameters")
	public synchronized URL save(InputStream inputStream, String path, URL oldUrl, Node node) {
		return store.writeResource(inputStream, path, oldUrl, node);
	}

	public <T extends GraphWrapper> T as(Class<T> aClass) {
		if (!wrappers.containsKey(aClass)) wrappers.put(aClass, GraphHelper.create(aClass, this));
		return (T) wrappers.get(aClass);
	}

	public void remove(Node node) {
		node.owner().remove(node);
		nodes.remove(node.id);
		save(node.stash());
	}

	public void remove(String stash) {
		nodesIn(stash).forEach(node -> {
			node.owner().remove(node);
			nodes.remove(node.id);
		});
		save(stash);
	}

	public void reload() {
		Set<String> openedStashes = new HashSet<>(this.openedStashes);
		clear();
		openedStashes.forEach(s -> doLoadStashes(stashOf(s)));
		wrappers.values().forEach(GraphWrapper::update);
	}

	public void clear() {
		model.componentList().forEach(model::remove);
		openedStashes.clear();
		languages.clear();
		concepts.clear();
		nodes.clear();
		loaders.clear();
		wrappers.values().forEach(GraphWrapper::update);
		layerFactory.clear();
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public synchronized Graph clone() {
		return GraphCloner.doClone(this, new Graph(this.store));
	}

	public <T extends Layer> T first(Class<T> aClass) {
		List<T> nodes = find(aClass);
		return nodes.isEmpty() ? null : nodes.get(0);
	}

	public <T extends Layer> List<T> find(Class<T> aClass) {
		return model.findNode(aClass);
	}

	public M1 model() {
		return model.as(M1.class);
	}

	public M2 metamodel() {
		return model.as(M2.class);
	}

	public M3 metametamodel() {
		return model.as(M3.class);
	}

	public M1 m1() {
		return model.as(M1.class);
	}

	public M2 m2() {
		return model.as(M2.class);
	}

	public M3 m3() {
		return model.as(M3.class);
	}

	public List<Node> rootList() {
		return new ArrayList<>(model.componentList());
	}

	public List<Node> rootList(java.util.function.Predicate<Node> predicate) {
		return new ArrayList<>(model.componentList()).stream().filter(predicate).collect(Collectors.toList());
	}

	public <T extends Layer> List<T> rootList(Class<T> layerClass) {
		return model.componentList(layerClass);
	}

	public List<Concept> conceptList() {
		return new ArrayList<>(concepts.values());
	}

	public Stream<Concept> conceptList(java.util.function.Predicate<Concept> predicate) {
		return concepts.values().stream().filter(predicate);
	}

	public Concept concept(String name) {
		return concepts.get(name);
	}

	public Concept concept(Class<? extends Layer> layerClass) {
		return concepts.get(layerFactory.names(layerClass).get(0));
	}

	public <T extends Layer> T createRoot(Class<T> layerClass) {
		return createRoot(layerClass, "Misc", createNodeName());
	}

	public Node createRoot(Concept concept, String stash) {
		return createRoot(concept, stash, createNodeName());
	}

	public <T extends Layer> T createRoot(Class<T> layerClass, String stash) {
		return createRoot(layerClass, stash, createNodeName());
	}

	public Node createRoot(String type, String stash) {
		return createRoot(concept(type), stash, createNodeName());
	}

	public <T extends Layer> T createRoot(Class<T> layerClass, String stash, String name) {
		Node node = createRoot(concept(layerClass), stash, name);
		return node != null ? node.as(layerClass) : null;
	}

	public Node createRoot(String type, String stash, String name) {
		return createRoot(concept(type), stash, name);
	}

	public Node createRoot(Concept concept, String stash, String name) {
		Node newNode = GraphHelper.createNode(this, concept, stash, name);
		if (newNode != null) commit(newNode);
		return newNode;
	}

	private void doLoadStashes(String... stashes) {
		stream(stashes).forEach(s -> {
			Stash stash = stashOf(stashWithExtension(s));
			doLoadStashes(stash);
			if(stash != null && !stash.concepts.isEmpty()) this.languages.add(s);
		});
	}

	void doLoadStashes(Stash... stashes) {
		if (stashes == null || stashes.length == 0) return;
		stashes = processUses(stashes);
		stream(stashes).filter(Objects::nonNull).forEach(s -> init(s.language));
		if (stashes.length == 0) return;
		readStashes(stashes);
	}

	private void readStashes(Stash[] stashes) {
		StashReader stashReader = new StashReader(this);
		of(stashes).forEach(stashReader::read);
		LinkedHashMap<Node, Map<String, List<?>>> clone = new LinkedHashMap<>(variables);
		clone.forEach((node, map) -> {
			variables.remove(node);
			map.forEach(node::load);
		});
	}

	private Stash[] processUses(Stash[] stashes) {
		List<Stash> stashList = stream(stashes).filter(Objects::nonNull).collect(toList());
		int stashListSize = 0;
		while (stashListSize != stashList.size()) {
			stashListSize = stashList.size();
			stashList.addAll(processUses(stashList));
		}
		return stashList.toArray(new Stash[stashList.size()]);
	}

	private List<Stash> processUses(List<Stash> stashList) {
		List<Stash> result = new ArrayList<>();
		stashList.forEach(s -> s.uses.stream().map(this::stashOf).filter(Objects::nonNull).forEach(result::add));
		return result;
	}

	private List<Node> nodesIn(String stash) {
		return model.graph.rootList().stream().filter(i -> i.stash().equals(stash)).collect(toList());
	}

	private Stash stashOf(String source) {
		return stashOf(source, true);
	}

	Stash stashOf(String source, boolean logFail) {
		source = stashWithExtension(source);
		if (openedStashes.contains(source)) return null;
		openedStashes.add(source);
		Stash stash = store.stashFrom(source);
		if (stash == null && logFail) getGlobal().warning("Stash " + source + " does not exist or cannot be opened");
		return stash;
	}

	String createNodeName() {
		return UUID.randomUUID().toString();
	}

	void addVariableIn(Node node, Map<String, List<?>> variables) {
		this.variables.put(node, variables);
	}

	Concept concept$(String name) {
		if (name == null) return null;
		if (!concepts.containsKey(name)) register(new Concept(name));
		return concepts.get(name);
	}

	Node node$(String name) {
		if (name == null) name = createNodeName();
//		TODO check if (nodes.containsKey(name)) return nodes.get(name);
		Node node = new Node(name);
		register(node);
		return node;
	}

	protected Node node(String name) {
		return nodes.get(name);
	}

	protected Node loadFromStash(String id) {
		return loadFromStash(id, true);
	}

	protected Node loadFromStash(String id, boolean logFail) {
		doLoadStashes(stashOf(stashWithExtension(id), logFail));
		return node(id);
	}

	void init(String language) {
		if(language == null) return;
		if (openedStashes.contains(stashWithExtension(language))) {
			languages.add(language);
			return;
		}
		if (languages.contains(language) || isMetaLanguage(language)) return;
		if (language == null || language.isEmpty()) return;
		doInit(language);
	}

	private boolean isMetaLanguage(String language) {
		return Meta.class.getSimpleName().equals(language) || Proteo.class.getSimpleName().equals(language);
	}

	private void doInit(String language) {
		this.languages.add(language);
		Stash stash = stashOf(language);
		doLoadStashes(stash);
	}

	private Node loadFromLoaders(String id) {
		Node result = null;
		for (NodeLoader loader : loaders) {
			result = loader.loadNode(id);
			if (result != null) break;
		}
		return result;
	}

	private void register(Concept concept) {
		concepts.put(concept.id, concept);
	}

	void register(Node node) {
		nodes.put(node.id, node);
	}

	private void commit(Node node) {
		model.add(node);
		register(node);
		openedStashes.add(stashWithExtension(node.stash()));
	}

}
