package io.intino.magritte.framework.stores;

import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.Store;
import io.intino.magritte.framework.loaders.ClassFinder;
import io.intino.magritte.io.StashDeserializer;
import io.intino.magritte.io.model.Stash;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.logging.Logger.getGlobal;

public class ResourcesStore implements Store {

	Set<String> whileList = new HashSet<>();

	@Override
	public Stash stashFrom(String path) {
		InputStream stream = shouldOpen(path) ? ClassFinder.getResourceAsStream(getPath(path)) : null;
		if (stream == null) return null;
		return StashDeserializer.stashFrom(stream);
	}

	public Store setWhiteList(Collection<String> whileList){
		this.whileList.clear();
		this.whileList.addAll(whileList);
		return this;
	}

	private boolean shouldOpen(String path) {
		return whileList.isEmpty() || whileList.contains(path);
	}

	@Override
	public String relativePathOf(URL url) {
		try {
			if (url.getProtocol().contains("jar")) return relativePathOfJar(url.toString());
			String inputPath = new File(url.toURI()).getAbsolutePath();
			final String rootPath = stream(System.getProperty("java.class.path").split(File.pathSeparator))
					.filter(path -> !path.endsWith(".jar"))
					.map(path -> path.endsWith(File.separator) ? path : path + File.separator)
					.filter(inputPath::startsWith)
					.findFirst().orElse("");
			return inputPath.substring(rootPath.length());
		} catch (URISyntaxException | NoSuchElementException e) {
			getGlobal().severe(e.getCause().getMessage());
		}
		getGlobal().severe("Url at " + url.toString() + " is not inside java resources");
		return null;
	}

	private String relativePathOfJar(String url) {
		return url.substring(url.indexOf("jar!/") + 5);
	}

	@Override
	public URL resourceFrom(String path) {
		return ClassFinder.getResource(getPath(path));
	}

	@Override
	public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Node node) {
		getGlobal().severe("Resource at " + newPath + "could not be stored. Stores based on java resources cannot save resources. Use a file system based store.");
		return null;
	}

	@Override
	public void writeStash(Stash stash, String path) {
		getGlobal().severe("Stash at " + path + "could not be stored. Stores based on java resources cannot save stashes");
	}

	@Override
	public boolean allowWriting() {
		return false;
	}

	private String getPath(String path) {
		return path.startsWith("/") ? path : "/" + path;
	}

}
