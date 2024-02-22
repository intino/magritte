package io.intino.magritte.framework.concepts;

import io.intino.magritte.framework.Graph;
import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.Store;
import io.intino.magritte.framework.layers.Child;
import io.intino.magritte.io.model.Stash;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

import static io.intino.magritte.framework.TestHelper.emptyStash;
import static io.intino.magritte.io.Helper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Creation {

	@Test
	public void createInstanceOfConceptWithManyParents() throws Exception {
		Graph graph = new Graph(mockStore()).loadStashes(emptyStash);
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
				newConcept("Child", false, false, false, true, "io.intino.magritte.framework.layers.Child", "Parent", list("Concept"), list(), list(newVariableOfList("childVar", list("child"))), list(newVariable("fillByChildVar", "filledByChild")), list()),
				newConcept("Parent", false, false, false, true, "io.intino.magritte.framework.layers.Parent", "Grandparent", list("Concept"), list(), list(newVariableOfList("parentVar", list("parent")), newVariable("fillByChildVar", "toBeFilled")), list(newVariable("fillByParentVar", "filledByParent")), list()),
				newConcept("Grandparent", false, false, false, true, "io.intino.magritte.framework.layers.Grandparent", null, list("Concept"), list(), list(newVariableOfList("grandparentVar", list("grandparent")), newVariableOfList("fillByParentVar", list("toBeFilled"))), list(), list())
		), list());
	}
}
