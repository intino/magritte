package tara.magritte.loaders;

import org.junit.Test;
import tara.io.Stash;
import tara.magritte.Model;
import tara.magritte.Store;
import tara.magritte.layers.MockLayer;
import tara.magritte.modelwrappers.MockApplication;
import tara.magritte.modelwrappers.MockPlatform;
import tara.magritte.stores.ResourcesStore;

import java.net.URL;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static tara.io.Helper.*;

public class ResourceLoaderTest {

	private static final String emptyStash = "Empty";

	@Test
	public void load_instance() throws Exception {
		Model model = Model.load(emptyStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		MockLayer mockLayer = model.newMain(MockLayer.class, emptyStash, "mock1");
		List<URL> list = ResourceLoader.load(asList("oldFile", "tara.magritte.natives.CodedResource"), mockLayer);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getFile(), endsWith("oldFile"));
		assertThat(list.get(1).getFile(), endsWith("oldFile"));
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
				list(newConcept("Mock", false, false, true, "tara.magritte.layers.MockLayer", null, list("Concept"), emptyList(), emptyList(), emptyList(), emptyList(), emptyList())),
				emptyList());
	}

}