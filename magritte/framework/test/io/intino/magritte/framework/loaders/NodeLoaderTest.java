package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Graph;
import io.intino.magritte.framework.TestHelper;
import io.intino.magritte.framework.layers.MockLayer;
import org.junit.Test;

import java.util.List;

import static io.intino.magritte.framework.loaders.NodeLoader.load;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NodeLoaderTest {

	@Test
	public void load_node() throws Exception {
		Graph graph = new Graph(TestHelper.mockStore()).loadStashes(TestHelper.emptyStash);
		MockLayer mockLayer = graph.createRoot(MockLayer.class, TestHelper.emptyStash, "mock1");
		graph.createRoot(MockLayer.class, TestHelper.emptyStash, "mock2");
		List<MockLayer> list = load(asList(TestHelper.emptyStash + "#mock1", "$@io.intino.magritte.framework.natives.CodedNode"), MockLayer.class, mockLayer);
		assertThat(list.size(), is(2));
		assertThat(list.get(0).name$(), is("mock1"));
		assertThat(list.get(1).name$(), is("mock2"));
	}

}