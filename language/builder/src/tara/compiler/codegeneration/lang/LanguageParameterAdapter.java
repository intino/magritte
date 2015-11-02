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
import tara.lang.model.rules.variable.CustomRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.parameter.ReferenceParameter;

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

	void addParameterConstraint(Frame frame, int i, Variable variable, String relation) {
		if (variable instanceof VariableReference)
			frame.addFrame(relation, referenceParameter(i, variable, relation));
		else frame.addFrame(relation, primitiveParameter(i, variable, relation));
	}

	int addTerminalParameterRequires(Node node, Frame requires) {
		int index = 0;
		Collection<Constraint> constraints = language.constraints(node.type());
		if (constraints == null) return 0;
		for (Constraint allow : constraints)
			if (allow instanceof Constraint.Parameter && isTerminal((Constraint.Parameter) allow) && !isRedefined((Constraint.Parameter) allow, node.variables()) && isRequired((Constraint.Parameter) allow)) {
				addParameter(requires, (Constraint.Parameter) allow, index, CONSTRAINT);
				index++;
			}
		return index;
	}

	private void addParameter(Frame frame, Constraint.Parameter parameter, int position, String type) {
		if (parameter instanceof ReferenceParameter)
			frame.addFrame(type, referenceParameter((ReferenceParameter) parameter, position, type));
		else frame.addFrame(type, primitiveParameter(parameter, position, type));
	}

	private boolean isRequired(Constraint.Parameter allow) {
		return allow.defaultValue() == null;
	}

	int addTerminalParameterAllows(Node node, Frame allowsFrame) {
		int index = 0;
		Collection<Constraint> nodeAllows = language.constraints(node.type());
		if (nodeAllows == null) return 0;
		for (Constraint allow : nodeAllows)
			if (allow instanceof Constraint.Parameter && isTerminal((Constraint.Parameter) allow) && !isRedefined((Constraint.Parameter) allow, node.variables()) && !isRequired((Constraint.Parameter) allow)) {
				addParameter(allowsFrame, (Constraint.Parameter) allow, index, CONSTRAINT);
				index++;
			}
		return index;
	}

	private boolean isRedefined(Constraint.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private boolean isTerminal(Constraint.Parameter allow) {
		for (String flag : allow.annotations())
			if (flag.equalsIgnoreCase(Tag.TERMINAL.name())) return true;
		return false;
	}

	private void addDefaultInfo(int i, Variable variable, Frame frame) {
		frame.addFrame(MULTIPLE, variable.isMultiple());
		frame.addFrame(POSITION, i);
		frame.addFrame(ANNOTATIONS, getFlags(variable));
		frame.addFrame(SIZE, new FrameBuilder().build(variable.size()));
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

	private Frame referenceParameter(ReferenceParameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER, REFERENCE).
			addFrame(NAME, parameter.name());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame primitiveParameter(Constraint.Parameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER).
			addFrame(NAME, parameter.name()).
			addFrame(TYPE, parameter.type());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private void addDefaultInfo(Constraint.Parameter parameter, Frame frame, int position) {
		final Frame rule = calculateRule(parameter);
		frame.addFrame(MULTIPLE, parameter.size()).
			addFrame(POSITION, position).
			addFrame(ANNOTATIONS, getFlags(parameter));
		if (rule != null) frame.addFrame(RULE, rule);
	}

	private Frame calculateRule(Constraint.Parameter parameter) {
		final Rule rule = parameter.rule();
		if (rule == null) return null;
		final Frame frame = ruleToFrame(rule);
		return frame.addTypes(parameter.type().getName());
	}

	private String[] getFlags(Variable variable) {
		List<String> flags = variable.flags().stream().map(Tag::name).collect(Collectors.toList());
		return flags.toArray(new String[flags.size()]);
	}

	private String[] getFlags(Constraint.Parameter variable) {
		List<String> flags = new ArrayList<>();
		for (String tag : variable.annotations())
			if (tag.equalsIgnoreCase(Tag.TERMINAL.name())) flags.add(TERMINAL_INSTANCE.name());
			else flags.add(tag);
		return flags.toArray(new String[flags.size()]);
	}

}
