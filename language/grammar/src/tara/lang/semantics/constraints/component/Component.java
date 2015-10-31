package tara.lang.semantics.constraints.component;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
import tara.lang.semantics.SemanticException;

import java.util.List;

public class Component implements tara.lang.semantics.Constraint.Component {
	private final String type;
	private final CompositionRule compositionRule;
	private final List<Tag> annotations;

	public Component(String type, CompositionRule compositionRule, List<Tag> annotations) {
		this.type = type;
		this.compositionRule = compositionRule;
		this.annotations = annotations;
	}

	@Override
	public String type() {
		return type;
	}

	public CompositionRule compositionRule() {
		return compositionRule;
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
