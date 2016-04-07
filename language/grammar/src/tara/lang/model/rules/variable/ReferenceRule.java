package tara.lang.model.rules.variable;

import tara.lang.model.Rule;

import java.util.*;

public class ReferenceRule implements Rule<String> {
	private Set<String> allowedReferences = new LinkedHashSet<>();

	public ReferenceRule(Collection<String> allowedReferences) {
		for (String allowedReference : allowedReferences) {
			this.allowedReferences.add(allowedReference);
			Collections.addAll(this.allowedReferences, allowedReference.split(":"));
		}
	}

	@Override
	public boolean accept(String value) {
		return allowedReferences.contains(value);
	}

	public List<String> allowedReferences() {
		return new ArrayList<>(allowedReferences);
	}

	public void setAllowedReferences(List<String> allowedReferences) {
		this.allowedReferences.clear();
		this.allowedReferences.addAll(allowedReferences);
	}

	@Override
	public String toString() {
		return String.join(", ", allowedReferences);
	}

	@Override
	public List<Object> errorParameters() {
		return Collections.singletonList(toString());
	}

	@Override
	public String errorMessage() {
		return "reject.parameter.reference";
	}
}
