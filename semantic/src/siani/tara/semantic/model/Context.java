package siani.tara.semantic.model;

import siani.tara.semantic.*;
import siani.tara.semantic.Constraint.Require.Multiple;
import siani.tara.semantic.Constraint.Require.OneOf;
import siani.tara.semantic.Constraint.Require.Single;

import java.util.*;

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

	public Collection<Constraint> constraints() {
		return constraints;
	}

	public Collection<Assumption> assumptions() {
		return assumptions;
	}

	public Collection<Allow> allows() {
		return allows;
	}

	public Context allow(final Allow... allows) {
		this.allows.addAll(Arrays.asList(allows));
		return add(new Constraint.Reject() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				List<Rejectable> rejectables = Rejectable.build(node);
				for (Allow allow : Context.this.allows)
					allow.check(node, rejectables);
				if (!rejectables.isEmpty()) throw new SemanticException(rejectables.get(0).error());
			}

		});
	}

	public Context assume(Assumption... assumptions) {
		this.assumptions.addAll(Arrays.asList(assumptions));
		return this;
	}

	public Context require(Constraint.Require... constraints) {
		addCorrespondingAllows(constraints);
		return add(constraints);
	}

	private void addCorrespondingAllows(Constraint.Require[] constraints) {
		for (Constraint.Require constraint : constraints)
			if (constraint instanceof Constraint.Require.Name)
				allow(name());
			else if (constraint instanceof Constraint.Require.Parameter)
				addAllowParameter((Constraint.Require.Parameter) constraint);
			else if (constraint instanceof Single)
				addAllowIncludeSingle((Single) constraint);
			else if (constraint instanceof Multiple)
				addAllowIncludeMultiple((Multiple) constraint);
			else if (constraint instanceof OneOf)
				addAllowIncludeOneOf((OneOf) constraint);
	}

	private void addAllowIncludeSingle(Single constraint) {
		allows.add(single(constraint.type()));
	}

	private void addAllowIncludeMultiple(Multiple constraint) {
		allow(multiple(constraint.type()));
	}

	private void addAllowParameter(Constraint.Require.Parameter parameter) {
		if (isWordOrReference(parameter))
			allow(parameter(parameter.name() + (parameter.type().equals("word") ? ":word" : ""), parameter.allowedValues(), parameter.multiple(), parameter.position(), parameter.metric(), parameter.annotations()));
		else
			allow(parameter(parameter.name(), parameter.type(), parameter.multiple(), parameter.position(), parameter.metric(), parameter.annotations()));
	}

	private void addAllowIncludeOneOf(OneOf constraint) {
		List<Allow> allows = new ArrayList<>();
		for (Constraint.Require require : constraint.requires())
			if (require instanceof Single)
				allows.add(single(((Single) require).type(), ((Single) require).annotations()));
			else
				allows.add(multiple(((Multiple) require).type(), ((Multiple) require).annotations()));
		allow(oneOf(allows.toArray(new Allow[allows.size()])));
	}

	private boolean isWordOrReference(Constraint.Require.Parameter parameter) {
		return parameter.type().equals("word") || parameter.type().equals("reference");
	}

	private Context add(Constraint... constraints) {
		this.constraints.addAll(Arrays.asList(constraints));
		return this;
	}

	void commit() {
		if (allows.isEmpty()) allow();
	}
}
