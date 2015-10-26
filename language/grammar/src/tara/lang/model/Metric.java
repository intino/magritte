package tara.lang.model;


import java.util.List;

public interface Metric<T> extends Rule<T> {
    T value(T value);

    default boolean accept(T value) {
        return true;
    }

    List<String> units();

    interface Converter<T> {
        double convert(T value);
    }

}
