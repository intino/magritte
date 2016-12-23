package tara.magritte.loaders;

import tara.magritte.Layer;
import tara.magritte.MagritteException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class InstantLoader {

	public static List<Instant> load(List<?> instants, Layer layer) {
		return instants.stream().map((date) -> processInstant((String) date, layer)).collect(Collectors.toList());
	}

	private static Instant processInstant(String instant, Layer layer) {
		if (instant.isEmpty()) return null;
		Object dateObject = ListProcessor.process((Object) instant, layer);
		return dateObject instanceof Instant ? (Instant) dateObject : parseInstant(instant);
	}

	private static Instant parseInstant(String instant) {
        try {
            return Instant.parse(instant);
        } catch (Exception e) {
            throw new MagritteException(e.getMessage());
        }
    }

}
