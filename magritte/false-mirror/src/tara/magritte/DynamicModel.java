package tara.magritte;

import tara.io.Stash;
import tara.io.refactor.Refactors;
import tara.magritte.stores.ResourcesStore;
import tara.magritte.utils.RefactorHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static tara.io.refactor.RefactorsDeserializer.refactorFrom;
import static tara.magritte.utils.StashHelper.stashWithExtension;

@SuppressWarnings("unused")
public class DynamicModel extends Model {

	Map<String, Set<Reference>> references = new HashMap<>();
	Set<String> stashesToKeep = new HashSet<>();
	RefactorHandler refactorHandler;

	protected DynamicModel(Store store) {
		super(store);
	}

	public static DynamicModel load() {
		return load("Model", new ResourcesStore());
	}

	public static DynamicModel load(Store store) {
		return load("Model", store);
	}

	public static DynamicModel load(String stash) {
		return load(stash, new ResourcesStore());
	}

	public static DynamicModel load(String stash, Store store) {
		DynamicModel model = new DynamicModel(store);
		model.refactorHandler = prepareRefactorHandler(store);
		model.stashesToKeep.add(stashWithExtension(stash));
		model.init(stash);
		return model;
	}

	@Override
	public Model loadStashes(String... paths) {
		Collections.addAll(stashesToKeep, paths);
		return super.loadStashes(paths);
	}

	@Override
	public Model loadStashes(Stash... stashes) {
		asList(stashes).stream().filter(s -> s.instances.size() > 0)
				.map(s -> stashWithExtension(s.instances.get(0).name))
				.forEach(s -> stashesToKeep.add(s));
		return super.loadStashes(stashes);
	}

	private void freeSpace() {
		if (Runtime.getRuntime().freeMemory() >= Runtime.getRuntime().totalMemory() * 0.2) return;
		freeReferences((int) Math.round(references.size() * 0.2));
		System.gc();
	}

	private void freeReferences(int amount) {
		List<String> keysToClear = selectInstancesToClear();
		keysToClear = amount > keysToClear.size() ? keysToClear : keysToClear.subList(0, amount);
		clearInstances(keysToClear).forEach((instance) -> {
			save(instance);
			if (platform != null) platform.removeInstance(instance);
			application.removeInstance(instance);
			instances.remove(instance.id);
			openedStashes.remove(stashWithExtension(instance.namespace()));
			graph.remove(instance);
		});
		keysToClear.forEach(k -> references.remove(k));
	}

	private Set<Node> clearInstances(List<String> keysToClear) {
		return keysToClear.stream().map(this::freeReferences).flatMap(Collection::stream).collect(toSet());
	}

	private Set<Node> freeReferences(String key) {
		return references.get(key).stream().map(Reference::free).collect(toSet());
	}

	private List<String> selectInstancesToClear() {
		return references
				.entrySet().stream().filter(e -> !stashesToKeep.contains(stashWithExtension(e.getKey())))
				.collect(toMap(Map.Entry::getKey, lastTimeUsed()))
				.entrySet().stream().sorted(byTime()).map(Map.Entry::getKey).collect(toList());
	}

	@Override
	public Node loadInstance(String name) {
		freeSpace();
		return super.loadInstance(name);
	}

	@Override
	Node newNode(String name) {
		if (name == null) name = newNodeId();
		if (instances.containsKey(name)) return instances.get(name);
		if (isLoaded(name)) return referenceOf(name);
		freeSpace();
		Node node = new Node(name);
		register(node);
		return node;
	}

	@Override
	Node instance(String name) {
		return isLoaded(name) ? referenceOf(name) : super.instance(name);
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

	public Node loadInstance(Reference reference) {
		register(reference);
		return loadInstance(reference.name);
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
		refactor(stash.instances, stash.applicationRefactorId, stash.platformRefactorId);
		save(stash);
		return stash;
	}

	private void save(Stash stash) {
		if(stash.platformRefactorId != refactorHandler.lastApplicationRefactor() || stash.applicationRefactorId == refactorHandler.lastPlatformRefactor())
			if(!stash.instances.isEmpty()) {
				stash.applicationRefactorId = refactorHandler.lastPlatformRefactor();
				stash.platformRefactorId = refactorHandler.lastApplicationRefactor();
				store.writeStash(stash, stashWithExtension(stash.instances.get(0).name));
			}
	}

	private List<tara.io.Instance> refactor(List<tara.io.Instance> instances, int platformRefactorId, int applicationRefactorId) {
		instances.forEach(i -> i.facets.forEach(f -> {
			f.name = refactor(f.name, platformRefactorId, applicationRefactorId);
			f.instances = refactor(f.instances, platformRefactorId, applicationRefactorId);
		}));
		return instances;
	}

	private String refactor(String name, int platformRefactorId, int applicationRefactorId) {
		String last = refactorHandler.lastApplicationRefactor(name, applicationRefactorId);
		return !last.equals(name) ? last : refactorHandler.lastPlatformRefactor(name, platformRefactorId);
	}
}
