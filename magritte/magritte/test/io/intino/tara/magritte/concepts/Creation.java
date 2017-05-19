package io.intino.tara.magritte.concepts;

import io.intino.tara.io.Stash;
import io.intino.tara.magritte.Graph;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.Store;
import io.intino.tara.magritte.layers.Child;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

import static io.intino.tara.io.Helper.*;
import static io.intino.tara.magritte.TestHelper.emptyStash;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Creation {

    @Test
    public void createInstanceOfConceptWithManyParents() throws Exception {
        Graph graph = new Graph(mockStore()).loadPaths(emptyStash);
        Child child = graph.createRoot(Child.class);
        assertThat(child.childVar(), is("child"));
        assertThat(child.fillByChildVar(), is("filledByChild"));
        assertThat(child.parentVar(), is("parent"));
        assertThat(child.fillByParentVar(), is("filledByParent"));
        assertThat(child.grandparentVar(), is("grandparent"));
    }

    private Store mockStore() {
        return new Store() {
            @Override
            public Stash stashFrom(String path) {
                return mockStash();
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

    private Stash mockStash() {
        return newStash("Proteo", list(), list(), list(
                newConcept("Child", false, false, true, "io.intino.tara.magritte.layers.Child", "Parent", list("Concept"), list(), list(newString("childVar", list("child"))), list(newString("fillByChildVar", "filledByChild")), list()),
                newConcept("Parent", false, false, true, "io.intino.tara.magritte.layers.Parent", "Grandparent", list("Concept"), list(), list(newString("parentVar", list("parent")), newString("fillByChildVar", "toBeFilled")), list(newString("fillByParentVar", "filledByParent")), list()),
                newConcept("Grandparent", false, false, true, "io.intino.tara.magritte.layers.Grandparent", null, list("Concept"), list(), list(newString("grandparentVar", list("grandparent")), newString("fillByParentVar", list("toBeFilled"))), list(), list())
        ), list());
    }
}
