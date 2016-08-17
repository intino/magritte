package tara.magritte.loaders;

import tara.magritte.Layer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@SuppressWarnings("unused")
public class DateLoader {

    private static final DateTimeFormatter[] dateFormats = new DateTimeFormatter[20];

    static {
        String[] patterns = {"dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "dd/MM/yyyy HH", "dd/MM/yyyy", "MM/yyyy", "yyyy"};
        asList(patterns).forEach(p -> dateFormats[p.length()] = DateTimeFormatter.ofPattern(p));
    }

    public static List<LocalDateTime> load(List<?> dates, Layer layer) {
        return dates.stream().map((date) -> processDate((String) date, layer)).collect(Collectors.toList());
    }

    private static LocalDateTime processDate(String date, Layer layer) {
        if (date.isEmpty()) return null;
        Object dateObject = ListProcessor.process(date, layer);
        return dateObject instanceof LocalDateTime ? (LocalDateTime) dateObject : parseDate(date);
    }

    private static LocalDateTime parseDate(String date) {
        date = addTime(date);
        if (date.length() < dateFormats.length && dateFormats[date.length()] != null)
            return LocalDateTime.from(dateFormats[date.length()].parse(date));
        throw new RuntimeException("Date couldn't be parsed: " + date);
    }

    private static String addTime(String date) {
        return date.length() == 10 ? date + " 00" :
                date.length() == 7 ? "01/" + date + " 00" :
                        date.length() == 4 ? "01/01/" + date + " 00" : date;
    }

}