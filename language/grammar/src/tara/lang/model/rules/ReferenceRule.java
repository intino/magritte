package tara.lang.model.rules;

import tara.lang.model.Rule;

import java.util.Arrays;
import java.util.List;

public class ReferenceRule implements Rule<String> {
	private List<String> allowedReferences;

	public ReferenceRule(String... allowedReferences) {
		this.allowedReferences = Arrays.asList(allowedReferences);
	}

	@Override
	public boolean accept(String value) {
		return allowedReferences.contains(value);
	}

	public List<String> getAllowedReferences() {
		return allowedReferences;
	}

	public void setAllowedReferences(List<String> allowedReferences) {
		this.allowedReferences = allowedReferences;
	}

	@Override
	public String toString() {
		return String.join(", ", allowedReferences);
	}
}
