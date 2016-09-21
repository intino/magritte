package happysense.schemas;

public class OpinionSnapshotResponseSchema {

    private java.time.LocalDateTime instant;
    private String scale = "";
    private Count count;
    private Difference difference;
    private TimeLine timeLine;

    public java.time.LocalDateTime instant() {
        return this.instant;
    }

    public String scale() {
        return this.scale;
    }

    public Count count() {
        return this.count;
    }

    public Difference difference() {
        return this.difference;
    }

    public TimeLine timeLine() {
        return this.timeLine;
    }

    public OpinionSnapshotResponseSchema instant(java.time.LocalDateTime instant) {
        this.instant = instant;
        return this;
    }

    public OpinionSnapshotResponseSchema scale(String scale) {
        this.scale = scale;
        return this;
    }

    public OpinionSnapshotResponseSchema count(Count count) {
        this.count = count;
        return this;
    }

    public OpinionSnapshotResponseSchema difference(Difference difference) {
        this.difference = difference;
        return this;
    }

    public OpinionSnapshotResponseSchema timeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
        return this;
    }

}