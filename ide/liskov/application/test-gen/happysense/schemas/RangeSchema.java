package happysense.schemas;

public class RangeSchema {

    private java.time.LocalDateTime from;
    private java.time.LocalDateTime to;

    public java.time.LocalDateTime from() {
        return this.from;
    }

    public java.time.LocalDateTime to() {
        return this.to;
    }

    public RangeSchema from(java.time.LocalDateTime from) {
        this.from = from;
        return this;
    }

    public RangeSchema to(java.time.LocalDateTime to) {
        this.to = to;
        return this;
    }

}