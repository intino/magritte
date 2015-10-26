package tara.lang.semantics.constraints;

import tara.lang.model.*;
import tara.lang.model.rules.ReferenceRule;
import tara.lang.semantics.*;
import tara.lang.semantics.constraints.allowed.*;
import tara.lang.semantics.constraints.required.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;


public class RuleFactory {

	private RuleFactory() {
	}

	public static Allow.Name name() {
		return (element, rejectables) -> rejectables.removeAll(rejectables.stream().
			filter(r -> r instanceof Rejectable.Name).
			collect(Collectors.toList()));
	}

	public static Allow.Multiple multiple(final String type) {
		return multiple(type, new Tag[0]);
	}

	public static Allow.Multiple multiple(final String type, final Tag... annotations) {
		return new MultipleAllow(type, annotations);
	}


	public static Allow.Single single(final String type) {
		return single(type, new Tag[0]);
	}

	public static Allow.Single single(final String type, final Tag... annotations) {
		return new AllowSingle(type, annotations);
	}

	public static Allow.OneOf oneOf(final Allow... allows) {
		return new AllowOneOf(allows);
	}

	public static Allow.Parameter parameter(final String name, final Primitive type, final boolean multiple, final Object defaultValue, final int position, Rule rule, String... tags) {
		return new PrimitiveParameterAllow(name, type, multiple, defaultValue, position, rule, asList(tags));
	}

	public static Allow.Parameter parameter(final String name, final boolean multiple, final Object defaultValue, final int position, ReferenceRule rule, String... tags) {
		return new ReferenceParameterAllow(name, multiple, defaultValue, position, rule, asList(tags));
	}

	public static Constraint.Require _multiple(final String type) {
		return _multiple(type, new Tag[0]);
	}

	public static Constraint.Require _multiple(final String type, final Tag... annotations) {
		return new MultipleRequired(type, annotations);
	}

	public static Constraint.Require _single(final String type) {
		return _single(type, new Tag[0]);
	}

	public static Constraint.Require _single(final String type, final Tag... annotations) {
		return new SingleRequired(type, annotations);
	}

	public static Constraint.Require.OneOf oneOf(final Constraint.Require... requires) {
		return new OneOfRequired(requires);
	}

	public static Constraint.Require.Parameter _parameter(final String name, final Primitive type, final boolean multiple, Object defaultValue, final int position, final Rule rule, final String... annotations) {
		return new ParameterRequired(name, type, multiple, defaultValue, position, rule, annotations);
	}

	public static Constraint.Require.Parameter _parameter(final String name, final boolean multiple, Object defaultValue, final int position, final ReferenceRule rule, final String... annotations) {
		return new ReferenceParameterRequired(name, multiple, defaultValue, position, rule, annotations);
	}

	public static Allow.Facet facet(final String type, String... with) {
		return new FacetAllow(type, with, false);
	}

	public static Allow.Facet facet(final String type, boolean terminal, String... with) {
		return new FacetAllow(type, with, terminal);
	}

	public static Constraint.Require _name() {
		return new Constraint.Require.Name() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (!node.isReference() && node.name().isEmpty())
					throw new SemanticException(new SemanticError("required.name", element, Collections.emptyList()));
			}
		};
	}

	public static Constraint.Require.TerminalVariableRedefinition redefine(final String name, String supertype) {
		return new Constraint.Require.TerminalVariableRedefinition() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (!node.flags().contains(Tag.TERMINAL_INSTANCE)) {
					for (Variable variable : node.variables())
						if (name.equals(variable.name())) return;
					throw new SemanticException(new SemanticError("required.terminal.variable.redefine", node, Arrays.asList(name, supertype)));
				}
			}
		};
	}

	public static Constraint.Require _plate() {
		return new Constraint.Require.Plate() {
			@Override
			public void check(Element element) throws SemanticException {
//				Node node = (Node) element;
//				if (element == null) return;
//				if (!node.isReference() && (node.plate() == null || node.plate().isEmpty()))
//					throw new SemanticException(new SemanticError("required.plate", node, singletonList(node.type())));
			}
		};
	}

	public static Assumption isSingle() {
		return new Assumption.Single() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.SINGLE)) node.addFlags(Tag.SINGLE);
			}
		};
	}

	public static Assumption isRequired() {
		return new Assumption.Required() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.REQUIRED)) node.addFlags(Tag.REQUIRED);
			}
		};
	}

	public static Assumption isFacet() {
		return new Assumption.Facet() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.FACET)) node.addFlags(Tag.FACET);
				if (!node.flags().contains(Tag.NAMED)) node.addFlags(Tag.NAMED);
			}
		};
	}

	public static Assumption isFacetInstance() {
		return new Assumption.FacetInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.FACET_INSTANCE)) node.addFlags(Tag.FACET_INSTANCE);
			}
		};
	}

	public static Assumption isMain() {
		return new Assumption.Main() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.MAIN)) node.addFlags(Tag.MAIN);
				node.moveToTheTop();
			}
		};
	}


	public static Assumption isFeature() {
		return new Assumption.Feature() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.FEATURE)) node.addFlags(Tag.FEATURE);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.FEATURE)).forEach(variable -> variable.addFlags(Tag.FEATURE));
				propagateFlags(node, Tag.FEATURE);
			}
		};
	}

	public static Assumption isFeatureInstance() {
		return new Assumption.FeatureInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.FEATURE_INSTANCE))
					node.addFlags(Tag.FEATURE_INSTANCE);
				propagateFlags(node, Tag.FEATURE_INSTANCE);
			}
		};
	}

	public static Assumption isTerminal() {
		return new Assumption.Terminal() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.TERMINAL)) node.addFlags(Tag.TERMINAL);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.TERMINAL)).forEach(variable -> variable.addFlags(Tag.TERMINAL));
				propagateFlags(node, Tag.TERMINAL);
			}
		};
	}

	public static Assumption intoPrototype() {
		return new Assumption.Prototype() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.PROTOTYPE)) node.addFlags(Tag.PROTOTYPE);
				propagateFlags(node, Tag.PROTOTYPE);
			}
		};
	}

	public static Assumption isPrototype() {
		return new Assumption.Prototype() {
			@Override
			public void assume(Node node) {
			}
		};
	}


	public static Assumption isTerminalInstance() {
		return new Assumption.TerminalInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.TERMINAL_INSTANCE))
					node.addFlags(Tag.TERMINAL_INSTANCE);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.TERMINAL_INSTANCE)).forEach(variable -> variable.addFlags(Tag.TERMINAL_INSTANCE));
				propagateFlags(node, Tag.TERMINAL_INSTANCE);
			}
		};
	}

	private static void propagateFlags(Node node, Tag flag) {
		for (Node include : node.components()) {
			if (!include.flags().contains(flag))
				include.addFlags(flag);
			if (!include.isReference()) propagateFlags(include, flag);
		}

	}

}
