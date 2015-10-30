package tara.compiler.codegeneration.lang;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.engine.adapters.ExcludeAdapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.VariableReference;
import tara.lang.model.Node;
import tara.lang.model.Rule;
import tara.lang.model.Tag;
import tara.lang.model.Variable;
import tara.lang.model.rules.CustomRule;
import tara.lang.semantics.Allow;
import tara.lang.semantics.constraints.allowed.ReferenceParameterAllow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Tag.TERMINAL_INSTANCE;

public class LanguageParameterAdapter implements TemplateTags {
	private final Language language;

	LanguageParameterAdapter(Language language) {
		this.language = language;
	}

	void addParameterRequire(Frame frame, int i, Variable variable, String relation) {
		if (variable instanceof VariableReference)
			frame.addFrame(relation, referenceParameter(i, variable, relation));
		else frame.addFrame(relation, primitiveParameter(i, variable, relation));
	}

	int addTerminalParameterRequires(Node node, Frame requires) {
		int index = 0;
		Collection<Allow> allows = language.allows(node.type());
		if (allows == null) return 0;
		for (Allow allow : allows)
			if (allow instanceof Allow.Parameter && isTerminal((Allow.Parameter) allow) && !isRedefined((Allow.Parameter) allow, node.variables()) && isRequired((Allow.Parameter) allow)) {
				addParameter(requires, (Allow.Parameter) allow, index, REQUIRE);
				index++;
			}
		return index;
	}

	private void addParameter(Frame frame, Allow.Parameter parameter, int position, String type) {
		if (parameter instanceof ReferenceParameterAllow)
			frame.addFrame(type, referenceParameter((ReferenceParameterAllow) parameter, position, type));
		else frame.addFrame(type, primitiveParameter(parameter, position, type));
	}

	private boolean isRequired(Allow.Parameter allow) {
		return allow.defaultValue() == null;
	}

	int addTerminalParameterAllows(Node node, Frame allowsFrame) {
		int index = 0;
		Collection<Allow> nodeAllows = language.allows(node.type());
		if (nodeAllows == null) return 0;
		for (Allow allow : nodeAllows)
			if (allow instanceof Allow.Parameter && isTerminal((Allow.Parameter) allow) && !isRedefined((Allow.Parameter) allow, node.variables()) && !isRequired((Allow.Parameter) allow)) {
				addParameter(allowsFrame, (Allow.Parameter) allow, index, ALLOW);
				index++;
			}
		return index;
	}

	private boolean isRedefined(Allow.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private boolean isTerminal(Allow.Parameter allow) {
		for (String flag : allow.flags())
			if (flag.equalsIgnoreCase(Tag.TERMINAL.name())) return true;
		return false;
	}

	private void addDefaultInfo(int i, Variable variable, Frame frame) {
		frame.addFrame(MULTIPLE, variable.isMultiple());
		frame.addFrame(POSITION, i);
		frame.addFrame(ANNOTATIONS, getFlags(variable));
		final Frame rule = ruleToFrame(variable.rule());
		if (rule != null) frame.addFrame(RULE, rule);
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

	private Frame referenceParameter(int i, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER, REFERENCE).
			addFrame(NAME, variable.name());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private Frame primitiveParameter(int i, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER).
			addFrame(NAME, variable.name()).
			addFrame(TYPE, variable.type());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private Frame referenceParameter(ReferenceParameterAllow parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER, REFERENCE).
			addFrame(NAME, parameter.name());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame primitiveParameter(Allow.Parameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER).
			addFrame(NAME, parameter.name()).
			addFrame(TYPE, parameter.type());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private void addDefaultInfo(Allow.Parameter parameter, Frame frame, int position) {
		final Frame rule = calculateRule(parameter);
		frame.addFrame(MULTIPLE, parameter.size()).
			addFrame(POSITION, position).
			addFrame(ANNOTATIONS, getFlags(parameter));
		if (rule != null) frame.addFrame(RULE, rule);
	}

	private Frame calculateRule(Allow.Parameter parameter) {
		final Rule rule = parameter.rule();
		if (rule == null) return null;
		final Frame frame = ruleToFrame(rule);
		return frame.addTypes(parameter.type().getName());
	}

	private String[] getFlags(Variable variable) {
		List<String> flags = variable.flags().stream().map(Tag::name).collect(Collectors.toList());
		return flags.toArray(new String[flags.size()]);
	}

	private String[] getFlags(Allow.Parameter variable) {
		List<String> flags = new ArrayList<>();
		for (String tag : variable.flags())
			if (tag.equalsIgnoreCase(Tag.TERMINAL.name())) flags.add(TERMINAL_INSTANCE.name());
			else flags.add(tag);
		return flags.toArray(new String[flags.size()]);
	}

}
