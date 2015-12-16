package tara.compiler;

import org.junit.Assert;
import org.junit.Test;
import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.io.StashSerializer;

import java.io.ByteArrayInputStream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static tara.io.Helper.*;

public class KryoTest {


	@Test
	public void testName() throws Exception {
		final Stash stash = newStash("tela", emptyList(), emptyList(), singletonList(newInstance("jose", singletonList(newFacet("name", singletonList(newBoolean("var", singletonList(true))), emptyList())))));
		final byte[] serialize = StashSerializer.serialize(stash);
		final Stash result = StashDeserializer.stashFrom(new ByteArrayInputStream(serialize));
		Assert.assertEquals("tela", result.language);
		Assert.assertEquals(true, result.instances.get(0).facets.get(0).variables.get(0).values.get(0));
	}
}
