package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.Date;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.MagritteException;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class DateLoader {

    public static List<Date> load(List<?> dates, Layer layer) {
        return dates.stream().map((date) -> processDate((String) date, layer)).collect(Collectors.toList());
    }

    private static Date processDate(String date, Layer layer) {
        if (date.isEmpty()) return null;
        Object dateObject = ListProcessor.process((Object) date, layer);
        return dateObject instanceof Date ? (Date) dateObject : parseDate(date);
    }

    private static Date parseDate(String date) {
        try {
            return Date.parse(date);
        } catch (Exception e) {
            throw new MagritteException(e.getMessage());
        }
    }

}
