package tara.magritte.stores;

import org.junit.Before;
import org.junit.Test;
import tara.magritte.Instance;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static tara.io.Helper.*;

public class AdvancedFileSystemStoreTest {

	private static final String CONTENT = "HelloWorld!";
	private AdvancedFileSystemStore store;
	private File tempDirectory;

	@Before
	public void setUp() throws Exception {
		tempDirectory = Files.createTempDirectory("magritte-test").toFile();
		tempDirectory.deleteOnExit();
		store = new AdvancedFileSystemStore(tempDirectory);
	}

	@Test
	public void file_should_be_stored() throws Exception {
		URL url = store.writeResource(inputStream(), "object/link.txt", null, new Instance("Test#Test1"));
		assertThat(new File(url.toURI()).getName(), containsString("link.txt"));
		assertThat(new File(url.toURI()).getName(), is(not("link.txt")));
		assertThat(Files.readAllLines(new File(url.toURI()).toPath()).get(0), is(CONTENT));
	}

	@Test
	public void file_is_removed_when_the_store_is_reloaded_and_the_instance_was_not_saved() throws Exception {
		URL url = store.writeResource(inputStream(), "object/link.txt", null, new Instance("Test#Test1"));
		new AdvancedFileSystemStore(tempDirectory);
		assertThat(new File(url.toURI()).exists(), is(false));
	}

	@Test
	public void past_file_is_removed_when_instance_is_saved() throws Exception {
		File previousFile = new File(tempDirectory, "mock.txt");
		previousFile.createNewFile();
		store.writeResource(inputStream(), "object/link.txt", previousFile.toURI().toURL(), new Instance("Test#Test1"));
		assertThat(previousFile.exists(), is(true));
		store.writeStash(newStash(null, emptyList(), emptyList(), list(newInstance("Test#Test1", emptyList()))), "Test.stash");
		assertThat(previousFile.exists(), is(false));
	}

	@Test
	public void after_two_modifications_only_last_one_file_is_existing_when_instance_is_saved() throws Exception {
		File previous = new File(tempDirectory, "mock.txt");
		previous.createNewFile();
		URL middle = store.writeResource(inputStream(), "mock2.txt", previous.toURI().toURL(), new Instance("Test#Test1"));
		URL last = store.writeResource(inputStream(), "object/link.txt", middle, new Instance("Test#Test1"));
		store.writeStash(newStash(null, emptyList(), emptyList(), list(newInstance("Test#Test1", emptyList()))), "Test.stash");
		assertThat(previous.exists(), is(false));
		assertThat(new File(middle.toURI()).exists(), is(false));
		assertThat(new File(last.toURI()).exists(), is(true));
	}

	@Test
	public void after_two_modifications_only_first_one_file_is_existing_when_instance_is_not_saved() throws Exception {
		File previous = new File(tempDirectory, "mock.txt");
		previous.createNewFile();
		URL middle = store.writeResource(inputStream(), "mock2.txt", previous.toURI().toURL(), new Instance("Test#Test1"));
		URL last = store.writeResource(inputStream(), "object/link.txt", middle, new Instance("Test#Test1"));
		new AdvancedFileSystemStore(tempDirectory);
		assertThat(previous.exists(), is(true));
		assertThat(new File(middle.toURI()).exists(), is(false));
		assertThat(new File(last.toURI()).exists(), is(false));
	}

	private InputStream inputStream() {
		return new ByteArrayInputStream(CONTENT.getBytes());
	}
}