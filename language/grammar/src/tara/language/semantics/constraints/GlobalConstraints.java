package tara.language.semantics.constraints;

import tara.language.model.*;
import tara.language.semantics.*;
import tara.language.semantics.constraints.flags.AnnotationChecker;
import tara.language.semantics.constraints.flags.FlagCheckerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tara.language.model.Primitive.*;

public class GlobalConstraints {

	private final Map<String, Context> rulesCatalog;

	public GlobalConstraints(Map<String, Context> rulesCatalog) {
		this.rulesCatalog = rulesCatalog;
	}

	public Constraint[] all() {
		return new Constraint.Require[]{parentConstraint(),
			duplicatedAnnotations(),
			invalidVariableAnnotations(),
			duplicatedFlags(),
			flagsCoherence(),
			duplicatedNames(),
			invalidValueTypeInVariable(),
			declarationReferenceVariables(),
			cardinalityInVariable(),
			wordValuesInVariable(),
			metricValuesInVariable(),
			contractExistence(),
			facetDeclaration(),
			facetInstantiation(),
			duplicatedFacets(),
			anyFacetWithoutConstrains()};
	}

	private Constraint.Require parentConstraint() {
		return element -> {
			Node node = (Node) element;
			final Node parent = node.parent();
			if (parent == null) return;
			parent.resolve();
			String parentType = parent.type();
			if (!parentType.equals(node.type()))
				throw new SemanticException(new SemanticError("reject.parent.different.type", node, asList(parentType, node.type())));
			if (parent.isTerminalInstance())
				throw new SemanticException(new SemanticError("reject.sub.of.declaration", node));
		};
	}

	private Constraint.Require duplicatedAnnotations() {
		return element -> {
			Node node = (Node) element;
			Set<String> annotations = new HashSet<>();
			for (Tag annotation : node.annotations()) {
				if (annotations.add(annotation.name())) continue;
				throw new SemanticException(new SemanticError("reject.duplicate.annotation", node, asList(annotation, node.type())));
			}
		};
	}

	private Constraint.Require invalidVariableAnnotations() {
		return element -> {
			Node node = (Node) element;
			final List<Tag> availableTags = Arrays.asList(Flags.variableAnnotations());
			for (Variable variable : node.variables())
				for (Tag tag : variable.flags())
					if (!availableTags.contains(tag)) if (tag.equals(Tag.TERMINAL_INSTANCE))
						throw new SemanticException(new SemanticError("reject.variable.in.declaration", variable, singletonList(variable.name())));
					else
						throw new SemanticException(new SemanticError("reject.invalid.annotation", variable, asList(tag.name(), variable.name())));
		};
	}

	private Constraint.Require duplicatedFlags() {
		return element -> {
			Node node = (Node) element;
			Set<String> flags = new HashSet<>();
			for (Tag flag : node.flags()) {
				if (flags.add(flag.name())) continue;
				throw new SemanticException(new SemanticError("reject.duplicate.flag", node, asList(flag, node.type() + " " + node.name())));
			}
		};
	}

	private Constraint.Require flagsCoherence() {
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

	private Constraint.Require invalidValueTypeInVariable() {
		return element -> {
			Node node = (Node) element;
			inNode(node);
			inFacetTargets(node);
			infacets(node);
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

	private void infacets(Node node) throws SemanticException {
		for (Facet facet : node.facets())
			for (Variable variable : facet.variables())
				checkVariable(variable);
	}

	private void checkVariable(Variable variable) throws SemanticException {
		if (!WORD.equals(variable.type()) && !variable.defaultValues().isEmpty() && !compatibleTypes(variable))
			throw new SemanticException(new SemanticError("reject.invalid.variable.type", variable, singletonList(variable.type())));
	}

	private Constraint.Require declarationReferenceVariables() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables())
				if (variable.isReference() && variable.destinyOfReference() != null && variable.destinyOfReference().isTerminalInstance())
					throw new SemanticException(new SemanticError("reject.declaration.reference.variable", variable));
		};
	}


