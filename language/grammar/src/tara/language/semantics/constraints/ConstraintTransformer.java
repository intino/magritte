package tara.language.semantics.constraints;

import tara.language.model.Primitives;
import tara.language.semantics.Allow;
import tara.language.semantics.AllowContainer;
import tara.language.semantics.Constraint;

import java.util.ArrayList;
import java.util.List;

public class ConstraintTransformer {

	private AllowContainer allowContainer;

	public ConstraintTransformer(AllowContainer allowContainer) {
		this.allowContainer = allowContainer;
	}

	public void transformToAllows(List<Constraint.Require> constraints) {
		for (Constraint.Require constraint : constraints)
			if (constraint instanceof Constraint.Require.Name)
				allowContainer.allow(RuleFactory.name());
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
		allowContainer.allows().add(RuleFactory.single(constraint.type()));
	}

	private void addAllowIncludeMultiple(Constraint.Require.Multiple constraint) {
		allowContainer.allow(RuleFactory.multiple(constraint.type()));
	}

	private void addAllowParameter(Constraint.Require.Parameter parameter) {
		if (isWordOrReference(parameter))
			allowContainer.allow(RuleFactory.parameter(parameter.name() + (Primitives.WORD.equals(parameter.type()) ? ":word" : ""), parameter.allowedValues(), parameter.multiple(), parameter.defaultValue(), parameter.position(), parameter.metric(), Primitives.TYPE.equals(parameter.type()), parameter.annotations()));
		else
			allowContainer.allow(RuleFactory.parameter(parameter.name(), parameter.type(), parameter.multiple(), parameter.defaultValue(), parameter.position(), parameter.metric(), parameter.annotations()));
	}

	private void addAllowIncludeOneOf(Constraint.Require.OneOf constraint) {
		List<Allow> includeAllows = new ArrayList<>();
		for (Constraint.Require require : constraint.requires())
			if (require instanceof Constraint.Require.Single)
				includeAllows.add(RuleFactory.single(((Constraint.Require.Single) require).type(), ((Constraint.Require.Single) require).annotations()));
			else
				includeAllows.add(RuleFactory.multiple(((Constraint.Require.Multiple) require).type(), ((Constraint.Require.Multiple) require).annotations()));
		allowContainer.allow(RuleFactory.oneOf(includeAllows.toArray(new Allow[includeAllows.size()])));
	}

	private boolean isWordOrReference(Constraint.Require.Parameter parameter) {
		return "word".equals(parameter.type()) || "reference".equals(parameter.type());
	}
}
