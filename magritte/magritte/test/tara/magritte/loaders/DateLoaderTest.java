package tara.magritte.loaders;

import org.junit.Ignore;
import org.junit.Test;
import tara.magritte.layers.MockLayer;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore
public class DateLoaderTest {

	@Test
	public void load() throws Exception {
		List<LocalDateTime> list = DateLoader.load(asList("12/01/2013", "$@tara.magritte.natives.CodedDate"), new MockLayer(null));
		assertThat(list.size(), is(2));
		assertThat(list.get(0), is(LocalDateTime.of(2013, 1, 12, 0, 0)));
		assertThat(list.get(1), is(LocalDateTime.of(1987, 9, 17, 4, 0)));
	}
}