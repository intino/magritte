package io.intino.magritte.lang.model.rules.custom;


import io.intino.magritte.lang.model.rules.variable.VariableRule;

import java.util.List;

public class Url implements VariableRule<List<String>> {

	private String REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";



	@Override
	public boolean accept(List<String> values) {
		for (String value : values)
			if (!value.isEmpty() && !value.matches(REGEX)) return false;
		return true;
	}

	@Override
	public String errorMessage() {
		return "URL does not have valid pattern";
	}
}
