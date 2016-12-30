package io.intino.tara.magritte.loaders;

import org.junit.Test;
import io.intino.tara.io.Stash;
import io.intino.tara.magritte.Concept;
import io.intino.tara.magritte.Graph;
import io.intino.tara.magritte.Store;
import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.modelwrappers.MockApplication;
import io.intino.tara.magritte.modelwrappers.MockPlatform;
import io.intino.tara.magritte.stores.ResourcesStore;

import java.util.List;

import static io.intino.tara.magritte.TestHelper.emptyStash;
import static io.intino.tara.magritte.TestHelper.mockStore;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.intino.tara.magritte.loaders.ConceptLoader.load;
import static io.intino.tara.io.Helper.*;

public class ConceptLoaderTest {

	@Test
	public void load_concept() throws Exception {
		Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
		MockLayer mockLayer = graph.createRoot(MockLayer.class);
		List<Concept> list = load(asList("Mock", "$@io.intino.tara.magritte.natives.CodedConcept"), mockLayer);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).id(), is("Mock"));
		assertThat(list.get(1).id(), is("Mock"));
	}

}