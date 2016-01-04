package tara.lang.semantics.constraints;

import tara.lang.model.*;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.flags.AnnotationCoherenceCheckerFactory;
import tara.lang.semantics.constraints.flags.FlagChecker;
import tara.lang.semantics.constraints.flags.FlagCoherenceCheckerFactory;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tara.lang.model.Tag.*;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;
import static tara.lang.semantics.errorcollector.SemanticNotification.WARNING;

public class GlobalConstraints {

	public GlobalConstraints() {
	}

	public Constraint[] all() {
		return new Constraint[]{
			parentConstraint(),
			invalidNodeFlags(),
			duplicatedTags(),
			tagsCoherence(),
			checkVariables(),
			nodeName(),
			duplicatedNames(),
			facetInstance(),
			duplicatedFacets(),
			anyFacetWithoutConstrains()};
	}

	private Constraint parentConstraint() {
		return element -> {
			Node node = (Node) element;
			final Node parent = node.parent();
			if (parent == null) return;
			parent.resolve();
			String nodeType = node.type();
			if (!parent.type().equals(nodeType)) error("reject.parent.different.type", node, asList(parent.type(), nodeType));
			if (parent.is(Instance)) error("reject.sub.of.instance", node);
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
		for (Variable variable : node.variables())
			checkVariable(variable);
	}

	private void inFacets(Node node) throws SemanticException {
		for (Facet facet : node.facets())
			for (Variable variable : facet.variables())
				checkVariable(variable);
	}

	private void checkVariable(Variable variable) throws SemanticException {
		if (!Primitive.WORD.equals(variable.type()) && !variable.defaultValues().isEmpty()) {
			if (!compatibleTypes(variable))
				error("reject.invalid.variable.type", variable, singletonList(variable.type()));
			else if (Primitive.WORD.equals(variable.type()) && !variable.defaultValues().isEmpty() && !hasCorrectValues(variable))
				error("reject.invalid.word.values", variable, singletonList((variable.rule()).errorParameters()));
		}
		if (Primitive.FUNCTION.equals(variable.type()) && variable.rule() == null)
			error("reject.nonexisting.variable.rule", variable, singletonList(variable.type()));
		if (variable.isReference() && variable.destinyOfReference() != null && variable.destinyOfReference().is(Instance))
			error("reject.instance.reference.variable", variable);
		if (!variable.defaultValues().isEmpty() && !variable.size().accept(variable.defaultValues()))
			error("reject.parameter.not.in.range", variable, Arrays.asList(variable.size().min(), variable.size().max()));
		checkVariableFlags(variable);
		if (Character.isUpperCase(variable.name().charAt(0)))
			warning("warning.variable.name.starts.uppercase", variable);

	}


	private void checkVariableFlags(Variable variable) throws SemanticException {
		final List<Tag> availableTags = Flags.forVariable();
		for (Tag tag : variable.flags())
			if (!availableTags.contains(tag)) if (tag.equals(Instance))
				error("reject.variable.in.instance", variable, singletonList(variable.name()));
			else
				error("reject.invalid.flag", variable, asList(tag.name(), variable.name()));
	}

	private boolean compatibleTypes(Variable variable) {
		List<Object> values = variable.defaultValues();
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null && PrimitiveTypeCompatibility.checkCompatiblePrimitives(variable.isReference() ? Primitive.REFERENCE : variable.type(), inferredType, variable.isMultiple());
	}

	private boolean hasCorrectValues(Variable variable) {
		return variable.rule().accept(variable.defaultValues());
	}

	private Constraint nodeName() {
		return element -> {
			Node node = (Node) element;
			node.resolve();
			if (!node.is(Instance) && node.isAnonymous() && !node.is(Prototype)) error("concept.with.no.name", node);
			if (node.is(Instance) && !node.isAnonymous() && Character.isUpperCase(node.name().charAt(0)))
				warning("warning.node.name.starts.uppercase", node);
		};
	}

	private Constraint duplicatedNames() {
		return element -> {
			Node node = (Node) element;
			checkNode(node, new HashMap<String, Element>() {
				@Override
				public Element put(String name, Element element) {
					if (isNotAcceptable(name, element)) return element;
					if (!super.containsKey(name.toLowerCase())) {
						super.put(name.toLowerCase(), element);
						return element;
					}
					return null;
				}

				public boolean isNotAcceptable(String name, Element element) {
					return element instanceof NodeRoot ||
						name == null || name.isEmpty() ||
						(element instanceof Node && ((Node) element).is(Fragment)) ||
						element.equals(super.get(name.toLowerCase())) ||
						element instanceof Variable && (((Variable) element).isOverriden() || ((Variable) element).isInherited());
				}
			});
		};
	}

	private void checkNode(NodeContainer node, Map<String, Element> names) throws SemanticException {
		for (Variable variable : node.variables())
			if (!variable.isInherited()) checkVariableDuplicity(node, names, variable);
		clearVariables(names);
		for (Node include : node.components())
			checkComponent(node, names, include);
		if (node instanceof Node)
			for (Facet facet : ((Node) node).facets()) checkNode(facet, names);
	}

	private void clearVariables(Map<String, Element> names) {
		names.entrySet().stream().
			filter(entry -> entry.getValue() instanceof Variable).map(Map.Entry::getKey).
			collect(Collectors.toList()).
			forEach(names::remove);
	}


	private void checkVariableDuplicity(NodeContainer node, Map<String, Element> names, Variable variable) throws SemanticException {
		if (!variable.isOverriden() && !variable.isInherited() && names.put(variable.name(), variable) == null)
			error("reject.duplicate.variable", variable, asList(variable.name(), node.qualifiedName()));
	}

	private void checkComponent(NodeContainer node, Map<String, Element> names, Node include) throws SemanticException {
		if (include == null) return;
		if (include.isReference() && include.destinyOfReference() != null) {
			if (names.put(include.destinyOfReference().name(), include.destinyOfReference()) == null)
				error("reject.duplicate.entries", include, asList(include.name(), node.type().isEmpty() ? "model" : node.qualifiedName()));
		} else {
			if (names.put(include.name(), include) == null)
				error("reject.duplicate.entries", include, asList(include.name(), node.type().isEmpty() ? "model" : node.qualifiedName()));
		}
	}

	private Constraint facetInstance() {
		return new FacetInstanceConstraint();
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

	private Constraint anyFacetWithoutConstrains() {
		return element -> {
			Node node = (Node) element;
			if (node.facetTarget() == null) return;
			if (node.facetTarget().target().equals(FacetTarget.ANY) && node.facetTarget().constraints().isEmpty())
				error("reject.facet.target.any.without.constrains", node.facetTarget());
		};
	}

//	private Constraint facetInstantiation() {
//		return element -> {
//			Node node = (Node) element;
//			Context context = rulesCatalog.get(node.type());
//			if (context == null) return;
//			for (Assumption assumption : context.assumptions())
//				if (assumption instanceof Assumption.FacetInstance)
//					throw new SemanticException(new SemanticNotification(ERROR, "reject.facet.as.primary", node));
//		};
//	}

	private static class FacetInstanceConstraint implements Constraint {
		@Override
		public void check(Element element) throws SemanticException {
			Node node = (Node) element;
			if (node.isSub() && node.facetTarget() != null && node.facetTarget().owner() == node) error("reject.target.in.sub", node);
			else if (node.isFacet() && hasSubs(node)) checkTargetExists(node);
		}

		private void checkTargetExists(Node node) throws SemanticException {
			if (node.isFacet() && node.facetTarget() == null && !node.isReference() && !node.isSub())
				error("no.targets.in.facet", node, singletonList(node.name()));
		}

		private boolean hasSubs(Node node) {
			return !node.subs().isEmpty();
		}
	}
}
