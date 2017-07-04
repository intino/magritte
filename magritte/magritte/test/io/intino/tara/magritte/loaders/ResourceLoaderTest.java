package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Graph;
import io.intino.tara.magritte.layers.MockLayer;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static io.intino.tara.magritte.TestHelper.emptyStash;
import static io.intino.tara.magritte.TestHelper.mockStore;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;


public class ResourceLoaderTest {

    @Test
    public void load_node() throws Exception {
        Graph graph = new Graph(mockStore()).loadStashes(emptyStash);
        MockLayer mockLayer = graph.createRoot(MockLayer.class, emptyStash, "mock1");
        List<URL> list = ResourceLoader.load(asList("oldFile", "$@io.intino.tara.magritte.natives.CodedResource"), mockLayer);
        assertThat(list.size(), is(2));
        assertThat(list.get(0).getFile(), endsWith("oldFile"));
        assertThat(list.get(1).getFile(), endsWith("oldFile"));
    }

}