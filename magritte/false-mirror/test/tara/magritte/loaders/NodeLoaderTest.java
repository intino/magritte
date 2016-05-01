package tara.magritte.loaders;

import org.junit.Test;
import tara.io.Stash;
import tara.magritte.Graph;
import tara.magritte.Store;
import tara.magritte.layers.MockLayer;
import tara.magritte.modelwrappers.MockApplication;
import tara.magritte.modelwrappers.MockPlatform;
import tara.magritte.stores.ResourcesStore;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tara.io.Helper.*;
import static tara.magritte.loaders.NodeLoader.load;

public class NodeLoaderTest {

	private static final String emptyStash = "Empty";

	@Test
	public void load_node() throws Exception {
		Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash, "mock1");
		graph.createRoot(MockLayer.class, emptyStash, "mock2");
		List<MockLayer> list = load(asList(emptyStash + "#mock1", "$@tara.magritte.natives.CodedNode"), MockLayer.class, mockLayer);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).name(), is("mock1"));
		assertThat(list.get(1).name(), is("mock2"));
	}

	private Store mockStore() {
		return new ResourcesStore() {

			@Override
			public Stash stashFrom(String path) {
				return path.contains(emptyStash) ? emptyStash() : null;
			}

		};
	}

	private Stash emptyStash() {
		return newStash("Proteo", emptyList(), emptyList(),
			list(newConcept("Mock", false, false, true, "tara.magritte.layers.MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
			emptyList());
	}
}