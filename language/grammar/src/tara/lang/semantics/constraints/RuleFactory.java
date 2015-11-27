package tara.lang.semantics.constraints;

import tara.Resolver;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.ReferenceRule;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.component.Component;
import tara.lang.semantics.constraints.component.OneOf;
import tara.lang.semantics.constraints.parameter.PrimitiveParameter;
import tara.lang.semantics.constraints.parameter.ReferenceParameter;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static tara.lang.model.Tag.*;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;


public class RuleFactory {

	private RuleFactory() {
	}

	public static tara.lang.semantics.Constraint.Component component(final String type, CompositionRule size, final Tag... annotations) {
		return new Component(type, size, asList(annotations));
	}

	public static tara.lang.semantics.Constraint.OneOf oneOf(CompositionRule size, final tara.lang.semantics.Constraint.Component... components) {
		return new OneOf(asList(components), size);
	}

	public static tara.lang.semantics.Constraint.Parameter parameter(final String name, final Primitive type, final Size size, final Object defaultValue, final int position, Rule rule, String... tags) {
		return new PrimitiveParameter(name, type, size, defaultValue, position, rule, asList(tags));
	}

	public static tara.lang.semantics.Constraint.Parameter parameter(final String name, final Size size, final Object defaultValue, final int position, ReferenceRule rule, String... tags) {
		return new ReferenceParameter(name, size, defaultValue, position, rule, asList(tags));
	}

	public static tara.lang.semantics.Constraint.Facet facet(final String type, boolean terminal, String... with) {
		return new FacetConstraint(type, terminal, with);
	}

	public static Constraint.ComponentNotFound rejectOtherComponents(List<String> types) {
		return new Constraint.ComponentNotFound() {

			@Override
			public void check(Element element) throws SemanticException {
				NodeContainer node = (NodeContainer) element;
				for (Node component : node.components())
					if (!areCompatibles(component, types))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.type.not.exists", component, Collections.singletonList(component.type())));
			}
		};
	}

	public static Constraint.RejectOtherParameters rejectOtherParameters(List<Constraint.Parameter> parameters) {
		return new Constraint.RejectOtherParameters() {
			@Override
			public void check(Element element) throws SemanticException {
				Parametrized parametrized = (Parametrized) element;
				for (tara.lang.model.Parameter parameter : parametrized.parameters())
					if (!isAcceptable(parameter, parameters))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.parameter.in.context", parameter, Collections.emptyList()));

			}

			private boolean isAcceptable(tara.lang.model.Parameter parameter, List<Parameter> parameters) {
				for (Parameter constraint : parameters)
					if (constraint.name().equals(parameter.name())) return true;
				return false;
			}
		};
	}

	private static boolean areCompatibles(Node node, List<String> types) {
		for (String nodeType : node.types())
			if (nodeType != null && types.contains(nodeType)) return true;
		return checkFacets(node, types);
	}


	private static boolean checkFacets(Node node, List<String> types) {
		List<String> shortTypes = types.stream().map(Resolver::shortType).collect(Collectors.toList());
		for (tara.lang.model.Facet facet : node.facets())
			if (shortTypes.contains(facet.type())) return true;
		return false;
	}


	public static Constraint name() {
		return new Constraint.Name() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (!node.isReference() && node.name().isEmpty())
					throw new SemanticException(new SemanticNotification(ERROR, "required.name", element, Collections.emptyList()));
			}
		};
	}
//
//	public static Constraint anchor() {
//		return new Constraint.Anchor() {
//			@Override
//			public void check(Element element) throws SemanticException {
//				Node node = (Node) element;
//				if (element == null) return;
//				if (!node.isReference() && (node.anchor() == null || node.anchor().isEmpty()))
//					throw new SemanticException(new SemanticError("required.anchor", node, singletonList(node.type())));
//			}
//		};
//	}

	public static Constraint.TerminalVariableRedefinition redefine(final String name, String superType) {
		return new Constraint.TerminalVariableRedefinition() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (!node.flags().contains(Tag.TERMINAL_INSTANCE)) {
					for (Variable variable : node.variables())
						if (name.equals(variable.name())) return;
					throw new SemanticException(new SemanticNotification(ERROR, "required.terminal.variable.redefine", node, asList(name, superType)));
				}
			}
		};
	}

	public static Assumption isFacet() {
		return new Assumption.Facet() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(FACET)) node.addFlag(FACET);
				if (!node.flags().contains(NAMED)) node.addFlag(NAMED);
			}
		};
	}

	public static Assumption isFacetInstance() {
		return new Assumption.FacetInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(FACET_INSTANCE)) node.addFlag(FACET_INSTANCE);
			}
		};
	}

	public static Assumption isMain() {
		return new Assumption.Main() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(MAIN)) node.addFlag(MAIN);
				if (!node.flags().contains(TERMINAL)) node.addFlag(TERMINAL);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.TERMINAL)).forEach(variable -> variable.addFlags(Tag.TERMINAL));
				propagateFlags(node, Tag.TERMINAL);
				node.moveToTheTop();
			}
		};
	}


	public static Assumption isFeature() {
		return new Assumption.Feature() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.FEATURE)) node.addFlag(Tag.FEATURE);
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
					node.addFlag(Tag.FEATURE_INSTANCE);
				propagateFlags(node, Tag.FEATURE_INSTANCE);
			}
		};
	}

	public static Assumption isTerminal() {
		return new Assumption.Terminal() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.TERMINAL)) node.addFlag(Tag.TERMINAL);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.TERMINAL)).forEach(variable -> variable.addFlags(Tag.TERMINAL));
				propagateFlags(node, Tag.TERMINAL);
			}
		};
	}

	public static Assumption intoPrototype() {
		return new Assumption.Prototype() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.PROTOTYPE)) node.addFlag(Tag.PROTOTYPE);
				propagateFlags(node, Tag.PROTOTYPE);
			}
		};
	}

	public static Assumption isPrototype() {
		return new Assumption.Prototype() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.PROTOTYPE)) node.addFlag(Tag.PROTOTYPE);
				propagateFlags(node, Tag.PROTOTYPE);
			}
		};
	}


	public static Assumption isTerminalInstance() {
		return new Assumption.TerminalInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.TERMINAL_INSTANCE)) node.addFlag(Tag.TERMINAL_INSTANCE);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.TERMINAL_INSTANCE)).forEach(variable -> variable.addFlags(Tag.TERMINAL_INSTANCE));
				propagateFlags(node, Tag.TERMINAL_INSTANCE);
			}
		};
	}

	private static void propagateFlags(Node node, Tag flag) {
		for (Node component : node.components()) {
			if (component.isReference()) continue;
			if (!component.flags().contains(flag)) component.addFlag(flag);
			propagateFlags(component, flag);
		}
	}

}
