package tara.compiler;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import org.junit.Before;
import org.junit.Test;
import tara.Entry;
import tara.Stash;
import tara.builder.StashBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AcceptedStashBuilder {

	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("test.res").getAbsolutePath();
	}

	@Test
	public void should_build_empty_stash() {
		new StashBuilder(new File(home)).build("Empty", Charset.forName("UTF-8"));
		assertThat("Empty.stash exists", new File(home, "Empty.stash").exists());
		assertThat(stashFrom(new File(home, "Empty.stash")).entries.length, is(0));
	}

	@Test
	public void should_build_stash_with_roots() {
		new StashBuilder(new File(home)).build("Months", Charset.forName("UTF-8"));
		assertThat("Months.stash exists", new File(home, "Months.stash").exists());
		assertThat(stashFrom(new File(home, "Months.stash")).entries.length, is(12));
	}

	@Test
	public void should_build_stash_with_entry_and_blobs() {
		new StashBuilder(new File(home)).build("World", Charset.forName("UTF-8"));
		final File stashFile = new File(home, "World.stash");
		assertThat("World.stash exists", stashFile.exists());
		final Stash stash = stashFrom(stashFile);
		assertThat(stash.entries.length, is(2));
		assertThat("Asia has 1 component", stash.entries[0].entries.length, is(1));
		assertThat("Asia has City component", stash.entries[0].entries[0].types[0], is("City"));
		assertThat("Asia has City component named Tokyo", stash.entries[0].entries[0].name, is("Tokyo"));
		assertThat("Blob variable has right value", stash.entries[0].entries[0].vars[1].v, is("%World$1"));
	}

	@Test
	public void should_transform_instant_to_date() {
		new StashBuilder(new File(home)).build("Data", Charset.forName("UTF-8"));
		assertThat("Data.stash exists", new File(home, "Temperature.stash").exists());
		Stash stash = stashFrom(new File(home, "Data.stash"));
		assertThat(stash.entries.length, is(1));
		assertThat(stash.entries[0].vars.length, is(4));
		assertThat(stash.entries[0].vars[0].v, instanceOf(long.class));
	}

	@Test
	public void should_build_stash_with_facets() {
		new StashBuilder(new File(home)).build("Temperature", Charset.forName("UTF-8"));
		assertThat("Temperature.stash exists", new File(home, "Temperature.stash").exists());
		assertThat(stashFrom(new File(home, "Temperature.stash")).entries.length, is(9));
	}

	@Test
	public void should_build_stash_with_passes() {
		new StashBuilder(new File(home)).build("Weather", Charset.forName("UTF-8"));
		assertThat("Weather.stash exists", new File(home, "Weather.stash").exists());
		final Stash root = stashFrom(new File(home, "Weather.stash"));
		assertThat(root.entries.length, is(24));
		for (Entry component : root.entries)
			assertThat("Root is Temperature", Arrays.asList(component.types).contains("Temperature"));
		assertThat("Temperature has city variable", root.entries[0].vars[0].n, is("city"));
		assertThat("Temperature has month variable", root.entries[0].vars[1].n, is("month"));
		assertThat("Temperature has temperature variable", root.entries[0].vars[2].n, is("temperature"));
		assertThat("temperature variable has right value", root.entries[0].vars[2].v, is(7.0));
		assertThat("Root has not entry", root.entries[1].entries.length, is(0));
		assertThat("city variable of 1º element has correct reference", root.entries[0].vars[0].v, is("!World.tara#Asia.Tokyo"));
		assertThat("city variable of 15º element has correct reference", root.entries[15].vars[0].v, is("!World.tara#Europe.London"));
		assertThat(root.entries[1].entries.length, is(0));

	}

	private Stash stashFrom(File file) {
		return stashFrom(bytesFrom(file));
	}

	private byte[] bytesFrom(File file) {
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			return new byte[0];
		}
	}


	private static Stash stashFrom(byte[] bytes) {
		Stash result;
		try (ByteArrayInputStream bs = new ByteArrayInputStream(bytes); Input input = new Input(bs)) {
			result = ((Stash) new Kryo().readClassAndObject(input));
		} catch (IOException | KryoException e) {
			result = new Stash();
		}
		return result;
	}

}
