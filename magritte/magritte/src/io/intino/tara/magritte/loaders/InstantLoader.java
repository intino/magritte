package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class InstantLoader {

    public static List<Instant> load(List<?> instants, Layer layer) {
        return instants.stream().map(i -> processInstant(i, layer)).collect(toList());
    }

    private static Instant processInstant(Object instant, Layer layer) {
        if (instant == null) return null;
        Object dateObject = ListProcessor.process(instant, layer);
        return dateObject instanceof Instant ? (Instant) dateObject : Instant.ofEpochMilli((long) instant);
    }

}
