package tara.compiler;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class AcceptedStashBuilder {

	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("test-res/stashes/").getAbsolutePath();
	}

	@Test
	public void should_read_stash() throws Exception {
//		final File root = new File(home, "Model.stash");
//		final Stash stash = stashFrom(root);
//		assert stash != null;
	}
}
