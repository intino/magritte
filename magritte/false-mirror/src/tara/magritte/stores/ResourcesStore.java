package tara.magritte.stores;

import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.io.StashSerializer;
import tara.magritte.Store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
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
	public String relativePathOf(URL url){
		return url.toString();
	}

	@Override
	public URL resourceFrom(String path) {
		return ResourcesStore.class.getResource(getPath(path));
	}

	@Override
	public void writeStash(String path, Stash stash) {
		if(ResourcesStore.class.getResourceAsStream(getPath(path)) != null)
			doWriteStash(path, stash);
		else
			doWriteStash(path, composeStash(path, stash));
	}

	private OutputStream getOutputStreamOf(String path) {
		try {
			URLConnection urlConnection = ResourcesStore.class.getResource(getPath(path)).openConnection();
			urlConnection.setDoOutput(true);
			return urlConnection.getOutputStream();
		} catch (IOException e) {
			return null;
		}
	}

	private void doWriteStash(String path, Stash stash) {
		OutputStream outputStream = getOutputStreamOf(path);
		if (outputStream == null) LOG.severe("Resource at " + path + " couldn't be written");
		else doWriteStash(outputStream, stash);
	}

	private void doWriteStash(OutputStream outputStream, Stash stash) {
		try {
			outputStream.write(StashSerializer.serialize(stash));
			outputStream.close();
		} catch (IOException e) {
			LOG.severe(e.getCause().getMessage());
		}
	}

	private String getPath(String path) {
		return path.startsWith("/") ? path : "/" + path;
	}

}
