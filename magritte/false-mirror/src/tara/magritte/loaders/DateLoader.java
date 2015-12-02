package tara.magritte.loaders;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class DateLoader {

    static DateTimeFormatter[] dateFormats = new DateTimeFormatter[20];

    static {
        String[] patterns = {"dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "dd/MM/yyyy HH", "dd/MM/yyyy", "MM/yyyy", "yyyy", "HH:mm:ss", "HH:mm", "HH"};
        asList(patterns).forEach(p -> dateFormats[p.length()] = DateTimeFormatter.ofPattern(p));
    }

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
        date = process(date);
        if (date.length() < dateFormats.length && dateFormats[date.length()] != null)
            return LocalDateTime.from(dateFormats[date.length()].parse(date));
        throw new RuntimeException("Date couldn't be parsed: " + date);
    }

    private static String process(String date) {
        return date.length() == 10 ? date + " 00" :
                date.length() == 7 ? "01/" + date + " 00" :
                date.length() == 4 ? "01/01/" + date + " 00" : date;
    }

    private static LocalTime parseTime(String time) {
        if (time.isEmpty()) return null;
        if (time.length() < dateFormats.length && dateFormats[time.length()] != null)
            return LocalTime.from(dateFormats[time.length()].parse(time));
        throw new RuntimeException("Time couldn't be parsed: " + time);
    }

}