	private Constraint.Require cardinalityInVariable() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
				if (!variable.defaultValues().isEmpty() && !compatibleCardinality(variable))
					throw new SemanticException(new SemanticError("reject.invalid.variable.size", variable, singletonList(variable.getSize())));
			}
		};
	}

	private Constraint.Require wordValuesInVariable() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables())
				if (WORD.equals(variable.type()) && !isCorrectValued(variable))
					throw new SemanticException(new SemanticError("reject.invalid.word.values", variable, singletonList(Arrays.toString(variable.allowedValues().toArray()))));
		};
	}

	private boolean isCorrectValued(Variable variable) {
		for (Object o : variable.defaultValues())
			if (!variable.allowedValues().contains(o.toString().replace(Parameter.REFERENCE_PREFIX, ""))) return false;
		return true;
	}

	private Constraint.Require metricValuesInVariable() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
//				if (MEASURE.equals(variable.type()) && !variable.defaultValues().isEmpty() && (variable.defaultExtension() == null || !isCorrectMetric(variable)))TODO
//					throw new SemanticException(new SemanticError("reject.measure.without.metric", variable, singletonList(variable.contract())));
			}
		};
	}

	private boolean isCorrectMetric(Variable variable) {
		for (String allowedMetric : allowedMetrics(variable.contract()))
			if (allowedMetric.equals(variable.defaultExtension())) return true;
		return false;
	}

	private String[] allowedMetrics(String contract) {
		if (!contract.contains("[")) return new String[0];
		String allowedMetrics = contract.substring(contract.indexOf("[") + 1, contract.lastIndexOf("]"));
		return allowedMetrics.split(", ");
	}

	private boolean compatibleCardinality(Variable variable) {
		List<Object> values = variable.defaultValues();
		return variable.getSize() == 0 || values.size() == variable.getSize();
	}

	private boolean compatibleTypes(Variable variable) {
		List<Object> values = variable.defaultValues();
		Primitive inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return inferredType != null && PrimitiveTypeCompatibility.checkCompatiblePrimitives(variable.isReference() ? REFERENCE : variable.type(), inferredType, variable.isMultiple());
	}

	private Constraint.Require contractExistence() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
				if (!NATIVE.equals(variable.type())) continue;
				if (variable.contract() == null)
					throw new SemanticException(new SemanticError("reject.nonexisting.variable.contract", variable, singletonList(variable.type())));
			}
		};
	}

	private Constraint.Require duplicatedNames() {
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
			throw new SemanticException(new SemanticError("reject.duplicate.variable", variable, asList(variable.name(), node.qualifiedName())));
	}

	private void checkComponent(NodeContainer node, Map<String, Element> names, Node include) throws SemanticException {
		if (include == null) return;
		if (include.isReference() && include.destinyOfReference() != null) {
			if (names.put(include.destinyOfReference().name(), include.destinyOfReference()) == null)
				throw new SemanticException(new SemanticError("reject.duplicate.entries", include, asList(include.name(), node.type().isEmpty() ? "model" : node.qualifiedName())));
		} else {
			if (names.put(include.name(), include) == null)
				throw new SemanticException(new SemanticError("reject.duplicate.entries", include, asList(include.name(), node.type().isEmpty() ? "model" : node.qualifiedName())));
		}
	}

	private Constraint.Require facetDeclaration() {
		return new FacetDeclarationConstraint();
	}

	private Constraint.Require facetInstantiation() {
		return element -> {
			Node node = (Node) element;
			Context context = rulesCatalog.get(node.type());
			if (context == null) return;
			for (Assumption assumption : context.assumptions())
				if (assumption instanceof Assumption.FacetInstance)
					throw new SemanticException(new SemanticError("reject.facet.as.primary", node));
		};
	}

	private Constraint.Require duplicatedFacets() {
		return element -> {
			Node node = (Node) element;
			Set<String> facets = new HashSet<>();
			for (Facet facet : node.facets())
				if (!facets.add(facet.type()))
					throw new SemanticException(new SemanticError("reject.duplicated.facet", node));
		};
	}

	private Constraint.Require anyFacetWithoutConstrains() {
		return element -> {
			Node node = (Node) element;
			for (FacetTarget facet : node.facetTargets())
				if (facet.target().equals(FacetTarget.ANY) && facet.constraints().isEmpty())
					throw new SemanticException(new SemanticError("reject.facet.target.any.without.constrains", facet));
		};
	}

	private static class FacetDeclarationConstraint implements Constraint.Require {
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
				throw new SemanticException(new SemanticError("no.targets.in.facet", node, singletonList(node.name())));
		}

		private void checkTargetNotExist(Node node) throws SemanticException {
			if (!node.facetTargets().isEmpty())
				throw new SemanticException(new SemanticError("reject.target.without.facet", node));
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
