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
import tara.lang.semantics.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LanguageInheritanceResolver implements TemplateTags {
	private final Frame root;
	private final List<String> instances;
	private final Language language;
	private final Model model;

	public LanguageInheritanceResolver(Frame root, List<String> instances, Language language, Model model) {
		this.root = root;
		this.instances = instances;
		this.language = language;
		this.model = model;
	}

	public LanguageInheritanceResolver(Language language) {
		this.root = null;
		this.instances = null;
		this.language = language;
		this.model = null;
	}

	public void fill() {
		if (instances == null || root == null) return;
		for (String instance : instances) {
			Frame nodeFrame = new Frame().addTypes(NODE);
			fillRuleInfo(nodeFrame, instance);
			addConstraints(nodeFrame, language.constraints(instance));
			addAssumptions(nodeFrame, language.assumptions(instance));
			root.addFrame(NODE, nodeFrame);
		}
	}

	private void fillRuleInfo(Frame frame, String aCase) {
		Context rules = language.catalog().get(aCase);
		frame.addFrame(TemplateTags.NAME, aCase);
		addTypes(rules.types(), frame);
	}

	private void addTypes(String[] types, Frame frame) {
		if (types == null) return;
		Frame typesFrame = new Frame().addTypes(NODE_TYPE);
		for (String type : types) typesFrame.addFrame(TemplateTags.TYPE, type);
		if (typesFrame.slots().length > 0) frame.addFrame(NODE_TYPE, typesFrame);
	}

	private void addConstraints(Frame frame, Collection<Constraint> constraints) {
		Frame constraintsFrame = new Frame().addTypes(CONSTRAINTS);
		addConstraints(constraints, constraintsFrame);
		frame.addFrame(CONSTRAINTS, constraintsFrame);
	}

	public void addConstraints(Collection<Constraint> constraints, Frame constraintsFrame) {
		for (Constraint constraint : constraints) {
			if (constraint instanceof Constraint.Name) addName(constraintsFrame, CONSTRAINT);
			else if (constraint instanceof Constraint.Component && isTerminal(((Constraint.Component) constraint).annotations()))
				addComponent(constraintsFrame, (Constraint.Component) constraint);
			else if (constraint instanceof Constraint.Parameter)
				addParameter(constraintsFrame, (Constraint.Parameter) constraint, CONSTRAINT);
			else if (constraint instanceof Constraint.Facet)
				addFacet(constraintsFrame, ((Constraint.Facet) constraint));
		}
	}

	public static boolean isTerminal(List<Tag> annotations) {
		return annotations.contains(Tag.Instance);
	}

	private void addAssumptions(Frame frame, Collection<Assumption> assumptions) {
		Frame assumptionsFrame = new Frame().addTypes(ASSUMPTIONS);
		for (Assumption assumption : assumptions)
			assumptionsFrame.addFrame(ASSUMPTION, getAssumptionValue(assumption));
		if (assumptionsFrame.slots().length != 0)
			frame.addFrame(ASSUMPTIONS, assumptionsFrame);
	}

	private Object getAssumptionValue(Assumption assumption) {
		return assumption.getClass().getInterfaces()[0].getName().
			substring(assumption.getClass().getInterfaces()[0].getName().lastIndexOf("$") + 1);
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

	private String[] instancesOfNonTerminalReference(ReferenceRule rule) {
		List<String> instances = new ArrayList<>();
		rule.getAllowedReferences().forEach(type -> findInstancesOf(model, type, instances));
		return instances.toArray(new String[instances.size()]);
	}

	private void findInstancesOf(Node node, String type, List<String> instances) {
		for (Node include : node.components()) {
			if (include.type().equals(type)) instances.add(include.qualifiedName());
			if (!(include instanceof NodeReference)) findInstancesOf(include, type, instances);
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
		final Frame constraint = new Frame().addTypes(CONSTRAINT, COMPONENT);
		constraint.addFrame(TYPE, component.type());
		constraint.addFrame(SIZE, sizeOfTerminal(component));
		frame.addFrame(CONSTRAINT, constraint);
		frame.addFrame(TAGS, component.annotations().toArray(new Tag[component.annotations().size()]));

	}

	public static Frame sizeOfTerminal(Constraint.Component constraint) {
		if (constraint == null) return new Frame().addFrame("value", "null");
		FrameBuilder builder = new FrameBuilder();
		final CompositionRule rule = constraint.compositionRule();
		return (Frame) builder.build(rule instanceof Size && rule.into() != null ? rule.into() : rule);
	}

	public static Frame sizeOfTerminal(Constraint.Parameter constraint) {
		if (constraint == null) return new Frame().addFrame("value", "null");
		FrameBuilder builder = new FrameBuilder();
		final Size rule = constraint.size();
		return (Frame) builder.build(rule.into() != null ? rule.into() : rule);
	}
}
