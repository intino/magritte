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

	List<DeclarationLoader> loaders = new ArrayList<>();
	List<VariableEntry> variables = new ArrayList<>();
	RootDeclaration root = new RootDeclaration();
	private Set<String> languages = new LinkedHashSet<>();
	private Engine engine;
	private Domain domain;
	private List<Declaration> roots = new ArrayList<>();
	private Map<String, Definition> definitions = new HashMap<>();
	private Map<Object, Declaration> declarations = new HashMap<>();
	private long declarationIndex = 0;

	@SuppressWarnings("unused")
	public static Model load(String stash) {
		Model model = new Model();
		model.init(stash);
		return model;
	}

	public static Model init(Class<? extends Domain> domainClass, Class<? extends Engine> engineClass) {
		final Model model = load("Model");
		model.engine = create(engineClass, model);
		model.domain = create(domainClass, model);
		return model;
	}

	public static Model init(String store, Class<? extends Domain> domain, Class<? extends Engine> engine) {
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
		clone.roots = new ArrayList<>(this.roots);
		clone.definitions = new HashMap<>(this.definitions);
		clone.declarations = new HashMap<>(this.declarations);
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
		variables.forEach(vEntry -> vEntry.variables.forEach(vEntry.declaration::load));
		variables.clear();
		return this;
	}

	public Declaration loadDeclaration(String name) {
		Declaration declaration = loadFromLoaders(name);
		if (declaration == null) declaration = declarations.get(name);
		if (declaration == null) declaration = loadFromStash(name);
		return declaration;
	}

	public <T extends Layer> List<T> find(Class<T> aClass) {
		return root.findComponents(aClass);
	}

	public List<Declaration> components() {
		return root.components();
	}

	public <T extends Layer> List<T> components(Class<T> layerClass) {
		return root.components(layerClass);
	}

	public void registerRoot(Declaration root) {
		roots.add(root);
	}

	public void save(Declaration declaration) {
		//TODO
	}

	public List<Definition> definitions() {
		return unmodifiableList(new ArrayList<>(definitions.values()));
	}

	public Definition definitionOf(String type) {
		return definitions.get(type);
	}

	public Definition definitionOf(Class<? extends Layer> layerClass) {
		return definitions.get(LayerFactory.names(layerClass).get(0));
	}

	public List<Definition> mainDefinitionsOf(String type) {
		return mainDefinitionsOf(definitions.get(type));
	}

	public List<Definition> mainDefinitionsOf(Class<? extends Layer> layerClass) {
		return mainDefinitionsOf(definitionOf(layerClass));
	}

	public List<Definition> mainDefinitionsOf(Definition type) {
		return definitions().stream().filter(t -> t.types().contains(type) && t.isMain()).collect(toList());
	}

	public Declaration newRoot(Definition definition) {
		return newRoot(definition, newDeclarationId());
	}

	public Declaration newRoot(Definition definition, String id) {
		if (!definition.isMain()) {
			Logger.severe("Definition " + definition.name() + " is not main");
			return null;
		}
		Declaration declaration = definition.create(id, root);
		register(declaration);
		registerRoot(declaration);
		return declaration;
	}

	public <T extends Layer> T newRoot(Class<T> layerClass) {
		return newRoot(layerClass, newDeclarationId());
	}

	public <T extends Layer> T newRoot(Class<T> layerClass, String id) {
		return newRoot(definitionOf(layerClass), id).as(layerClass);
	}

	public Declaration newRoot(String type) {
		return newRoot(type, newDeclarationId());
	}

	public Declaration newRoot(String type, String id) {
		return newRoot(definitionOf(type), id);
	}

	public List<Declaration> roots() {
		return unmodifiableList(roots);
	}

	String newDeclarationId() {
		return "d" + declarationIndex++;
	}

	void addVariableIn(Declaration declaration, Map<String, Object> variables) {
		this.variables.add(new VariableEntry(declaration, variables));
	}

	Definition getDefinition(String name) {
		if (name == null) return null;
		if (!definitions.containsKey(name)) register(new Definition(name));
		return definitions.get(name);
	}

	Declaration getDeclaration(String name) {
		if (name == null) name = newDeclarationId();
		if (!declarations.containsKey(name)) register(new Declaration(name));
		return declarations.get(name);
	}

	private Declaration loadFromStash(String id) {
		//TODO
		return null;
	}

	private void init(String language) {
		if (languages.contains(language)) return;
		if (language.contains("Proteo")) return;
		this.languages.add(language);
		this.doInit(language);
	}

	private Declaration loadFromLoaders(String id) {
		for (DeclarationLoader loader : loaders)
			if (loader.loadDeclaration(id) != null)
				return loader.loadDeclaration(id);
		return null;
	}

	private void doLoad(StashReader stashReader, Stash stash) {
		init(stash.language);
		stashReader.read(stash);
	}

	private void doInit(String stash) {
		loadStashes(stash);
	}

	private void register(Definition definition) {
		definitions.put(definition.name, definition);
	}

	private void register(Declaration declaration) {
		declarations.put(declaration.name, declaration);
	}

	static class VariableEntry {
		Declaration declaration;
		Map<String, Object> variables;

		public VariableEntry(Declaration declaration, Map<String, Object> variables) {
			this.declaration = declaration;
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

	public class RootDeclaration extends Declaration {

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
