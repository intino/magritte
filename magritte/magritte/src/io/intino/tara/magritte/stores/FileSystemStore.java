package io.intino.tara.magritte.stores;

import io.intino.tara.io.Stash;
import io.intino.tara.io.StashDeserializer;
import io.intino.tara.io.StashSerializer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.Store;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import static java.util.logging.Logger.getGlobal;

@SuppressWarnings({"unused", "WeakerAccess"})
public class FileSystemStore implements Store {

	protected final File file;
	protected boolean allowWriting = true;

	public FileSystemStore(File file) {
		this.file = file;
	}

	public File directory() {
		return file;
	}

	@Override
	public boolean allowWriting() {
		return allowWriting;
	}

	public FileSystemStore allowWriting(boolean allowWriting) {
		this.allowWriting = allowWriting;
		return this;
	}

	@Override
	public Stash stashFrom(String path) {
		if (fileOf(path).exists()) return StashDeserializer.stashFrom(fileOf(path));
		return new ResourcesStore().stashFrom(path);
	}

	@Override
	public URL resourceFrom(String path) {
		try {
			return fileOf(path).exists() ? fileOf(path).toURI().toURL() : new ResourcesStore().resourceFrom(path);
		} catch (MalformedURLException e) {
			getGlobal().severe(e.getCause().getMessage());
			return null;
		}
	}

	@Override
	public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Node node) {
		try {
			if (inputStream == null) return preparePath(newPath).toURI().toURL();
			Files.write(preparePath(newPath).toPath(), bytesOf(inputStream));
			return resourceFrom(newPath);
		} catch (IOException e) {
			getGlobal().severe("Resource at " + newPath + "could not be stored. Cause: " + e.getCause().getMessage());
			return null;
		}
	}

	@Override
	public String relativePathOf(URL url) {
		try {
			if (!url.getProtocol().contains("file")) return new ResourcesStore().relativePathOf(url);
			String absolutePath = new File(url.toURI()).getAbsolutePath();
			if (!absolutePath.startsWith(file.getAbsolutePath())) return new ResourcesStore().relativePathOf(url);
			return absolutePath.equals(file.getAbsolutePath()) ? "" :
					absolutePath.substring(file.getAbsolutePath().length() + 1);
		} catch (URISyntaxException ignored) {
			return null;
		}
	}

	@Override
	public void writeStash(Stash stash, String path) {
		try {
			Stash composedStash = composeStash(path, stash);
			if (hasContent(composedStash))
				Files.write(preparePath(path).toPath(), StashSerializer.serialize(composedStash));
			else preparePath(path).delete();
		} catch (IOException e) {
			getGlobal().severe("File at " + path + " couldn't be written");
		}
	}

	private boolean hasContent(Stash composedStash) {
		return !composedStash.nodes.isEmpty() || !composedStash.concepts.isEmpty();
	}

	private Stash previousStash(String path) {
		return fileOf(path).exists() ? stashFrom(path) : new ResourcesStore().stashFrom(path);
	}

	private File preparePath(String path) {
		File file = fileOf(path);
		file.getParentFile().mkdirs();
		return file;
	}

	protected File fileOf(String path) {
		return new File(this.file, path);
	}

}
