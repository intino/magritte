package io.intino.tara.lang.semantics.constraints;

import io.intino.tara.Language;
import io.intino.tara.dsl.ProteoConstants;
import io.intino.tara.lang.model.Aspect;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.model.rules.variable.VariableCustomRule;
import io.intino.tara.lang.model.rules.variable.WordRule;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.constraints.flags.AnnotationCoherenceCheckerFactory;
import io.intino.tara.lang.semantics.constraints.flags.FlagChecker;
import io.intino.tara.lang.semantics.constraints.flags.FlagCoherenceCheckerFactory;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.*;

import static io.intino.tara.lang.model.Primitive.WORD;
import static io.intino.tara.lang.model.Tag.*;
import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;
import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.WARNING;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class GlobalConstraints {

	public GlobalConstraints() {
	}

	private static boolean isOverridden(Variable variable, Variable parentVar) {
		return parentVar.type() != null && parentVar.type().equals(variable.type()) && parentVar.name() != null && parentVar.name().equals(variable.name());
	}

	private static void error(String message, Element element, List<?> parameters) throws SemanticException {
		throw new SemanticException(new SemanticNotification(ERROR, message, element, parameters));
	}

	private static void error(String message, Element element) throws SemanticException {
		throw new SemanticException(new SemanticNotification(ERROR, message, element));
	}

	public Constraint[] all() {
		return new Constraint[]{
				parentConstraint(),
				referencesInInstances(),
				invalidNodeFlags(),
				duplicatedTags(),
				duplicatedInstances(),
				tagsCoherence(),
				invalidNodeRules(),
				checkVariables(),
				nodeName(),
				duplicatedAspects()
		};
	}

	private Constraint invalidNodeRules() {
		return element -> {
			Node node = (Node) element;
			for (Node component : node.components())
				if (node.rulesOf(component).stream().filter(r -> r instanceof Size).count() > 1)
					error("reject.component.with.multiple.size.rule", node);
		};
	}

	private Constraint parentConstraint() {
		return element -> {
			Node node = (Node) element;
			final Node parent = node.parent();
			if (parent == null) return;
			parent.resolve();
			String nodeType = node.type();
			if (parent.type().equals(ProteoConstants.FACET)) {
				if (!nodeType.equals(ProteoConstants.FACET) && !nodeType.equals(ProteoConstants.ASPECT) && !nodeType.equals(ProteoConstants.META_ASPECT))
					error("reject.parent.different.type", node, asList(parent.type(), nodeType));
			} else if (!parent.type().equals(nodeType.split(":")[0]) && !parent.type().equals(nodeType))
				error("reject.parent.different.type", node, asList(parent.type(), nodeType));
			if (parent.is(Instance)) error("reject.sub.of.instance", node);
		};
	}

	private Constraint referencesInInstances() {
		return element -> {
			Node node = (Node) element;
			if (!node.is(Instance)) return;
			final Node reference = node.components().stream().filter(Node::isReference).findFirst().orElse(null);
			if (reference != null)
				error("reject.reference.in.instance", node, Collections.emptyList());
		};
	}

	private Constraint duplicatedInstances() {
		return element -> {
			Node node = (Node) element;
			if (!node.is(Instance) || node.isAnonymous()) return;
			final List<Node> siblings = node.siblings();
			for (Node sibling : siblings)
				if (node.name().equals(sibling.name()) && node.name().equals(sibling.file()))
					error("reject.duplicate.entries", node, Collections.singletonList(node.container().name()));
		};
	}

	private Constraint duplicatedTags() {
		return element -> {
			Node node = (Node) element;
			Set<String> tags = new HashSet<>();
			for (Tag annotation : node.annotations()) {
				if (tags.add(annotation.name())) continue;
				error("reject.duplicate.annotation", node, asList(annotation, node.type()));
			}
			tags.clear();
			for (Tag flag : node.flags())
				if (!tags.add(flag.name()))
					error("reject.duplicate.flag", node, asList(flag, node.type() + " " + node.name()));
		};
	}

	private Constraint invalidNodeFlags() {
		return element -> {
			Node node = (Node) element;
			if (node.flags().isEmpty()) return;
			List<Tag> availableTags;
			if (node.isReference()) return;//TODO check referenceFlags
			else if (node.container() instanceof NodeRoot) {
				availableTags = new ArrayList<>(Flags.forRoot());
				if (!node.isAspect()) availableTags.remove(Required);
			} else availableTags = Flags.forComponent();
			for (Tag tag : node.flags())
				if (!isInternalFlag(tag) && !availableTags.contains(tag))
					error("reject.invalid.flag", node, asList(tag.name(), node.type()));
		};
	}

	private boolean isInternalFlag(Tag tag) {
		return Flags.internalTags().contains(tag);
	}

	private Constraint tagsCoherence() {
		return element -> {
			Node node = (Node) element;
			for (Tag tag : node.flags()) checkFlagConstrains(tag.name(), node);
			for (Tag tag : node.annotations()) checkAnnotationConstrains(tag.name(), node);
			if (node.isTerminal() && !node.annotations().isEmpty()) error("reject.annotations.in.terminal", node);
		};
	}

	private void checkFlagConstrains(String flag, Node node) throws SemanticException {
		FlagChecker aClass = FlagCoherenceCheckerFactory.get(flag.toLowerCase());
		if (aClass != null) aClass.check(node);
	}

	private void checkAnnotationConstrains(String flag, Node node) throws SemanticException {
		FlagChecker aClass = AnnotationCoherenceCheckerFactory.get(flag.toLowerCase());
		if (aClass != null) aClass.check(node);
	}

	private Constraint checkVariables() {
		return element -> {
			Node node = (Node) element;
			checkDuplicatesBetween(node.components(), node.variables());
			checkDuplicates(node.variables());
			checkDuplicates(node.parameters());
			for (Variable variable : node.variables()) checkVariable(variable);
		};
	}

	private void checkDuplicatesBetween(List<Node> components, List<Variable> variables) throws SemanticException {
		for (Variable var : variables) {
			if (components.stream().anyMatch(c -> var.name().equals(c.name())))
				error("reject.duplicated.name.between.variables.and.components", var, Collections.singletonList(var.name()));
		}
	}

	private void checkDuplicates(List<? extends Valued> values) throws SemanticException {
		Set<String> names = new LinkedHashSet();
		for (Valued valued : values)
			if (valued.name() != null && !valued.name().isEmpty() && !names.add(valued.name()))
				error("reject.duplicated.valued", valued, Collections.singletonList(valued.getClass().getSimpleName().contains("Parameter") ? "parameter" : "variable"));
	}

	private void checkVariable(Variable variable) throws SemanticException {
		final List<Object> values = variable.values();
		if (variable.container().is(Instance)) error("reject.variable.in.node", variable);
		else if (WORD.equals(variable.type()) && !values.isEmpty() && !hasCorrectValues(variable) && variable.rule() != null)
			error("reject.invalid.word.values", variable, singletonList((variable.rule()).errorParameters()));
		else if (WORD.equals(variable.type()) && variable.name().equals(variable.container().name()))
			error("reject.invalid.word.name", variable, singletonList((variable.rule()).errorParameters()));
		else if (WORD.equals(variable.type()) && variable.rule() instanceof WordRule && ((WordRule) variable.rule()).words().isEmpty())
			error("reject.invalid.word.names", variable, Collections.emptyList());
		else if (!WORD.equals(variable.type()) && !values.isEmpty() && !compatibleTypes(variable))
			error("reject.invalid.variable.type", variable, singletonList(variable.type().javaName()));
		else if (Primitive.FUNCTION.equals(variable.type()) && variable.rule() == null)
			error("reject.nonexisting.variable.rule", variable, singletonList(variable.type().javaName()));
		else if (Primitive.REFERENCE.equals(variable.type()) && !hasCorrectReferenceValues(variable))
			error("reject.default.value.reference.variable", variable);
		else if (Primitive.INSTANT.equals(variable.type()) && !hasCorrectInstantValues(variable))
			error("reject.value.instant.variable", variable);
		else if (variable.isReference() && variable.destinyOfReference() != null && variable.destinyOfReference().is(Instance))
			error("reject.default.value.reference.to.instance", variable);
		else if (!variable.isReference() && isRedefiningTerminal(variable))
			error("reject.terminal.variable.redefinition", variable);
		else if (!values.isEmpty() && !variable.size().accept(values))
			error("reject.element.not.in.range", variable, asList(variable.size().min(), variable.size().max()));
		else if (!values.isEmpty() && !(values.get(0) instanceof EmptyNode) && variable.rule() != null && !(variable.rule() instanceof NativeRule) && !hasExpressionValue(values) && !variable.rule().accept(values, variable.defaultMetric())) {
			final String message = variable.rule().errorMessage();
			error(message == null || message.isEmpty() ? "custom.rule.class.not.comply" : message, variable, singletonList((variable.rule()).errorParameters()));
		}

		checkVariableFlags(variable);
		if (variable.name() != null && Character.isUpperCase(variable.name().charAt(0)))
			warning("warning.variable.name.starts.uppercase", variable);
	}

	private boolean hasCorrectInstantValues(Variable variable) {
		return variable.values().stream().filter(v -> !(v instanceof Primitive.Expression)).noneMatch(o -> o.toString().isEmpty());
	}

	private boolean isRedefiningTerminal(Variable variable) {
		final Language language = variable.language();
		if (language == null || variable.container() == null) return false;
		final List<Constraint> constraints = language.constraints(variable.container().type());
		if (constraints == null) return false;
		final Constraint constraint = constraints.stream().filter(c -> c instanceof Constraint.Parameter && ((Constraint.Parameter) c).name().equals(variable.name())).findFirst().orElse(null);
		return constraint != null && (((Constraint.Parameter) constraint).flags().contains(Tag.Terminal));
	}

	private boolean hasCorrectReferenceValues(Variable variable) {
		for (Object value : variable.values())
			if (!(value instanceof EmptyNode || hasInstanceValue(variable.values()) || hasExpressionValue(variable.values())))
				return false;
		return true;
	}

	private void checkVariableFlags(Variable variable) throws SemanticException {
		if (variable.flags().contains(Tag.Private) && !variable.isInherited() && !isInAbstract(variable) && variable.values().isEmpty())
			error("reject.private.variable.without.default.value", variable, singletonList(variable.name()));
		if (variable.flags().contains(Reactive) && variable.type().equals(Primitive.FUNCTION))
			error("reject.invalid.flag", variable, asList(Reactive.name(), variable.name()));
		if (!WORD.equals(variable.type()) && variable.flags().contains(Reactive) && variable.rule() != null && (variable.rule() instanceof VariableCustomRule)) {
			if (variable.values().isEmpty() || hasExpressionValue(variable))
				error("reject.reactive.variable.with.rules", variable, asList(Reactive.name(), variable.name()));
			else if (variable.rule() instanceof VariableCustomRule || !hasExpressionValue(variable))
				error("reject.reactive.with.no.expression.value", variable, asList(Reactive.name(), variable.name()));
		}
		final List<Tag> availableTags = Flags.forVariable();
		for (Tag tag : variable.flags())
			if (!availableTags.contains(tag))
				if (tag.equals(Instance))
					error("reject.variable.in.instance", variable, singletonList(variable.name()));
				else error("reject.invalid.flag", variable, asList(tag.name(), variable.name()));
		Variable parentVariable = findParentVariable(variable);
		if (parentVariable != null) checkParentVariables(variable, parentVariable);
	}

	private boolean hasExpressionValue(Variable variable) {
		return variable.values().get(0) instanceof Primitive.Expression || variable.values().get(0) instanceof Primitive.MethodReference;
	}

	private void checkParentVariables(Variable variable, Variable parentVariable) throws SemanticException {
		if (parentVariable.flags().contains(Reactive) != variable.flags().contains(Reactive))
			error("reject.parent.variable.tags", variable);

	}

	private Variable findParentVariable(Variable variable) {
		Node node = variable.container();
		if (node == null) return null;
		Node parent = node.parent();
		while (parent != null) {
			for (Variable parentVar : parent.variables())
				if (isOverridden(variable, parentVar))
					return parentVar;
			parent = parent.parent();
		}
		return null;
	}

	private boolean isInAbstract(Variable variable) {
		return variable.container() != null && variable.container().isAbstract();
	}

	private boolean compatibleTypes(Variable variable) {
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(variable.values().get(0));
		return inferredType != null && PrimitiveTypeCompatibility.checkCompatiblePrimitives(variable.isReference() ? Primitive.REFERENCE : variable.type(), inferredType, variable.isMultiple());
	}

	private boolean hasCorrectValues(Variable variable) {
		return variable.values().get(0) instanceof EmptyNode || hasExpressionValue(variable.values()) || (variable.rule() != null && variable.rule().accept(variable.values()));
	}

	private boolean hasExpressionValue(List<Object> values) {
		return !values.isEmpty() && (values.get(0) instanceof Primitive.Expression || values.get(0) instanceof Primitive.MethodReference);
	}

	private boolean hasInstanceValue(List<Object> values) {
		return !values.isEmpty() && asPrimitiveReference(values) || asNode(values);
	}

	private boolean asNode(List<Object> values) {
		return (values.get(0) instanceof Node) && (((Node) values.get(0)).is(Instance));
	}

	private boolean asPrimitiveReference(List<Object> values) {
		return (values.get(0) instanceof Primitive.Reference) && (((Primitive.Reference) values.get(0)).reference().is(Instance));
	}

	private Constraint nodeName() {
		return element -> {
			Node node = (Node) element;
			node.resolve();
			if (!node.is(Instance) && node.isAnonymous()) error("concept.with.no.name", node);
			else if (node.is(Instance)) return;
			if (node.container() != null && node.container() != null && !node.isReference() && !node.isAnonymous() && node.name().equals(node.container().name()))
				error("reject.container.and.component.namesake", node);
		};
	}

	private Constraint duplicatedAspects() {
		return element -> {
			Node node = (Node) element;
			Set<String> aspects = new HashSet<>();
			for (Aspect aspect : node.appliedAspects())
				if (!aspects.add(aspect.type()))
					error("reject.duplicated.aspect", aspect);
		};
	}

	private void warning(String message, Element element) throws SemanticException {
		throw new SemanticException(new SemanticNotification(WARNING, message, element));
	}
}