package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.layers.MockLayer;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class IntegerLoaderTest {

	@Test
	public void load() throws Exception {
		List<Integer> list = IntegerLoader.load(asList(1, "$@io.intino.magritte.framework.natives.CodedInteger", 47), new MockLayer(null));
		assertThat(list.size(), is(3));
		assertThat(list.get(0), is(1));
		assertThat(list.get(1), is(2));
		assertThat(list.get(2), is(47));
	}
}