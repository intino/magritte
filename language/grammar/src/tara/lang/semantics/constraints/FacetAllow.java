package tara.lang.semantics.constraints;

import tara.lang.model.FacetTarget;
import tara.lang.semantics.Rejectable;
import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.semantics.Allow;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.SemanticException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

class FacetAllow implements Allow.Facet {
	private final String type;
	private final String[] with;
	private final boolean terminal;
	private final List<Constraint> hases;
	private final List<Allow> allows;

	public FacetAllow(String type, String[] with, boolean terminal) {
		this.type = type;
		this.terminal = terminal;
		this.with = with.clone();
		hases = new ArrayList<>();
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

	public boolean terminal() {
		return terminal;
	}

	@Override
	public List<Constraint> constraints() {
		return hases;
	}

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) throws SemanticException {
		List<Rejectable> toRemove = new ArrayList<>();
		for (Rejectable rejectable : rejectables) {
			if (!(rejectable instanceof Rejectable.Facet)) continue;
			tara.lang.model.Facet facet = ((Rejectable.Facet) rejectable).getFacet();
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
				tara.lang.model.Facet facet = (tara.lang.model.Facet) element;
				List<Rejectable> rejectables = Rejectable.build(facet);
				for (Allow allow : allows)
					allow.check(facet, rejectables);
				if (!rejectables.isEmpty()) throw new SemanticException(rejectables.get(0).error());
			}
		});
	}

	@Override
	public Facet require(Constraint.Has... requires) {
		final ConstraintTransformer transformer = new ConstraintTransformer(this);
		transformer.transformToAllows(Arrays.asList(requires));
		return add(requires);
	}

	private boolean checkFacetConstrains(tara.lang.model.Facet facet) throws SemanticException {
		for (Constraint require : hases) require.check(facet);
		return true;
	}


	private Facet add(Constraint... hases) {
		this.hases.addAll(asList(hases));
		return this;
	}

}
