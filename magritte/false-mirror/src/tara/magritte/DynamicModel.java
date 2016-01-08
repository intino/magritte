package tara.magritte;

import tara.magritte.stores.ResourcesStore;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static tara.magritte.utils.StashHelper.stashName;

@SuppressWarnings("unused")
public class DynamicModel extends Model {

	Map<String, Set<Reference>> references = new HashMap<>();

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
        model.init(stash);
        return model;
    }

	private void freeSpace() {
		// TODO remove openedstashes
		if (Runtime.getRuntime().freeMemory() >= Runtime.getRuntime().totalMemory() * 0.2) return;
		freeReferences((int) Math.round(references.size() * 0.2));
		System.gc();
	}

	private void freeReferences(int amount) {
		List<String> keysToClear = selectInstancesToClear().subList(0, amount);
		clearInstances(keysToClear).forEach((instance) -> {
			save(instance);
			engine.removeInstance(instance);
			domain.removeInstance(instance);
			instances.remove(instance.name);
			openedStashes.remove(instance.stash());
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
		return references.entrySet().stream().collect(toMap(Map.Entry::getKey, lastTimeUsed()))
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

	public void register(Reference reference){
		if(!references.containsKey(reference.qn))
			references.put(reference.qn, new HashSet<>());
		if(!references.get(reference.qn).contains(reference))
			references.get(reference.qn).add(reference);
	}

	@Override
	protected void register(Instance instance) {
		if(languages.contains(stashName(instance.name)))
			super.register(instance);
		else {
			register(referenceOf(instance));
			updateReferences(instance);
		}
	}

	private Reference referenceOf(Instance instance) {
		Reference reference = new Reference();
		reference.qn = instance.name;
		reference.model = this;
		reference.instance = instance;
		return reference;
	}

	@Override
	protected void unregister(Instance instance) {
		super.unregister(instance);
		if(references.containsKey(instance.name)) references.get(instance.name).forEach(r -> r.instance = null);
		references.remove(instance.name);
	}

	private void updateReferences(Instance instance) {
		if(references.containsKey(instance.name)) references.get(instance.name).forEach(r -> r.instance = instance);
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
		return loadInstance(reference.qn);
	}
}
