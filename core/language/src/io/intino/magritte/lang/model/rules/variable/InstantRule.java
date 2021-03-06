package io.intino.magritte.lang.model.rules.variable;

import java.time.Instant;
import java.util.List;

public class InstantRule implements VariableRule<List<String>> {

	@Override
	public boolean accept(List<String> values, String metric) {
		return accept(values);
	}

	@Override
	public boolean accept(List<String> values) {
		for (String instant : values) {
			if (instant.isEmpty()) return true;
			try {
				Instant.parse(instant);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String errorMessage() {
		return "Instant value must have ISO_INSTANT format";
	}
}