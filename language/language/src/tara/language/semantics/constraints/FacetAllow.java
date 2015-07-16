package tara.language.semantics.constraints;

import tara.language.semantics.Allow;
import tara.language.semantics.Constraint;
import tara.language.semantics.Rejectable;
import tara.language.semantics.SemanticException;
import tara.language.model.Element;
import tara.language.model.FacetTarget;
import tara.language.model.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

class FacetAllow implements Allow.Facet {
	private final String type;
	private final String[] with;
	List<Constraint> constraints;
	List<Allow> allows;

	public FacetAllow(String type, String[] with) {
		this.type = type;
		this.with = with;
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
	public Collection<Allow> allows() {
		return allows;
	}


	@Override
	public Collection<Constraint> constraints() {
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
			if ((facet.type().equals(RuleFactory.shortType(type)) || FacetTarget.ANY.equals(facet.type())) && hasType && checkFacetConstrains(facet))
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
	public Facet allow(Allow... parameter) {
		this.allows.addAll(asList(parameter));
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
		final FacetConstraintTransformer facetConstraintTransformer = new FacetConstraintTransformer();
		facetConstraintTransformer.transformCorrespondingAllows(requires);
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

	public class FacetConstraintTransformer {

		public void transformCorrespondingAllows(Constraint.Require[] constraints) {
			for (Constraint.Require constraint : constraints)
				if (constraint instanceof Constraint.Require.Name)
					allow(RuleFactory.name());
				else if (constraint instanceof Constraint.Require.Parameter)
					addAllowParameter((Constraint.Require.Parameter) constraint);
				else if (constraint instanceof Constraint.Require.Single)
					addAllowIncludeSingle((Constraint.Require.Single) constraint);
				else if (constraint instanceof Constraint.Require.Multiple)
					addAllowIncludeMultiple((Constraint.Require.Multiple) constraint);
				else if (constraint instanceof Constraint.Require.OneOf)
					addAllowIncludeOneOf((Constraint.Require.OneOf) constraint);
		}

		private void addAllowIncludeSingle(Constraint.Require.Single constraint) {
			allows.add(RuleFactory.single(constraint.type()));
		}

		private void addAllowIncludeMultiple(Constraint.Require.Multiple constraint) {
			allow(RuleFactory.multiple(constraint.type()));
		}

		private void addAllowParameter(Constraint.Require.Parameter parameter) {
			if (isWordOrReference(parameter))
				allow(RuleFactory.parameter(parameter.name() + (parameter.type().equals("word") ? ":word" : ""), parameter.allowedValues(), parameter.multiple(), parameter.position(), parameter.metric(), parameter.annotations()));
			else
				allow(RuleFactory.parameter(parameter.name(), parameter.type(), parameter.multiple(), parameter.position(), parameter.metric(), parameter.annotations()));
		}

		private void addAllowIncludeOneOf(Constraint.Require.OneOf constraint) {
			List<Allow> allows = new ArrayList<>();
			for (Constraint.Require require : constraint.requires())
				if (require instanceof Constraint.Require.Single)
					allows.add(RuleFactory.single(((Constraint.Require.Single) require).type(), ((Constraint.Require.Single) require).annotations()));
				else
					allows.add(RuleFactory.multiple(((Constraint.Require.Multiple) require).type(), ((Constraint.Require.Multiple) require).annotations()));
			allow(RuleFactory.oneOf(allows.toArray(new Allow[allows.size()])));
		}

		private boolean isWordOrReference(Constraint.Require.Parameter parameter) {
			return parameter.type().equals("word") || parameter.type().equals("reference");
		}
	}
}
