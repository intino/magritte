package io.intino.tara.compiler.codegeneration.lang;

import io.intino.itrules.engine.FrameBuilder;
import io.intino.itrules.model.Frame;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.VariableReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.model.rules.variable.VariableCustomRule;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.shared.Configuration.Level.Product;
import static io.intino.tara.lang.model.Tag.Instance;
import static io.intino.tara.lang.model.Tag.Reactive;
import static java.util.Collections.emptyList;

class LanguageParameterAdapter extends Generator implements TemplateTags {
	private final Language language;
	private final Level level;

	LanguageParameterAdapter(Language language, String outDSL, String workingPackage, String languageWorkingPackage, Level level) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.language = language;
		this.level = level;
	}

	void addParameterConstraint(Frame frame, String facet, int position, Variable variable, String relation) {
		if (variable instanceof VariableReference)
			frame.addSlot(relation, referenceParameter(position, facet, variable, relation));
		else frame.addSlot(relation, primitiveParameter(position, facet, variable, relation));
	}

	int addTerminalParameterConstraints(Node node, Frame constraintsFrame) {
		int index = 0;
		Collection<Constraint> constraints = language.constraints(node.type());
		if (constraints == null) return 0;
		for (Constraint c : constraints) {
			if (isSuitableParameter(node, c)) {
				addTerminalParameter(constraintsFrame, (Constraint.Parameter) c, index, CONSTRAINT);
				index++;
			}
		}
		return index;
	}

	static int terminalParameters(Language language, Node node) {
		int index = 0;
		Collection<Constraint> constraints = language.constraints(node.type());
		if (constraints == null) return 0;
		for (Constraint c : constraints) {
			if (isSuitableParameter(node, c)) {
				index++;
			}
		}
		return index;
	}

	private static boolean isSuitableParameter(Node node, Constraint c) {
		return c instanceof Constraint.Parameter &&
				isTerminal((Constraint.Parameter) c) &&
				!isRedefined((Constraint.Parameter) c, node.variables()) &&
				!isRequired((Constraint.Parameter) c) &&
				!isFilled(node, (Constraint.Parameter) c);
	}

	private void addTerminalParameter(Frame frame, Constraint.Parameter parameter, int position, String type) {
		if (parameter instanceof ReferenceParameter)
			frame.addSlot(type, referenceParameter((ReferenceParameter) parameter, position, type));
		else frame.addSlot(type, primitiveParameter(parameter, position, type));
	}

	private static boolean isTerminal(Constraint.Parameter constraint) {
		for (Tag flag : constraint.flags())
			if (flag.equals(Tag.Terminal)) return true;
		return false;
	}

	private static boolean isRequired(Constraint.Parameter constraint) {
		return constraint.size().isRequired();
	}

	private static boolean isRedefined(Constraint.Parameter constraint, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(constraint.name())) return true;
		return false;
	}

	private static boolean isFilled(Node node, Constraint.Parameter constraint) {
		for (Parameter parameter : node.parameters())
			if (parameter.name().equals(constraint.name()) && (constraint.size() == null || constraint.size().accept(parameter.values())))
				return true;
		return false;
	}

	private void addDefaultInfo(int position, Variable variable, Frame frame) {
		frame.addSlot(POSITION, position);
		frame.addSlot(TAGS, getFlags(variable));
		frame.addSlot(SCOPE, workingPackage);
		frame.addSlot(SIZE, isTerminal(variable) ? transformSizeRuleOfTerminalNode(variable) : new FrameBuilder().build(variable.size()));
		final Frame rule = (variable.rule() instanceof VariableCustomRule && ((VariableCustomRule) variable.rule()).loadedClass() == null) ? null : ruleToFrame(variable.rule());
		if (rule != null) frame.addSlot(RULE, rule);
		else if (variable.flags().contains(Reactive)) {
			final Frame ruleFrame = ruleToFrame(new NativeRule("", "", emptyList()));
			if (ruleFrame != null) frame.addSlot(RULE, ruleFrame);
		}
	}

	private boolean isTerminal(Variable variable) {
		return variable.isTerminal() && !variable.container().isTerminal() && Product.compareLevelWith(level) > 0;
	}

	private Frame transformSizeRuleOfTerminalNode(Variable variable) {
		final Size rule = variable.size();
		final Size size = new Size(0, rule.max(), rule);
		return (Frame) new FrameBuilder().build(size);
	}

	private Frame referenceParameter(int i, String facet, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER, REFERENCE).
				addSlot(NAME, variable.name()).
				addSlot(FACET, facet).
				addSlot(TYPE, variable.destinyOfReference().qualifiedName());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private Frame primitiveParameter(int i, String facet, Variable variable, String relation) {
		Frame frame = new Frame().addTypes(relation, PARAMETER).
				addSlot(NAME, variable.name()).
				addSlot(FACET, facet).
				addSlot(TYPE, variable.type());
		addDefaultInfo(i, variable, frame);
		return frame;
	}

	private Frame referenceParameter(ReferenceParameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER, REFERENCE).
				addSlot(NAME, parameter.name()).
				addSlot(FACET, parameter.facet());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private Frame primitiveParameter(Constraint.Parameter parameter, int position, String type) {
		Frame frame = new Frame().addTypes(type, PARAMETER).
				addSlot(FACET, parameter.facet()).
				addSlot(NAME, parameter.name()).
				addSlot(TYPE, parameter.type());
		addDefaultInfo(parameter, frame, position);
		return frame;
	}

	private void addDefaultInfo(Constraint.Parameter parameter, Frame frame, int position) {
		frame.addSlot(SIZE, new FrameBuilder().build(new Size(1, parameter.size().max())));
		frame.addSlot(POSITION, position);
		frame.addSlot(TAGS, getFlags(parameter));
		final Frame rule = calculateRule(parameter);
		if (rule != null) frame.addSlot(RULE, rule);
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
