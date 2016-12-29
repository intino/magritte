package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Graph;
import io.intino.tara.magritte.layers.MockLayer;
import io.intino.tara.magritte.modelwrappers.MockApplication;
import io.intino.tara.magritte.modelwrappers.MockPlatform;
import org.junit.Test;

import java.util.List;

import static io.intino.tara.magritte.TestHelper.emptyStash;
import static io.intino.tara.magritte.TestHelper.mockStore;
import static io.intino.tara.magritte.loaders.NodeLoader.load;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NodeLoaderTest {

    @Test
    public void load_node() throws Exception {
        Graph graph = Graph.load(emptyStash, mockStore()).wrap(MockApplication.class, MockPlatform.class);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash, "mock1");
        graph.createRoot(MockLayer.class, emptyStash, "mock2");
        List<MockLayer> list = load(asList(emptyStash + "#mock1", "$@io.intino.tara.magritte.natives.CodedNode"), MockLayer.class, mockLayer);
        assertThat(list.size(), is(2));
        assertThat(list.get(0).name(), is("mock1"));
        assertThat(list.get(1).name(), is("mock2"));
    }

}