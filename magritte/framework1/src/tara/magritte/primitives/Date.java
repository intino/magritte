package tara.magritte.primitives;

import java.util.Arrays;
import java.util.Calendar;

public class Date {

    public static Date date(long value) {
        return new Date(value);
    }

    public static Date date(int... values) {
        return new Date(values);
    }

    private long timestamp;

    private Date(int... values) {
        this.timestamp = values.length == 0 ? System.currentTimeMillis() : timeInMillis(Arrays.copyOf(values, 6));
    }

    private Date(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timeInMillis(int[] values) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.MILLISECOND, 0);
        instance.set(values[0], values[1]-1, values[2], values[3], values[4], values[5]);
        return instance.getTimeInMillis();
    }

    public long timestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object object) {
        return (this == object) || (object instanceof Date && timestamp == ((Date) object).timestamp);
    }

    @Override
    public String toString() {
        return "%" + timestamp;
    }
}
