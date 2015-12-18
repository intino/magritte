package tara.magritte.stores;

import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.Instance;
import tara.magritte.Store;

import java.io.InputStream;
import java.net.URL;
import java.util.logging.Logger;

public class ResourcesStore implements Store {

	private static final Logger LOG = Logger.getLogger(ResourcesStore.class.getName());

	@Override
	public Stash stashFrom(String path) {
		InputStream stream = ResourcesStore.class.getResourceAsStream(getPath(path));
		if (stream == null) return null;
		return StashDeserializer.stashFrom(stream);
	}

	@Override
	public String relativePathOf(URL url) {
		return url.toString();
	}

	@Override
	public URL resourceFrom(String path) {
		return ResourcesStore.class.getResource(getPath(path));
	}

	@Override
	public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Instance instance) {
		LOG.severe("Resource at " + newPath + "could not be stored. Stores based on java resources cannot save resources. Use a file system based store.");
		return null;
	}

	@Override
	public void writeStash(Stash stash, String path) {
		LOG.severe("Stash at " + path + "could not be stored. Stores based on java resources cannot save resources");
	}

	private String getPath(String path) {
		return path.startsWith("/") ? path : "/" + path;
	}

}
