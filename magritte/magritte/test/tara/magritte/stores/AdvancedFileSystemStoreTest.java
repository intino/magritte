package tara.magritte.stores;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import tara.io.Stash;
import tara.magritte.Node;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static tara.io.Helper.*;

@Ignore
public class AdvancedFileSystemStoreTest {

	private static final String CONTENT = "HelloWorld!";
	private static final String NODE_NAME = "Test#Test1";
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
		URL url = store.writeResource(inputStream(), "object/link.txt", null, new Node(NODE_NAME));
		assertThat(new File(url.toURI()).getName(), containsString("link.txt"));
		assertThat(new File(url.toURI()).getName(), is(not("link.txt")));
		assertThat(Files.readAllLines(new File(url.toURI()).toPath()).get(0), is(CONTENT));
	}

	@Test
	public void file_is_removed_when_the_store_is_reloaded_and_the_node_was_not_saved() throws Exception {
		URL url = store.writeResource(inputStream(), "object/link.txt", null, new Node(NODE_NAME));
		new AdvancedFileSystemStore(tempDirectory);
		assertFalse(new File(url.toURI()).exists());
	}

	@Test
	public void past_file_is_removed_when_node_is_saved() throws Exception {
		File previousFile = new File(tempDirectory, "mock.txt");
		previousFile.createNewFile();
		store.writeResource(inputStream(), "object/link.txt", previousFile.toURI().toURL(), new Node(NODE_NAME));
		assertTrue(previousFile.exists());
		store.writeStash(stashWithOneNode(), "Test.stash");
		assertFalse(previousFile.exists());
	}

	@Test
	public void after_two_modifications_only_last_one_file_is_existing_when_node_is_saved() throws Exception {
		File previous = new File(tempDirectory, "mock.txt");
		previous.createNewFile();
		URL middle = store.writeResource(inputStream(), "mock2.txt", previous.toURI().toURL(), new Node(NODE_NAME));
		URL last = store.writeResource(inputStream(), "object/link.txt", middle, new Node(NODE_NAME));
		store.writeStash(stashWithOneNode(), "Test.stash");
		assertFalse(previous.exists());
		assertFalse(new File(middle.toURI()).exists());
		assertTrue(new File(last.toURI()).exists());
	}

	@Test
	public void after_two_modifications_only_first_one_file_is_existing_when_node_is_not_saved() throws Exception {
		File previous = new File(tempDirectory, "mock.txt");
		previous.createNewFile();
		URL middle = store.writeResource(inputStream(), "mock2.txt", previous.toURI().toURL(), new Node(NODE_NAME));
		URL last = store.writeResource(inputStream(), "object/link.txt", middle, new Node(NODE_NAME));
		new AdvancedFileSystemStore(tempDirectory);
		assertTrue(previous.exists());
		assertFalse(new File(middle.toURI()).exists());
		assertFalse(new File(last.toURI()).exists());
	}

	@Test
	public void after_saving_when_reloading_new_file_should_be_kept() throws Exception {
		URL newUrl = store.writeResource(inputStream(), "link.txt", null, new Node(NODE_NAME));
		assertTrue(new File(newUrl.toURI()).exists());
		store.writeStash(stashWithOneNode(), "Test.stash");
		assertTrue(new File(newUrl.toURI()).exists());
		new AdvancedFileSystemStore(tempDirectory);
		assertTrue(new File(newUrl.toURI()).exists());
	}

	@Test
	public void files_on_resources_should_be_kept_even_if_they_are_not_referenced_anymore() throws Exception {
		store.writeResource(inputStream(), "link.txt", this.getClass().getResource("/oldFile"), new Node(NODE_NAME));
		assertTrue(this.getClass().getResource("/oldFile") != null);
		store.writeStash(stashWithOneNode(), "Test.stash");
		assertTrue(this.getClass().getResource("/oldFile") != null);
	}

	private Stash stashWithOneNode() {
		return newStash(null, list(newNode(NODE_NAME, emptyList(), emptyList(), emptyList())));
	}

	private InputStream inputStream() {
		return new ByteArrayInputStream(CONTENT.getBytes());
	}
}