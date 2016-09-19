package happysense.schemas;

public class Count {

    private Integer total = 0;
    private Integer satisfied = 0;
    private Integer notVerySatisfied = 0;
    private Integer dissatisfied = 0;

    public Integer total() {
        return this.total;
    }

    public Integer satisfied() {
        return this.satisfied;
    }

    public Integer notVerySatisfied() {
        return this.notVerySatisfied;
    }

    public Integer dissatisfied() {
        return this.dissatisfied;
    }

    public Count total(Integer total) {
        this.total = total;
        return this;
    }

    public Count satisfied(Integer satisfied) {
        this.satisfied = satisfied;
        return this;
    }

    public Count notVerySatisfied(Integer notVerySatisfied) {
        this.notVerySatisfied = notVerySatisfied;
        return this;
    }

    public Count dissatisfied(Integer dissatisfied) {
        this.dissatisfied = dissatisfied;
        return this;
    }

}