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
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;
import static tara.lang.semantics.errorcollector.SemanticNotification.WARNING;

public class GlobalConstraints {

	public GlobalConstraints() {
	}

	public Constraint[] all() {
		return new Constraint[]{parentConstraint(),
			invalidNodeFlags(),
			duplicatedTags(),
			tagsCoherence(),
			checkVariables(),
			varInitInFacetTargets(),
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
			String parentType = parent.type();
			if (!parentType.equals(node.type()))
				error("reject.parent.different.type", node, asList(parentType, node.type()));
			if (parent.isInstance()) error("reject.sub.of.instance", node);
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
			inFacetTargets(node);
			inFacets(node);
		};
	}

	private void inNode(Node node) throws SemanticException {
		for (Variable variable : node.variables())
			checkVariable(variable);
	}

	private void inFacetTargets(Node node) throws SemanticException {
		for (FacetTarget facetTarget : node.facetTargets())
			for (Variable variable : facetTarget.variables())
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
		if (variable.isReference() && variable.destinyOfReference() != null && variable.destinyOfReference().isInstance())
			error("reject.declaration.reference.variable", variable);
		if (!variable.defaultValues().isEmpty() && !variable.size().accept(variable.defaultValues()))
			error("reject.parameter.not.in.range", variable, Arrays.asList(variable.size().min(), variable.size().max()));
		checkVariableFlags(variable);
		if (Character.isUpperCase(variable.name().charAt(0)))
			warning("warning.variable.name.starts.uppercase", variable);

	}


	private void checkVariableFlags(Variable variable) throws SemanticException {
		final List<Tag> availableTags = Flags.forVariable();
		for (Tag tag : variable.flags())
			if (!availableTags.contains(tag)) if (tag.equals(Tag.Instance))
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

	private Constraint varInitInFacetTargets() {
		return element -> {
			Node node = (Node) element;
			for (FacetTarget target : node.facetTargets())
				if (!target.parameters().isEmpty())
					error("reject.facet.target.with.parameter", target.parameters().get(0));
		};

	}

	private Constraint nodeName() {
		return element -> {
			Node node = (Node) element;
			node.resolve();
			if (!node.isInstance() && node.isAnonymous() && !node.isPrototype()) error("concept.with.no.name", node);
			if (node.isInstance() && !node.isAnonymous() && Character.isUpperCase(node.name().charAt(0)))
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
			for (FacetTarget facet : node.facetTargets())
				if (facet.target().equals(FacetTarget.ANY) && facet.constraints().isEmpty())
					error("reject.facet.target.any.without.constrains", facet);
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
			if (node.isFacet() && !isAbstract(node)) checkTargetExists(node);
			else checkTargetNotExist(node);
		}

		private void checkTargetExists(Node node) throws SemanticException {
			if (node.facetTargets().isEmpty() && !node.isReference() && node.subs().isEmpty() && !isAbstract(node))
				error("no.targets.in.facet", node, singletonList(node.name()));
		}

		private void checkTargetNotExist(Node node) throws SemanticException {
			if (!node.facetTargets().isEmpty())
				error("reject.target.without.facet", node);
		}

		private boolean isAbstract(Node node) {
			return node.flags().contains(Tag.Abstract) || !node.subs().isEmpty();
		}
	}
}
