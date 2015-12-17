package tara.magritte.stores;

import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.io.StashSerializer;
import tara.magritte.Store;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class FileSystemStore implements Store {

    private static final Logger LOG = Logger.getLogger(FileSystemStore.class.getName());

    private final File file;

    public FileSystemStore(File file) {
        this.file = file;
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
            LOG.severe(e.getMessage());
            return null;
        }
    }

	@Override
	public URL writeResource(InputStream inputStream, String path) {
		try {
			Files.write(fileOf(path).toPath(), bytesOf(inputStream));
			return resourceFrom(path);
		} catch (IOException e) {
			LOG.severe("Resource at " + path + "could not be stored. Cause: " + e.getCause().getMessage());
			return null;
		}
	}

	@Override
	public String relativePathOf(URL url) {
		try {
			String absolutePath = new File(url.toURI()).getAbsolutePath();
			return absolutePath.substring(absolutePath.indexOf(file.getAbsolutePath()) + 1);
		} catch (URISyntaxException ignored) {
			return null;
		}
	}

	@Override
	public void writeStash(Stash stash, String path) {
		if(fileOf(path).exists())
			doWriteStash(path, composeStash(path, stash));
		else if(new ResourcesStore().stashFrom(path) != null)
			new ResourcesStore().writeStash(stash, path);
		else
			doWriteStash(path, stash);
	}

	private File fileOf(String path) {
		return new File(this.file, path);
	}

	private void doWriteStash(String path, Stash stash) {
		try {
			Files.write(fileOf(path).toPath(), StashSerializer.serialize(stash));
		} catch (IOException e) {
			LOG.severe("File at " + path + " couldn't be written");
		}
	}

}
