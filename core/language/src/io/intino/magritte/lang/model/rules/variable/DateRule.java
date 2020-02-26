package io.intino.magritte.lang.model.rules.variable;

public class DateRule implements VariableRule<String> {

	@Override
	public boolean accept(String value) {
		return value.isEmpty() || Date.parse(value) != null;
	}
}
