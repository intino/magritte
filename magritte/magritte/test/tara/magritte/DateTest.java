package tara.magritte;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.temporal.TemporalField;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoField.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class DateTest {

    private final String input;
    private final int[] from;
    private final int[] to;

    public DateTest(String input, int[] from, int[] to) {
        this.input = input;
        this.from = from;
        this.to = to;
    }


    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"2016", data(2016), data(2017)},
                {"2016,2018", data(2016), data(2018)},
                {"2016, 2018", data(2016), data(2018)},
                {"2016-2", data(2016, 2), data(2016, 3)},
                {"2016-02", data(2016, 2), data(2016, 3)},
                {"2016 - 02", data(2016, 2), data(2016, 3)},
                {"2016-0002", data(2016, 2), data(2016, 3)},
                {"2016-02", data(2016, 2), data(2016, 3)},
                {"2016-2,2016-05", data(2016, 2), data(2016, 5)},
                {"2016-02-05", data(2016, 2, 5), data(2016, 2, 6)},
                {"2016-02-05,2016-04", data(2016, 2, 5), data(2016, 4, 1)},
                {"2016-02-05T10:20", data(2016, 2, 5, 10, 20), data(2016, 2, 5, 10, 21)},
                {"2016-02-05-10:20", data(2016, 2, 5, 10, 20), data(2016, 2, 5, 10, 21)},
                {"2016-02-05|10:20", data(2016, 2, 5, 10, 20), data(2016, 2, 5, 10, 21)},
                {"2016-02-05/10:20", data(2016, 2, 5, 10, 20), data(2016, 2, 5, 10, 21)},
                {"2016-02-05 10:20", data(2016, 2, 5, 10, 20), data(2016, 2, 5, 10, 21)},
                {"2016-02-05T10:20:43", data(2016, 2, 5, 10, 20, 43), data(2016, 2, 5, 10, 20, 44)},
                {"2016-02-05-10-20-43", data(2016, 2, 5, 10, 20, 43), data(2016, 2, 5, 10, 20, 44)},
                {"2016-02-05T10:00,2016-04-02T20:34", data(2016, 2, 5, 10, 0), data(2016, 4, 2, 20, 34)},
                {"2016-02-05T10:00:12,2016-04-02T20:34:30", data(2016, 2, 5, 10, 0, 12), data(2016, 4, 2, 20, 34, 30)},
                {"2016-12-20T10:00:00.000Z,2016-12-20T13:10:50.740Z", data(2016, 12, 20, 10, 0, 0, 0), data(2016, 12, 20, 13, 10, 50, 740)},
                {"2016-12-20 10:00:00.000,2016-12-20 13:10:50.740", data(2016, 12, 20, 10, 0, 0, 0), data(2016, 12, 20, 13, 10, 50, 740)},
                {"xxxx", null, null},
                {"2016-13-10", null, null},
                {"2016-10-32", null, null},
                {"2016-11-31", null, null},
                {"2016-12-31 10:67", null, null},
                {"2016-12-31 24:07", null, null},
                {"2016-12-20,2016-11-04", null, null},
                {"2016-12-20,2016-12-20", null, null}
        };
    }

    @Test
    public void should_parse_text() {
        TemporalField[] fields = { YEAR, MONTH_OF_YEAR, DAY_OF_MONTH, HOUR_OF_DAY, MINUTE_OF_HOUR, SECOND_OF_MINUTE, MILLI_OF_SECOND };
        try {
            Date date = Date.parse(input);
            for (int i = 0; i < Math.min(from.length,to.length); i++) {
                String message = "[" + input + " " + fields[i].toString() + "]";
                assertThat(message, date.from().atZone(UTC).get(fields[i]),is(from[i]));
                assertThat(message, date.to().atZone(UTC).get(fields[i]),is(to[i]));
            }
        } catch (Date.ParseException e) {
            assertThat(input, from == null && to == null, is(true));
        }
    }


    private static int[] data(int... data) {
        return data;
    }

}