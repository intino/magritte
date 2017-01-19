package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.types.DateX;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.MagritteException;

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
