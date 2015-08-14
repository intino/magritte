package tara.language.semantics.constraints.allowed;

import tara.language.model.Element;
import tara.language.model.Tag;
import tara.language.semantics.Allow;
import tara.language.semantics.Rejectable;
import tara.language.semantics.constraints.ConstraintHelper;

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
