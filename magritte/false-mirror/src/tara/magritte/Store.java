package tara.magritte;

import tara.io.Stash;

import java.net.URL;

public interface Store {

    Stash stashFrom(String path);
    URL resourceFrom(String path);

}
