package tara.lang.semantics.constraints.allowed;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.semantics.Rejectable;
import tara.lang.semantics.constraints.ConstraintHelper;
import tara.lang.semantics.Allow;

import java.util.Arrays;
import java.util.List;

public class AllowSingle implements Allow.Single {
	private final String type;
	private final Tag[] annotations;

	public AllowSingle(String type, Tag... annotations) {
		this.type = type;
		this.annotations = annotations;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public Tag[] annotations() {
		return Arrays.copyOf(annotations, annotations.length);
	}

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) {
		List<Rejectable.Include> rejectableIncludes = ConstraintHelper.getRejectableIncludesBy(type, rejectables);
		ConstraintHelper.addFlagsAndAnnotations(rejectableIncludes, annotations);
		if (rejectableIncludes.size() > 1) setCauseToRejectables(rejectableIncludes);
		else rejectables.removeAll(rejectableIncludes);
	}

	private void setCauseToRejectables(List<Rejectable.Include> rejectableIncludesBy) {
		rejectableIncludesBy.forEach(Rejectable.Include::multiple);
	}
}
