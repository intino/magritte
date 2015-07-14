package tara.magritte;

public interface Metric {

    double value(double value);

    interface Converter {
        double convert(double value);
    }
}
