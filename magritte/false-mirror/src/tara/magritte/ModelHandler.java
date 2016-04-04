package tara.magritte;

import tara.io.Stash;
import tara.magritte.utils.MessageProvider;

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
	final Soil soil = new Soil();
	private final List<VariableEntry> variables = new ArrayList<>();
	protected ModelWrapper platform;
	protected ModelWrapper application;
	LayerFactory layerFactory = new LayerFactory();
	Set<String> openedStashes = new HashSet<>();
	Set<String> languages = new LinkedHashSet<>();
	Map<String, Concept> concepts = new HashMap<>();
	Map<String, Instance> instances = new HashMap<>();
	List<InstanceLoader> loaders = new ArrayList<>();
	MessageProvider messageProvider = new MessageProvider();

	public ModelHandler(Store store) {
		this.store = store;
	}

	protected static <T> T create(Class<T> aClass, Model model) {
		try {
			return aClass.getConstructor(Model.class).newInstance(model);
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
		loadVariables();
	}

	private void loadVariables() {
		while(!variables.isEmpty()) process(getAllEntries(variables.get(0).layer.instance()));
	}

	private List<VariableEntry> getAllEntries(Instance instance) {
		List<VariableEntry> result = new ArrayList<>();
		for (VariableEntry entry : variables) {
			if(!entry.layer.instance().equals(instance)) break;
			result.add(entry);
		}
		return result;
	}

	private void process(List<VariableEntry> allEntries) {
		Exception catchedException = null;
		int numberOfVariablesToLoad;
		do{
			numberOfVariablesToLoad = allEntries.stream().mapToInt(e -> e.variables.size()).sum();
			for (VariableEntry layerEntry : allEntries) {
				for (Map.Entry<String, List<?>> varEntry : layerEntry.variables.entrySet()) {
					try {
						layerEntry.layer._load(varEntry.getKey(), varEntry.getValue());
					}
					catch (Exception e){
						catchedException = e;
					}
				}
			}
		}while(allEntries.stream().mapToInt(e -> e.variables.size()).sum() < numberOfVariablesToLoad);
		if(catchedException != null) LOG.severe(catchedException.getCause().getMessage());
		variables.removeAll(allEntries);
	}

	public Instance loadInstance(String name) {
		Instance instance = loadFromLoaders(name);
		if (instance == null) instance = instances.get(name);
		if (instance == null) instance = loadFromStash(name);
		if (instance == null) LOG.warning("A reference to an instance named as " + name + " has not been found");
		return instance;
	}

	public MessageProvider messageProvider() {
		return messageProvider;
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

	protected abstract void registerRoot(Instance root);

	public Store store() {
		return store;
	}

	@SuppressWarnings("UnusedParameters")
	public void save(Instance instance) {
		if (!store.allowWriting()) return;
		save(instance.stash());
	}

	private void save(String stashName) {
		save(stashName, soil.model.roots().stream().filter(i -> i.stash().equals(stashName)).collect(toList()));
	}

	private void save(String stashName, List<Instance> instances) {
		StashWriter.write(this, stashWithExtension(stashName), instances);
	}

	@SuppressWarnings("UnusedParameters")
	public URL save(URL url, String path, URL oldUrl, Instance instance) {
		try {
			return store.writeResource(url.openConnection().getInputStream(), path, oldUrl, instance);
		} catch (IOException e) {
			LOG.severe("Url at " + url.toString() + " could not be accessed");
			return null;
		}
	}

	@SuppressWarnings("UnusedParameters")
	public URL save(InputStream inputStream, String path, URL oldUrl, Instance instance) {
		return store.writeResource(inputStream, path, oldUrl, instance);
	}

	protected Stash stashOf(String source) {
		openedStashes.add(source);
		Stash stash = store.stashFrom(source);
		if (stash == null) LOG.severe("Stash " + source + " does not exist or cannot be opened");
		return stash;
	}

	String newInstanceId() {
		return UUID.randomUUID().toString();
	}

	void addVariableIn(Layer layer, Map<String, List<?>> variables) {
		this.variables.add(new VariableEntry(layer, variables));
	}

	Concept concept(String name) {
		if (name == null) return null;
		if (!concepts.containsKey(name)) register(new Concept(name));
		return concepts.get(name);
	}

	Instance newInstance(String name) {
		if (name == null) name = newInstanceId();
		if (instances.containsKey(name)) return instances.get(name);
		Instance instance = new Instance(name);
		register(instance);
		return instance;
	}

	Instance instance(String name) {
		return instances.get(name);
	}

	private Instance loadFromStash(String id) {
		if (!openedStashes.contains(stashWithExtension(id)))
			doLoadStashes(stashOf(stashWithExtension(id)));
		return instance(id);
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

	private Instance loadFromLoaders(String id) {
		return loaders.stream().map(l -> l.loadInstance(id)).filter(i -> i != null).findFirst().orElse(null);
	}

	private void doLoad(StashReader stashReader, Stash stash) {
		init(stash.language);
		stashReader.read(stash);
	}

	protected void register(Concept concept) {
		concepts.put(concept.id, concept);
	}

	protected void register(Instance instance) {
		if (!instance.name().equals("null"))
			instances.put(instance.id, instance);
	}

	public <T extends Platform> T platform() {
		return (T) platform;
	}

	public <T extends Application> T application() {
		return (T) application;
	}

	public void remove(Instance instance) {
		instance.owner().removeInstance(instance);
		unregister(instance);
		if (store.allowWriting()) save(instance.stash());
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
		soil.components().forEach(soil::removeInstance);
		openedStashes.clear();
		languages.clear();
		concepts.clear();
		instances.clear();
		loaders.clear();
		if (platform != null) platform.update();
		application.update();
		layerFactory.clear();
	}

	protected void unregister(Instance instance) {
		instances.remove(instance.id);
		if (platform != null) platform.removeInstance(instance);
		application.removeInstance(instance);
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