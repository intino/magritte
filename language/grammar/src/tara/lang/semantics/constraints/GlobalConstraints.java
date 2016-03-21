package tara.lang.semantics.constraints;

import tara.lang.model.*;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.flags.AnnotationCoherenceCheckerFactory;
import tara.lang.semantics.constraints.flags.FlagChecker;
import tara.lang.semantics.constraints.flags.FlagCoherenceCheckerFactory;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tara.dsl.ProteoConstants.FACET;
import static tara.dsl.ProteoConstants.METAFACET;
import static tara.lang.model.Primitive.*;
import static tara.lang.model.Tag.*;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.WARNING;

public class GlobalConstraints {

	public GlobalConstraints() {
	}

	public Constraint[] all() {
		return new Constraint[]{
			parentConstraint(),
			referencesInInstances(),
			invalidNodeFlags(),
			duplicatedTags(),
			tagsCoherence(),
			checkVariables(),
			nodeName(),
			facetInstance(),
			abstractFacetTarget(),
			duplicatedFacets(),
			facetAnyWithoutConstrains()};
	}

	private Constraint parentConstraint() {
		return element -> {
			Node node = (Node) element;
			final Node parent = node.parent();
			if (parent == null) return;
			parent.resolve();
			String nodeType = node.type();
			if (!parent.type().equals(nodeType.split(":")[0]) && !parent.type().equals(nodeType))
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

	private static void error(String message, Element element, List<?> parameters) throws SemanticException {
		throw new SemanticException(new SemanticNotification(ERROR, message, element, parameters));
	}

	private static void error(String message, Element element) throws SemanticException {
		throw new SemanticException(new SemanticNotification(ERROR, message, element));
	}

	private void warning(String message, Element element) throws SemanticException {
		throw new SemanticException(new SemanticNotification(WARNING, message, element));
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
			else if (node.container() instanceof NodeRoot) availableTags = Flags.forRoot();
			else availableTags = Flags.forComponent();
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
			inNode(node);
			inFacets(node);
		};
	}

	private void inNode(Node node) throws SemanticException {
		checkDuplicates(node.variables());
		for (Variable variable : node.variables())
			checkVariable(variable);
	}

	private void inFacets(Node node) throws SemanticException {
		for (Facet facet : node.facets())
			for (Variable variable : facet.variables())
				error("reject.variable.in.variable", variable, Collections.emptyList());
	}

	private void checkDuplicates(List<Variable> variables) throws SemanticException {
		Set<String> names = new LinkedHashSet();
		for (Variable variable : variables) {
			if (!names.add(variable.name())) error("reject.duplicated.variable", variable, Collections.emptyList());
		}
	}

	private void checkVariable(Variable variable) throws SemanticException {
		final List<Object> values = variable.values();
		if (!WORD.equals(variable.type()) && !values.isEmpty() && !compatibleTypes(variable))
			error("reject.invalid.variable.type", variable, singletonList(variable.type()));
		else if (WORD.equals(variable.type()) && !values.isEmpty() && !hasCorrectValues(variable))
			error("reject.invalid.word.values", variable, singletonList((variable.rule()).errorParameters()));
		else if (FUNCTION.equals(variable.type()) && variable.rule() == null)
			error("reject.nonexisting.variable.rule", variable, singletonList(variable.type()));
		else if (REFERENCE.equals(variable.type()) && !hasCorrectReferenceValues(variable))
			error("reject.default.value.reference.variable", variable);
		else if (!values.isEmpty() && values.get(0) instanceof Primitive.Expression && !variable.flags().contains(Native) && !variable.type().equals(FUNCTION))
			error("reject.expression.value.in.non.native", variable, singletonList(variable.type()));
		else if (variable.isReference() && variable.destinyOfReference() != null && variable.destinyOfReference().is(Instance))
			error("reject.instance.reference.variable", variable);
		else if (!values.isEmpty() && !variable.size().accept(values))
			error("reject.parameter.not.in.range", variable, asList(variable.size().min(), variable.size().max()));
		checkVariableFlags(variable);
		if (Character.isUpperCase(variable.name().charAt(0)))
			warning("warning.variable.name.starts.uppercase", variable);
	}

	private boolean hasCorrectReferenceValues(Variable variable) throws SemanticException {
		for (Object object : variable.values())
			if (!(object instanceof EmptyNode) && !(object instanceof Expression))
				return false;
		return true;
	}

	private void checkVariableFlags(Variable variable) throws SemanticException {
		if (variable.flags().contains(Tag.Native) && FUNCTION.equals(variable.type()))
			error("reject.function.variable.with.native.flag", variable, singletonList(variable.name()));
		final List<Tag> availableTags = Flags.forVariable();
		for (Tag tag : variable.flags())
			if (!availableTags.contains(tag))
				if (tag.equals(Instance))
					error("reject.variable.in.instance", variable, singletonList(variable.name()));
				else error("reject.invalid.flag", variable, asList(tag.name(), variable.name()));
	}

	private boolean compatibleTypes(Variable variable) {
		List<Object> values = variable.values();
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null && PrimitiveTypeCompatibility.checkCompatiblePrimitives(variable.isReference() ? REFERENCE : variable.type(), inferredType, variable.isMultiple());
	}

	private boolean hasCorrectValues(Variable variable) {
		return variable.rule().accept(variable.values());
	}

	private Constraint nodeName() {
		return element -> {
			Node node = (Node) element;
			node.resolve();
			if (!node.is(Instance) && node.isAnonymous() && !node.is(Prototype)) error("concept.with.no.name", node);
//			if (node.is(Instance) && node.name() != null && !node.name().isEmpty() && Character.isUpperCase(node.name().charAt(0)))
//				warning("warning.node.name.starts.uppercase", node);
		};
	}

	private Constraint facetInstance() {
		return new Constraint() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (node.isSub() && node.facetTarget() != null && node.facetTarget().owner() == node)
					error("reject.target.in.sub", node);
				else if (node.isFacet() && hasSubs(node)) checkTargetExists(node);
			}

			private void checkTargetExists(Node node) throws SemanticException {
				if (node.isFacet() && !node.isReference() && node.facetTarget() == null && !node.isSub())
					error("no.targets.in.facet", node, singletonList(node.name()));
			}

			private boolean hasSubs(Node node) {
				return !node.subs().isEmpty();
			}
		};
	}

	private Constraint duplicatedFacets() {
		return element -> {
			Node node = (Node) element;
			Set<String> facets = new HashSet<>();
			for (Facet facet : node.facets())
				if (!facets.add(facet.type()))
					error("reject.duplicated.facet", facet);
		};
	}

	private Constraint facetAnyWithoutConstrains() {
		return element -> {
			Node node = (Node) element;
			if (node.facetTarget() == null) return;
			if (node.facetTarget().target().equals(FacetTarget.ANY) && node.facetTarget().constraints().isEmpty())
				error("reject.facet.target.any.without.constrains", node.facetTarget());
		};
	}

	private Constraint abstractFacetTarget() {
		return element -> {
			Node node = (Node) element;
			if ((node.type().equals(METAFACET) || node.type().equals(FACET)) && node.facetTarget() == null && !node.isAbstract() && node.subs().isEmpty())
				error("no.targets.in.facet", node, singletonList(node.name()));
		};
	}

}
