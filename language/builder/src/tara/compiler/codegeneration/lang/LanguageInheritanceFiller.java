package tara.compiler.codegeneration.lang;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.engine.adapters.ExcludeAdapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.*;
import tara.lang.model.rules.CustomRule;
import tara.lang.model.rules.ReferenceRule;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LanguageInheritanceFiller implements TemplateTags {
	private final Frame root;
	private final List<String> cases;
	private final Language language;
	private final Model model;

	public LanguageInheritanceFiller(Frame root, List<String> cases, Language language, Model model) {
		this.root = root;
		this.cases = cases;
		this.language = language;
		this.model = model;
	}

	public LanguageInheritanceFiller(Language language) {
		this.root = null;
		this.cases = null;
		this.language = language;
		this.model = null;
	}

	public void fill() {
		for (String aCase : cases) {
			Frame nodeFrame = new Frame().addTypes(NODE);
			fillRuleInfo(nodeFrame, aCase);
			addConstraints(nodeFrame, language.constraints(aCase));
			addAssumptions(nodeFrame, language.assumptions(aCase));
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
		if (typesFrame.slots().length > 0)
			frame.addFrame(NODE_TYPE, typesFrame);
	}

	private void addConstraints(Frame frame, Collection<Constraint> allows) {
		Frame allowsFrame = new Frame().addTypes(CONSTRAINTS);
		addConstraints(allows, allowsFrame);
		if (allowsFrame.slots().length != 0) frame.addFrame(CONSTRAINTS, allowsFrame);
	}

	public void addConstraints(Collection<Constraint> allows, Frame allowsFrame) {
		for (Constraint allow : allows) {
			if (allow instanceof Constraint.Name) addName(allowsFrame, CONSTRAINT);
			if (allow instanceof Constraint.Component && isTerminal(((Constraint.Component) allow).annotations()))
				addMultiple(allowsFrame, CONSTRAINT, ((Constraint.Component) allow).type());
			if (allow instanceof Constraint.Parameter) addParameter(allowsFrame, (Constraint.Parameter) allow, CONSTRAINT);
			if (allow instanceof Constraint.Facet) addFacet(allowsFrame, ((Constraint.Facet) allow));
		}
	}

	public static boolean isTerminal(List<Tag> annotations) {
		return annotations.contains(Tag.TERMINAL_INSTANCE);
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

	private void addName(Frame allows, String relation) {
		allows.addFrame(relation, NAME);
	}

	private void addFacet(Frame allows, Constraint.Facet facet) {
		final Frame frame = new Frame().addTypes(CONSTRAINT, FACET);
		frame.addFrame(VALUE, facet.type());
		if (facet.terminal()) frame.addFrame(TERMINAL, "true");
		frame.addFrame(WITH, facet.with());
		addConstraints(facet.constraints(), frame);
		allows.addFrame(CONSTRAINT, frame);
	}

	private void addParameter(Frame allowsFrame, Constraint.Parameter allow, String relation) {
		Object[] parameters = {allow.name(), allow.type(), allow.size(), allow.position(), ruleToFrame(allow.rule())};
		final Frame primitiveFrame = new Frame();
		if (Primitive.REFERENCE.equals(allow.type())) {
			fillAllowedReferences((ReferenceRule) allow.rule());
			primitiveFrame.addTypes(REFERENCE);
		}
		renderPrimitive(primitiveFrame, parameters, relation);
		allowsFrame.addFrame(relation, primitiveFrame);
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

	private void addParameter(Frame containerFrame, Constraint.Parameter require) {
		Object[] parameters = {require.name(), require.type(), require.size(), require.position(), ruleToFrame(require.rule())};
		final Frame primitiveFrame = renderPrimitive(new Frame(), parameters, REQUIRE);
		if (Primitive.REFERENCE.equals(require.type())) primitiveFrame.addTypes(REFERENCE);
		containerFrame.addFrame(REQUIRE, primitiveFrame);
	}

	private Frame renderPrimitive(Frame frame, Object[] parameters, String relation) {
		frame.addTypes(relation, PARAMETER);
		fillParameterFrame(parameters, frame);
		return frame;
	}

	private void fillParameterFrame(Object[] parameters, Frame frame) {
		frame.addFrame(NAME, parameters[0]).
			addFrame(TYPE, parameters[1]).
			addFrame(MULTIPLE, parameters[2]).
			addFrame(POSITION, parameters[3]);
		if (parameters[4] != null)
			frame.addFrame(RULE, (Frame) parameters[4]);
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

	private void addMultiple(Frame frameFrame, String frameRelation, String type) {
		frameFrame.addFrame(frameRelation, new Frame().addTypes(MULTIPLE, frameRelation).addFrame(TYPE, type));
	}

	private void addSingle(Frame frame, String frameRelation, String type) {
		frame.addFrame(frameRelation, new Frame().addTypes(SINGLE, frameRelation).
			addFrame(TYPE, type));
	}

	private void addAddress(Frame requireFrame) {
		requireFrame.addFrame("address", "");
	}
}
