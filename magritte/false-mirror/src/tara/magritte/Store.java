package tara.magritte;

import tara.io.Stash;

import java.net.URL;

public interface Store {

	Stash stashFrom(String path);

	void writeStash(String path, Stash stash);

	URL resourceFrom(String path);

	String relativePathOf(URL url);

	default Stash composeStash(String path, Stash stash) {
		Stash result = stashFrom(path);
		result.instances.clear();
		result.instances.addAll(stash.instances);
		return result;
	}

}
