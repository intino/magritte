package tara.magritte.loaders;

import org.junit.Test;
import tara.io.Stash;
import tara.magritte.Concept;
import tara.magritte.Instance;
import tara.magritte.Model;
import tara.magritte.Store;
import tara.magritte.layers.MockLayer;
import tara.magritte.modelwrappers.MockApplication;
import tara.magritte.modelwrappers.MockPlatform;
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
import static org.junit.Assert.*;
import static tara.io.Helper.list;
import static tara.io.Helper.newConcept;
import static tara.io.Helper.newStash;
import static tara.magritte.loaders.ConceptLoader.load;

public class ConceptLoaderTest {

	private static final String emptyStash = "Empty";

	@Test
	public void load_concept() throws Exception {
		Model model = Model.load(emptyStash, mockStore()).init(MockApplication.class, MockPlatform.class);
		MockLayer mockLayer = model.newMain(MockLayer.class);
		List<Concept> list = load(asList("Mock", "tara.magritte.natives.CodedConcept"), mockLayer);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).name(), is("Mock"));
		assertThat(list.get(1).name(), is("Mock"));
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