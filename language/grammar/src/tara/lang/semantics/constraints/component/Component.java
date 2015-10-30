package tara.lang.semantics.constraints.component;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.model.rules.Size;
import tara.lang.semantics.SemanticException;

import java.util.List;

public class Component implements tara.lang.semantics.Constraint.Component {
	private final String type;
	private final Size size;
	private final List<Tag> annotations;

	public Component(String type, Size size, List<Tag> annotations) {
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

	@Override
	public void check(Element element) throws SemanticException {
//		ConstraintHelper.addFlagsAndAnnotations(rejectableIncludes, annotations);
//		rejectables.removeAll(rejectableIncludes);
	}
}
