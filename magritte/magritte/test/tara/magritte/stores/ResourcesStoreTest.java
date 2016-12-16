package tara.magritte.stores;

import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResourcesStoreTest {

	@Test
	public void relative_path_of_a_jar_should_be_correctly_calculated() throws Exception {
		URL url = new URL("jar:file:/Users/jevora/Projects/Tara.jar!/templates/remember-subject.tpl");
		assertThat(new ResourcesStore().relativePathOf(url), is("templates/remember-subject.tpl"));
	}

	@Test
	public void relative_path_of_anchors_should_be_right() throws Exception {
		ResourcesStore resourcesStore = new ResourcesStore();
		assertThat(resourcesStore.relativePathOf(resourcesStore.resourceFrom("oldFile")), is("oldFile"));
	}
}