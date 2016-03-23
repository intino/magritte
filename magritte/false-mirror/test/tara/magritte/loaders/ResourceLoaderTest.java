package tara.magritte.loaders;

import org.junit.Test;
import tara.io.Stash;
import tara.magritte.DynamicModel;
import tara.magritte.Instance;
import tara.magritte.Model;
import tara.magritte.Store;
import tara.magritte.layers.DynamicMockLayer;
import tara.magritte.modelwrappers.DynamicMockApplication;
import tara.magritte.modelwrappers.DynamicMockPlatform;
import tara.magritte.stores.ResourcesStore;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tara.io.Helper.*;
import static tara.magritte.loaders.InstanceLoader.load;

public class ResourceLoaderTest {

	private static final String emptyStash = "Empty";
	private static final String Extension = ".stash";

	@Test
	public void load_instance() throws Exception {


		Model model = DynamicModel.load(emptyStash, mockStore()).init(DynamicMockApplication.class, DynamicMockPlatform.class);
		DynamicMockLayer mockLayer = model.newMain(DynamicMockLayer.class, emptyStash, "mock1");
		model.newMain(DynamicMockLayer.class, emptyStash, "mock2");
		List<DynamicMockLayer> list = load(asList(emptyStash + "#mock1", "tara.magritte.natives.CodedReference"), DynamicMockLayer.class, mockLayer);
		assertThat(list.size(), is(2));
		assertThat(list.get(0)._simpleName(), is("mock1"));
		assertThat(list.get(1)._simpleName(), is("mock2"));
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
				list(newConcept("Mock", false, false, true, "tara.magritte.layers.DynamicMockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}

}