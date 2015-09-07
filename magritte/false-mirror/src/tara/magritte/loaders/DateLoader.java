package tara.magritte.loaders;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DateLoader {

    private static final String[] DATE_FORMATS = {"dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "dd/MM/yyyy HH", "dd/MM/yyyy", "MM/yyyy", "yyyy", "HH:mm"};
    private static final String[] TIME_FORMATS = {"HH:mm:ss", "HH:mm", "HH"};

    public static LocalDateTime asDate(String date) {
        return parseDate(date);
    }

    public static List<LocalDateTime> asDate(List<String> dates) {
        return dates.stream().map(DateLoader::parseDate).collect(Collectors.toList());
    }

    public static LocalTime asTime(String date) {
        return parseTime(date);
    }

    public static List<LocalTime> asTime(List<String> dates) {
        return dates.stream().map(DateLoader::parseTime).collect(Collectors.toList());
    }

    private static LocalDateTime parseDate(String date) {
        if (date.isEmpty()) return null;
        for (String formatString : DATE_FORMATS) {
            try {
                return LocalDateTime.from(DateTimeFormatter.ofPattern(formatString).parse(date));
            } catch (DateTimeException ignored) {
            }
        }
        throw new RuntimeException("Date couldn't be parsed: " + date);
    }

    private static LocalTime parseTime(String time) {
        if (time.isEmpty()) return null;
        for (String formatString : TIME_FORMATS) {
            try {
                return LocalTime.from(DateTimeFormatter.ofPattern(formatString).parse(time));
            } catch (DateTimeException ignored) {
            }
        }
        throw new RuntimeException("Time couldn't be parsed: " + time);
    }

}
