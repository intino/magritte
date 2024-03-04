package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.layers.MockLayer;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.List;

import static io.intino.magritte.framework.loaders.WordLoaderTest.Modes.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class WordLoaderTest {

	@Test
	public void load() throws Exception {
		List<Modes> list = WordLoader.load(asList("Off", "$@io.intino.magritte.framework.natives.CodedWord", "Idle"), Modes.class, new MockLayer(null));
		assertThat(list.size(), is(3));
		MatcherAssert.assertThat(list.get(0), CoreMatchers.is(Off));
		MatcherAssert.assertThat(list.get(1), CoreMatchers.is(On));
		MatcherAssert.assertThat(list.get(2), CoreMatchers.is(Idle));
	}

	public enum Modes {
		On, Off, Idle
	}
}