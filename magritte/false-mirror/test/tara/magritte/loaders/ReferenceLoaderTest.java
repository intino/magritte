package tara.magritte.loaders;

import org.junit.Ignore;
import org.junit.Test;
import tara.io.Stash;
import tara.magritte.DynamicGraph;
import tara.magritte.Graph;
import tara.magritte.Store;
import tara.magritte.layers.DynamicMockLayer;
import tara.magritte.modelwrappers.DynamicMockApplication;
import tara.magritte.modelwrappers.DynamicMockPlatform;
import tara.magritte.stores.ResourcesStore;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tara.io.Helper.*;
import static tara.magritte.loaders.NodeLoader.load;

@Ignore
public class ReferenceLoaderTest {

	private static final String emptyStash = "Empty";

	@Test
	public void load_node() throws Exception {
		Graph graph = DynamicGraph.load(emptyStash, mockStore()).wrap(DynamicMockApplication.class, DynamicMockPlatform.class);
		DynamicMockLayer mockLayer = graph.createRoot(DynamicMockLayer.class, emptyStash, "mock1");
		graph.createRoot(DynamicMockLayer.class, emptyStash, "mock2");
		List<DynamicMockLayer> list = load(asList(emptyStash + "#mock1", "$@tara.magritte.natives.CodedReference"), DynamicMockLayer.class, mockLayer);
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
			list(newConcept("Mock", false, false, true, "tara.magritte.layers.DynamicMockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList())),
			emptyList());
	}

}