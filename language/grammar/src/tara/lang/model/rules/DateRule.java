package tara.lang.model.rules;

import tara.lang.model.Rule;

public class DateRule implements Rule<String> {

	private final Size size;
	private String regex = "";

	public DateRule(Size size) {
		this.size = size;
	}

	@Override
	public boolean accept(String value) {
		return true;
	}
}
