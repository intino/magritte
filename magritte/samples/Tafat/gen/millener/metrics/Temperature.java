package millener.metrics;

import magritte.Metric;

public enum Temperature implements Metric {
    Celsius, Farenheit, Kelvin;

    @Override
    public double value(double value) {
        return converters[this.ordinal()].convert(value);
    }

    @Override
    public String toString() {
        return "°" + super.toString().charAt(0);
    }

    private static final Converter[] converters = { celsius(), farenheit(), kelvin() };

    private static Converter celsius() {
        return new Converter() {
            @Override
            public double convert(double value) {
                return value;
            }
        };
    }

    private static Converter farenheit() {
        return new Converter() {
            @Override
            public double convert(double value) {
                return (value  -  32)  *  5 / 9;
            }
        };
    }

    private static Converter kelvin() {
        return new Converter() {
            @Override
            public double convert(double value) {
                return value - 273;
            }
        };
    }
}
