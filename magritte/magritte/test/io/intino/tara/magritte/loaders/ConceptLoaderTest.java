package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Concept;
import io.intino.tara.magritte.Graph;
import io.intino.tara.magritte.layers.MockLayer;
import org.junit.Test;

import java.util.List;

import static io.intino.tara.magritte.TestHelper.emptyStash;
import static io.intino.tara.magritte.TestHelper.mockStore;
import static io.intino.tara.magritte.loaders.ConceptLoader.load;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConceptLoaderTest {

    @Test
    public void load_concept() throws Exception {
        Graph graph = new Graph(mockStore()).loadStashes(emptyStash);
        MockLayer mockLayer = graph.createRoot(MockLayer.class);
        List<Concept> list = load(asList("Mock", "$@io.intino.tara.magritte.natives.CodedConcept"), mockLayer);
        assertThat(list.size(), is(2));
        assertThat(list.get(0).id(), is("Mock"));
        assertThat(list.get(1).id(), is("Mock"));
    }

}