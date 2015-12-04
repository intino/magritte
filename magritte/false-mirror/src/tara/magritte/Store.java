package tara.magritte;

import tara.io.Stash;
import java.net.URI;

public interface Store {

    Stash stashFrom(String path);
    URI resourceFrom(String path);

}
