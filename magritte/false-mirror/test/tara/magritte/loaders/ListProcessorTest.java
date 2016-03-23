package tara.magritte.loaders;

import org.junit.Test;
import tara.magritte.Instance;
import tara.magritte.layers.MockLayer;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tara.magritte.loaders.ListProcessor.process;

public class ListProcessorTest {

	@Test
	public void should_execute_method_without_strings() throws Exception {
		List<?> list = process(asList(1, "tara.magritte.natives.CodedInteger"), new MockLayer(new Instance("mockito")));
		assertThat(list.size(), is(2));
		assertThat(list.get(0), is(1));
		assertThat(list.get(1), is(2));
	}

	@Test
	public void should_execute_method_in_native_using_strings() throws Exception {
		List<?> list = process(asList("Hey hey none", "tara.magritte.natives.CodedString"), new MockLayer(new Instance("mockito")));
		assertThat(list.size(), is(2));
		assertThat(list.get(0), is("Hey hey none"));
		assertThat(list.get(1), is("Hey hey mockito"));
	}

}