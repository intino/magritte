package tara.lang.model.rules.variable;

import tara.lang.model.Rule;

import java.util.Collections;
import java.util.List;

public class StringRule implements Rule<String> {

	private final String regex;

	public StringRule(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean accept(String value) {
		return value.matches(regex);
	}

	@Override
	public String errorMessage() {
		return "reject.parameter.string.value.not.matches.regex";
	}

	@Override
	public List<String> errorParameters() {
		return Collections.singletonList(regex);
	}
}
