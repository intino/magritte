package tara.lang.semantics.constraints;

import tara.lang.model.Element;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

class FacetConstraint implements Constraint.Facet {
	private final String type;
	private final String[] with;
	private final boolean terminal;
	private final List<Constraint> constraints;

	public FacetConstraint(String type, boolean terminal, String[] with) {
		this.type = type;
		this.terminal = terminal;
		this.with = with.clone();
		constraints = new ArrayList<>();
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public String[] with() {
		return with;
	}

	public boolean terminal() {
		return terminal;
	}

	@Override
	public List<Constraint> constraints() {
		return constraints;
	}

	@Override
	public Facet has(Constraint... requires) {
		this.constraints.addAll(asList(requires));
		return this;
	}

	@Override
	public void check(Element element) throws SemanticException {
//		for (Rejectable rejectable : rejectables) {
//			if (!(rejectable instanceof Rejectable.Facet)) continue;
//			tara.lang.model.Facet facet = ((Rejectable.Facet) rejectable).getFacet();
//			List<String> containerTypes = ((Node) facet.container()).types();
//			final boolean hasType = is(containerTypes);
//			if ((facet.type().equals(ConstraintHelper.shortType(type)) || FacetTarget.ANY.equals(facet.type())) && hasType && checkFacetConstrains(facet))
//				toRemove.add(rejectable);
//			else if (!hasType) ((Rejectable.Facet) rejectable).constrains(containerTypes);
//		}
//		rejectables.removeAll(toRemove);
	}

	private boolean is(List<String> nodeTypes) {
		if (with == null) return true;
		for (String aType : with)
			if (!nodeTypes.contains(aType)) return false;
		return true;
	}


	private boolean checkFacetConstrains(tara.lang.model.Facet facet) throws SemanticException {
		for (Constraint require : constraints) require.check(facet);
		return true;
	}

}
