package tara.compiler.codegeneration.lang;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.engine.adapters.ExcludeAdapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.CustomRule;
import tara.lang.model.rules.variable.ReferenceRule;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalConstraintManager implements TemplateTags {

	private final Language language;
	private final NodeContainer scope;

	public TerminalConstraintManager(Language language, Model model) {
		this.language = language;
		this.scope = model;
	}

	public TerminalConstraintManager(Language language, NodeContainer scope) {
		this.language = language;
		this.scope = scope;
	}

	public void addConstraints(List<Constraint> constraints, Frame constraintsFrame) {
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
		Object[] parameters = {constraint.name(), constraint.type(), sizeOfTerminal(constraint), constraint.position(), ruleToFrame(constraint.rule()), constraint.annotations().toArray(new String[constraint.annotations().size()])};
		final Frame primitiveFrame = new Frame();
		if (Primitive.REFERENCE.equals(constraint.type())) {
			fillAllowedReferences((ReferenceRule) constraint.rule());
			primitiveFrame.addTypes(REFERENCE);
		}
		renderPrimitive(primitiveFrame, parameters, relation);
		constraints.addFrame(relation, primitiveFrame);
	}

	private void fillAllowedReferences(ReferenceRule rule) {
		if (!allowedValuesAreTerminal(rule)) {
			rule.setAllowedReferences(Arrays.asList(instancesOfNonTerminalReference(rule)));
		}
	}

	private Frame renderPrimitive(Frame frame, Object[] parameters, String relation) {
		frame.addTypes(relation, PARAMETER);
		fillParameterFrame(parameters, frame);
		return frame;
	}

	private void fillParameterFrame(Object[] parameters, Frame frame) {
		frame.addFrame(NAME, parameters[0]).
			addFrame(TYPE, parameters[1]).
			addFrame(SIZE, (Frame) parameters[2]).
			addFrame(POSITION, parameters[3]);
		if (parameters[4] != null)
			frame.addFrame(RULE, (Frame) parameters[4]);
		frame.addFrame(ANNOTATIONS, (String[]) parameters[5]);
	}

	private Frame ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder frameBuilder = new FrameBuilder();
		frameBuilder.register(Rule.class, new ExcludeAdapter<>("loadedClass"));
		final Frame frame = rule.getClass().isEnum() ? new Frame().addTypes("customrule", "rule") : (Frame) frameBuilder.build(rule);
		if (rule instanceof CustomRule) fillCustomRule((CustomRule) rule, frame);
		else if (rule.getClass().isEnum()) fillInheritedCustomRule(rule, frame);
		return frame;
	}


	private String[] instancesOfNonTerminalReference(ReferenceRule rule) {
		List<Node> instances = new ArrayList<>();
		rule.getAllowedReferences().forEach(type -> findInstancesOf(type, instances));
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

	private boolean allowedValuesAreTerminal(ReferenceRule rule) {
		for (String node : rule.getAllowedReferences())
			if (!isTerminal(node)) return false;
		return true;
	}

	private boolean isTerminal(String node) {
		for (Assumption assumption : language.assumptions(node))
			if (!(assumption instanceof Assumption.Terminal)) return true;
		return false;
	}

	private static boolean isInstance(List<Tag> annotations) {
		return annotations.contains(Tag.Instance);
	}


	private void fillCustomRule(CustomRule rule, Frame frame) {
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
		constraint.addFrame(SIZE, sizeOfTerminal(component));
		constraint.addFrame(TAGS, component.annotations().toArray(new Tag[component.annotations().size()]));
		if (component instanceof Constraint.OneOf)
			((Constraint.OneOf) component).components().forEach(c -> addComponent(constraint, c));
		frame.addFrame(CONSTRAINT, constraint);
	}

	private void addComponent(Frame frame, Node component) {
		if (component.name() == null) return;
		final Frame constraint = new Frame().addTypes(CONSTRAINT, COMPONENT);
		constraint.addFrame(TYPE, component.name());
		constraint.addFrame(SIZE, new FrameBuilder().build(component.container().ruleOf(component)));
		constraint.addFrame(TAGS, component.flags().toArray(new Tag[component.flags().size()]));
		frame.addFrame(CONSTRAINT, constraint);
	}

	public static Frame sizeOfTerminal(Constraint.Component constraint) {
		if (constraint == null) return new Frame().addFrame("value", "null");
		FrameBuilder builder = new FrameBuilder();
		final CompositionRule rule = constraint.compositionRule();
		return (Frame) builder.build(rule instanceof Size && rule.into() != null ? rule.into() : rule);
	}

	public Frame sizeOfTerminal(Constraint.Parameter constraint) {
		if (constraint == null) return new Frame().addFrame("value", "null");
		boolean isFilled = isFilled(constraint.name());
		FrameBuilder builder = new FrameBuilder();
		final Size size = constraint.size();
		if (isFilled) return (Frame) builder.build(size);
		return (Frame) builder.build(size.into() != null ? size.into() : size);
	}

	private boolean isFilled(String name) {
		if (scope instanceof Parametrized)
			for (Parameter parameter : ((Parametrized) scope).parameters())
				if (name.equals(parameter.name())) return true;
		return false;
	}

}
