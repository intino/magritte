package io.intino.tara.lang.model.rules.variable;

import java.time.LocalTime;
import java.util.List;

public class TimeRule implements VariableRule<List<String>> {

	@Override
	public boolean accept(List<String> values, String metric) {
		return accept(values);
	}

	@Override
	public boolean accept(List<String> values) {
		for (String time : values) {
			if (time.isEmpty()) return false;
			try {
				LocalTime.parse(time);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}


	@Override
	public String errorMessage() {
		return "Time must match ISO pattern";
	}
}
