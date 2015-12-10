package tara.lang.semantics.constraints;

import tara.lang.model.Element;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticException;

public class MetaFacetConstraint implements Constraint.MetaFacet {

	private final String type;
	private final String[] with;

	public MetaFacetConstraint(String type, String[] with) {
		this.type = type;
		this.with = with;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public String[] with() {
		return with;
	}

	@Override
	public void check(Element element) throws SemanticException {

	}
}
