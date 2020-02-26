package io.intino.magritte.lang.model.rules.variable;

import java.util.Collections;
import java.util.List;

public class StringRule implements VariableRule<String> {

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
	public List<Object> errorParameters() {
		return Collections.singletonList(regex);
	}
}
