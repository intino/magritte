package io.intino.magritte.framework.loaders;

import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.MagritteException;
import io.intino.magritte.framework.types.DateX;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class DateXLoader {

	public static List<DateX> load(List<?> dates, Layer layer) {
		return dates.stream().map((date) -> processDate((String) date, layer)).collect(Collectors.toList());
	}

	private static DateX processDate(String date, Layer layer) {
		if (date.isEmpty()) return null;
		Object dateObject = ListProcessor.process((Object) date, layer);
		return dateObject instanceof DateX ? (DateX) dateObject : parseDate(date);
	}

	private static DateX parseDate(String date) {
		try {
			return DateX.parse(date);
		} catch (Exception e) {
			throw new MagritteException(e.getMessage());
		}
	}

}
