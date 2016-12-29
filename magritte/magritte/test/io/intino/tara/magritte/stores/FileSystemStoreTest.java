package io.intino.tara.magritte.stores;

import org.junit.Before;
import org.junit.Test;
import io.intino.tara.magritte.Node;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static io.intino.tara.io.Helper.list;
import static io.intino.tara.io.Helper.newNode;
import static io.intino.tara.io.Helper.newStash;

public class FileSystemStoreTest {

	private static final String CONTENT = "HelloWorld!";
	private FileSystemStore store;
	private File tempDirectory;

	@Before
	public void setUp() throws Exception {
		tempDirectory = Files.createTempDirectory("magritte-test").toFile();
		tempDirectory.deleteOnExit();
		store = new FileSystemStore(tempDirectory);
	}

	@Test
	public void relative_path_of_a_created_file_is_correctly_calculated() throws Exception {
		String path = "object/link.txt".replace("/", File.separator);
		URL url = store.writeResource(inputStream(), path, null, new Node("Test#Test1"));
		assertThat(store.relativePathOf(url), startsWith(path));
	}

	@Test
	public void relative_path_of_a_jar_should_be_correctly_calculated() throws Exception {
		URL url = new URL("jar:file:/Users/jevora/Projects/Tara.jar!/templates/remember-subject.tpl");
		assertThat(store.relativePathOf(url), is("templates/remember-subject.tpl"));
	}

    @Test
    public void should_delete_file_if_stash_is_empty() throws Exception {
        store.writeStash(newStash("xxx", list(newNode("xxx#yyy", list(), list(), list()))), "xxx.stash");
        assertTrue(new File(tempDirectory, "xxx.stash").exists());
        store.writeStash(newStash("xxx", list()), "xxx.stash");
        assertFalse(new File(tempDirectory, "xxx.stash").exists());
    }

    private InputStream inputStream() {
		return new ByteArrayInputStream(CONTENT.getBytes());
	}

}