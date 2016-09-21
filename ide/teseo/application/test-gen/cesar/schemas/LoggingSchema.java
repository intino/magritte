package cesar.schemas;

public class LoggingSchema {

    private java.time.LocalDateTime instant;
    private double memory = 0.0;
    private double cpu = 0.0;
    private Integer connections = 0;

    public java.time.LocalDateTime instant() {
        return this.instant;
    }

    public double memory() {
        return this.memory;
    }

    public double cpu() {
        return this.cpu;
    }

    public Integer connections() {
        return this.connections;
    }

    public LoggingSchema instant(java.time.LocalDateTime instant) {
        this.instant = instant;
        return this;
    }

    public LoggingSchema memory(double memory) {
        this.memory = memory;
        return this;
    }

    public LoggingSchema cpu(double cpu) {
        this.cpu = cpu;
        return this;
    }

    public LoggingSchema connections(Integer connections) {
        this.connections = connections;
        return this;
    }

}