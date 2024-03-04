package io.intino.magritte.framework.stores;

import io.intino.magritte.io.StashDeserializer;
import io.intino.magritte.io.model.Stash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.intino.magritte.io.StashSerializer.serialize;

public class VolatileStore extends ResourcesStore {
	protected final Map<String, byte[]> stashes = new ConcurrentHashMap<>();

	@Override
	public Stash stashFrom(String path) {
		return stashes.containsKey(path) ?
				StashDeserializer.stashFrom(stashes.get(path)) :
				super.stashFrom(path);
	}

	@Override
	public void writeStash(Stash stash, String path) {
		stashes.put(path, serialize(stash));
	}

	@Override
	public boolean allowWriting() {
		return true;
	}

}
