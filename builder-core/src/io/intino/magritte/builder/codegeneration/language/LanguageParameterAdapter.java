package io.intino.magritte.builder.codegeneration.language;

import io.intino.Configuration.Artifact.Model.Level;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.adapters.ExcludeAdapter;
import io.intino.magritte.Language;
import io.intino.magritte.builder.codegeneration.TemplateTags;
import io.intino.magritte.builder.model.VariableReference;
import io.intino.magritte.lang.model.*;
import io.intino.magritte.lang.model.rules.Size;
import io.intino.magritte.lang.model.rules.variable.NativeRule;
import io.intino.magritte.lang.model.rules.variable.VariableCustomRule;
import io.intino.magritte.lang.semantics.Constraint;
import io.intino.magritte.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.intino.Configuration.Artifact.Model.Level.Product;
import static io.intino.magritte.lang.model.Tag.Instance;
import static io.intino.magritte.lang.model.Tag.Reactive;
import static java.util.Collections.emptyList;

class LanguageParameterAdapter implements TemplateTags {
	private final Language language;
	private final String workingPackage;
	private final Level level;

	LanguageParameterAdapter(Language language, String workingPackage, Level level) {
		this.language = language;
		this.workingPackage = workingPackage;
		this.level = level;
	}

	static int terminalParameters(Language language, Node node) {
		int index = 0;
		Collection<Constraint> constraints = language.constraints(node.type());
		if (constraints == null) return 0;
		for (Constraint c : constraints)
			if (isSuitableParameter(node, c)) index++;
		return index;
	}

	private static boolean isSuitableParameter(Node node, Constraint c) {
		return c instanceof Constraint.Parameter &&
				isTerminal((Constraint.Parameter) c) &&
				!isRedefined((Constraint.Parameter) c, node.variables()) &&
				!isRequired((Constraint.Parameter) c) &&
				!isFilled(node, (Constraint.Parameter) c);
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

	void addParameterConstraint(FrameBuilder frame, String aspect, int position, Variable variable, String relation) {
		if (variable instanceof VariableReference)
			frame.add(relation, referenceParameter(position, aspect, variable, relation));
		else frame.add(relation, primitiveParameter(position, aspect, variable, relation));
	}

	int addTerminalParameterConstraints(Node node, FrameBuilder constraintsFrame) {
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

	private void addTerminalParameter(FrameBuilder builder, Constraint.Parameter parameter, int position, String type) {
		if (parameter instanceof ReferenceParameter)
			builder.add(type, referenceParameter((ReferenceParameter) parameter, position, type));
		else builder.add(type, primitiveParameter(parameter, position, type));
	}

	private void addDefaultInfo(int position, Variable variable, FrameBuilder frame) {
		frame.add(POSITION, position);
		frame.add(TAGS, getFlags(variable));
		frame.add(SCOPE, workingPackage);
		frame.add(SIZE, isTerminal(variable) ? transformSizeRuleOfTerminalNode(variable) : new FrameBuilder().append(variable.size()).toFrame());
		final Frame rule = (variable.rule() == null || (variable.rule() instanceof VariableCustomRule && ((VariableCustomRule) variable.rule()).loadedClass() == null)) ?
				null :
				ruleToFrame(variable.rule()).toFrame();
		if (rule != null) frame.add(RULE, rule);
		else if (variable.flags().contains(Reactive)) {
			final FrameBuilder ruleFrame = ruleToFrame(new NativeRule("", "", emptyList()));
			if (ruleFrame != null) frame.add(RULE, ruleFrame.toFrame());
		}
	}

	protected FrameBuilder ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder builder = new FrameBuilder();
		builder.put(Rule.class, new ExcludeAdapter<>("loadedClass"));
		builder.append(rule);
		if (rule instanceof VariableCustomRule) {
			FrameBuilder frameBuilder = new FrameBuilder("customRule");
			frameBuilder.add(QN, cleanQn(((VariableCustomRule) rule).qualifiedName()));
			frameBuilder.add("aClass", cleanQn(((VariableCustomRule) rule).externalClass()));
			if (((VariableCustomRule) rule).isMetric()) {
				frameBuilder.add(METRIC);
				frameBuilder.add(DEFAULT, ((VariableCustomRule) rule).getDefaultUnit());
			}
			return frameBuilder;
		}
		return builder;
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANONYMOUS, "").replace("[", "").replace("]", "").replace(":", "").replace("$", ".");
	}

	private boolean isTerminal(Variable variable) {
		return variable.isTerminal() && !variable.container().isTerminal() && Product.compareLevelWith(level) > 0;
	}

	private Frame transformSizeRuleOfTerminalNode(Variable variable) {
		final Size rule = variable.size();
		final Size size = new Size(0, rule.max(), rule);
		return new FrameBuilder().append(size).toFrame();
	}

	private Frame referenceParameter(int i, String aspect, Variable variable, String relation) {
		FrameBuilder builder = new FrameBuilder(relation, PARAMETER, REFERENCE).
				add(NAME, variable.name()).
				add(ASPECT, aspect).
				add(TYPE, variable.destinyOfReference().qualifiedName());
		addDefaultInfo(i, variable, builder);
		return builder.toFrame();
	}

	private Frame primitiveParameter(int i, String aspect, Variable variable, String relation) {
		FrameBuilder builder = new FrameBuilder(relation, PARAMETER).
				add(NAME, variable.name()).
				add(ASPECT, aspect).
				add(TYPE, variable.type());
		addDefaultInfo(i, variable, builder);
		return builder.toFrame();
	}

	private Frame referenceParameter(ReferenceParameter parameter, int position, String type) {
		FrameBuilder builder = new FrameBuilder(type, PARAMETER, REFERENCE).
				add(NAME, parameter.name()).
				add(ASPECT, parameter.aspect());
		addDefaultInfo(parameter, builder, position);
		return builder.toFrame();
	}

	private Frame primitiveParameter(Constraint.Parameter parameter, int position, String type) {
		FrameBuilder builder = new FrameBuilder(type, PARAMETER).
				add(ASPECT, parameter.aspect()).
				add(NAME, parameter.name()).
				add(TYPE, parameter.type());
		addDefaultInfo(parameter, builder, position);
		return builder.toFrame();
	}

	private void addDefaultInfo(Constraint.Parameter parameter, FrameBuilder frame, int position) {
		frame.add(SIZE, new FrameBuilder().append(new Size(1, parameter.size().max())).toFrame());
		frame.add(POSITION, position);
		frame.add(TAGS, getFlags(parameter));
		final Frame rule = calculateRule(parameter);
		if (rule != null) frame.add(RULE, rule);
	}

	private Frame calculateRule(Constraint.Parameter parameter) {
		final Rule rule = parameter.rule();
		if (rule == null) return null;
		final FrameBuilder builder = ruleToFrame(rule);
		return builder.add(parameter.type().getName()).toFrame();
	}

	private String[] getFlags(Variable variable) {
		return variable.flags().stream().map(Tag::name).toArray(String[]::new);
	}

	private String[] getFlags(Constraint.Parameter variable) {
		List<Tag> flags = new ArrayList<>();
		for (Tag tag : variable.flags())
			if (tag.equals(Tag.Terminal)) flags.add(Instance);
			else flags.add(tag);
		return flags.stream().map(Enum::name).toArray(String[]::new);
	}
}