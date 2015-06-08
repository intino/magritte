package siani.tara.semantic.constraints;

import siani.tara.semantic.Allow;
import siani.tara.semantic.Constraint;
import siani.tara.semantic.Rejectable;
import siani.tara.semantic.SemanticException;
import siani.tara.semantic.model.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static siani.tara.semantic.constraints.RuleFactory.*;

class FacetAllow implements Allow.Facet {
	private final String type;
	List<Constraint> constraints;
	List<Allow> allows;

	public FacetAllow(String type) {
		this.type = type;
		constraints = new ArrayList<>();
		allows = new ArrayList<>();
	}

	@Override
	public String type() {
		return type;
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
	public Facet allow(Allow... parameter) {
		this.allows.addAll(asList(parameter));
		return add(new Constraint.Reject() {
			@Override
			public void check(Element element) throws SemanticException {
				siani.tara.semantic.model.Facet facet = (siani.tara.semantic.model.Facet) element;
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

	@Override
	public void check(Element element, List<? extends Rejectable> rejectables) throws SemanticException {
		List<Rejectable> toRemove = new ArrayList<>();
		for (Rejectable rejectable : rejectables) {
			if (!(rejectable instanceof Rejectable.Facet)) continue;
			siani.tara.semantic.model.Facet facet = ((Rejectable.Facet) rejectable).getFacet();
			if (facet.type().equals(RuleFactory.shortType(type)) && checkConstrains(facet))
				toRemove.add(rejectable);
		}
		rejectables.removeAll(toRemove);
	}

	private boolean checkConstrains(siani.tara.semantic.model.Facet facet) throws SemanticException {
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
					allow(name());
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
			allows.add(single(constraint.type()));
		}

		private void addAllowIncludeMultiple(Constraint.Require.Multiple constraint) {
			allow(multiple(constraint.type()));
		}

		private void addAllowParameter(Constraint.Require.Parameter parameter) {
			if (isWordOrReference(parameter))
				allow(parameter(parameter.name() + (parameter.type().equals("word") ? ":word" : ""), parameter.allowedValues(), parameter.multiple(), parameter.position(), parameter.metric(), parameter.annotations()));
			else
				allow(parameter(parameter.name(), parameter.type(), parameter.multiple(), parameter.position(), parameter.metric(), parameter.annotations()));
		}

		private void addAllowIncludeOneOf(Constraint.Require.OneOf constraint) {
			List<Allow> allows = new ArrayList<>();
			for (Constraint.Require require : constraint.requires())
				if (require instanceof Constraint.Require.Single)
					allows.add(single(((Constraint.Require.Single) require).type(), ((Constraint.Require.Single) require).annotations()));
				else
					allows.add(multiple(((Constraint.Require.Multiple) require).type(), ((Constraint.Require.Multiple) require).annotations()));
			allow(oneOf(allows.toArray(new Allow[allows.size()])));
		}

		private boolean isWordOrReference(Constraint.Require.Parameter parameter) {
			return parameter.type().equals("word") || parameter.type().equals("reference");
		}
	}
}
