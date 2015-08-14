package tara.language.semantics.constraints.allowed;

import tara.language.model.Element;
import tara.language.model.Tag;
import tara.language.semantics.Allow;
import tara.language.semantics.Rejectable;
import tara.language.semantics.SemanticException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllowOneOf implements Allow.OneOf {
	private final Allow[] allows;

	public AllowOneOf(Allow... allows) {
		this.allows = allows;
	}

	@Override
	public List<Allow> allows() {
		return Collections.unmodifiableList(Arrays.asList(allows));
	}

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) {
		for (Allow allow : allows) {
			try {
				allow.check(element, rejectables);
			} catch (SemanticException ignored) {
			}
		}
	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public Tag[] annotations() {
		return new Tag[0];
	}
}
