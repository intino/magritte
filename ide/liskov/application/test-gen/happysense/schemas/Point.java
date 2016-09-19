package happysense.schemas;

public class Point {

    private java.time.LocalDateTime instant;
    private double value = 0.0;

    public java.time.LocalDateTime instant() {
        return this.instant;
    }

    public double value() {
        return this.value;
    }

    public Point instant(java.time.LocalDateTime instant) {
        this.instant = instant;
        return this;
    }

    public Point value(double value) {
        this.value = value;
        return this;
    }

}