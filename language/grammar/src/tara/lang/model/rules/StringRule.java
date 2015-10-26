package tara.lang.model.rules;

import tara.lang.model.Rule;

public class StringRule implements Rule<String> {

	private final String regex;

	public StringRule(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean accept(String value) {
		return value.matches(regex);
	}
}
