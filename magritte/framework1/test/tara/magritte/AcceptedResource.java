package tara.magritte;

import tara.magritte.primitives.Resource;
import org.junit.Test;

import static tara.magritte.sources.BoxSource.in;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AcceptedResource {

	@Test
	public void should_be_loaded_using_a_box_source() throws Exception {
		Resource resource = new Resource(in("bee.jpg"));
		assertThat(resource.format(), is("jpg"));
		assertThat(resource.size(), is(2370));
	}
}
