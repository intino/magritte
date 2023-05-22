package io.intino.magritte.lang.model.rules.variable;

import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.Primitive;

import java.util.*;

public class ReferenceRule implements VariableRule<List<Primitive.Reference>> {
	private final List<String> allowedReferences = new ArrayList<>();

	public ReferenceRule(Collection<String> allowedReferences) {
		allowedReferences.forEach(ref -> {
			if (!this.allowedReferences.contains(ref)) this.allowedReferences.add(ref);
			Arrays.stream(ref.split(":")).filter(r -> !this.allowedReferences.contains(r)).forEach(r -> this.allowedReferences.add(r));
		});
	}

	public boolean accept(List<Primitive.Reference> values, String metric) {
		return accept(values);
	}

	public boolean accept(List<Primitive.Reference> values) {
		return values.stream().anyMatch(v -> acceptValue(v.reference()) || acceptValue(v.reference().resolve()));
	}

	private boolean acceptValue(Node v) {
		return v.types().stream()
				.anyMatch(type -> allowedReferences.contains(type) || allowedReferences.contains(type.split(":")[0]));
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
