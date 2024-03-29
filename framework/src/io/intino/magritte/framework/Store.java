package io.intino.magritte.framework;

import io.intino.magritte.io.model.Stash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface Store {

	Stash stashFrom(String path);

	void writeStash(Stash stash, String path);

	URL resourceFrom(String path);

	URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Node node);

	String relativePathOf(URL url);

	default boolean allowWriting() {
		return true;
	}

	default Stash composeStash(String path, Stash stash) {
		Stash result = stashFrom(path);
		if (result == null) return stash;
		result.nodes.clear();
		result.nodes.addAll(stash.nodes);
		return result;
	}

	default byte[] bytesOf(InputStream input) throws IOException {
		byte[] buffer = new byte[8192];
		int bytesRead;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		while ((bytesRead = input.read(buffer)) != -1)
			output.write(buffer, 0, bytesRead);
		return output.toByteArray();
	}

}
