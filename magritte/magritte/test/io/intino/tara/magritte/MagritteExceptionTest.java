package io.intino.tara.magritte;

import org.junit.Test;
import io.intino.tara.io.Stash;

import java.io.InputStream;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static io.intino.tara.io.Helper.*;

public class MagritteExceptionTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void should_provide_a_magritte_exception_concept_not_found() throws Exception {
        try {
            new Graph(store()).loadPaths("Model");
            assertFalse(true);
        }catch (MagritteException e){
            assertThat(e.getMessage(), is("Concept Mock not found"));
        }
    }

    private Store store() {
        return new Store() {
            @Override
            public Stash stashFrom(String path) {
                return newStash("Proteo", list(), list(), list(), list(newNode("xxx", list("Mock"), list(), list())));
            }

            @Override
            public void writeStash(Stash stash, String path) {

            }

            @Override
            public URL resourceFrom(String path) {
                return null;
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
}
