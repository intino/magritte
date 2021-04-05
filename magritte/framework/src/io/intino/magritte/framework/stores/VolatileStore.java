package io.intino.magritte.framework.stores;

import io.intino.magritte.io.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VolatileStore extends ResourcesStore {

	protected final Map<String, byte[]> stashes = new ConcurrentHashMap<>();

	@Override
	public Stash stashFrom(String path) {
		return stashes.containsKey(path) ?
				ThreadSafeStashDeserializer.stashFrom(stashes.get(path)) :
				super.stashFrom(path);
	}

	@Override
	public void writeStash(Stash stash, String path) {
		stashes.put(path, ThreadSafeStashSerializer.serialize(stash));
	}

	@Override
	public boolean allowWriting() {
		return true;
	}

}
