package io.intino.magritte.lang.model.rules.variable;

import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.Primitive;

import java.util.*;

public class ReferenceRule implements VariableRule<List<Primitive.Reference>> {
	private List<String> allowedReferences = new ArrayList<>();

	public ReferenceRule(Collection<String> allowedReferences) {
		for (String allowedReference : allowedReferences) {
			if (!this.allowedReferences.contains(allowedReference)) this.allowedReferences.add(allowedReference);
			Arrays.stream(allowedReference.split(":")).filter(r -> !this.allowedReferences.contains(r)).forEach(r -> this.allowedReferences.add(r));
		}
	}


	public boolean accept(List<Primitive.Reference> values, String metric) {
		return accept(values);
	}

	public boolean accept(List<Primitive.Reference> values) {
		for (Primitive.Reference v : values) {
			final Node reference = v.reference();
			for (String type : reference.types())
				if (allowedReferences.contains(type) || allowedReferences.contains(type.split(":")[0])) return true;
		}
		return false;
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
