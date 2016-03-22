package tara.magritte.loaders;

import org.junit.Test;
import tara.magritte.layers.MockLayer;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static tara.magritte.loaders.BooleanLoader.load;

public class BooleanLoaderTest {

	@Test
	public void load_boolean_mix() throws Exception {
		List<Boolean> list = load(asList(true, "tara.magritte.natives.CodedBoolean", false), new MockLayer(null));
		assertThat(list.size(), is(3));
		assertThat(list.get(0), is(true));
		assertThat(list.get(1), is(false));
		assertThat(list.get(2), is(false));
	}
}