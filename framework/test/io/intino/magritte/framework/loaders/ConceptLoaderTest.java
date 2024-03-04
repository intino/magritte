package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Concept;
import io.intino.magritte.framework.Graph;
import io.intino.magritte.framework.TestHelper;
import io.intino.magritte.framework.layers.MockLayer;
import org.junit.Test;

import java.util.List;

import static io.intino.magritte.framework.loaders.ConceptLoader.load;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConceptLoaderTest {

	@Test
	public void load_concept() throws Exception {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		MockLayer mockLayer = graph.createRoot(MockLayer.class);
		List<Concept> list = load(asList("Mock", "$@io.intino.magritte.framework.natives.CodedConcept"), mockLayer);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).id(), is("Mock"));
		assertThat(list.get(1).id(), is("Mock"));
	}

}