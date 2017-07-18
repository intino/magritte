package io.intino.tara.compiler.codegeneration.lang;

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
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.engine.adapters.ExcludeAdapter;
import org.siani.itrules.model.Frame;

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

	void addConstraints(List<Constraint> constraints, Frame constraintsFrame) {
		for (Constraint c : constraints) {
			if (c instanceof Constraint.Name) addName(constraintsFrame, CONSTRAINT);
			else if (c instanceof Constraint.Component)
				asComponent(constraintsFrame, (Constraint.Component) c);
			else if (c instanceof Constraint.Parameter)
				addParameter(constraintsFrame, (Constraint.Parameter) c, CONSTRAINT);
			else if (c instanceof Constraint.Facet)
				addFacet(constraintsFrame, ((Constraint.Facet) c));
		}
	}

	private void asComponent(Frame constraintsFrame, Constraint.Component component) {
		if (isInstance(component.annotations())) addComponent(constraintsFrame, component);
		else {
			List<Node> nodes = new ArrayList<>();
			findInstancesOf(component.type(), nodes);
			nodes.forEach(n -> addComponent(constraintsFrame, n));
		}
	}

	private void addName(Frame constraints, String relation) {
		constraints.addFrame(relation, NAME);
	}

	private void addFacet(Frame constraints, Constraint.Facet facet) {
		final Frame frame = new Frame().addTypes(CONSTRAINT, FACET);
		frame.addFrame(VALUE, facet.type());
		if (facet.terminal()) frame.addFrame(TERMINAL, "true");
		frame.addFrame(WITH, facet.with());
		addConstraints(facet.constraints(), frame);
		constraints.addFrame(CONSTRAINT, frame);
	}

	private void addParameter(Frame constraints, Constraint.Parameter constraint, String relation) {
		Object[] parameters = {constraint.name(), constraint.type(), sizeOfTerminal(constraint), constraint.facet(), constraint.position(), constraint.scope(), ruleToFrame(constraint.rule()), constraint.flags().stream().map(Enum::name).toArray(String[]::new)};
		final Frame primitiveFrame = new Frame();
		if (Primitive.REFERENCE.equals(constraint.type())) {
			fillAllowedReferences(constraint);
			primitiveFrame.addTypes(REFERENCE);
		}
		renderPrimitive(primitiveFrame, parameters, relation);
		constraints.addFrame(relation, primitiveFrame);
	}

	private void fillAllowedReferences(Constraint.Parameter constraint) {
		if (constraint.rule() instanceof ReferenceRule) fillAllowedReferences((ReferenceRule) constraint.rule());
	}

	private void fillAllowedReferences(ReferenceRule rule) {
		if (!allowedValuesAreTerminal(rule.allowedReferences()))
			rule.setAllowedReferences(Arrays.asList(instancesOfNonTerminalReference(rule)));
	}

	private void renderPrimitive(Frame frame, Object[] parameters, String relation) {
		frame.addTypes(relation, PARAMETER);
		fillParameterFrame(parameters, frame);
	}

	private void fillParameterFrame(Object[] parameters, Frame frame) {
		frame.addFrame(NAME, parameters[0]).
				addFrame(TYPE, parameters[1]).
				addFrame(SIZE, (Frame) parameters[2]).
				addFrame(FACET, parameters[3]).
				addFrame(POSITION, parameters[4]).
				addFrame(SCOPE, parameters[5]);
		if (parameters[6] != null) frame.addFrame(RULE, (Frame) parameters[6]);
		frame.addFrame(TAGS, (String[]) parameters[7]);
	}

	private Frame ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder frameBuilder = new FrameBuilder();
		frameBuilder.register(Rule.class, new ExcludeAdapter<>("loadedClass"));
		final Frame frame = rule.getClass().isEnum() ? new Frame().addTypes("customrule", "rule") : (Frame) frameBuilder.build(rule);
		if (rule instanceof VariableCustomRule) fillCustomRule((VariableCustomRule) rule, frame);
		else if (rule.getClass().isEnum()) fillInheritedCustomRule(rule, frame);
		return frame;
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

	private static boolean isInstance(List<Tag> annotations) {
		return annotations.contains(Tag.Instance);
	}


	private void fillCustomRule(VariableCustomRule rule, Frame frame) {
		frame.addFrame(QN, rule.getLoadedClass().getName());
		if (rule.isMetric()) {
			frame.addTypes(METRIC);
			frame.addFrame(DEFAULT, rule.getDefaultUnit());
		}
	}

	private void fillInheritedCustomRule(Rule rule, Frame frame) {
		frame.addFrame(QN, rule.getClass().getName());
		if (rule instanceof Metric) {
			frame.addTypes(METRIC);
			frame.addFrame(DEFAULT, ((Enum) rule).name());
		}
	}

	private void addComponent(Frame frame, Constraint.Component component) {
		final Frame constraint = new Frame().addTypes(CONSTRAINT, component instanceof Constraint.OneOf ? ONE_OF : COMPONENT);
		constraint.addFrame(TYPE, component.type());
		final Frame sizeOfTerminal = sizeOfTerminal(component);
		if (sizeOfTerminal == null) return;
		constraint.addFrame(SIZE, sizeOfTerminal);
		constraint.addFrame(TAGS, component.annotations().stream().map(Enum::name).toArray(String[]::new));
		if (component instanceof Constraint.OneOf)
			((Constraint.OneOf) component).components().forEach(c -> addComponent(constraint, c));
		frame.addFrame(CONSTRAINT, constraint);
	}

	private void addComponent(Frame frame, Node component) {
		if (component.name() == null) return;
		final Frame constraint = new Frame().addTypes(CONSTRAINT, COMPONENT);
		constraint.addFrame(TYPE, component.name());
		final Size size = component.container().sizeOf(component);
		if (size.min() == 0 && size.max() == 0) return;
		constraint.addFrame(SIZE, new FrameBuilder().build(size));
		constraint.addFrame(TAGS, component.flags().stream().map(Enum::name).toArray(String[]::new));
		frame.addFrame(CONSTRAINT, constraint);
	}

	private Frame sizeOfTerminal(Constraint.Component constraint) {
		if (constraint == null) return new Frame().addFrame("value", "null");
		FrameBuilder builder = new FrameBuilder();
		final Size rule = (Size) constraint.rules().stream().filter(r -> r instanceof Size).findFirst().orElse(Size.MULTIPLE());
		final Size size = rule.into() != null ? getIntoRule(constraint, rule) : rule;
		if (size.min() == 0 && size.max() == 0) return null;
		return (Frame) builder.build(size);
	}

	private Size getIntoRule(Constraint.Component constraint, Size rule) {
		final boolean existsComponent = existsComponent(constraint.type());
		if (!rule.into().isRequired()) return rule.into();
		if (rule.into().isSingle()) return new Size(0, 0);
		return existsComponent ? new Size(0, rule.into().max()) : rule.into();
	}

	private Frame sizeOfTerminal(Constraint.Parameter constraint) {
		if (constraint == null) return new Frame().addFrame("value", "null");
		boolean isFilled = isParameterFilled(constraint.name());
		FrameBuilder builder = new FrameBuilder();
		final Size size = constraint.size();
		if (isFilled) return (Frame) builder.build(size);
		return (Frame) builder.build(size.into() != null ? size.into() : size);
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
