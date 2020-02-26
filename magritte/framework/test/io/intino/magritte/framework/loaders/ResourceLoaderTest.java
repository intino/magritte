package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Graph;
import io.intino.magritte.framework.TestHelper;
import io.intino.magritte.framework.layers.MockLayer;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;


public class ResourceLoaderTest {

	@Test
	public void load_node() throws Exception {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, TestHelper.emptyStash, "mock1");
		List<URL> list = ResourceLoader.load(asList("oldFile", "$@io.intino.magritte.framework.natives.CodedResource"), mockLayer);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getFile(), endsWith("oldFile"));
		assertThat(list.get(1).getFile(), endsWith("oldFile"));
	}

}