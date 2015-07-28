package tara.compiler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tara.builder.StashBuilder;
import tara.io.Case;
import tara.io.Stash;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static tara.io.StashDeserializer.stashFrom;

public class AcceptedStashBuilder {

	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("test.res").getAbsolutePath();
	}

	@After
	public void deleteStashes() throws Exception {
		for (File file : new File(home).listFiles((dir, name) -> name.endsWith(".stash"))) file.delete();
	}

	@Test
	public void should_build_empty_stash() {
		new StashBuilder(new File(home)).build("Empty", Charset.forName("UTF-8"));
		assertThat("Empty.stash exists", new File(home, "Empty.stash").exists());
		assertThat(stashFrom(new File(home, "Empty.stash")).cases.size(), is(0));
	}

	@Test
	public void should_build_stash_with_roots() {
		new StashBuilder(new File(home)).build("Months", Charset.forName("UTF-8"));
		assertThat("Months.stash exists", new File(home, "Months.stash").exists());
		final Stash stash = stashFrom(new File(home, "Months.stash"));
		assertThat(stash.language, is("Months"));
		assertThat(stash.cases.size(), is(12));
	}

	@Test
	public void should_build_stash_with_entry_and_blobs() {
		new StashBuilder(new File(home)).build("World", Charset.forName("UTF-8"));
		final File stashFile = new File(home, "World.stash");
		assertThat("World.stash exists", stashFile.exists());
		final Stash stash = stashFrom(stashFile);
		assertThat(stash.cases.size(), is(2));
		assertThat("Asia has 1 component", stash.cases.get(0).cases.size(), is(1));
		assertThat("Asia has City component", stash.cases.get(0).cases.get(0).types[0], is("City"));
		assertThat("Asia has City component named Tokyo", stash.cases.get(0).cases.get(0).name, is("Tokyo"));
		assertThat("Blob variable has right value", stash.cases.get(0).cases.get(0).variables[1].v, is("%World$1"));
	}

	@Test
	public void should_transform_instant_to_date() {
		new StashBuilder(new File(home)).build("Instant", Charset.forName("UTF-8"));
		assertThat("Data.stash exists", new File(home, "Instant.stash").exists());
		Stash stash = stashFrom(new File(home, "Instant.stash"));
		assertThat(stash.cases.size(), is(1));
		assertThat(stash.cases.get(0).variables.length, is(4));
		assertThat(stash.cases.get(0).variables[0].v, instanceOf(long.class));
	}

	@Test
	public void should_build_stash_with_facets() {
		new StashBuilder(new File(home)).build("Temperature", Charset.forName("UTF-8"));
		assertThat("Temperature.stash exists", new File(home, "Temperature.stash").exists());
		assertThat(stashFrom(new File(home, "Temperature.stash")).cases.size(), is(9));
	}

	@Test
	public void should_build_stash_with_many_entries() {
		new StashBuilder(new File(home)).build("ManyEntries", Charset.forName("UTF-8"));
		assertThat("ManyEntries.stash exists", new File(home, "ManyEntries.stash").exists());
		final Stash stash = stashFrom(new File(home, "ManyEntries.stash"));
		assertThat(stash.cases.size(), is(1380));
		assertThat(stash.cases.get(10).variables.length, is(7));
		assertThat(stash.cases.get(10).variables[0].n, is("instant"));
		assertThat(stash.cases.get(10).variables[0].v, is(1320040860000L));
		assertThat(stash.cases.get(10).variables[5].v, is(23.0));
		assertThat(stash.cases.get(1000).variables[5].v, is(32.9));
	}

	@Test
	public void should_build_stash_with_passes() {
		new StashBuilder(new File(home)).build("Weather", Charset.forName("UTF-8"));
		assertThat("Weather.stash exists", new File(home, "Weather.stash").exists());
		final Stash stash = stashFrom(new File(home, "Weather.stash"));
		assertThat(stash.cases.size(), is(24));
		for (Case component : stash.cases)
			assertThat("Root is Temperature", Arrays.asList(component.types).contains("Temperature"));
		assertThat("Temperature has city variable", stash.cases.get(0).variables[0].n, is("city"));
		assertThat("Temperature has month variable", stash.cases.get(0).variables[1].n, is("month"));
		assertThat("Temperature has temperature variable", stash.cases.get(0).variables[2].n, is("temperature"));
		assertThat("temperature variable has right value", stash.cases.get(0).variables[2].v, is(7.0));
		assertThat("Temperature Root Node has not cases", stash.cases.get(1).cases, is(nullValue()));
		assertThat("city variable of 1º element has correct reference", stash.cases.get(0).variables[0].v, is("!World.tara#Asia.Tokyo"));
		assertThat("city variable of 15º element has correct reference", stash.cases.get(15).variables[0].v, is("!World.tara#Europe.London"));
	}


}
