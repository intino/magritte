package tara.lang.model.rules.variable;

import tara.lang.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReferenceRule implements Rule<String> {
	private List<String> allowedReferences;

	public ReferenceRule(Collection<String> allowedReferences) {
		this.allowedReferences = new ArrayList<>(allowedReferences);
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