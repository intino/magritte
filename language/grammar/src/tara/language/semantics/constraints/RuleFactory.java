package tara.language.semantics.constraints;

import tara.language.model.Element;
import tara.language.model.Node;
import tara.language.model.Tag;
import tara.language.model.Variable;
import tara.language.semantics.*;
import tara.language.semantics.constraints.allowed.*;
import tara.language.semantics.constraints.required.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tara.language.model.Tag.*;


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

	public static Allow.Parameter parameter(final String name, final String type, final boolean multiple, final int position, String contract, String... tags) {
		return new PrimitiveParameterAllow(name, type, multiple, position, contract, asList(tags));
	}

	public static Allow.Parameter parameter(final String name, final String[] values, final boolean multiple, final int position, String contract, String... tags) {
		return new ReferenceParameterAllow(name, asList(values), multiple, position, contract, asList(tags));
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

	public static Constraint.Require.Parameter _parameter(final String name, final String type, final boolean multiple, final int position, final String contract, final String... annotations) {
		return new ParameterRequired(name, type, multiple, position, contract, annotations);
	}

	public static Constraint.Require.Parameter _parameter(final String name, final String[] values, final boolean multiple, final int position, final String metric, final String... annotations) {
		return new ReferenceParameterRequired(name, multiple, values, position, metric, annotations);
	}

	public static Allow.Facet facet(final String type, String... with) {
		return new FacetAllow(type, with);
	}

	public static Constraint.Require _name() {
		return new Constraint.Require.Name() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (node.name().isEmpty() && !node.isReference())
					throw new SemanticException(new SemanticError("required.name", element, Collections.emptyList()));
			}
		};
	}

	public static Constraint.Require.TerminalVariableRedefinition redefine(final String name, String supertype) {
		return new Constraint.Require.TerminalVariableRedefinition() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (!node.flags().contains(TERMINAL_INSTANCE)) {
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
				Node node = (Node) element;
				if (element == null) return;
				if (!node.isReference() && (node.plate() == null || node.plate().isEmpty()))
					throw new SemanticException(new SemanticError("required.plate", node, singletonList(node.type())));
			}
		};
	}

	public static Assumption isSingle() {
		return new Assumption.Single() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(SINGLE)) node.addFlags(SINGLE);
			}
		};
	}

	public static Assumption isRequired() {
		return new Assumption.Required() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(REQUIRED)) node.addFlags(REQUIRED);
			}
		};
	}

	public static Assumption isFacet() {
		return new Assumption.Facet() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(FACET)) node.addFlags(FACET);
				if (!node.flags().contains(NAMED)) node.addFlags(NAMED);
			}
		};
	}

	public static Assumption isFacetInstance() {
		return new Assumption.FacetInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(FACET_INSTANCE)) node.addFlags(FACET_INSTANCE);
			}
		};
	}

	public static Assumption isMain() {
		return new Assumption.Main() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(MAIN)) node.addFlags(MAIN);
				node.moveToTheTop();
			}
		};
	}


	public static Assumption isFeature() {
		return new Assumption.Feature() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(FEATURE)) node.addFlags(FEATURE);
				node.variables().stream().filter(variable -> !variable.flags().contains(FEATURE)).forEach(variable -> variable.addFlags(FEATURE));
				propagateFlags(node, FEATURE);
			}
		};
	}

	public static Assumption isFeatureInstance() {
		return new Assumption.FeatureInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(FEATURE_INSTANCE))
					node.addFlags(FEATURE_INSTANCE);
				node.variables().stream().filter(variable -> !variable.flags().contains(FEATURE_INSTANCE)).forEach(variable -> variable.addFlags(FEATURE_INSTANCE));
				propagateFlags(node, FEATURE_INSTANCE);
			}
		};
	}

	public static Assumption isTerminal() {
		return new Assumption.Terminal() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(TERMINAL)) node.addFlags(TERMINAL);
				node.variables().stream().filter(variable -> !variable.flags().contains(TERMINAL)).forEach(variable -> variable.addFlags(TERMINAL));
				propagateFlags(node, TERMINAL);
			}
		};
	}

	public static Assumption isPrototype() {
		return new Assumption.Prototype() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(PROTOTYPE)) node.addFlags(PROTOTYPE);
				propagateFlags(node, PROTOTYPE);
			}
		};
	}


	public static Assumption isTerminalInstance() {
		return new Assumption.TerminalInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(TERMINAL_INSTANCE))
					node.addFlags(TERMINAL_INSTANCE);
				node.variables().stream().filter(variable -> !variable.flags().contains(TERMINAL_INSTANCE)).forEach(variable -> variable.addFlags(TERMINAL_INSTANCE));
				propagateFlags(node, TERMINAL_INSTANCE);
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
