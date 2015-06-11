package siani.tara.semantic.constraints;

import org.siani.itrules.engine.formatters.PluralFormatter;
import siani.tara.semantic.Assumption;
import siani.tara.semantic.Constraint;
import siani.tara.semantic.SemanticError;
import siani.tara.semantic.SemanticException;
import siani.tara.semantic.constraints.flags.AnnotationChecker;
import siani.tara.semantic.constraints.flags.FlagCheckerFactory;
import siani.tara.semantic.model.*;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static siani.tara.semantic.constraints.PrimitiveTypeCompatibility.inferType;

public class GlobalConstraints {

	private final Map<String, Context> rulesCatalog;
	private final Locale locale;

	public GlobalConstraints(Map<String, Context> rulesCatalog, Locale locale) {
		this.rulesCatalog = rulesCatalog;
		this.locale = locale;
	}

	public Constraint[] all() {
		return new Constraint.Require[]{parentConstraint(),
			duplicatedAnnotations(),
			duplicatedFlags(),
			flagsCoherence(),
			duplicatedNames(),
			invalidValueTypeInVariable(),
			facetDeclaration(),
			namedInsideFeature(),
			facetInstantiation()};
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
			for (String annotation : node.annotations()) {
				if (annotations.add(annotation)) continue;
				throw new SemanticException(new SemanticError("reject.duplicate.annotation", node, asList(annotation, node.type())));
			}
		};
	}

	private Constraint.Require duplicatedFlags() {
		return element -> {
			Node node = (Node) element;
			Set<String> flags = new HashSet<>();
			for (String flag : node.flags()) {
				if (flags.add(flag)) continue;
				throw new SemanticException(new SemanticError("reject.duplicate.flag", node, asList(flag, node.type() + " " + node.name())));
			}
		};
	}

	private Constraint.Require flagsCoherence() {
		return element -> {
			Node node = (Node) element;
			for (String flags : node.flags())
				checkFlagConstrains(flags, node);
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
				if (variable.defaultValue().length != 0 && !compatibleTypes(variable))
					throw new SemanticException(new SemanticError("reject.invalid.variable.type", variable, singletonList(variable.type())));
			}
		};
	}

	private boolean compatibleTypes(Variable variable) {
		Object[] values = variable.defaultValue();
		String inferredType = inferType(values[0]);
		return !inferredType.isEmpty() && PrimitiveTypeCompatibility.checkCompatiblePrimitives(variable.isReference() ? Primitives.REFERENCE : variable.type(), inferredType);
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
				if (variable.isOverriden() || names.add(variable.name()) || names.add(plural(variable.name())))
					continue;
				throw new SemanticException(new SemanticError("reject.duplicate.variable", element, asList(variable.name(), node.name())));
			}

			for (Node include : node.includes()) {
				if (names.add(include.name()) || names.add(plural(include.name()))) continue;
				throw new SemanticException(new SemanticError("reject.duplicate.entries", include, asList(include.name(), node.type().isEmpty() ? "model" : node.name())));
			}
		};
	}

	private String plural(String name) {
		return String.valueOf(new PluralFormatter(locale).format(name));
	}

	private Constraint.Require namedInsideFeature() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (isInFeature(node) && !node.name().isEmpty())
					throw new SemanticException(new SemanticError("reject.named.node.in.feature", node));
			}

			private boolean isInFeature(Node node) {
				Node context = node.context();
				while (context != null) {
					if (isFeature(context)) return true;
					context = context.context();
				}
				return false;
			}

			private boolean isFeature(Node context) {
				for (String flag : context.flags())
					if (flag.equalsIgnoreCase(Tag.FEATURE_INSTANCE.name()))
						return true;
				return false;
			}
		};
	}

	private Constraint.Require facetDeclaration() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (isFacet(node) && !isAbstract(node)) checkTargetExists(node);
				else
					checkTargetNotExist(node);
			}

			private boolean isFacet(Node node) {
				return isFacet(node.flags()) || isFacetInherited(node);
			}

			private void checkTargetExists(Node node) throws SemanticException {
				if (node.facetTargets().length == 0 && !node.isReference() && (!node.hasSubs() && !isAbstract(node)))
					throw new SemanticException(new SemanticError("no.targets.in.facet", node, singletonList(node.name())));
			}

			private void checkTargetNotExist(Node node) throws SemanticException {
				if (node.facetTargets().length > 0)
					throw new SemanticException(new SemanticError("reject.target.without.facet", node));
			}

			private boolean isFacet(String[] flags) {
				for (String flag : flags) if (flag.equalsIgnoreCase(Tag.FACET.name())) return true;
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
				return asList(node.flags()).contains("abstract") || node.hasSubs();
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
}
