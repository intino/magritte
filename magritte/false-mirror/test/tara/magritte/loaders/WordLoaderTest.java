package tara.magritte.loaders;

import org.junit.Test;
import tara.magritte.layers.MockLayer;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tara.magritte.loaders.WordLoaderTest.Modes.Idle;
import static tara.magritte.loaders.WordLoaderTest.Modes.Off;
import static tara.magritte.loaders.WordLoaderTest.Modes.On;

public class WordLoaderTest {

	@Test
	public void load() throws Exception {
		List<Modes> list = WordLoader.load(asList("Off", "tara.magritte.natives.CodedWord", "Idle"), Modes.class, new MockLayer(null));
		assertThat(list.size(), is(3));
		assertThat(list.get(0), is(Off));
		assertThat(list.get(1), is(On));
		assertThat(list.get(2), is(Idle));
	}

	public enum Modes{
		On, Off, Idle
	}
}