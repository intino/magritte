package tara.magritte.stores;

import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.Node;
import tara.magritte.Store;
import tara.magritte.loaders.ClassFinder;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class ResourcesStore implements Store {

	private static final Logger LOG = Logger.getLogger(ResourcesStore.class.getName());

	@Override
	public Stash stashFrom(String path) {
		InputStream stream = ClassFinder.classLoader().getResourceAsStream(getPath(path));
		if (stream == null) return null;
		return StashDeserializer.stashFrom(stream);
	}

	@Override
	public String relativePathOf(URL url) {
		try {

			if (url.getProtocol().contains("jar")) return relativePathOfJar(url.toString());
			String inputPath = new File(url.toURI()).getAbsolutePath();
			final String rootPath = Arrays.asList(System.getProperty("java.class.path").split(File.pathSeparator)).stream()
				.filter(path -> !path.endsWith(".jar"))
				.map(path -> path.endsWith(File.separator) ? path : path + File.separator)
				.filter(inputPath::startsWith)
				.findFirst().get();
			return inputPath.substring(rootPath.length());
		} catch (URISyntaxException | NoSuchElementException e) {
			LOG.severe(e.getCause().getMessage());
		}
		LOG.severe("Url at " + url.toString() + " is not inside java resources");
		return null;
	}

	private String relativePathOfJar(String url) {
		return url.substring(url.indexOf("jar!/") + 5);
	}

	@Override
	public URL resourceFrom(String path) {
		return ClassFinder.classLoader().getResource(getPath(path));
	}

	@Override
	public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Node node) {
		LOG.severe("Resource at " + newPath + "could not be stored. Stores based on java resources cannot save resources. Use a file system based store.");
		return null;
	}

	@Override
	public void writeStash(Stash stash, String path) {
		LOG.severe("Stash at " + path + "could not be stored. Stores based on java resources cannot save stashes");
	}

	@Override
	public boolean allowWriting() {
		return false;
	}

	private String getPath(String path) {
		return path.startsWith("/") ? path : "/" + path;
	}

}
