package tara.language.semantics.constraints;

import tara.language.model.*;
import tara.language.semantics.*;
import tara.language.semantics.constraints.flags.AnnotationChecker;
import tara.language.semantics.constraints.flags.FlagCheckerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class GlobalConstraints {

	private final Map<String, Context> rulesCatalog;

	public GlobalConstraints(Map<String, Context> rulesCatalog) {
		this.rulesCatalog = rulesCatalog;
	}

	public Constraint[] all() {
		return new Constraint.Require[]{parentConstraint(),
			duplicatedAnnotations(),
			duplicatedFlags(),
			flagsCoherence(),
			duplicatedNames(),
			invalidValueTypeInVariable(),
			cardinalityInVariable(),
			contractExistence(),
			facetDeclaration(),
			facetInstantiation(),
			duplicatedFacets(),
			anyFacetWithoutConstrains()};
	}

	private Constraint.Require parentConstraint() {
		return element -> {
			Node node = (Node) element;
			if (node.parent() == null) return;
			String parentType = node.parent().type();
			if (!parentType.equals(node.type()))
				throw new SemanticException(new SemanticError("reject.parent.different.type", node, asList(parentType, node.type())));
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
			Class<? extends AnnotationChecker> aClass = FlagCheckerFactory.get(flag);
			if (aClass == null) return;
			aClass.newInstance().check(node);
		} catch (InstantiationException | IllegalAccessException ignored) {
		}

	}

	private Constraint.Require invalidValueTypeInVariable() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
				if ("word".equals(variable.type())) continue;
				if (!variable.defaultValues().isEmpty() && !compatibleTypes(variable))
					throw new SemanticException(new SemanticError("reject.invalid.variable.type", variable, singletonList(variable.type())));
			}
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

	private boolean compatibleCardinality(Variable variable) {
		List<Object> values = variable.defaultValues();
		return variable.getSize() == 0 || values.size() == variable.getSize();
	}

	private boolean compatibleTypes(Variable variable) {
		List<Object> values = variable.defaultValues();
		String inferredType = PrimitiveTypeCompatibility.inferType(values.get(0));
		return !inferredType.isEmpty() && PrimitiveTypeCompatibility.checkCompatiblePrimitives(variable.isReference() ? Primitives.REFERENCE : variable.type(), inferredType);
	}

	private Constraint.Require contractExistence() {
		return element -> {
			Node node = (Node) element;
			for (Variable variable : node.variables()) {
				if (!Primitives.NATIVE.equals(variable.type()) && !Primitives.MEASURE.equals(variable.type())) continue;
				if (variable.contract() == null)
					throw new SemanticException(new SemanticError("reject.unexisting.variable.contract", variable, singletonList(variable.type())));
			}
		};
	}

	private Constraint.Require duplicatedNames() {
		return element -> {
			Node node = (Node) element;
			Set<String> names = new HashSet<String>() {
				@Override
				public boolean add(String name) {
					return name == null || name.isEmpty() || super.add(name);
				}
			};
			for (Variable variable : node.variables()) {
				if (variable.isOverriden() || names.add(variable.name()))
					continue;
				throw new SemanticException(new SemanticError("reject.duplicate.variable", element, asList(variable.name(), node.name())));
			}

			for (Node include : node.components()) {
				if (include.isReference() ? names.add(include.destinyOfReference().name()) : names.add(include.name()))
					continue;
				throw new SemanticException(new SemanticError("reject.duplicate.entries", include, asList(include.name(), node.type().isEmpty() ? "model" : node.name())));
			}
		};
	}

	private Constraint.Require facetDeclaration() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (isFacet(node) && !isAbstract(node)) checkTargetExists(node);
				else checkTargetNotExist(node);
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
		};
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
}
