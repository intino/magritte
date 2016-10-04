package tara.compiler.codegeneration.lang;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.core.CompilerConfiguration.Level;
import tara.compiler.model.VariableReference;
import tara.lang.model.Node;
import tara.lang.model.Rule;
import tara.lang.model.Tag;
import tara.lang.model.Variable;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static tara.compiler.core.CompilerConfiguration.Level.Application;
import static tara.lang.model.Tag.Instance;
import static tara.lang.model.Tag.Reactive;

class LanguageParameterAdapter extends Generator implements TemplateTags {
	private final Language language;
	private final Level level;

	LanguageParameterAdapter(Language language, String outDSL, String workingPackage, Level level) {
		super(language, outDSL, workingPackage);
		this.language = language;
		this.level = level;
	}

	void addParameterConstraint(Frame frame, String facet, int position, Variable variable, String relation) {
		if (variable instanceof VariableReference)
			frame.addFrame(relation, referenceParameter(position, facet, variable, relation));
		else frame.addFrame(relation, primitiveParameter(position, facet, variable, relation));
	}

	int addTerminalParameterConstraints(Node node, Frame allowsFrame) {
		int index = 0;
		Collection<Constraint> constraints = language.constraints(node.type());
		if (constraints == null) return 0;
		for (Constraint c : constraints)
			if (c instanceof Constraint.Parameter && isTerminal((Constraint.Parameter) c) && !isRedefined((Constraint.Parameter) c, node.variables()) && !isRequired((Constraint.Parameter) c)) {
				addParameter(allowsFrame, (Constraint.Parameter) c, index, CONSTRAINT);
				index++;
			}
		return index;
	}

	private void addParameter(Frame frame, Constraint.Parameter parameter, int position, String type) {
		if (parameter instanceof ReferenceParameter)
			frame.addFrame(type, referenceParameter((ReferenceParameter) parameter, position, type));
		else frame.addFrame(type, primitiveParameter(parameter, position, type));
	}

	private boolean isRequired(Constraint.Parameter constraint) {
		return constraint.size().isRequired();
	}

	private boolean isRedefined(Constraint.Parameter constraint, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(constraint.name())) return true;
		return false;
	}

	private boolean isTerminal(Constraint.Parameter constraint) {
		for (Tag flag : constraint.flags())
			if (flag.equals(Tag.Terminal)) return true;
		return false;
	}

	private void addDefaultInfo(int position, Variable variable, Frame frame) {
		frame.addFrame(POSITION, position);
		frame.addFrame(TAGS, getFlags(variable));
		frame.addFrame(SCOPE, workingPackage);
		frame.addFrame(SIZE, isTerminal(variable) ? transformSizeRuleOfTerminalNode(variable) : new FrameBuilder().build(variable.size()));
		final Frame rule = ruleToFrame(variable.rule());
		if (rule != null) frame.addFrame(RULE, rule);
		else if (variable.flags().contains(Reactive))
			frame.addFrame(RULE, ruleToFrame(new NativeRule("", "", emptyList())));
	}

	private boolean isTerminal(Variable variable) {
		return variable.isTerminal() && !variable.container().isTerminal() && Application.compareLevelWith(level) > 0;
	}

	private Frame transformSizeRuleOfTerminalNode(Variable variable) {
		final Size rule = variable.size();
		final Size size = new Size(0, rule.max(), rule);
		return (Frame) new FrameBuilder().build(size);
	}

	private Frame referenceParameter(int i, String facet, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER, REFERENCE).
			addFrame(NAME, variable.name()).
			addFrame(FACET, facet).
			addFrame(TYPE, variable.destinyOfReference().qualifiedName());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private Frame primitiveParameter(int i, String facet, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER).
			addFrame(NAME, variable.name()).
			addFrame(FACET, facet).
			addFrame(TYPE, variable.type());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private Frame referenceParameter(ReferenceParameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER, REFERENCE).
			addFrame(NAME, parameter.name()).
			addFrame(FACET, parameter.facet());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame primitiveParameter(Constraint.Parameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER).
			addFrame(FACET, parameter.facet()).
			addFrame(NAME, parameter.name()).
			addFrame(TYPE, parameter.type());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private void addDefaultInfo(Constraint.Parameter parameter, Frame frame, int position) {
		frame.addFrame(SIZE, new FrameBuilder().build(parameter.size()));
		frame.addFrame(POSITION, position);
		frame.addFrame(TAGS, getFlags(parameter));
		final Frame rule = calculateRule(parameter);
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
		List<Tag> flags = new ArrayList<>();
		for (Tag tag : variable.flags())
			if (tag.equals(Tag.Terminal)) flags.add(Instance);
			else flags.add(tag);
		return flags.stream().map(Enum::name).toArray(String[]::new);
	}

}
