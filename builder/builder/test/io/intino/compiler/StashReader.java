package io.intino.compiler;

import io.intino.tara.io.Stash;
import io.intino.tara.io.StashDeserializer;
import io.intino.tara.io.StashSerializer;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

public class StashReader {

	@Test
	public void name() throws Exception {
		final Stash stash = StashDeserializer.stashFrom(this.getClass().getResourceAsStream("/drillwise.stash"));
		stash.nodes.get(0).nodes.get(0).name ="projects/drillwise#drillwise$drillwise:iointinodrillwise-solution100";
		Files.write(new File("drillwise2.stash").toPath(), StashSerializer.serialize(stash));
	}
}
