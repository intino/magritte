package tara.magritte.stores;

import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.Store;

import java.io.File;
import java.net.URI;

public class FileSystemStore implements Store {

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
    public URI resourceFrom(String path) {
        return null;
    }
}
