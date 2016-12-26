package io.intino.tara.magritte.loaders;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import io.intino.tara.magritte.layers.MockLayer;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.intino.tara.magritte.loaders.WordLoaderTest.Modes.*;


public class WordLoaderTest {

	@Test
	public void load() throws Exception {
		List<Modes> list = WordLoader.load(asList("Off", "$@CodedWord", "Idle"), Modes.class, new MockLayer(null));
		assertThat(list.size(), is(3));
		assertThat(list.get(0), CoreMatchers.is(Off));
		assertThat(list.get(1), CoreMatchers.is(On));
		assertThat(list.get(2), CoreMatchers.is(Idle));
	}

	public enum Modes{
		On, Off, Idle
	}
}