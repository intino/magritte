package tara.magritte.loaders;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@SuppressWarnings("unused")
public class TimeLoader {

    private static final DateTimeFormatter[] dateFormats = new DateTimeFormatter[8];

    static {
        String[] patterns = {"HH:mm:ss", "HH:mm", "HH"};
        asList(patterns).forEach(p -> dateFormats[p.length()] = DateTimeFormatter.ofPattern(p));
    }

    public static List<LocalTime> load(List<?> times) {
        return StringLoader.load(times).stream().map(TimeLoader::parseTime).collect(Collectors.toList());
    }

    private static LocalTime parseTime(String time) {
        if (time.isEmpty()) return null;
        if (time.length() < dateFormats.length && dateFormats[time.length()] != null)
            return LocalTime.from(dateFormats[time.length()].parse(time));
        throw new RuntimeException("Time couldn't be parsed: " + time);
    }

}
