package tara.lang.semantics.constraints;

import tara.lang.model.rules.ReferenceRule;
import tara.lang.semantics.Allow;
import tara.lang.semantics.AllowContainer;
import tara.lang.semantics.Constraint;

import java.util.ArrayList;
import java.util.List;

import static tara.lang.model.Primitive.REFERENCE;

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
		final String[] tags = parameter.annotations().toArray(new String[parameter.annotations().size()]);
		if (REFERENCE.equals(parameter.type()))
			allowContainer.allow(RuleFactory.parameter(parameter.name(), parameter.multiple(), parameter.defaultValue(), parameter.position(), (ReferenceRule) parameter.rule(), tags));
		else
			allowContainer.allow(RuleFactory.parameter(parameter.name(), parameter.type(), parameter.multiple(), parameter.defaultValue(), parameter.position(), parameter.rule(), tags));
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

}
