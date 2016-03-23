package tara.magritte.loaders;

import org.junit.Test;
import tara.magritte.layers.MockLayer;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeLoaderTest {

	@Test
	public void load() throws Exception {
		List<LocalTime> list = TimeLoader.load(asList("10:30", "tara.magritte.natives.CodedTime"), new MockLayer(null));
		assertThat(list.size(), is(2));
		assertThat(list.get(0), is(LocalTime.of(10, 30)));
		assertThat(list.get(1), is(LocalTime.of(4, 0)));
	}
}