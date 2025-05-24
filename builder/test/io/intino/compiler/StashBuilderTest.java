package io.intino.compiler;

import io.intino.magritte.builder.StashBuilder;
import io.intino.magritte.io.model.Stash;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class StashBuilderTest {


	@Test
	public void shouldGenerateStash() {
		List<File> files = List.of(new File("/Users/oroncal/workspace/infrastructure/magritte/builder/test-res/infecar.tara"));
		Stash stash = new StashBuilder(files, "io.quassar:picota:1.0.0", "accessor", System.out).build()[0];
		System.out.println(stash.nodes);
	}
}
