package io.intino.tara.lang.model.rules.variable;

public class DateRule implements VariableRule<String> {

	@Override
	public boolean accept(String value) {
		return DateLoader.load(value) != null;
	}
}
