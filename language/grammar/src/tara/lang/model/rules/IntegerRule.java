package tara.lang.model.rules;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntegerRule implements tara.lang.model.Rule<Integer> {
	private static final String REJECT_NUMBER_PARAMETER_NOT_IN_RANGE = "reject.number.parameter.not.in.range";
	private static final String REJECT_NUMBER_PARAMETER_WITH_ERRONEOUS_METRIC = "reject.number.parameter.with.erroneous.metric";
	private static final String REJECT_NUMBER_PARAMETER_WITH_METRIC = "reject.number.parameter.with.metric";
	private int min = Integer.MIN_VALUE;
	private int max = Integer.MAX_VALUE;
	private String metric = "";
	private String message = "";


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
		final boolean check = !(value < min) && !(value > max);
		if (!check) message = REJECT_NUMBER_PARAMETER_NOT_IN_RANGE;
		return check;
	}

	@Override
	public boolean accept(Integer value, String metric) {
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
