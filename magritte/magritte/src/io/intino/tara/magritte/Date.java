package io.intino.tara.magritte;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static java.util.Arrays.*;

public class Date {
    private final Instant start;
    private final Instant end;

    public static Date parse(String text) {
        return Parser.of(text).date();
    }

    public Date(Instant start, Instant end) {
        this.start = start;
        this.end = end;
        checkRange();
    }

    public Instant from() {
        return start;
    }

    public Instant to() {
        return end;
    }

    private void checkRange() {
        if (start.compareTo(end) > 0) throw new RangeException("Invalid range: start is after end");
        if (start.compareTo(end) == 0) throw new RangeException("Invalid range: start is equals to end");
    }

    private static class Parser {
        private final String text;

        public static Parser of(String text) {
            return new Parser(text);
        }

        private Parser(String text) {
            this.text = text;
        }

        public Date date() {
            return isRange() ? parseRange() : parseDate();
        }

        private Date parseRange() {
            String[] split = text.split(",");
            return new Date(parseText(split[0]), parseText(split[1]));
        }

        private Instant parseText(String text) {
            return isInstant(text) ? Instant.parse(text) : instantOf(valuesOf(text));
        }

        private Date parseDate() {
            return parseDate(valuesOf(text));
        }

        private Date parseDate(int[] values) {
            Instant from = instantOf(values);
            Instant to = instantOf(next(values));
            return new Date(from, to);
        }

        private int[] valuesOf(String text) {
            return normalize(split(text));
        }

        private int[] normalize(int[] values) {
            return copyOf(values, 7);
        }

        private int[] next(int[] values) {
            int[] result = copyOf(values,7);
            for (int i = 0; i < values.length; i++) {
                if ((result[i]) != 0) continue;
                result[i-1]++;
                break;
            }
            return result;
        }

        private int[] split(String text) throws ParseException {
            try {
                return stream(text.split("[-T\\|/\\. :]")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
            }
            catch (NumberFormatException e) {
                throw new ParseException("Invalid number " + e.getMessage().toLowerCase());
            }
        }

        private Instant instantOf(int[] v) {
            try {
                return LocalDateTime.of(v[0], noZero(v[1]), noZero(v[2]), v[3], v[4], v[5], v[6] * 1000000).toInstant(UTC);
            }
            catch (DateTimeException e) {
                throw new ParseException(e.getMessage());
            }
        }

        private int noZero(int v) {
            return v == 0 ? 1 : v;
        }

        private boolean isInstant(String text) {
            return text.endsWith("Z") && text.length() == 24;
        }


        private boolean isRange() {
            return text.contains(",");
        }

    }


    public static class ParseException extends RuntimeException {
        public ParseException(String message) {
            super(message);
        }
    }
    public static class RangeException extends RuntimeException {
        public RangeException(String message) {
            super(message);
        }
    }
}
