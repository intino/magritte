package tara.lang.model.rules.variable;

import java.util.Collections;
import java.util.List;

public class ReferenceNativeRule extends NativeRule {

	private List<String> allowedReferences;

	public ReferenceNativeRule(List<String> allowedReferences, String language) {
		super("", "", Collections.emptyList(), language);
		this.allowedReferences = allowedReferences;
	}

	public List<String> allowedTypes() {
		return allowedReferences;
	}

	public void allowedTypes(List<String> allowedTypes) {
		this.allowedReferences = allowedTypes;
	}

	@Override
	public String toString() {
		return "NativeReferenceRule{" + String.join(",", allowedReferences) + '}';
	}
}
