package tara.compiler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tara.StashBuilder;
import tara.io.Stash;
import tara.io.StashDeserializer;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tara.io.StashDeserializer.stashFrom;

public class AcceptedStashBuilder {

	private String home;
	private static boolean verbose = true;

	@Before
	public void setUp() throws Exception {
		home = new File("test-res/stashes/").getAbsolutePath();
	}

	@After
	public void deleteStashes() throws Exception {
//		for (File file : new File(home).listFiles((dir, name) -> name.endsWith(".stash") || name.endsWith(".level"))) file.delete();
	}

	@Test
	public void should_build_CompleteModel() throws Exception {
		final File root = new File(home, "Model");
		new StashBuilder(root.getAbsolutePath()).buildAll(true);
	}

	@Test
	public void should_read_stash() throws Exception {
		final File root = new File("/Main.stash");
		final Stash stash = stashFrom(root);
		System.out.println("");
	}

	@Test
	public void should_build_Fact() throws Exception {
		final File root = new File(home, "Model");
		new StashBuilder(root.getAbsolutePath()).build(new File(root, "Karlsruhe/2011/001/00.tara").getAbsolutePath(), verbose);
		final File stashFile = new File(root, "Karlsruhe/2011/001/00.stash");
		assertThat("00.stash not exists", stashFile.exists());
		final Stash stash = stashFrom(stashFile);
		assertThat(stash.language, is("Dwellings"));
	}

	@Test
	public void should_build_Scene() throws Exception {
		final File root = new File(home, "Model");
		new StashBuilder(root.getAbsolutePath()).build(new File(root, "Karlsruhe/Scene.tara").getAbsolutePath(), verbose);
		final File stashFile = new File(root, "Karlsruhe/Scene.stash");
		assertThat("Scene.stash not exists", stashFile.exists());
		final Stash stash = stashFrom(stashFile);
		assertThat(stash.language, is("Dwellings"));
	}

	@Test
	public void should_build_empty_stash() throws Exception {
		new StashBuilder(home).build(new File(home, "Empty.tara").getAbsolutePath(), verbose);
		assertThat("Empty.stash not exists", !new File(home, "Empty.stash").exists());
	}

	@Test
	public void should_build_stash_with_roots() throws Exception {
		new StashBuilder(home).build(new File(home, "Months.tara").getAbsolutePath(), verbose);
		assertThat("Months.stash exists", new File(home, "Months.stash").exists());
		final Stash stash = stashFrom(new File(home, "Months.stash"));
		assertThat(stash.language, is("Months"));
		assertThat(stash.instances.size(), is(12));
	}

	@Test
	public void testReadOldStash() throws Exception {
		final Stash stash = StashDeserializer.stashFrom(this.getClass().getResourceAsStream("/stashes/Main.stash"));
		System.out.println(stash.language);
	}
}
