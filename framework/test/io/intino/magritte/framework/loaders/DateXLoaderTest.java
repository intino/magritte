package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.layers.MockLayer;
import io.intino.magritte.framework.types.DateX;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateXLoaderTest {

	@Test
	public void load() throws Exception {
		List<DateX> list = DateXLoader.load(asList("2013-01-12", "$@io.intino.magritte.framework.natives.CodedDateX"), new MockLayer(null));
		assertThat(list.size(), is(2));
		assertThat(list.get(0).from(), is(LocalDateTime.of(2013, 1, 12, 0, 0).toInstant(UTC)));
		assertThat(list.get(1).from(), is(LocalDateTime.of(1987, 9, 17, 4, 0).toInstant(UTC)));
	}
}