package siani.tara.semantic.constraints;

import siani.tara.semantic.Assumption;
import siani.tara.semantic.Constraint;
import siani.tara.semantic.SemanticError;
import siani.tara.semantic.SemanticException;
import siani.tara.semantic.constraints.flags.AnnotationChecker;
import siani.tara.semantic.constraints.flags.FlagCheckerFactory;
import siani.tara.semantic.model.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static siani.tara.semantic.constraints.PrimitiveTypeCompatibility.inferType;

public class GlobalConstraints {

	private final Map<String, Context> rulesCatalog;

	public GlobalConstraints(Map<String, Context> rulesCatalog) {
		this.rulesCatalog = rulesCatalog;
	}

	public Constraint[] all() {
		return new Constraint[]{parentConstraint(),
			duplicatedAnnotations(),
			duplicatedFlags(),
			flagsCoherence(),
			duplicateNode(),
			duplicateVariable(),
			invalidValueTypeInVariable(),
			facetDeclaration(),
			facetInstantiation()};
	}

	private Constraint parentConstraint() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (node.parent() == null) return;
				String parentType = node.parent().type();
				if (!parentType.equals(node.type()))
					throw new SemanticException(new SemanticError("reject.parent.different.type", node, new Object[]{parentType, node.type()}));
			}
		};
	}

	private Constraint duplicatedAnnotations() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				Set<String> annotations = new HashSet<>();
				for (String annotation : node.annotations()) {
					if (annotations.add(annotation)) continue;
					throw new SemanticException(new SemanticError("reject.duplicate.annotation", node, new Object[]{annotation, node.type()}));
				}
			}
		};
	}

	private Constraint duplicatedFlags() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				Set<String> flags = new HashSet<>();
				for (String flag : node.flags()) {
					if (flags.add(flag)) continue;
					throw new SemanticException(new SemanticError("reject.duplicate.flag", node, new Object[]{flag, node.type()}));
				}
			}
		};
	}

	private Constraint flagsCoherence() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				for (String flags : node.flags())
					checkFlagConstrains(flags, node);
			}
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

	private Constraint duplicateNode() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				Set<String> nodeNames = new HashSet<String>() {
					@Override
					public boolean add(String name) {
						return name.isEmpty() || super.add(name);
					}
				};
				for (Node include : node.includes()) {
					if (nodeNames.add(include.name())) continue;
					throw new SemanticException(new SemanticError("reject.duplicate.entries", include, new String[]{include.name(), node.type().isEmpty() ? "model" : node.name()}));
				}
			}
		};
	}

	private Constraint invalidValueTypeInVariable() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				for (Variable variable : node.variables())
					if (variable.defaultValue().length != 0 && !compatibleTypes(variable))
						throw new SemanticException(new SemanticError("reject.invalid.variable.type", (Element) variable, new String[]{variable.type()}));
			}

			private boolean compatibleTypes(Variable variable) {
				Object[] values = variable.defaultValue();
				String inferredType = inferType(values[0]);
				return !inferredType.isEmpty() && PrimitiveTypeCompatibility.checkCompatiblePrimitives(variable.type(), inferredType);
			}
		};
	}

	private Constraint duplicateVariable() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				Set<String> varNames = new HashSet<String>() {
					@Override
					public boolean add(String name) {
						return name == null || name.isEmpty() || super.add(name);
					}
				};
				for (Variable variable : node.variables()) {
					if (varNames.add(variable.name())) continue;
					throw new SemanticException(new SemanticError("reject.duplicate.variable", variable.name(), node.name()));
				}
			}
		};
	}

	private Constraint facetDeclaration() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (isFacet(node)) checkTargetExists(node);
				else
					checkTargetNotExist(node);
			}

			private boolean isFacet(Node node) {
				return isFacet(node.flags()) || isFacetInherited(node);
			}

			private void checkTargetExists(Node node) throws SemanticException {
				if (node.facetTargets().length == 0 && !node.isReference() && (!node.hasSubs() && !isAbstract(node)))
					throw new SemanticException(new SemanticError("no.targets.in.facet", node, new Object[]{node.name()}));
			}

			private void checkTargetNotExist(Node node) throws SemanticException {
				if (node.facetTargets().length > 0)
					throw new SemanticException(new SemanticError("reject.target.without.facet", node, new Object[0]));
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
				return Arrays.asList(node.flags()).contains("abstract");
			}
		};
	}

	private Constraint facetInstantiation() {
		return new Constraint.Require() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				Context context = rulesCatalog.get(node.type());
				if (context == null) return;
				for (Assumption assumption : context.assumptions()) {
					if (assumption instanceof Assumption.FacetInstance)
						throw new SemanticException(new SemanticError("reject.facet.as.primary", node, new Object[0]));
				}
			}
		};
	}
}
