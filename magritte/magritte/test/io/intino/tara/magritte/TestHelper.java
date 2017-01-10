package io.intino.tara.magritte;

import io.intino.tara.io.Stash;
import io.intino.tara.magritte.stores.ResourcesStore;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static io.intino.tara.io.Helper.*;
import static java.util.Collections.emptyList;

@SuppressWarnings("WeakerAccess")
public class TestHelper {

    public static final String emptyStash = "Empty";
    public static final String oneMockStash = "OneMock";
    public static final String firstStash = "firstStash";
    public static final String secondStash = "secondStash";
    public static final String thirdStash = "thirdStash";
    public static final String dependantStashByUse = "dependantStashByUse";
    public static final String independentStash = "subnamespace/independant";
    public static final String m1 = "m1";
    public static final String m2 = "m2";
    public static final String m3 = "m3";
    public static final String Extension = ".stash";

    public static Store mockStore() {
        return new Store() {

            Map<String, Stash> store = new HashMap<String, Stash>() {{
                put(emptyStash + Extension, emptyStash());
                put(oneMockStash + Extension, oneMockStash());
                put(firstStash + Extension, firstStash());
                put(secondStash + Extension, secondStash());
                put(thirdStash + Extension, thirdStash());
                put(dependantStashByUse + Extension, dependantStashByUse());
                put(independentStash + Extension, independentStashInSubNamespace());
                put(m1 + Extension, m1());
                put(m2 + Extension, m2());
                put(m3 + Extension, m3());
            }};

            @Override
            public Stash stashFrom(String path) {
                return store.get(path);
            }

            @Override
            public void writeStash(Stash stash, String path) {
                store.put(path, composeStash(path, stash));
            }

            @Override
            public URL resourceFrom(String path) {
                return new ResourcesStore().resourceFrom(path);
            }

            @Override
            public URL writeResource(InputStream inputStream, String newPath, URL oldUrl, Node node) {
                return null;
            }

            @Override
            public String relativePathOf(URL url) {
                return null;
            }
        };
    }

    public static Stash firstStash() {
        Stash stash = emptyStash();
        stash.nodes.add(newNode(firstStash + "#x", list("Mock"), list(newReference("mockLayer", secondStash + "#y")), emptyList()));
        return stash;
    }

    public static Stash secondStash() {
        Stash stash = emptyStash();
        stash.nodes.add(newNode(secondStash + "#y", list("Mock"), list(newReference("mockLayer", thirdStash + "#z")), emptyList()));
        return stash;
    }

    public static Stash thirdStash() {
        Stash stash = emptyStash();
        stash.nodes.add(newNode(thirdStash + "#z", list("Mock"), list(newReference("mockLayer", firstStash + "#x")), emptyList()));
        return stash;
    }

    public static Stash oneMockStash() {
        Stash stash = emptyStash();
        stash.nodes.add(newNode(oneMockStash + "#x", list("Mock"), emptyList(), emptyList()));
        stash.nodes.add(newNode(oneMockStash + "#y", list("Mock"), emptyList(), emptyList()));
        return stash;
    }

    public static Stash dependantStashByUse() {
        Stash stash = emptyStash();
        stash.uses.add(oneMockStash);
        stash.nodes.add(newNode(dependantStashByUse + "#x", list("Mock"), emptyList(), emptyList()));
        return stash;
    }

    public static Stash independentStashInSubNamespace() {
        Stash stash = emptyStash();
        stash.nodes.add(newNode(independentStash + "#x", list("Mock"), emptyList(), emptyList()));
        return stash;
    }

    public static Stash m1() {
        Stash stash = newStash("m2", list());
        stash.nodes.add(newNode(m1 + "#x", list("Mock"), emptyList(), emptyList()));
        stash.nodes.add(newNode(m1 + "#y", list("Mock"), emptyList(), emptyList()));
        return stash;
    }

    public static Stash m2() {
        return newStash("m3", list());
    }

    public static Stash m3() {
        return emptyStash();
    }

    static Stash emptyStash() {
        return newStash("Proteo", emptyList(), emptyList(),
                list(newConcept("Mock", false, false, true, "io.intino.tara.magritte.layers.MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
                emptyList());
    }
}
