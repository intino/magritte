package tara.lang.model.rules;

import tara.lang.model.Rule;

public class DoubleRule implements Rule<Double> {

	private double min = Double.NEGATIVE_INFINITY;
	private double max = Double.POSITIVE_INFINITY;
	private String metric = "";

	public DoubleRule(Double min, Double max) {
		this(min, max, "");
	}

	public DoubleRule(Double min, Double max, String metric) {
		this.min = min;
		this.max = max;
		this.metric = metric;
	}

	@Override
	public boolean accept(Double value) {
		return !(value < min) && !(value > max);
	}

	@Override
	public boolean accept(Double value, String metric) {
		return (accept(value) && this.metric.equals(metric));
	}


	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public String getMetric() {
		return metric;
	}
}
