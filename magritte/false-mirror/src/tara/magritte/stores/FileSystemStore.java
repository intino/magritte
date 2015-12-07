package tara.magritte.stores;

import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.Store;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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
        File file = new File(this.file, path);
        if (file.exists()) return StashDeserializer.stashFrom(file);
        return new ResourcesStore().stashFrom(path);
    }

    @Override
    public URL resourceFrom(String path) {
        try {
            File file = new File(this.file, path);
            return file.exists() ? file.toURI().toURL() : new ResourcesStore().resourceFrom(path);
        } catch (MalformedURLException e) {
            LOG.severe(e.getMessage());
            return null;
        }
    }
}
