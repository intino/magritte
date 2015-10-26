package tara.lang.model.rules.custom;


import tara.lang.model.Metric;

import java.util.List;

import static java.util.Collections.singletonList;

public class Percentage implements Metric<Double> {

	@Override
	public Double value(Double value) {
		return value;
	}

	@Override
	public List<String> units() {
		return singletonList("%");
	}

}
