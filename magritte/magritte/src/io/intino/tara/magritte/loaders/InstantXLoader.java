package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.MagritteException;
import io.intino.tara.magritte.types.InstantX;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class InstantXLoader {

	public static List<InstantX> load(List<?> instants, Layer layer) {
		return instants.stream().map((date) -> processInstant((String) date, layer)).collect(Collectors.toList());
	}

	private static InstantX processInstant(String instant, Layer layer) {
		if (instant.isEmpty()) return null;
		Object dateObject = ListProcessor.process((Object) instant, layer);
		return dateObject instanceof InstantX ? (InstantX) dateObject : parseInstant(instant);
	}

	private static InstantX parseInstant(String instant) {
        try {
            return InstantX.parse(instant);
        } catch (Exception e) {
            throw new MagritteException(e.getMessage());
        }
    }

}
