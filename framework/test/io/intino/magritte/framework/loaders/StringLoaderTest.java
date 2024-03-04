package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.layers.MockLayer;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class StringLoaderTest {

	@Test
	public void load() throws Exception {
		List<String> list = StringLoader.load(asList("Hey hey", "$@io.intino.magritte.framework.natives.CodedString"), new MockLayer(new Node("Model#mock")));
		assertThat(list.size(), is(2));
		assertThat(list.get(0), is("Hey hey"));
		assertThat(list.get(1), is("Hey hey mock"));
	}
}