package tara.lang.model.rules.custom;


import tara.lang.model.Metric;

import java.util.List;

import static java.util.Collections.singletonList;

public enum Percentage implements Metric<Double> {

	PERCENTAGE;

	@Override
	public Double value(Double value) {
		return value;
	}


	@Override
	public List<String> units() {
		return singletonList("%");
	}

}
