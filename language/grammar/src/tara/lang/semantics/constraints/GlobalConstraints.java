package tara.lang.semantics.constraints;

import tara.lang.model.*;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;
import tara.lang.semantics.constraints.flags.AnnotationChecker;
import tara.lang.semantics.constraints.flags.FlagCheckerFactory;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;
import static tara.lang.semantics.errorcollector.SemanticNotification.WARNING;

public class GlobalConstraints {

	private final Map<String, Context> rulesCatalog;

	public GlobalConstraints(Map<String, Context> rulesCatalog) {
		this.rulesCatalog = rulesCatalog;
	}

	public Constraint[] all() {
		return new Constraint[]{parentConstraint(),
			invalidNodeFlags(),
			duplicatedAnnotations(),
			invalidVariableFlags(),
			duplicatedFlags(),
			flagsCoherence(),
			duplicatedNames(),
			invalidValueTypeInVariable(),
			declarationReferenceVariables(),
			varInitInFacetTargets(),
			variableName(),
			cardinalityInVariable(),
			wordValuesInVariable(),
			contractExistence(),
			facetDeclaration(),
			facetInstantiation(),
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
				throw new SemanticException(new SemanticNotification(ERROR, "reject.parent.different.type", node, asList(parentType, node.type())));
			if (parent.isTerminalInstance())
				throw new SemanticException(new SemanticNotification(ERROR, "reject.sub.of.declaration", node));
		};
	}

	private Constraint duplicatedAnnotations() {
		return element -> {
			Node node = (Node) element;
			Set<String> annotations = new HashSet<>();
			for (Tag annotation : node.annotations()) {
				if (annotations.add(annotation.name())) continue;
				throw new SemanticException(new SemanticNotification(ERROR, "reject.duplicate.annotation", node, asList(annotation, node.type())));
			}
		};
	}

	private Constraint invalidNodeFlags() {
		return element -> {
			Node node = (Node) element;
			List<Tag> availableTags;
			if (node.isReference()) return;//TODO check referenceFlags
			else if (node.container() == null) availableTags = Flags.primeTags();
			else if (node.container() == null) availableTags = Flags.primeTags();
			else availableTags = Flags.componentTags();
			for (Tag tag : node.flags())
				if (!isInternalFlag(tag) && !availableTags.contains(tag))
					throw new SemanticException(new SemanticNotification(ERROR, "reject.invalid.flag", node, asList(tag.name(), node.name())));
		};
	}

	private boolean isInternalFlag(Tag tag) {
		return Flags.internalTags().contains(tag);
	}

	private Constraint invalidVariableFlags() {
		return element -> {
			Node node = (Node) element;
			final List<Tag> availableTags = Flags.variableTags();
			for (Variable variable : node.variables())
				for (Tag tag : variable.flags())
					if (!availableTags.contains(tag)) if (tag.equals(Tag.TERMINAL_INSTANCE))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.variable.in.declaration", variable, singletonList(variable.name())));
					else
						throw new SemanticException(new SemanticNotification(ERROR, "reject.invalid.flag", variable, asList(tag.name(), variable.name())));
		};
	}

	private Constraint duplicatedFlags() {
		return element -> {
			Node node = (Node) element;
			Set<String> flags = new HashSet<>();
			for (Tag flag : node.flags()) {
				if (flags.add(flag.name())) continue;
				throw new SemanticException(new SemanticNotification(ERROR, "reject.duplicate.flag", node, asList(flag, node.type() + " " + node.name())));
			}
		};
	}

	private Constraint flagsCoherence() {
		return element -> {
			Node node = (Node) element;
			for (Tag flags : node.flags())
				checkFlagConstrains(flags.name(), node);
		};
	}

	private void checkFlagConstrains(String flag, Node node) throws SemanticException {
		try {
			Class<? extends AnnotationChecker> aClass = FlagCheckerFactory.get(flag.toLowerCase());
			if (aClass == null) return;
			aClass.newInstance().check(node);
		} catch (InstantiationException | IllegalAccessException ignored) {
			ignored.printStackTrace();
		}

	}

	private Constraint invalidValueTypeInVariable() {
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
		if (!Primitive.WORD.equals(variable.type()) && !variable.defaultValues().isEmpty() && !compatibleTypes(variable))
			throw new SemanticException(new SemanticNotification(ERROR, "reject.invalid.variable.type", variable, singletonList(variable.type())));
	}

