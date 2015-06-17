package siani.tara.semantic.model;

import siani.tara.semantic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static siani.tara.semantic.constraints.RuleFactory.*;

public class Context {

	private final String[] types;
	private final List<Constraint> constraints = new ArrayList<>();
	private final List<Assumption> assumptions = new ArrayList<>();
	private final List<Allow> allows = new ArrayList<>();

	public Context(String[] types, Constraint[] globalConstrains) {
		this.types = types;
		Collections.addAll(constraints, globalConstrains);
	}

	public String[] types() {
		return types;
	}

	public List<Constraint> constraints() {
		return constraints;
	}

	public List<Assumption> assumptions() {
		return assumptions;
	}

	public List<Allow> allows() {
		return allows;
	}

	public Context allow(final Allow... allows) {
		this.allows.addAll(Arrays.asList(allows));
		return add(new Constraint.Reject() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				List<Rejectable> rejectables = Rejectable.build(node);
				for (Allow allow : Context.this.allows) allow.check(node, rejectables);
				if (!rejectables.isEmpty()) throw new SemanticException(rejectables.get(0).error());
			}
		});
	}

	public Context assume(Assumption... assumptions) {
		this.assumptions.addAll(Arrays.asList(assumptions));
		return this;
	}

	public Context require(Constraint.Require... constraints) {
		new ContextConstraintTransformer().transformCorrespondingAllows(constraints);
		return add(constraints);
	}


	private Context add(Constraint... constraints) {
		this.constraints.addAll(Arrays.asList(constraints));
		return this;
	}

	void commit() {
		if (allows.isEmpty()) allow();
	}


	public class ContextConstraintTransformer {

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
