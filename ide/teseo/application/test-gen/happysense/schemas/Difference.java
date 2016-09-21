package happysense.schemas;

public class Difference {

    private double satisfied = 0.0;
    private double notVerySatisfied = 0.0;
    private double dissatisfied = 0.0;

    public double satisfied() {
        return this.satisfied;
    }

    public double notVerySatisfied() {
        return this.notVerySatisfied;
    }

    public double dissatisfied() {
        return this.dissatisfied;
    }

    public Difference satisfied(double satisfied) {
        this.satisfied = satisfied;
        return this;
    }

    public Difference notVerySatisfied(double notVerySatisfied) {
        this.notVerySatisfied = notVerySatisfied;
        return this;
    }

    public Difference dissatisfied(double dissatisfied) {
        this.dissatisfied = dissatisfied;
        return this;
    }

}