package tara.language.semantics.constraints;

import tara.language.model.Element;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.semantics.Allow;
import tara.language.semantics.Constraint;
import tara.language.semantics.Rejectable;
import tara.language.semantics.SemanticException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

class FacetAllow implements Allow.Facet {
	private final String type;
	private final String[] with;
	List<Constraint> constraints;
	List<Allow> allows;

	public FacetAllow(String type, String[] with) {
		this.type = type;
		this.with = with.clone();
		constraints = new ArrayList<>();
		allows = new ArrayList<>();
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
	public List<Allow> allows() {
		return allows;
	}


	@Override
	public List<Constraint> constraints() {
		return constraints;
	}

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) throws SemanticException {
		List<Rejectable> toRemove = new ArrayList<>();
		for (Rejectable rejectable : rejectables) {
			if (!(rejectable instanceof Rejectable.Facet)) continue;
			tara.language.model.Facet facet = ((Rejectable.Facet) rejectable).getFacet();
			List<String> containerTypes = ((Node) facet.container()).types();
			final boolean hasType = is(containerTypes);
			if ((facet.type().equals(ConstraintHelper.shortType(type)) || FacetTarget.ANY.equals(facet.type())) && hasType && checkFacetConstrains(facet))
				toRemove.add(rejectable);
			else if (!hasType) ((Rejectable.Facet) rejectable).constrains(containerTypes);
		}
		rejectables.removeAll(toRemove);
	}

	private boolean is(List<String> nodeTypes) {
		if (with == null) return true;
		for (String aType : with)
			if (!nodeTypes.contains(aType)) return false;
		return true;
	}

	@Override
	public Facet allow(Allow... allow) {
		this.allows.addAll(asList(allow));
		return add(new Constraint.Reject() {
			@Override
			public void check(Element element) throws SemanticException {
				tara.language.model.Facet facet = (tara.language.model.Facet) element;
				List<Rejectable> rejectables = Rejectable.build(facet);
				for (Allow allow : allows)
					allow.check(facet, rejectables);
				if (!rejectables.isEmpty()) throw new SemanticException(rejectables.get(0).error());
			}
		});
	}

	@Override
	public Facet require(Constraint.Require... requires) {
		final ConstraintTransformer transformer = new ConstraintTransformer(this);
		transformer.transformCorrespondingAllows(requires);
		return add(requires);
	}

	private boolean checkFacetConstrains(tara.language.model.Facet facet) throws SemanticException {
		for (Constraint require : constraints) require.check(facet);
		return true;
	}


	private Facet add(Constraint... constraints) {
		this.constraints.addAll(asList(constraints));
		return this;
	}

}
