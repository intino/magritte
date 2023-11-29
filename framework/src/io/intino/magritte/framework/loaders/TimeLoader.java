package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Layer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static io.intino.magritte.framework.loaders.ListProcessor.process;
import static java.util.Arrays.asList;

@SuppressWarnings("unused")
public class TimeLoader {

	private static final DateTimeFormatter[] dateFormats = new DateTimeFormatter[9];

	static {
		String[] patterns = {"HH:mm:ss", "HH:mm", "HH"};
		asList(patterns).forEach(p -> dateFormats[p.length()] = DateTimeFormatter.ofPattern(p));
	}

	public static List<LocalTime> load(List<?> times, Layer layer) {
		return times.stream().map(item -> processTime((String) item, layer)).collect(Collectors.toList());
	}

	private static LocalTime processTime(String time, Layer layer) {
		if (time.isEmpty()) return null;
		Object timeObject = process((Object) time, layer);
		return timeObject instanceof LocalTime ? (LocalTime) timeObject : parseTime(time);
	}

	private static LocalTime parseTime(String time) {
		if (time.length() < dateFormats.length && dateFormats[time.length()] != null)
			return LocalTime.from(dateFormats[time.length()].parse(time));
		throw new RuntimeException("Time couldn't be parsed: " + time);
	}

}
