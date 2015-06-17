package siani.tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.Allow;
import siani.tara.semantic.Assumption;
import siani.tara.semantic.Constraint;
import siani.tara.semantic.Constraint.Require;
import siani.tara.semantic.constraints.PrimitiveParameterAllow;
import siani.tara.semantic.constraints.ReferenceParameterAllow;
import siani.tara.semantic.model.Context;
import siani.tara.semantic.model.Primitives;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LanguageInheritanceFiller {
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
			Frame nodeFrame = new Frame().addTypes("node");
			fillRuleInfo(nodeFrame, aCase);
			addAllows(nodeFrame, language.allows(aCase));
			addRequires(nodeFrame, language.constraints(aCase));
			addAssumptions(nodeFrame, language.assumptions(aCase));
			root.addFrame("node", nodeFrame);
		}
	}

	private void fillRuleInfo(Frame frame, String aCase) {
		Context rules = language.catalog().get(aCase);
		frame.addFrame("name", aCase);
		addTypes(rules.types(), frame);
	}

	private void addTypes(String[] types, Frame frame) {
		if (types == null) return;
		Frame typesFrame = new Frame().addTypes("nodeType");
		for (String type : types) typesFrame.addFrame("type", type);
		if (typesFrame.slots().length > 0)
			frame.addFrame("nodeType", typesFrame);
	}

	private void addAllows(Frame frame, Collection<Allow> allows) {
		Frame allowsFrame = new Frame().addTypes("allows");
		for (Allow allow : allows) {
			if (allow instanceof Allow.Name) addName(allowsFrame, "allow");
			if (allow instanceof Allow.Multiple)
				addMultiple(allowsFrame, "allow", ((Allow.Multiple) allow).type());
			if (allow instanceof Allow.Single)
				addSingle(allowsFrame, "allow", ((Allow.Single) allow).type());
			if (allow instanceof Allow.Parameter) addParameter(allowsFrame, (Allow.Parameter) allow, "allow");
			if (allow instanceof Allow.Facet) addFacet(allowsFrame, ((Allow.Facet) allow).type());
		}
		if (allowsFrame.slots().length != 0) frame.addFrame("allows", allowsFrame);
	}

	private void addRequires(Frame frame, Collection<Constraint> requires) {
		Frame requireFrame = new Frame().addTypes("requires");
		for (Constraint require : requires) {
			if (require instanceof Require.Name) addName(requireFrame, "require");
			if (require instanceof Require.Multiple)
				addMultiple(requireFrame, "require", ((Require.Multiple) require).type());
			if (require instanceof Require.Single)
				addSingle(requireFrame, "require", ((Require.Single) require).type());
			if (require instanceof Require.Parameter)
				addParameter(requireFrame, (Require.Parameter) require);
			if (require instanceof Require.Plate) addAddress(requireFrame);

		}
		if (requireFrame.slots().length != 0)
			frame.addFrame("requires", requireFrame);
	}

	private void addAssumptions(Frame frame, Collection<Assumption> assumptions) {
		Frame assumptionsFrame = new Frame().addTypes("assumptions");
		for (Assumption assumption : assumptions) {
			assumptionsFrame.addFrame("assumption", getAssumptionValue(assumption));
		}
		if (assumptionsFrame.slots().length != 0)
			frame.addFrame("assumptions", assumptionsFrame);
	}

	private Object getAssumptionValue(Assumption assumption) {
		return assumption.getClass().getInterfaces()[0].getName().
			substring(assumption.getClass().getInterfaces()[0].getName().lastIndexOf("$") + 1);
	}

	private void addName(Frame allows, String relation) {
		allows.addFrame(relation, "name");
	}

	private void addFacet(Frame allows, String facet) {
		allows.addFrame("allow", new Frame().addTypes("allow", "facet").addFrame("value", facet));
	}

	private void addParameter(Frame allowsFrame, Allow.Parameter allow, String relation) {
		Object[] values = {allow.name(), allow.type(), getAllowedValues(allow), allow.multiple(), allow.position(), getMetric(allow.contract())};
		if (allow.allowedValues() != null && !allow.allowedValues().isEmpty())
			if (allow.type().equals("word")) renderWord(allowsFrame, values, relation);
			else renderReference(allowsFrame, values, relation);
		else renderPrimitive(allowsFrame, values, relation);
	}

	private String[] getAllowedValues(Allow.Parameter allow) {
		if (allow instanceof PrimitiveParameterAllow) return values(allow);
		if (allow instanceof ReferenceParameterAllow && allow.type().equals("word")) return values(allow);
		if (allowedValuesAreTerminal(allow)) return values(allow);
		return instancesOfNonTerminalReference(allow);
	}

	private String[] instancesOfNonTerminalReference(Allow.Parameter allow) {
		List<String> instances = new ArrayList<>();
		allow.allowedValues().forEach(type -> findInstancesOf(model, type, instances));
		return instances.toArray(new String[instances.size()]);
	}

	private void findInstancesOf(Node node, String type, List<String> instances) {
		for (Node include : node.getIncludedNodes()) {
			if (include.getType().equals(type)) instances.add(include.getQualifiedName());
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
		String relation = "require";
		if (require.allowedValues() != null && require.allowedValues().length > 0)
			if (require.type().equals("word")) renderWord(frame, values, relation);
			else renderReference(frame, values, relation);
		else renderPrimitive(frame, values, relation);
	}


	private void renderPrimitive(Frame allowsFrame, Object[] values, String relation) {
		allowsFrame.addFrame(relation, new Frame().addTypes(relation, "parameter").
			addFrame("name", values[0]).
			addFrame("type", values[1]).
			addFrame("multiple", values[3]).
			addFrame("position", values[4]).
			addFrame("contract", getContract(values[1].toString(), values[5])));
	}

	private Object getContract(String type, Object value) {
		if (!type.equals(Primitives.NATIVE)) return value;
		return value.toString() + Variable.NATIVE_SEPARATOR + language.languageName();
	}

	private void renderWord(Frame allowsFrame, Object[] values, String relation) {
		allowsFrame.addFrame(relation, new Frame().addTypes(relation, "parameter", "word").
			addFrame("name", values[0] + ":word").
			addFrame("words", (String[]) values[2]).
			addFrame("multiple", values[3]).
			addFrame("position", values[4]).
			addFrame("contract", values[5]));
	}

	private void renderReference(Frame allowsFrame, Object[] values, String relation) {
		allowsFrame.addFrame(relation, new Frame().addTypes(relation, "parameter", "reference").
			addFrame("name", values[0]).
			addFrame("types", (String[]) values[2]).
			addFrame("multiple", values[3]).
			addFrame("position", values[4]).
			addFrame("contract", values[5]));
	}

	private void addMultiple(Frame frameFrame, String frameRelation, String type) {
		frameFrame.addFrame(frameRelation, new Frame().addTypes("multiple", frameRelation).
			addFrame("type", type));
	}

	private void addSingle(Frame frame, String frameRelation, String type) {
		frame.addFrame(frameRelation, new Frame().addTypes("single", frameRelation).
			addFrame("type", type));
	}

	private void addAddress(Frame requireFrame) {
		requireFrame.addFrame("address", "");
	}
}
