package io.intino.tara.magritte;

import io.intino.tara.io.Stash;
import io.intino.tara.magritte.utils.I18n;
import io.intino.tara.magritte.utils.PathHelper;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

import static io.intino.tara.magritte.utils.PathHelper.pathWithExtension;
import static java.util.Arrays.stream;
import static java.util.Collections.unmodifiableList;
import static java.util.logging.Logger.getGlobal;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class GraphHandler {

    final Store store;
    final Model model = new Model();
    private final Map<Node, Map<String, List<?>>> variables = new HashMap<>();
    protected Map<Class<? extends GraphWrapper>, GraphWrapper> wrappers = new HashMap<>();
    LayerFactory layerFactory = new LayerFactory();
    Set<String> openedStashes = new HashSet<>();
    Set<String> languages = new LinkedHashSet<>();
    Map<String, Concept> concepts = new HashMap<>();
    Map<String, Node> nodes = new HashMap<>();
    List<NodeLoader> loaders = new ArrayList<>();
    private I18n i18n = new I18n();

    GraphHandler(Store store) {
        this.store = store;
    }

    static <T extends GraphWrapper> T create(Class<T> aClass, Graph graph) {
        try {
            T instance = aClass.getConstructor(Graph.class).newInstance(graph);
            instance.update();
            return instance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unused")
    public List<String> languages() {
        return unmodifiableList(new ArrayList<>(languages));
    }

    void doLoadPath(String... paths) {
        doLoadStashes(stream(paths).map(PathHelper::pathWithExtension).toArray(String[]::new));
    }

    void doLoadStashes(String... paths) {
        doLoadStashes(stream(paths).map(this::stashOf).toArray(Stash[]::new));
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
            map.forEach(node::load);
            variables.remove(node);
        });
    }

    protected Stash[] processUses(Stash[] stashes) {
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

    public Node loadNode(String id) {
        Node node = loadFromLoaders(id);
        if (node == null) node = nodes.get(id);
        if (node == null) node = loadFromStash(id);
        if (node == null) getGlobal().warning("A reference to a node named as " + id + " has not been found");
        return node;
    }

    public I18n i18n() {
        return i18n;
    }

    @SuppressWarnings("unused")
    public URL loadResource(String path) {
        URL url = store.resourceFrom(path);
        if (url == null)
            getGlobal().severe("Resource at " + path + " not found");
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
    void save(Node node) {
        save(node.path());
    }

    private void save(String path) {
        if (!store.allowWriting()) return;
        synchronized (this) {
            StashWriter.write(this, pathWithExtension(path), nodesIn(path));
        }
    }

    private List<Node> nodesIn(String path) {
        return model.graph.rootList().stream().filter(i -> i.path().equals(path)).collect(toList());
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

    Stash stashOf(String source) {
        source = pathWithExtension(source);
        if (openedStashes.contains(source)) return null;
        openedStashes.add(source);
        Stash stash = store.stashFrom(source);
        if (stash == null) getGlobal().severe("Stash " + source + " does not exist or cannot be opened");
        return stash;
    }

    String createNodeName() {
        return UUID.randomUUID().toString();
    }

    void addVariableIn(Node node, Map<String, List<?>> variables) {
        this.variables.put(node, variables);
    }

    Concept $concept(String name) {
        if (name == null) return null;
        if (!concepts.containsKey(name)) register(new Concept(name));
        return concepts.get(name);
    }

    Node $node(String name) {
        if (name == null) name = createNodeName();
//		TODO check if (nodes.containsKey(name)) return nodes.get(name);
        Node node = new Node(name);
        register(node);
        return node;
    }

    Node node(String name) {
        return nodes.get(name);
    }

    private Node loadFromStash(String id) {
        doLoadStashes(stashOf(pathWithExtension(id)));
        return node(id);
    }

    void init(String language) {
        if (openedStashes.contains(pathWithExtension(language))){
            languages.add(language);
            return;
        }
        if (languages.contains(language) || "Verso".equals(language) || "Proteo".equals(language)) return;
        if (language == null || language.isEmpty()) return;
        doInit(language);
    }

    private void doInit(String language) {
        this.languages.add(language);
        Stash stash = stashOf(language);
        if (stash == null) getGlobal().severe("Language or model corrupt or not found: " + language);
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

    public <T extends GraphWrapper> T wrapper(Class<T> aClass) {
        return (T) wrappers.get(aClass);
    }

    public void remove(Node node) {
        node.owner().remove(node);
        nodes.remove(node.id);
        save(node.path());
    }

    public void remove(String path) {
        nodesIn(path).forEach(node -> {
            node.owner().remove(node);
            nodes.remove(node.id);
        });
        save(path);
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

}