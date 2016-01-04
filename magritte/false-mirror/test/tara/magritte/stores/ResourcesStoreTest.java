package tara.magritte.stores;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResourcesStoreTest {

	@Test
	public void relative_path_of_anchors_should_be_right() throws Exception {
		ResourcesStore resourcesStore = new ResourcesStore();
		assertThat(resourcesStore.relativePathOf(resourcesStore.resourceFrom(".anchors")), is(".anchors"));
	}
}