package io.intino.tara.magritte.utils;

import io.intino.tara.io.Stash;
import io.intino.tara.io.StashSerializer;
import io.intino.tara.magritte.stores.FileSystemStore;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static io.intino.tara.io.Helper.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreAuditorTest {

	private File tempDirectory;

	@Before
	public void setUp() throws Exception {
		tempDirectory = Files.createTempDirectory("magritte-test").toFile();
		tempDirectory.deleteOnExit();
	}

	@Test
	public void should_provide_correct_changes() throws Exception {
		Files.write(new File(tempDirectory, "xxx.stash").toPath(), StashSerializer.serialize(stashOld()));
		StoreAuditor auditor = new StoreAuditor(new FileSystemStore(tempDirectory));
		auditor.trace("xxx.stash");
		assertThat(auditor.changeList().size(), is(6));
		auditor.commit();
		Files.write(new File(tempDirectory, "xxx.stash").toPath(), StashSerializer.serialize(stashNew()));
		auditor = new StoreAuditor(new FileSystemStore(tempDirectory));
		auditor.trace("xxx.stash");
		assertThat(auditor.changeList().size(), is(7));
	}

	private Stash stashOld() {
		return newStash("xxx", list(
				newNode("xxx#yyyy", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$5fbf91b6-ad94-40bc-bbf2-9cf1bd563950", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#yyyy2", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$zzzz", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#yyyy3", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$zzzz", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#yyyy4", list("test"), list(newString("test", "hola2")), list(
						newNode("xxx#yyyy$5fbf91b6-ad94-40bc-bbf2-9cf1bd563950", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#5fbf91b6-ad94-40bc-bbf2-9cf1bd563950", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$zzzz", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#5fbf91b6-ad94-40bc-bbf2-9cf1bd563951", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$zzzz", list("test"), list(newString("test", "hola")), list())))
		));
	}

	private Stash stashNew() {
		return newStash("xxx", list(
				newNode("xxx#yyyy", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$5fbf91b6-ad94-40bc-bbf2-9cf1bd563951", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#yyyy2", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$zzzz", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#yyyy4", list("test"), list(newString("test", "hola3")), list(
						newNode("xxx#yyyy$5fbf91b6-ad94-40bc-bbf2-9cf1bd563951", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#6fbf91b6-ad94-40bc-bbf2-9cf1bd563950", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$zzzz", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#6fbf91b6-ad94-40bc-bbf2-9cf1bd563951", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$zzzz", list("test"), list(newString("test", "hola")), list()))),
				newNode("xxx#6fbf91b6-ad94-40bc-bbf2-9cf1bd563952", list("test"), list(newString("test", "hola")), list(
						newNode("xxx#yyyy$zzzz", list("test"), list(newString("test", "hola")), list())))
		));
	}

}
