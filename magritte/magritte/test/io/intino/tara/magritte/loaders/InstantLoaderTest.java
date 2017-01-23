package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.layers.MockLayer;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstantLoaderTest {

    @Test
    public void load() throws Exception {
        List<Instant> list = InstantLoader.load(asList(Instant.parse("2013-01-12T22:22:22Z").toEpochMilli(), "$@io.intino.tara.magritte.natives.CodedInstant"), new MockLayer(null));
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is(LocalDateTime.of(2013, 1, 12, 22, 22, 22).toInstant(UTC)));
        assertThat(list.get(1), is(LocalDateTime.of(1987, 9, 17, 4, 0).toInstant(UTC)));
    }
}