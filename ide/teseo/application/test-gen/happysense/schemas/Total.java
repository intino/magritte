package happysense.schemas;

public class Total {

    private Count count;
    private Difference difference;

    public Count count() {
        return this.count;
    }

    public Difference difference() {
        return this.difference;
    }

    public Total count(Count count) {
        this.count = count;
        return this;
    }

    public Total difference(Difference difference) {
        this.difference = difference;
        return this;
    }

}