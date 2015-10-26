package tara.lang.semantics.constraints.allowed;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.semantics.Allow;
import tara.lang.semantics.Rejectable;
import tara.lang.semantics.SemanticException;

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
