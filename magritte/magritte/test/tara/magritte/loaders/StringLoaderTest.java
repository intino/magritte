package tara.magritte.loaders;

import org.junit.Ignore;
import org.junit.Test;
import tara.magritte.Node;
import tara.magritte.layers.MockLayer;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore
public class StringLoaderTest {

	@Test
	public void load() throws Exception {
		List<String> list = StringLoader.load(asList("Hey hey", "$@tara.magritte.natives.CodedString"), new MockLayer(new Node("Model#mock")));
		assertThat(list.size(), is(2));
		assertThat(list.get(0), is("Hey hey"));
		assertThat(list.get(1), is("Hey hey mock"));
	}
}