package io.intino.compiler;

import io.intino.tara.io.Concept;
import io.intino.tara.io.Stash;
import io.intino.tara.io.StashDeserializer;
import org.junit.Test;

public class StashReader {

	@Test
	public void name() throws Exception {
		final Stash stash = StashDeserializer.stashFrom(this.getClass().getResourceAsStream("/Legio.stash"));
		for (Concept concept : stash.concepts) {
			System.out.println(concept.className);
		}
	}
}
