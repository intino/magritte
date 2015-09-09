package tara.magritte;

import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.loaders.LevelLoader;
import tara.util.WordGenerator;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Model extends Layer {

    List<DeclarationLoader> loaders = new ArrayList<>();
    private Set<String> levels = new LinkedHashSet<>();
    private Set<Viewer> viewers = new LinkedHashSet<>();
    private List<Declaration> roots = new ArrayList<>();
    private Map<String, Definition> definitions = new HashMap<>();
    private Map<Object, Declaration> declarations = new HashMap<>();


    protected Model(Declaration _declaration) {
        super(_declaration);
        _declaration.morphWith(Model.class);
    }

    @SuppressWarnings("unused")
    public static Model load(String level) {
        Model model = new Model(new Declaration("_"));
        model.init(level);
        return model;
    }

    private static Stash stashOf(String source) {
        return StashDeserializer.stashFrom(Model.class.getResourceAsStream(source));
    }

    public Declaration loadDeclaration(String name) {
        Declaration declaration = loadFromLoaders(name);
        if (declaration == null) declaration = declarations.get(name);
        if (declaration == null) declaration = loadFromStash(name);
        return declaration;
    }

    public <T extends Viewer> T viewer(Class<T> viewerClass){
        for (Viewer viewer : viewers)
            if (viewerClass.isAssignableFrom(viewer.getClass()))
                return (T) viewer;
        return null;
    }

    public void add(Viewer viewer){
        viewers.add(viewer);
    }

    public void load(String... sources) {
        doLoad(sources);
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
        return newRoot(definition, WordGenerator.generate());
    }

    public Declaration newRoot(Definition definition, String id) {
        if (!definition.isMain()) {
            Logger.severe("Definition " + definition.name() + " is not main");
            return null;
        }
        Declaration declaration = definition.create(id, _declaration);
        register(declaration);
        registerRoot(declaration);
        return declaration;
    }

    public <T extends Layer> T newRoot(Class<T> layerClass) {
        return newRoot(layerClass, WordGenerator.generate());
    }

    public <T extends Layer> T newRoot(Class<T> layerClass, String id) {
        return newRoot(definitionOf(layerClass), id).as(layerClass);
    }

    public Declaration newRoot(String type) {
        return newRoot(type, WordGenerator.generate());
    }

    public Declaration newRoot(String type, String id) {
        return newRoot(definitionOf(type), id);
    }

    public List<Declaration> roots() {
        return unmodifiableList(roots);
    }

    Definition getDefinition(String name) {
        if (!definitions.containsKey(name)) register(new Definition(name));
        return definitions.get(name);
    }

    Declaration getDeclaration(String name) {
        if (name == null) name = WordGenerator.generate();
        if (!declarations.containsKey(name)) register(new Declaration(name));
        return declarations.get(name);
    }

    private Declaration loadFromStash(String id) {
        //TODO
        return null;
    }

    private void init(String level) {
        if (levels.contains(level)) return;
        if (level.contains("Proteo")) return;
        this.levels.add(level);
        this.doInit(level);
    }

    private Declaration loadFromLoaders(String id) {
        for (DeclarationLoader loader : loaders)
            if (loader.loadDeclaration(id) != null)
                return loader.loadDeclaration(id);
        return null;
    }

    private void doLoad(String[] sources) {
        StashReader stashReader = new StashReader(this);
        for (String source : sources)
            doLoad(stashReader, source);
    }

    private void doLoad(StashReader stashReader, String source) {
        Stash stash = stashOf(source);
        init(stash.language);
        stashReader.read(stash);
    }

    private void doInit(String level) {
        doLoad(LevelLoader.load(level));
    }

    private void register(Definition definition) {
        definitions.put(definition.name, definition);
    }

    private void register(Declaration declaration) {
        declarations.put(declaration.name, declaration);
    }
}
