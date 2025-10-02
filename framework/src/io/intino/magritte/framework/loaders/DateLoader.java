package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Layer;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("unused")
public class DateLoader {

	public static List<LocalDate> load(List<?> instants, Layer layer) {
		return instants.stream().map(i -> processLocalDate(i, layer)).collect(toList());
	}

	private static LocalDate processLocalDate(Object date, Layer layer) {
		if (date == null) return null;
		Object dateObject = ListProcessor.process(date, layer);
		return dateObject instanceof LocalDate ? (LocalDate) dateObject : LocalDate.ofEpochDay((long) date);
	}

}
