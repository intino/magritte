package tara.compiler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tara.StashBuilder;
import tara.io.Case;
import tara.io.Stash;
import tara.io.StashDeserializer;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
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
		assertThat(stash.cases.size(), is(12));
	}

	@Test
	public void should_build_stash_with_entry_and_blobs() throws Exception {
		new StashBuilder(home).build(new File(home, "World.tara").getAbsolutePath(), verbose);
		final File stashFile = new File(home, "World.stash");
		assertThat("World.stash exists", stashFile.exists());
		final Stash stash = stashFrom(stashFile);
		assertThat(stash.cases.size(), is(2));
		assertThat("Asia has 1 component", stash.cases.get(0).cases.size(), is(1));
		assertThat("Asia has City component", stash.cases.get(0).cases.get(0).types.get(0), is("City"));
		assertThat("Asia has City component named Tokyo", stash.cases.get(0).cases.get(0).name, is("World#Asia$Tokyo"));
		assertThat("Blob variable has right value", ((List) stash.cases.get(0).cases.get(0).variables.get(1).v).get(0), is("%World$1"));
	}

	@Test
	public void should_transform_instant_to_date() throws Exception {
		new StashBuilder(home).build(new File(home, "Instant.tara").getAbsolutePath(), verbose);
		assertThat("Data.stash exists", new File(home, "Instant.stash").exists());
		Stash stash = stashFrom(new File(home, "Instant.stash"));
		assertThat(stash.cases.size(), is(1));
		assertThat(stash.cases.get(0).variables.size(), is(4));
		assertThat(stash.cases.get(0).variables.get(0).v, instanceOf(long.class));
	}

	@Test
	public void should_build_stash_with_facets() throws Exception {
		new StashBuilder(home).build(new File(home, "Temperature.tara").getAbsolutePath(), verbose);
		assertThat("Temperature.stash exists", new File(home, "Temperature.stash").exists());
		assertThat(stashFrom(new File(home, "Temperature.stash")).cases.size(), is(9));
	}

	@Test
	public void should_build_stash_with_many_entries() throws Exception {
		new StashBuilder(home).build(new File(home, "ManyEntries.tara").getAbsolutePath(), verbose);
		assertThat("ManyEntries.stash exists", new File(home, "ManyEntries.stash").exists());
		final Stash stash = stashFrom(new File(home, "ManyEntries.stash"));
		assertThat(stash.cases.size(), is(1380));
		assertThat(stash.cases.get(10).variables.size(), is(7));
		assertThat(stash.cases.get(10).variables.get(0).n, is("instant"));
		assertThat(((List) stash.cases.get(10).variables.get(0).v).get(0), is("31/10/2011 06:01"));
		assertThat(((List) stash.cases.get(10).variables.get(5).v).get(0), is(23.0));
		assertThat(((List) stash.cases.get(1000).variables.get(5).v).get(0), is(32.9));
	}

	@Test
	public void should_build_stash_with_passes() throws Exception {
		new StashBuilder(home).build(new File(home, "Weather.tara").getAbsolutePath(), verbose);
		assertThat("Weather.stash exists", new File(home, "Weather.stash").exists());
		final Stash stash = stashFrom(new File(home, "Weather.stash"));
		assertThat(stash.cases.size(), is(24));
		for (Case component : stash.cases)
			assertThat("Root is Temperature", component.types.contains("Temperature"));
		assertThat("Temperature has city variable", stash.cases.get(0).variables.get(0).n, is("city"));
		assertThat("Temperature has month variable", stash.cases.get(0).variables.get(1).n, is("month"));
		assertThat("Temperature has temperature variable", stash.cases.get(0).variables.get(2).n, is("temperature"));
		assertThat("temperature variable has right value", ((List) stash.cases.get(0).variables.get(2).v).get(0), is(7.0));
		assertThat("Temperature Root Node has not cases", stash.cases.get(1).cases, is(Collections.<Case>emptyList()));
		assertThat("city variable of 1º element has correct reference", ((List) stash.cases.get(0).variables.get(0).v).get(0), is("World#Asia.Tokyo"));
		assertThat("city variable of 15º element has correct reference", ((List) stash.cases.get(15).variables.get(0).v).get(0), is("World#Europe.London"));
	}

	@Test
	public void testReadOldStash() throws Exception {
		final Stash stash = StashDeserializer.stashFrom(this.getClass().getResourceAsStream("/stashes/Main.stash"));
		System.out.println(stash.language);
	}
}
