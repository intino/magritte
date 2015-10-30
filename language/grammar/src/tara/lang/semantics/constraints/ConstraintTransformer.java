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

	public void transformToAllows(List<Constraint.Has> constraints) {
		for (Constraint.Has constraint : constraints)
			if (constraint instanceof Constraint.Has.Name)
				allowContainer.allow(RuleFactory.name());
			else if (constraint instanceof Constraint.Has.Parameter)
				addAllowParameter((Constraint.Has.Parameter) constraint);
			else if (constraint instanceof Constraint.Has.Component)
				addAllowInclude((Constraint.Has.Component) constraint);
			else if (constraint instanceof Constraint.Has.OneOf)
				addAllowIncludeOneOf((Constraint.Has.OneOf) constraint);
	}


	private void addAllowInclude(Constraint.Has.Component constraint) {
		allowContainer.allow(RuleFactory.include(constraint.type(), constraint.size(), constraint.annotations()));
	}

	private void addAllowParameter(Constraint.Has.Parameter parameter) {
		final String[] tags = parameter.annotations().toArray(new String[parameter.annotations().size()]);
		if (REFERENCE.equals(parameter.type()))
			allowContainer.allow(RuleFactory.parameter(parameter.name(), parameter.size(), parameter.defaultValue(), parameter.position(), (ReferenceRule) parameter.rule(), tags));
		else
			allowContainer.allow(RuleFactory.parameter(parameter.name(), parameter.type(), parameter.size(), parameter.defaultValue(), parameter.position(), parameter.rule(), tags));
	}

	private void addAllowIncludeOneOf(Constraint.Has.OneOf constraint) {
		List<Allow> includeAllows = new ArrayList<>();
		for (Constraint.Has require : constraint.components())
			if (require instanceof Constraint.Has.Component)
				includeAllows.add(RuleFactory.include(((Constraint.Has.Component) require).type(), ((Constraint.Has.Component) require).size(), ((Constraint.Has.Component) require).annotations()));
		allowContainer.allow(RuleFactory.oneOf(includeAllows.toArray(new Allow[includeAllows.size()])));
	}

}
