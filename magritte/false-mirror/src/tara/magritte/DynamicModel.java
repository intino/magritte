package tara.magritte;

import tara.io.Stash;
import tara.io.refactor.Refactors;
import tara.magritte.stores.ResourcesStore;
import tara.magritte.utils.RefactorHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Arrays.*;
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
			engine.removeInstance(instance);
			domain.removeInstance(instance);
			instances.remove(instance.name);
			openedStashes.remove(stashWithExtension(instance.stash()));
			soil.removeInstance(instance);
		});
		keysToClear.forEach(k -> references.remove(k));
	}

	private Set<Instance> clearInstances(List<String> keysToClear) {
		return keysToClear.stream().map(this::freeReferences).flatMap(Collection::stream).collect(toSet());
	}

	private Set<Instance> freeReferences(String key) {
		return references.get(key).stream().map(Reference::free).collect(toSet());
	}

	private List<String> selectInstancesToClear() {
		return references
				.entrySet().stream().filter(e -> !stashesToKeep.contains(stashWithExtension(e.getKey())))
				.collect(toMap(Map.Entry::getKey, lastTimeUsed()))
				.entrySet().stream().sorted(byTime()).map(Map.Entry::getKey).collect(toList());
	}

	@Override
	public Instance loadInstance(String name) {
		freeSpace();
		return super.loadInstance(name);
	}

	@Override
	Instance newInstance(String name) {
		if (name == null) name = newInstanceId();
		if (instances.containsKey(name)) return instances.get(name);
		if (isLoaded(name)) return referenceOf(name);
		freeSpace();
		Instance instance = new Instance(name);
		register(instance);
		return instance;
	}

	@Override
	Instance instance(String name) {
		return isLoaded(name) ? referenceOf(name) : super.instance(name);
	}

	private Instance referenceOf(String name) {
		return references.get(name).iterator().next().instance;
	}

	public void register(Reference reference) {
		if (!references.containsKey(reference.name))
			references.put(reference.name, new HashSet<>());
		if (!references.get(reference.name).contains(reference))
			references.get(reference.name).add(reference);
	}

	@Override
	protected void register(Instance instance) {
		super.register(instance);
		register(referenceOf(instance));
		updateReferences(instance);
	}

	private Reference referenceOf(Instance instance) {
		Reference reference = new Reference();
		reference.name = instance.name;
		reference.model = this;
		reference.instance = instance;
		return reference;
	}

	@Override
	protected void unregister(Instance instance) {
		super.unregister(instance);
		if (references.containsKey(instance.name)) references.get(instance.name).forEach(r -> r.instance = null);
		references.remove(instance.name);
	}

	private void updateReferences(Instance instance) {
		if (references.containsKey(instance.name)) references.get(instance.name).forEach(r -> r.instance = instance);
	}

	private boolean isLoaded(String name) {
		return references.containsKey(name) ?
				references.get(name).stream().map(r -> r.instance != null).reduce((b1, b2) -> b1 && b2).get() :
				false;
	}

	private Comparator<Map.Entry<String, LocalDateTime>> byTime() {
		return (e1, e2) -> e1.getValue().compareTo(e2.getValue());
	}

	private java.util.function.Function<Map.Entry<String, Set<Reference>>, LocalDateTime> lastTimeUsed() {
		return e -> e.getValue().stream().reduce((r1, r2) -> r1.time.isAfter(r2.time) ? r1 : r2).get().time;
	}

	public Instance loadInstance(Reference reference) {
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
		Refactors engine = new Refactors();
		Refactors domain = new Refactors();
		try {
			engine = store.resourceFrom("engine") != null ? refactorFrom(store.resourceFrom("engine").openStream()) : engine;
			domain = store.resourceFrom("domain") != null ? refactorFrom(store.resourceFrom("domain").openStream()) : domain;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new RefactorHandler(engine, domain);
	}

	@Override
	protected Stash stashOf(String source) {
		Stash stash = super.stashOf(source);
		refactor(stash.instances, stash.engineRefactorId, stash.domainRefactorId);
		save(stash);
		return stash;
	}

	private void save(Stash stash) {
		if(stash.domainRefactorId != refactorHandler.lastDomainRefactor() || stash.engineRefactorId == refactorHandler.lastEngineRefactor())
			if(!stash.instances.isEmpty()) {
				stash.engineRefactorId = refactorHandler.lastEngineRefactor();
				stash.domainRefactorId = refactorHandler.lastDomainRefactor();
				store.writeStash(stash, stashWithExtension(stash.instances.get(0).name));
			}
	}

	private List<tara.io.Instance> refactor(List<tara.io.Instance> instances, int engineRefactorId, int domainRefactorId) {
		instances.forEach(i -> i.facets.forEach(f -> {
			f.name = refactor(f.name, engineRefactorId, domainRefactorId);
			f.instances = refactor(f.instances, engineRefactorId, domainRefactorId);
		}));
		return instances;
	}

	private String refactor(String name, int engineRefactorId, int domainRefactorId) {
		String last = refactorHandler.lastDomainRefactor(name, domainRefactorId);
		return !last.equals(name) ? last : refactorHandler.lastEngineRefactor(name, engineRefactorId);
	}
}
