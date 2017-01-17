package io.intino.tara.lang.model.rules.variable;

import java.time.Instant;

public class InstantRule implements VariableRule<String> {

	@Override
	public boolean accept(String instant) {
		if (instant.isEmpty()) return false;
		try {
			return Instant.parse(instant) != null;
		} catch (Exception e) {
			return false;
		}
	}
}
