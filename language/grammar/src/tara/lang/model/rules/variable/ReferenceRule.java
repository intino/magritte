package tara.lang.model.rules.variable;

import tara.lang.model.Rule;

import java.util.*;

public class ReferenceRule implements Rule<String> {
	private List<String> allowedReferences = new ArrayList<>();

	public ReferenceRule(Collection<String> allowedReferences) {
		for (String allowedReference : allowedReferences) {
			if (!this.allowedReferences.contains(allowedReference)) this.allowedReferences.add(allowedReference);
			Arrays.asList(allowedReference.split(":")).stream().filter(r -> !this.allowedReferences.contains(r)).forEach(r -> this.allowedReferences.add(r));
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
