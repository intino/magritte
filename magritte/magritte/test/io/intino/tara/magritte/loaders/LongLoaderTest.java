package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.layers.MockLayer;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class LongLoaderTest {

    @Test
    public void load() throws Exception {
        List<Long> list = LongLoader.load(asList(1L, "$@io.intino.tara.magritte.natives.CodedLong", 47L), new MockLayer(null));
        assertThat(list.size(), is(3));
        assertThat(list.get(0), is(1L));
        assertThat(list.get(1), is(2L));
        assertThat(list.get(2), is(47L));
    }
}