package tara.language.semantics.constraints.allowed;

import tara.language.model.Element;
import tara.language.model.Tag;
import tara.language.semantics.Allow;
import tara.language.semantics.Rejectable;
import tara.language.semantics.constraints.ConstraintHelper;

import java.util.List;

public class MultipleAllow implements Allow.Multiple {
	private final String type;
	private final Tag[] annotations;

	public MultipleAllow(String type, Tag... annotations) {
		this.type = type;
		this.annotations = annotations;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public Tag[] annotations() {
		return annotations;
	}

	@SuppressWarnings("SuspiciousMethodCalls")
	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) {
		List<Rejectable.Include> rejectableIncludes = ConstraintHelper.getRejectableIncludesBy(type, rejectables);
		ConstraintHelper.addFlagsAndAnnotations(rejectableIncludes, annotations);
		rejectables.removeAll(rejectableIncludes);
	}
}
