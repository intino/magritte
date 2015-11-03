package tara.lang.semantics.constraints.component;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticException;

import java.util.Collections;
import java.util.List;

public class OneOf implements Constraint.OneOf {
	private final CompositionRule compositionRule;
	private final List<Component> constraints;

	public OneOf(List<Component> constraints, CompositionRule compositionRule) {
		this.compositionRule = compositionRule;
		this.constraints = constraints;
	}

	@Override
	public void check(Element element) throws SemanticException {

	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public CompositionRule compositionRule() {
		return this.compositionRule;
	}

	@Override
	public List<Tag> annotations() {
		return Collections.emptyList();
	}

	@Override
	public List<Component> components() {
		return constraints;
	}


}
