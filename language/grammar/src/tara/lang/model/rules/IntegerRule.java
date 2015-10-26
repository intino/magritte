package tara.lang.model.rules;

public class IntegerRule implements tara.lang.model.Rule<Integer> {
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;
    private String metric = "";

    public IntegerRule(String metric) {
        this.metric = metric;
    }

    public IntegerRule(int min, int max) {
        this(min, max, "");
    }

    public IntegerRule(int min, int max, String metric) {
        this.min = min;
        this.max = max;
        this.metric = metric;
    }

    @Override
    public boolean accept(Integer value) {
        return !(value < min) && !(value > max);
    }

    @Override
    public boolean accept(Integer value, String metric) {
        return (accept(value) && this.metric.equals(metric));
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getMetric() {
        return metric;
    }
}
