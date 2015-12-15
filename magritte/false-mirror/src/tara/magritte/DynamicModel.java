package tara.magritte;

import tara.magritte.stores.ResourcesStore;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings("unused")
public class DynamicModel extends Model {

	Map<String, Set<Reference>> references = new HashMap<>();

    protected DynamicModel(Store store) {
        super(store);
        soil.model = this;
        soil.addLayer(SoilLayer.class);
        soil.typeNames.add("Soil");
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
		if(Runtime.getRuntime().freeMemory() < Runtime.getRuntime().totalMemory() / 90)
			freeReferences(references.size() / 10);
	}

	private void freeReferences(int amount) {
		List<String> keysToClear = references.entrySet().stream().collect(toMap(Map.Entry::getKey, lastTimeUsed()))
				.entrySet().stream().sorted(byTime())
				.map(Map.Entry::getKey).collect(toList());
		keysToClear.forEach(k -> references.get(k).forEach(Reference::free));
	}

	@Override
	public Instance loadInstance(String name) {
		freeSpace();
		return super.loadInstance(name);
	}

	public void register(Reference reference){
		if(!references.containsKey(reference.qn))
			references.put(reference.qn, new HashSet<>());
		references.get(reference.qn).add(reference);
	}

	@Override
	protected void register(Instance instance) {
		if(stashName(instance.name).equals("Model"))
			super.register(instance);
	}

	private Comparator<Map.Entry<String, LocalDateTime>> byTime() {
		return (e1, e2) -> e1.getValue().compareTo(e2.getValue());
	}

	private java.util.function.Function<Map.Entry<String, Set<Reference>>, LocalDateTime> lastTimeUsed() {
		return e -> e.getValue().stream().reduce((r1, r2) -> r1.time.isAfter(r2.time) ? r1 : r2).get().time;
	}
}
