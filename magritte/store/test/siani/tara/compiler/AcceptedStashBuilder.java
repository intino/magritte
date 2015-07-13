package siani.tara.compiler;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import org.junit.Before;
import org.junit.Test;
import siani.tara.builder.StashBuilder;
import siani.tara.compiler.model.impl.Stash;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AcceptedStashBuilder {

	private String home;

	@Before
	public void setUp() throws Exception {
		home = new File("./test.res").getAbsolutePath();
	}

	@Test
	public void should_build_empty_stash() {
		new StashBuilder(new File(home + "Empty.tara"), Charset.forName("UTF-8")).build();
		assertThat("Empty.stash exists", new File(home + "Empty.stash").exists());
		assertThat(stashFrom(new File(home + "Empty.stash")).roots.length, is(0));
	}

	@Test
	public void should_build_stash_with_roots() {
		new StashBuilder(new File(home + "Months.tara"), Charset.forName("UTF-8")).build();
		assertThat("Months.stash exists", new File(home + "Months.stash").exists());
		assertThat(stashFrom(new File(home + "Months.stash")).roots.length, is(12));
	}

	@Test
	public void should_build_stash_with_components_and_blobs() {
		new StashBuilder(new File(home + "World.tara"), Charset.forName("UTF-8")).build();
		assertThat("World.stash exists", new File(home + "World.stash").exists());
		assertThat(stashFrom(new File(home + "World.stash")).roots.length, is(2));
	}

	@Test
	public void should_build_stash_with_facets() {
		new StashBuilder(new File(home + "Temperature.tara"), Charset.forName("UTF-8")).build();
		assertThat("Temperature.stash exists", new File(home + "Temperature.stash").exists());
		assertThat(stashFrom(new File(home + "Temperature.stash")).roots.length, is(9));
	}

	@Test
	public void should_build_stash_with_passes() {
		new StashBuilder(new File(home + "Weather.tara"), Charset.forName("UTF-8")).build();
		assertThat("Weather.stash exists", new File(home + "Weather.stash").exists());
		assertThat(stashFrom(new File(home + "Weather.stash")).roots.length, is(24));

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
