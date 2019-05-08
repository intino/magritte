package io.intino.tara.compiler.codegeneration.lang;

import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.itrules.adapters.ExcludeAdapter;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.variable.ReferenceRule;
import io.intino.tara.lang.model.rules.variable.VariableCustomRule;
import io.intino.tara.lang.semantics.Assumption;
import io.intino.tara.lang.semantics.Constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class TerminalConstraintManager implements TemplateTags {

	private final Language language;
	private final NodeContainer scope;

	TerminalConstraintManager(Language language, Model model) {
		this.language = language;
		this.scope = model;
	}

	TerminalConstraintManager(Language language, NodeContainer scope) {
		this.language = language;
		this.scope = scope;
	}

	private static boolean isInstance(List<Tag> annotations) {
		return annotations.contains(Tag.Instance);
	}

	void addConstraints(List<Constraint> constraints, FrameBuilderContext constraintsFrame) {
		for (Constraint c : constraints) {
			if (c instanceof Constraint.Name) addName(constraintsFrame, CONSTRAINT);
			else if (c instanceof Constraint.Component)
				asComponent(constraintsFrame, (Constraint.Component) c);
			else if (c instanceof Constraint.Parameter)
				addParameter(constraintsFrame, (Constraint.Parameter) c);
			else if (c instanceof Constraint.Facet)
				addFacet(constraintsFrame, ((Constraint.Facet) c));
		}
	}

	private void asComponent(FrameBuilderContext constraintsBuilder, Constraint.Component component) {
		if (isInstance(component.annotations())) addComponent(constraintsBuilder, component);
		else {
			List<Node> nodes = new ArrayList<>();
			findInstancesOf(component.type(), nodes);
			nodes.forEach(n -> addComponent(constraintsBuilder, n));
		}
	}

	private void addName(FrameBuilderContext constraints, String relation) {
		constraints.add(relation, NAME);
	}

	private void addFacet(FrameBuilderContext constraints, Constraint.Facet facet) {
		final FrameBuilder builder = new FrameBuilder(CONSTRAINT, FACET);
		builder.add(VALUE, facet.type());
		if (facet.terminal()) builder.add(TERMINAL, "true");
		builder.add(WITH, facet.with());
		addConstraints(facet.constraints(), builder);
		constraints.add(CONSTRAINT, builder.toFrame());
	}

	private void addParameter(FrameBuilderContext constraints, Constraint.Parameter constraint) {
		Object[] parameters = {constraint.name(), constraint.type(), sizeOfTerminal(constraint), constraint.facet(), constraint.position(), constraint.scope(), ruleToFrame(constraint.rule()), constraint.flags().stream().map(Enum::name).toArray(String[]::new)};
		final FrameBuilder primitiveFrameBuilder = new FrameBuilder();
		if (Primitive.REFERENCE.equals(constraint.type())) {
			fillAllowedReferences(constraint);
			primitiveFrameBuilder.add(REFERENCE);
		}
		renderPrimitive(primitiveFrameBuilder, parameters, TemplateTags.CONSTRAINT);
		constraints.add(CONSTRAINT, primitiveFrameBuilder.toFrame());
	}

	private void fillAllowedReferences(Constraint.Parameter constraint) {
		if (constraint.rule() instanceof ReferenceRule) fillAllowedReferences((ReferenceRule) constraint.rule());
	}

	private void fillAllowedReferences(ReferenceRule rule) {
		if (!allowedValuesAreTerminal(rule.allowedReferences()))
			rule.setAllowedReferences(Arrays.asList(instancesOfNonTerminalReference(rule)));
	}

	private void renderPrimitive(FrameBuilder builder, Object[] parameters, String relation) {
		builder.add(relation).add(PARAMETER);
		fillParameterFrame(parameters, builder);
	}

	private void fillParameterFrame(Object[] parameters, FrameBuilder builder) {
		builder.add(NAME, parameters[0]).
				add(TYPE, parameters[1]).
				add(SIZE, parameters[2]).
				add(FACET, parameters[3]).
				add(POSITION, parameters[4]).
				add(SCOPE, parameters[5]);
		if (parameters[6] != null) builder.add(RULE, parameters[6]);
		builder.add(TAGS, parameters[7]);
	}

	private Frame ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder frameBuilder = new FrameBuilder();
		frameBuilder.put(Rule.class, new ExcludeAdapter<>("loadedClass"));
		final FrameBuilder builder = rule.getClass().isEnum() ? new FrameBuilder("customrule", "rule") : frameBuilder.append(rule);
		if (rule instanceof VariableCustomRule) fillCustomRule((VariableCustomRule) rule, builder);
		else if (rule.getClass().isEnum()) fillInheritedCustomRule(rule, builder);
		return builder.toFrame();
	}

	private String[] instancesOfNonTerminalReference(ReferenceRule rule) {
		List<Node> instances = new ArrayList<>();
		rule.allowedReferences().forEach(type -> findInstancesOf(type, instances));
		return instances.stream().map(Node::qualifiedName).collect(Collectors.toList()).toArray(new String[instances.size()]);
	}

	private void findInstancesOf(String type, List<Node> instances) {
		findInstancesOf(scope, type, instances);
	}

	private void findInstancesOf(NodeContainer node, String type, List<Node> result) {
		for (Node component : node.components()) {
			if (component.type().equals(type)) result.add(component);
			if (!(component instanceof NodeReference)) findInstancesOf(component, type, result);
		}
	}

	private boolean allowedValuesAreTerminal(List<String> references) {
		for (String node : references)
			if (!isTerminal(node)) return false;
		return true;
	}

	private boolean isTerminal(String node) {
		if (language.assumptions(node) == null) return false;
		for (Assumption assumption : language.assumptions(node))
			if (!(assumption instanceof Assumption.Terminal)) return true;
		return false;
	}

	private void fillCustomRule(VariableCustomRule rule, FrameBuilder builder) {
		builder.add(QN, rule.loadedClass().getName());
		if (rule.isMetric()) {
			builder.add(METRIC);
			builder.add(DEFAULT, rule.getDefaultUnit());
		}
	}

	private void fillInheritedCustomRule(Rule rule, FrameBuilder builder) {
		builder.add(QN, rule.getClass().getName());
		if (rule instanceof Metric) {
			builder.add(METRIC);
			builder.add(DEFAULT, ((Enum) rule).name());
		}
	}

	private void addComponent(FrameBuilderContext builderContext, Constraint.Component component) {
		final FrameBuilder constraintBuilder = new FrameBuilder(CONSTRAINT, component instanceof Constraint.OneOf ? ONE_OF : COMPONENT);
		constraintBuilder.add(TYPE, component.type());
		final Frame sizeOfTerminal = sizeOfTerminal(component);
		if (sizeOfTerminal == null) return;
		constraintBuilder.add(SIZE, sizeOfTerminal);
		constraintBuilder.add(TAGS, component.annotations().stream().map(Enum::name).toArray(Object[]::new));
		if (component instanceof Constraint.OneOf)
			((Constraint.OneOf) component).components().forEach(c -> addComponent(constraintBuilder, c));
		builderContext.add(CONSTRAINT, constraintBuilder.toFrame());
	}

	private void addComponent(FrameBuilderContext frame, Node component) {
		if (component.name() == null) return;
		final FrameBuilder builder = new FrameBuilder(CONSTRAINT, COMPONENT);
		builder.add(TYPE, component.name());
		final Size size = component.container().sizeOf(component);
		if (size.min() == 0 && size.max() == 0) return;
		builder.add(SIZE, new FrameBuilder().append(size).toFrame());
		builder.add(TAGS, component.flags().stream().filter(f -> !Tag.Required.equals(f)).map(Enum::name).toArray(Object[]::new));
		frame.add(CONSTRAINT, builder.toFrame());
	}

	private Frame sizeOfTerminal(Constraint.Component constraint) {
		if (constraint == null) return new FrameBuilder().add("value", "null").toFrame();
		FrameBuilder builder = new FrameBuilder();
		final Size rule = (Size) constraint.rules().stream().filter(r -> r instanceof Size).findFirst().orElse(Size.MULTIPLE());
		final Size size = rule.into() != null ? obtainRule(constraint, rule.into()) : rule;
		if (size == null) return null;
		return builder.append(size).toFrame();
	}

	private Size obtainRule(Constraint.Component constraint, Size rule) {
		final boolean existsComponent = existsComponent(constraint.type());
		if (existsComponent) return rule.isSingle() ? null : new Size(0, rule.max());
		else return rule;
	}

	private Frame sizeOfTerminal(Constraint.Parameter constraint) {
		if (constraint == null) return new FrameBuilder().add("value", "null").toFrame();
		boolean isFilled = isParameterFilled(constraint.name());
		FrameBuilder builder = new FrameBuilder();
		final Size size = constraint.size();
		if (isFilled) return builder.append(size).toFrame();
		return builder.append(size.into() != null ? size.into() : size).toFrame();
	}

	private boolean isParameterFilled(String name) {
		if (scope instanceof Parametrized)
			for (Parameter parameter : ((Parametrized) scope).parameters())
				if (name.equals(parameter.name())) return true;
		return false;
	}

	private boolean existsComponent(String type) {
		if (scope instanceof Node)
			for (Node node : scope.components())
				if (type.equals(node.type())) return true;
		return false;
	}
}
