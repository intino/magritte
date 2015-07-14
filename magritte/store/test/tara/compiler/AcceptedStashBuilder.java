package tara.compiler;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import org.junit.Before;
import org.junit.Test;
import tara.builder.StashBuilder;
import tara.compiler.model.impl.Stash;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;

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
		assertThat(stashFrom(new File(home, "Empty.stash")).components.length, is(0));
	}

	@Test
	public void should_build_stash_with_roots() {
		new StashBuilder(new File(home)).build("Months", Charset.forName("UTF-8"));
		assertThat("Months.stash exists", new File(home, "Months.stash").exists());
		assertThat(stashFrom(new File(home, "Months.stash")).components.length, is(12));
	}

	@Test
	public void should_build_stash_with_components_and_blobs() {
		new StashBuilder(new File(home)).build("World", Charset.forName("UTF-8"));
		final File stashFile = new File(home, "World.stash");
		assertThat("World.stash exists", stashFile.exists());
		final Stash stash = stashFrom(stashFile);
		assertThat(stash.components.length, is(2));
		assertThat("Asia has 1 component", stash.components[0].components.length, is(1));
		assertThat("Asia has City component", stash.components[0].components[0].types[0], is("City"));
		assertThat("Asia has City component named Tokyo", stash.components[0].components[0].name, is("Tokyo"));
		assertThat("Blob variable has size 1", stash.components[0].components[0].variables[1].values.length, is(1));
		assertThat("Blob variable has right value", stash.components[0].components[0].variables[1].values[0], is("%World$1"));
	}

	@Test
	public void should_build_stash_with_facets() {
		new StashBuilder(new File(home)).build("Temperature", Charset.forName("UTF-8"));
		assertThat("Temperature.stash exists", new File(home, "Temperature.stash").exists());
		assertThat(stashFrom(new File(home, "Temperature.stash")).components.length, is(9));
	}

	@Test
	public void should_build_stash_with_passes() {
		new StashBuilder(new File(home)).build("Weather", Charset.forName("UTF-8"));
		assertThat("Weather.stash exists", new File(home, "Weather.stash").exists());
		final Stash root = stashFrom(new File(home, "Weather.stash"));
		assertThat(root.components.length, is(24));
		for (Stash component : root.components)
			assertThat("Root is Temperature", Arrays.asList(component.types).contains("Temperature"));
		assertThat("Temperature has city variable", root.components[0].variables[0].name, is("city"));
		assertThat("Temperature has month variable", root.components[0].variables[1].name, is("month"));
		assertThat("Temperature has temperature variable", root.components[0].variables[2].name, is("temperature"));
		assertThat("temperature variable has right value", Arrays.equals(root.components[0].variables[2].values, new Double[]{7.0}));
		assertThat("Root has not components", root.components[1].components.length, is(0));
		assertThat("city variable of 1º element has correct size", root.components[0].variables[0].values.length, is(1));
		assertThat("city variable of 1º element has correct reference", root.components[0].variables[0].values[0], is("!World.tara#Asia.Tokyo"));
		assertThat("city variable of 15º element has correct reference", root.components[15].variables[0].values[0], is("!World.tara#Europe.London"));
		assertThat(root.components[1].components.length, is(0));

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
