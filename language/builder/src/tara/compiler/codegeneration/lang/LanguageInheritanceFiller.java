package tara.compiler.codegeneration.lang;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.engine.adapters.ExcludeAdapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.Tag;
import tara.lang.model.rules.CustomRule;
import tara.lang.model.rules.ReferenceRule;
import tara.lang.semantics.Allow;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Constraint.Require;
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
			addAllows(nodeFrame, language.allows(aCase));
			addRequires(nodeFrame, language.constraints(aCase));
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

	private void addAllows(Frame frame, Collection<Allow> allows) {
		Frame allowsFrame = new Frame().addTypes(ALLOWS);
		addAllows(allows, allowsFrame);
		if (allowsFrame.slots().length != 0) frame.addFrame(ALLOWS, allowsFrame);
	}

	public void addAllows(Collection<Allow> allows, Frame allowsFrame) {
		for (Allow allow : allows) {
			if (allow instanceof Allow.Name) addName(allowsFrame, ALLOW);
			if (allow instanceof Allow.Multiple && isTerminal(((Allow.Multiple) allow).annotations()))
				addMultiple(allowsFrame, ALLOW, ((Allow.Multiple) allow).type());
			if (allow instanceof Allow.Single && isTerminal(((Allow.Single) allow).annotations()))
				addSingle(allowsFrame, ALLOW, ((Allow.Single) allow).type());
			if (allow instanceof Allow.Parameter) addParameter(allowsFrame, (Allow.Parameter) allow, ALLOW);
			if (allow instanceof Allow.Facet) addFacet(allowsFrame, ((Allow.Facet) allow));
		}
	}

	public static boolean isTerminal(Tag[] annotations) {
		return Arrays.asList(annotations).contains(Tag.TERMINAL_INSTANCE);
	}

	private void addRequires(Frame frame, Collection<Constraint> requires) {
		Frame requireFrame = new Frame().addTypes(REQUIRES);
		addRequires(requires, requireFrame);
		if (requireFrame.slots().length != 0)
			frame.addFrame(REQUIRES, requireFrame);
	}

	public void addRequires(Collection<Constraint> requires, Frame requireFrame) {
		for (Constraint require : requires) {
			if (require instanceof Require.Name) addName(requireFrame, REQUIRE);
			if (require instanceof Require.Multiple && isTerminal(((Require.Multiple) require).annotations()))
				addMultiple(requireFrame, REQUIRE, ((Require.Multiple) require).type());
			if (require instanceof Require.Single && isTerminal(((Require.Single) require).annotations()))
				addSingle(requireFrame, REQUIRE, ((Require.Single) require).type());
			if (require instanceof Require.Parameter)
				addParameter(requireFrame, (Require.Parameter) require);
			if (require instanceof Require.Plate) addAddress(requireFrame);
		}
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

	private void addFacet(Frame allows, Allow.Facet facet) {
		final Frame frame = new Frame().addTypes(ALLOW, FACET);
		frame.addFrame(VALUE, facet.type());
		if (facet.terminal()) frame.addFrame(TERMINAL, "true");
		frame.addFrame(WITH, facet.with());
		addRequires(facet.constraints(), frame);
		allows.addFrame(ALLOW, frame);
	}

	private void addParameter(Frame allowsFrame, Allow.Parameter allow, String relation) {
		Object[] parameters = {allow.name(), allow.type(), allow.multiple(), allow.position(), ruleToFrame(allow.rule())};
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

	private void addParameter(Frame containerFrame, Require.Parameter require) {
		Object[] parameters = {require.name(), require.type(), require.multiple(), require.position(), ruleToFrame(require.rule())};
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
		final Frame frame = (Frame) frameBuilder.build(rule);
		if (rule instanceof CustomRule) {
			frame.addFrame(QN, ((CustomRule) rule).getLoadedClass().getName());
			if (((CustomRule) rule).isMetric()) {
				frame.addTypes(METRIC);
				frame.addFrame(DEFAULT, ((CustomRule) rule).getDefaultUnit());
			}
		}
		return frame;
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
