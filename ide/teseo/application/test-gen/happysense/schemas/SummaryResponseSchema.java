package happysense.schemas;

public class SummaryResponseSchema {

    private Today today;
    private Total total;

    public Today today() {
        return this.today;
    }

    public Total total() {
        return this.total;
    }

    public SummaryResponseSchema today(Today today) {
        this.today = today;
        return this;
    }

    public SummaryResponseSchema total(Total total) {
        this.total = total;
        return this;
    }

}