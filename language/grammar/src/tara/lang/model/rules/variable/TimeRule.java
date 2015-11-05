package tara.lang.model.rules.variable;

import tara.lang.model.Rule;

public class TimeRule implements Rule<String> {

	private String regex = "";

	@Override
	public boolean accept(String value) {
		return false;
	}
}
