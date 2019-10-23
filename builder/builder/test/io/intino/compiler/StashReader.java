package io.intino.compiler;

import io.intino.tara.io.Stash;
import io.intino.tara.io.StashDeserializer;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

@Ignore
public class StashReader {

	@Test
	public void m3() throws Exception {
		final Stash stash = StashDeserializer.stashFrom(new File("/Users/oroncal/workspace/sandbox/tafat/platform/res/Tafat.stash"));
		stash.nodes.get(0).nodes.get(0).name = "projects/drillwise#drillwise$drillwise:iointinodrillwise-solution100";
	}

	@Test
	public void m2() throws Exception {
		final Stash stash = StashDeserializer.stashFrom(new File("/Users/oroncal/workspace/sandbox/tara-problems/m1/res/m1.stash"));
		stash.nodes.get(0).nodes.get(0).name ="projects/drillwise#drillwise$drillwise:iointinodrillwise-solution100";
	}
}
