package tara.lang.semantics.constraints.allowed;

import tara.lang.model.Tag;
import tara.lang.semantics.constraints.ConstraintHelper;
import tara.lang.model.Element;
import tara.lang.semantics.Allow;
import tara.lang.semantics.Rejectable;

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
