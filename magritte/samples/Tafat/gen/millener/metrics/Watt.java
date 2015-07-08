package millener.metrics;

import magritte.Metric;

public enum Watt implements Metric {
    mW, W, KW, MW, GW, TW;

    @Override
    public double value(double value) {
        return converters[this.ordinal()].convert(value);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private static final Converter[] converters = { factor(1e-3), factor(1.0), factor(1e3), factor(1e6), factor(1e9), factor(1e12) };

    private static Converter factor(final double factor) {
        return new Converter() {
            @Override
            public double convert(double value) {
                return value * factor;
            }
        };
    }

}
