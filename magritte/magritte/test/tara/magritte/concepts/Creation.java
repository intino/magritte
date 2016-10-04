package tara.magritte.concepts;

import org.junit.Test;
import tara.io.Stash;
import tara.magritte.Graph;
import tara.magritte.Node;
import tara.magritte.Store;
import tara.magritte.layers.Child;
import tara.magritte.modelwrappers.CreationApplication;

import java.io.InputStream;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static tara.io.Helper.*;

public class Creation {

    @Test
    public void createInstanceOfConceptWithManyParents() throws Exception {
        Graph graph = Graph.load(mockStore()).wrap(CreationApplication.class);
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
                newConcept("Child", false, false, true, "tara.magritte.layers.Child", "Parent", list("Concept"), list(), list(newString("childVar", list("child"))), list(newString("fillByChildVar", "filledByChild")), list()),
                newConcept("Parent", false, false, true, "tara.magritte.layers.Parent", "Grandparent", list("Concept"), list(), list(newString("parentVar", list("parent")), newString("fillByChildVar", "toBeFilled")), list(newString("fillByParentVar", "filledByParent")), list()),
                newConcept("Grandparent", false, false, true, "tara.magritte.layers.Grandparent", null, list("Concept"), list(), list(newString("grandparentVar", list("grandparent")), newString("fillByParentVar", list("toBeFilled"))), list(), list())
        ), list());
    }
}
