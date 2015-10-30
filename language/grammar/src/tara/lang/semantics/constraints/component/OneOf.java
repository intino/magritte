package tara.lang.semantics.constraints.component;

import tara.lang.model.Element;
import tara.lang.model.Tag;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticException;

import java.util.Collections;
import java.util.List;

public class OneOf implements Constraint.OneOf {
	private final Size size;
	private final Constraint[] constraints;

	public OneOf(Size size, Constraint.Component... constraints) {
		this.size = size;
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
	public Size size() {
		return this.size;
	}

	@Override
	public List<Tag> annotations() {
		return Collections.emptyList();
	}

	@Override
	public Constraint[] components() {
		return constraints;
	}


}
