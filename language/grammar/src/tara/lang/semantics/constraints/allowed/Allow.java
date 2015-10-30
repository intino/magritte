package tara.lang.semantics.constraints.allowed;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Rejectable;
import tara.lang.semantics.constraints.ConstraintHelper;

import java.util.List;

public class Allow implements tara.lang.semantics.Allow.Include {
	private final String type;
	private final Size size;
	private final List<Tag> annotations;

	public Allow(String type, Size size, List<Tag> annotations) {
		this.type = type;
		this.size = size;
		this.annotations = annotations;
	}

	@Override
	public String type() {
		return type;
	}

	public Size size() {
		return size;
	}

	@Override
	public List<Tag> annotations() {
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
