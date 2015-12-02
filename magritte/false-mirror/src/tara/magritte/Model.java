package tara.magritte;

import tara.io.Stash;
import tara.io.StashDeserializer;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Model {

	List<InstanceLoader> loaders = new ArrayList<>();
	List<VariableEntry> variables = new ArrayList<>();
	Soil soil = new Soil();
	private Set<String> languages = new LinkedHashSet<>();
	private Engine engine;
	private Domain domain;
	private Map<String, Concept> concepts = new HashMap<>();
	private Map<Object, Instance> instances = new HashMap<>();
	private long instanceIndex = 0;

	public Model() {
		soil.addLayer(SoilLayer.class);
		soil.typeNames.add("Soil");
	}

	@SuppressWarnings("unused")
	public static Model load() {
		Model model = new Model();
		model.init("Model");
		return model;
	}

	public Model init(Class<? extends Domain> domainClass, Class<? extends Engine> engineClass) {
		engine = create(engineClass, this);
		domain = create(domainClass, this);
		return this;
	}

	public Model init(String store, Class<? extends Domain> domain, Class<? extends Engine> engine) {
		//TODO store
		return init(domain, engine);
	}

	private static <T> T create(Class<T> class_, Model model) {
		try {
			return class_.getConstructor(Model.class).newInstance(model);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Model clone() {
		Model clone = new Model();
		clone.loaders = new ArrayList<>(this.loaders);
		clone.languages = new HashSet<>(this.languages);
		clone.engine = this.engine;
		clone.domain = this.domain;
		soil.components().forEach(c -> clone.soil.add(c));
		clone.concepts = new HashMap<>(this.concepts);
		clone.instances = new HashMap<>(this.instances);
		return clone;
	}

	private static Stash stashOf(String source) {
		return StashDeserializer.stashFrom(Model.class.getResourceAsStream(source));
	}

	private static Stash stashOf(Path source) {
		return StashDeserializer.stashFrom(source.toFile());
	}

	public List<String> levels() {
		return unmodifiableList(new ArrayList<>(languages));
	}

	public Model loadStashes(String... sources) {
		return loadStashes(asList(sources).stream().map(Model::stashOf).toArray(Stash[]::new));
	}

	public Model loadStashes(Path... sources) {
		return loadStashes(asList(sources).stream().map(Model::stashOf).toArray(Stash[]::new));
	}

	public Model loadStashes(Stash... stashes) {
		StashReader stashReader = new StashReader(this);
		for (Stash stash : stashes)
			doLoad(stashReader, stash);
		variables.forEach(vEntry -> vEntry.variables.forEach(vEntry.instance::load));
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
			Logger.severe("Concept " + concept.name() + " is not main");
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

	String newInstanceId() {
		return "i" + instanceIndex++;
	}

	void addVariableIn(Instance instance, Map<String, Object> variables) {
		this.variables.add(new VariableEntry(instance, variables));
	}

	Concept getConcept(String name) {
		if (name == null) return null;
		if (!concepts.containsKey(name)) register(new Concept(name));
		return concepts.get(name);
	}

	Instance getInstance(String name) {
		if (name == null) name = newInstanceId();
		if (!instances.containsKey(name)) register(new Instance(name));
		return instances.get(name);
	}

	private Instance loadFromStash(String id) {
		//TODO
		return null;
	}

	private void init(String language) {
		if (languages.contains(language)) return;
		if (language.contains("Proteo")) return;
		this.languages.add(language);
		this.doInit("/" + language + ".stash");
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

	private void doInit(String stash) {
		loadStashes(stash);
	}

	private void register(Concept concept) {
		concepts.put(concept.name, concept);
	}

	private void register(Instance instance) {
		instances.put(instance.name, instance);
	}

	static class VariableEntry {
		Instance instance;
		Map<String, Object> variables;

		public VariableEntry(Instance instance, Map<String, Object> variables) {
			this.instance = instance;
			this.variables = variables;
		}
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