	private Constraint declarationReferenceVariables() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables())
				if (variable.isReference() && variable.destinyOfReference() != null && variable.destinyOfReference().isTerminalInstance())
					throw new SemanticException(new SemanticNotification(ERROR, "reject.declaration.reference.variable", variable));
		};
	}

	private Constraint varInitInFacetTargets() {
		return element -> {
			Node node = (Node) element;
			for (FacetTarget target : node.facetTargets())
				if (!target.parameters().isEmpty())
					throw new SemanticException(new SemanticNotification(ERROR, "reject.facet.target.with.parameter", target.parameters().get(0)));
		};

	}


	private Constraint cardinalityInVariable() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
				final Size size = variable.size();
				if (!variable.defaultValues().isEmpty() && !size.accept(variable.defaultValues()))
					throw new SemanticException(new SemanticNotification(ERROR, "reject.parameter.not.in.range", variable, Arrays.asList(size.min(), size.max())));
			}
		};
	}

	private Constraint variableName() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
				if (Character.isUpperCase(variable.name().charAt(0)))
					throw new SemanticException(new SemanticNotification(WARNING, "warning.variable.name.starts.uppercase", variable));
			}
		};
	}

	private Constraint wordValuesInVariable() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
				if (Primitive.WORD.equals(variable.type()) && !variable.defaultValues().isEmpty() && !hasCorrectValues(variable))
					throw new SemanticException(new SemanticNotification(ERROR, "reject.invalid.word.values", variable, singletonList((variable.rule()).errorParameters())));
			}
		};
	}

	private boolean hasCorrectValues(Variable variable) {
		return variable.rule().accept(variable.defaultValues());
	}


	private boolean compatibleTypes(Variable variable) {
		List<Object> values = variable.defaultValues();
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null && PrimitiveTypeCompatibility.checkCompatiblePrimitives(variable.isReference() ? Primitive.REFERENCE : variable.type(), inferredType, variable.isMultiple());
	}

	private Constraint contractExistence() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
				if (Primitive.FUNCTION.equals(variable.type()) && variable.rule() == null)
					throw new SemanticException(new SemanticNotification(ERROR, "reject.nonexisting.variable.rule", variable, singletonList(variable.type())));
			}
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
			if (!variable.isInherited()) checkVariable(node, names, variable);
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


	private void checkVariable(NodeContainer node, Map<String, Element> names, Variable variable) throws SemanticException {
		if (!variable.isOverriden() && !variable.isInherited() && names.put(variable.name(), variable) == null)
			throw new SemanticException(new SemanticNotification(ERROR, "reject.duplicate.variable", variable, asList(variable.name(), node.qualifiedName())));
	}

	private void checkComponent(NodeContainer node, Map<String, Element> names, Node include) throws SemanticException {
		if (include == null) return;
		if (include.isReference() && include.destinyOfReference() != null) {
			if (names.put(include.destinyOfReference().name(), include.destinyOfReference()) == null)
				throw new SemanticException(new SemanticNotification(ERROR, "reject.duplicate.entries", include, asList(include.name(), node.type().isEmpty() ? "model" : node.qualifiedName())));
		} else {
			if (names.put(include.name(), include) == null)
				throw new SemanticException(new SemanticNotification(ERROR, "reject.duplicate.entries", include, asList(include.name(), node.type().isEmpty() ? "model" : node.qualifiedName())));
		}
	}

	private Constraint facetDeclaration() {
		return new FacetDeclarationConstraint();
	}

	private Constraint facetInstantiation() {
		return element -> {
			Node node = (Node) element;
			Context context = rulesCatalog.get(node.type());
			if (context == null) return;
			for (Assumption assumption : context.assumptions())
				if (assumption instanceof Assumption.FacetInstance)
					throw new SemanticException(new SemanticNotification(ERROR, "reject.facet.as.primary", node));
		};
	}

	private Constraint duplicatedFacets() {
		return element -> {
			Node node = (Node) element;
			Set<String> facets = new HashSet<>();
			for (Facet facet : node.facets())
				if (!facets.add(facet.type()))
					throw new SemanticException(new SemanticNotification(ERROR, "reject.duplicated.facet", facet));
		};
	}

	private Constraint anyFacetWithoutConstrains() {
		return element -> {
			Node node = (Node) element;
			for (FacetTarget facet : node.facetTargets())
				if (facet.target().equals(FacetTarget.ANY) && facet.constraints().isEmpty())
					throw new SemanticException(new SemanticNotification(ERROR, "reject.facet.target.any.without.constrains", facet));
		};
	}

	private static class FacetDeclarationConstraint implements Constraint {
		@Override
		public void check(Element element) throws SemanticException {
			Node node = (Node) element;
			if (isFacet(node) && !isAbstract(node)) {
				checkTargetExists(node);
			} else checkTargetNotExist(node);
		}

		private boolean isFacet(Node node) {
			return isFacet(node.flags()) || isFacetInherited(node);
		}

		private void checkTargetExists(Node node) throws SemanticException {
			if (node.facetTargets().isEmpty() && !node.isReference() && node.subs().isEmpty() && !isAbstract(node))
				throw new SemanticException(new SemanticNotification(ERROR, "no.targets.in.facet", node, singletonList(node.name())));
		}

		private void checkTargetNotExist(Node node) throws SemanticException {
			if (!node.facetTargets().isEmpty())
				throw new SemanticException(new SemanticNotification(ERROR, "reject.target.without.facet", node));
		}

		private boolean isFacet(List<Tag> flags) {
			for (Tag flag : flags) if (flag.equals(Tag.FACET)) return true;
			return false;
		}

		private boolean isFacetInherited(Node node) {
			Node parent = node.parent();
			while (parent != null) {
				if (isFacet(parent.flags())) return true;
				parent = parent.parent();
			}
			return false;
		}

		private boolean isAbstract(Node node) {
			return node.flags().contains(Tag.ABSTRACT) || !node.subs().isEmpty();
		}
	}
}
