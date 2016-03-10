package tara.lang.model.rules.variable;

import tara.lang.model.Rule;

public class DateRule implements Rule<String> {

	@Override
	public boolean accept(String value) {
		return DateLoader.load(value) != null;
	}
}
