package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.layers.MockLayer;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListProcessorTest {

	@Test
	public void should_execute_method_without_strings() throws Exception {
		List<?> list = ListProcessor.process(asList(1, "$@io.intino.magritte.framework.natives.CodedInteger"), new MockLayer(new Node("mockito")));
		assertThat(list.size(), is(2));
		assertThat(list.get(0), is(1));
		assertThat(list.get(1), is(2));
	}

	@Test
	public void should_execute_method_in_native_using_strings() throws Exception {
		List<?> list = ListProcessor.process(asList("Hey hey none", "$@io.intino.magritte.framework.natives.CodedString"), new MockLayer(new Node("mockito")));
		assertThat(list.size(), is(2));
		assertThat(list.get(0), is("Hey hey none"));
		assertThat(list.get(1), is("Hey hey mockito"));
	}

}