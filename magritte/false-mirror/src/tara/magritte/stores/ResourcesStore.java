package tara.magritte.stores;

import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.Store;

import java.io.InputStream;
import java.net.URL;

public class ResourcesStore implements Store {

    @Override
    public Stash stashFrom(String path) {
        InputStream stream = ResourcesStore.class.getResourceAsStream("/" + path);
        if (stream == null) return null;
        return StashDeserializer.stashFrom(stream);
    }

    @Override
    public URL resourceFrom(String path) {
        return ResourcesStore.class.getResource("/" + path);
    }

}
