package tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.language.model.Node;
import tara.language.model.Primitives;
import tara.language.model.Tag;
import tara.language.model.Variable;
import tara.language.semantics.Allow;
import tara.language.semantics.Assumption;
import tara.language.semantics.Constraint;
import tara.language.semantics.Constraint.Require;
import tara.language.semantics.Context;
import tara.language.semantics.constraints.allowed.PrimitiveParameterAllow;
import tara.language.semantics.constraints.allowed.ReferenceParameterAllow;

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
		for (Allow allow : allows) {
			if (allow instanceof Allow.Name) addName(allowsFrame, ALLOW);
			if (allow instanceof Allow.Multiple && isTerminal(((Allow.Multiple) allow).annotations()))
				addMultiple(allowsFrame, ALLOW, ((Allow.Multiple) allow).type());
			if (allow instanceof Allow.Single && isTerminal(((Allow.Single) allow).annotations()))
				addSingle(allowsFrame, ALLOW, ((Allow.Single) allow).type());
			if (allow instanceof Allow.Parameter) addParameter(allowsFrame, (Allow.Parameter) allow, ALLOW);
			if (allow instanceof Allow.Facet) addFacet(allowsFrame, ((Allow.Facet) allow).type());
		}
		if (allowsFrame.slots().length != 0) frame.addFrame(ALLOWS, allowsFrame);
	}

	private boolean isTerminal(Tag[] annotations) {
		return Arrays.asList(annotations).contains(Tag.TERMINAL_INSTANCE);
	}

	private void addRequires(Frame frame, Collection<Constraint> requires) {
		Frame requireFrame = new Frame().addTypes(REQUIRES);
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
		if (requireFrame.slots().length != 0)
			frame.addFrame(REQUIRES, requireFrame);
	}

	private void addAssumptions(Frame frame, Collection<Assumption> assumptions) {
		Frame assumptionsFrame = new Frame().addTypes(ASSUMPTIONS);
		for (Assumption assumption : assumptions) {
			assumptionsFrame.addFrame(ASSUMPTION, getAssumptionValue(assumption));
		}
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

	private void addFacet(Frame allows, String facet) {
		allows.addFrame(ALLOW, new Frame().addTypes(ALLOW, FACET).addFrame(VALUE, facet));
	}

	private void addParameter(Frame allowsFrame, Allow.Parameter allow, String relation) {
		Object[] values = {allow.name(), allow.type(), getAllowedValues(allow), allow.multiple(), allow.position(), getMetric(allow.contract())};
		if (allow.allowedValues() != null && !allow.allowedValues().isEmpty())
			if (Primitives.WORD.equals(allow.type())) renderWord(allowsFrame, values, relation);
			else renderReference(allowsFrame, values, relation);
		else renderPrimitive(allowsFrame, values, relation);
	}

	private String[] getAllowedValues(Allow.Parameter allow) {
		if (allow instanceof PrimitiveParameterAllow) return values(allow);
		if (allow instanceof ReferenceParameterAllow && Primitives.WORD.equals(allow.type())) return values(allow);
		if (allowedValuesAreTerminal(allow)) return values(allow);
		return instancesOfNonTerminalReference(allow);
	}

	private String[] instancesOfNonTerminalReference(Allow.Parameter allow) {
		List<String> instances = new ArrayList<>();
		allow.allowedValues().forEach(type -> findInstancesOf(model, type, instances));
		return instances.toArray(new String[instances.size()]);
	}

	private void findInstancesOf(Node node, String type, List<String> instances) {
		for (Node include : node.components()) {
			if (include.type().equals(type)) instances.add(include.qualifiedName());
			if (!(include instanceof NodeReference)) findInstancesOf(include, type, instances);
		}
	}

	private boolean allowedValuesAreTerminal(Allow.Parameter allow) {
		for (String value : allow.allowedValues())
			if (!isTerminal(value)) return false;
		return true;
	}

	private boolean isTerminal(String value) {
		for (Assumption assumption : language.assumptions(value))
			if (!(assumption instanceof Assumption.Terminal)) return true;
		return false;
	}

	private String[] values(Allow.Parameter allow) {
		return allow.allowedValues().toArray(new String[allow.allowedValues().size()]);
	}

	private String getMetric(String metric) {
		return metric == null || metric.isEmpty() ? "" : metric;
	}

	private void addParameter(Frame frame, Require.Parameter require) {
		Object[] values = {require.name(), require.type(), require.allowedValues(), require.multiple(), require.position(), getMetric(require.metric())};
		String relation = REQUIRE;
		if (require.allowedValues() != null && require.allowedValues().length > 0)
			if (Primitives.WORD.equals(require.type())) renderWord(frame, values, relation);
			else renderReference(frame, values, relation);
		else renderPrimitive(frame, values, relation);
	}

	private void renderPrimitive(Frame allowsFrame, Object[] values, String relation) {
		allowsFrame.addFrame(relation, new Frame().addTypes(relation, PARAMETER).
			addFrame(NAME, values[0]).
			addFrame(TYPE, values[1]).
			addFrame(MULTIPLE, values[3]).
			addFrame(POSITION, values[4]).
			addFrame(CONTRACT, getContract(values[1].toString(), values[5])));
	}

	private Object getContract(String type, Object value) {
		if (!type.equals(Primitives.NATIVE))
			return value.toString() + Variable.NATIVE_SEPARATOR + Variable.NATIVE_SEPARATOR + language.languageName();
		return value.toString() + Variable.NATIVE_SEPARATOR + language.languageName();
	}

	private void renderWord(Frame allowsFrame, Object[] values, String relation) {
		allowsFrame.addFrame(relation, new Frame().addTypes(relation, PARAMETER, WORD).
			addFrame(NAME, values[0] + ":" + WORD).
			addFrame(WORDS, (String[]) values[2]).
			addFrame(MULTIPLE, values[3]).
			addFrame(POSITION, values[4]).
			addFrame(CONTRACT, values[5]));
	}

	private void renderReference(Frame allowsFrame, Object[] values, String relation) {
		allowsFrame.addFrame(relation, new Frame().addTypes(relation, PARAMETER, REFERENCE).
			addFrame(NAME, values[0]).
			addFrame(TYPES, (String[]) values[2]).
			addFrame(MULTIPLE, values[3]).
			addFrame(POSITION, values[4]).
			addFrame(CONTRACT, values[5]));
	}

	private void addMultiple(Frame frameFrame, String frameRelation, String type) {
		frameFrame.addFrame(frameRelation, new Frame().addTypes(MULTIPLE, frameRelation).
			addFrame(TYPE, type));
	}

	private void addSingle(Frame frame, String frameRelation, String type) {
		frame.addFrame(frameRelation, new Frame().addTypes(SINGLE, frameRelation).
			addFrame(TYPE, type));
	}

	private void addAddress(Frame requireFrame) {
		requireFrame.addFrame("address", "");
	}
}
