package tara.lang.model.rules.variable;

import java.util.Collections;
import java.util.List;

public class NativeReferenceRule extends NativeRule {

	private List<String> allowedReferences;

	public NativeReferenceRule(List<String> allowedReferences) {
		super("", "", Collections.emptyList());
		this.allowedReferences = allowedReferences;
	}

	public List<String> allowedTypes() {
		return allowedReferences;
	}

	@Override
	public String toString() {
		return "NativeReferenceRule{" + String.join(",", allowedReferences) + '}';
	}
}
