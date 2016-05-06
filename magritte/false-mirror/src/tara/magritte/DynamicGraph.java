package tara.magritte;

import tara.io.Stash;
import tara.io.refactor.Refactors;
import tara.magritte.stores.ResourcesStore;
import tara.magritte.utils.RefactorHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static tara.io.refactor.RefactorsDeserializer.refactorFrom;
import static tara.magritte.utils.StashHelper.stashWithExtension;

@SuppressWarnings("unused")
public class DynamicGraph extends Graph {

	Map<String, Set<Reference>> references = new HashMap<>();
	Set<String> stashesToKeep = new HashSet<>();
	RefactorHandler refactorHandler;

	protected DynamicGraph(Store store) {
		super(store);
	}

	public static ModelLoad load() {
		return load("Model", new ResourcesStore());
	}

	public static ModelLoad load(Store store) {
		return load("Model", store);
	}

	public static ModelLoad load(String stash) {
		return load(stash, new ResourcesStore());
	}

	public static ModelLoad load(String stash, Store store) {
		DynamicGraph model = new DynamicGraph(store);
		model.refactorHandler = prepareRefactorHandler(store);
		model.stashesToKeep.add(stashWithExtension(stash));
		model.init(stash);
		return model.modelLoad();
	}

	@Override
	public Graph loadStashes(String... paths) {
		Collections.addAll(stashesToKeep, paths);
		return super.loadStashes(paths);
	}

	@Override
	public Graph loadStashes(Stash... stashes) {
		asList(stashes).stream().filter(s -> s.nodes.size() > 0)
			.map(s -> stashWithExtension(s.nodes.get(0).name))
				.forEach(s -> stashesToKeep.add(s));
		return super.loadStashes(stashes);
	}

	private void freeSpace() {
		if (Runtime.getRuntime().freeMemory() >= Runtime.getRuntime().totalMemory() * 0.2) return;
		freeReferences((int) Math.round(references.size() * 0.2));
		System.gc();
	}

	private void freeReferences(int amount) {
		List<String> keysToClear = selectNodesToClear();
		keysToClear = amount > keysToClear.size() ? keysToClear : keysToClear.subList(0, amount);
		clearNodes(keysToClear).forEach((node) -> {
			save(node);
			if (platform != null) platform.removeNode(node);
			application.removeNode(node);
			nodes.remove(node.id);
			openedStashes.remove(stashWithExtension(node.namespace()));
			model.remove(node);
		});
		keysToClear.forEach(k -> references.remove(k));
	}

	private Set<Node> clearNodes(List<String> keysToClear) {
		return keysToClear.stream().map(this::freeReferences).flatMap(Collection::stream).collect(toSet());
	}

	private Set<Node> freeReferences(String key) {
		return references.get(key).stream().map(Reference::free).collect(toSet());
	}

	private List<String> selectNodesToClear() {
		return references
				.entrySet().stream().filter(e -> !stashesToKeep.contains(stashWithExtension(e.getKey())))
				.collect(toMap(Map.Entry::getKey, lastTimeUsed()))
				.entrySet().stream().sorted(byTime()).map(Map.Entry::getKey).collect(toList());
	}

	@Override
	public Node loadNode(String id) {
		freeSpace();
		return super.loadNode(id);
	}

	@Override
	Node $node(String name) {
		if (name == null) name = createNodeName();
		if (nodes.containsKey(name)) return nodes.get(name);
		if (isLoaded(name)) return referenceOf(name);
		freeSpace();
		Node node = new Node(name);
		register(node);
		return node;
	}

	@Override
	Node node(String name) {
		return isLoaded(name) ? referenceOf(name) : super.node(name);
	}

	private Node referenceOf(String name) {
		return references.get(name).iterator().next().node;
	}

	public void register(Reference reference) {
		if (!references.containsKey(reference.name))
			references.put(reference.name, new HashSet<>());
		if (!references.get(reference.name).contains(reference))
			references.get(reference.name).add(reference);
	}

	@Override
	protected void register(Node node) {
		super.register(node);
		register(referenceOf(node));
		updateReferences(node);
	}

	private Reference referenceOf(Node node) {
		Reference reference = new Reference();
		reference.name = node.id;
		reference.model = this;
		reference.node = node;
		return reference;
	}

	@Override
	protected void unregister(Node node) {
		super.unregister(node);
		if (references.containsKey(node.id)) references.get(node.id).forEach(r -> r.node = null);
		references.remove(node.id);
	}

	private void updateReferences(Node node) {
		if (references.containsKey(node.id)) references.get(node.id).forEach(r -> r.node = node);
	}

	private boolean isLoaded(String name) {
		return references.containsKey(name) ?
				references.get(name).stream().map(r -> r.node != null).reduce((b1, b2) -> b1 && b2).get() :
				false;
	}

	private Comparator<Map.Entry<String, LocalDateTime>> byTime() {
		return (e1, e2) -> e1.getValue().compareTo(e2.getValue());
	}

	private java.util.function.Function<Map.Entry<String, Set<Reference>>, LocalDateTime> lastTimeUsed() {
		return e -> e.getValue().stream().reduce((r1, r2) -> r1.time.isAfter(r2.time) ? r1 : r2).get().time;
	}

	public Node loadNode(Reference reference) {
		register(reference);
		return loadNode(reference.name);
	}

	@Override
	public void clear() {
		super.clear();
		references.clear();
		stashesToKeep.clear();
	}

	private static RefactorHandler prepareRefactorHandler(Store store) {
		Refactors platform = new Refactors();
		Refactors application = new Refactors();
		try {
			platform = store.resourceFrom("platform") != null ? refactorFrom(store.resourceFrom("platform").openStream()) : platform;
			application = store.resourceFrom("application") != null ? refactorFrom(store.resourceFrom("application").openStream()) : application;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new RefactorHandler(platform, application);
	}

	@Override
	protected Stash stashOf(String source) {
		Stash stash = super.stashOf(source);
		refactor(stash.nodes, stash.applicationRefactorId, stash.platformRefactorId);
		save(stash);
		return stash;
	}

	private void save(Stash stash) {
		if(stash.platformRefactorId != refactorHandler.lastApplicationRefactor() || stash.applicationRefactorId == refactorHandler.lastPlatformRefactor())
			if (!stash.nodes.isEmpty()) {
				stash.applicationRefactorId = refactorHandler.lastPlatformRefactor();
				stash.platformRefactorId = refactorHandler.lastApplicationRefactor();
				store.writeStash(stash, stashWithExtension(stash.nodes.get(0).name));
			}
	}

	private void refactor(List<tara.io.Node> nodes, int platformRefactorId, int applicationRefactorId) {
		nodes.forEach(n -> {
			n.facets = n.facets.stream().map(f -> refactor(f, platformRefactorId, applicationRefactorId)).collect(toList());
			refactor(n.nodes, platformRefactorId, applicationRefactorId);
		});
	}

	private String refactor(String name, int platformRefactorId, int applicationRefactorId) {
		String last = refactorHandler.lastApplicationRefactor(name, applicationRefactorId);
		return !last.equals(name) ? last : refactorHandler.lastPlatformRefactor(name, platformRefactorId);
	}
}