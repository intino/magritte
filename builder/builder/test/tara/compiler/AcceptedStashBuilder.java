package tara.compiler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import tara.StashBuilder;

import java.io.File;

@Ignore
public class AcceptedStashBuilder {

	private File home;

	@Before
	public void setUp() throws Exception {
		home = new File("test-res/stashbuilder/");
	}

	@Test
	public void should_create_stash() throws Exception {
		final File tara = new File("/Users/oroncal/workspace/tara/ide/legio/legio-core/test/legio/Example.tara");
		StashBuilder builder = new StashBuilder(home, tara, "Legio", "legio");
		builder.build();
	}
}
