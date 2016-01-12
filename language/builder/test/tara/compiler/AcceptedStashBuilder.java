package tara.compiler;

import org.junit.Before;
import org.junit.Test;
import tara.io.Stash;

import java.io.File;

import static tara.io.StashDeserializer.stashFrom;

public class AcceptedStashBuilder {

	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("test-res/stashes/").getAbsolutePath();
	}

	@Test
	public void should_read_stash() throws Exception {
		final File root = new File(home, "Model.stash");
		final Stash stash = stashFrom(root);
		assert stash != null;
	}
}
