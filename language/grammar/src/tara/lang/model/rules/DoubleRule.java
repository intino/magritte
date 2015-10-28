package tara.lang.model.rules;

import tara.lang.model.Rule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DoubleRule implements Rule<Double> {

	private static final String REJECT_NUMBER_PARAMETER_NOT_IN_RANGE = "reject.number.parameter.not.in.range";
	private static final String REJECT_NUMBER_PARAMETER_WITH_ERRONEOUS_METRIC = "reject.number.parameter.with.erroneous.metric";
	private static final String REJECT_NUMBER_PARAMETER_WITH_METRIC = "reject.number.parameter.with.metric";
	private double min = Double.NEGATIVE_INFINITY;
	private double max = Double.POSITIVE_INFINITY;
	private String metric = "";
	private String message = "";

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
		final boolean check = !(value < min) && !(value > max);
		if (!check) message = REJECT_NUMBER_PARAMETER_NOT_IN_RANGE;
		return check;
	}

	@Override
	public boolean accept(Double value, String metric) {
		final boolean check = accept(value) && this.metric.equals(metric);
		if (!this.metric.equals(metric))
			message = this.metric.isEmpty() ?
				REJECT_NUMBER_PARAMETER_WITH_METRIC :
				REJECT_NUMBER_PARAMETER_WITH_ERRONEOUS_METRIC;
		return check;
	}

	@Override
	public List<String> errorParameters() {
		return errorMessage().equals(REJECT_NUMBER_PARAMETER_WITH_ERRONEOUS_METRIC) ?
			Collections.singletonList(metric)
			: Arrays.asList(min + "", max + "", metric);
	}

	@Override
	public String errorMessage() {
		return message;
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
