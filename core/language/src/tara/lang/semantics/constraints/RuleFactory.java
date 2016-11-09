package tara.lang.semantics.constraints;

import tara.Resolver;
import tara.lang.model.*;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.VariableRule;
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
import static tara.lang.model.Tag.FacetInstance;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;


public class RuleFactory {

	private RuleFactory() {
	}

	public static tara.lang.semantics.Constraint.Component component(final String type, List<Rule> rules, final Tag... flags) {
		return new Component(type, rules, asList(flags));
	}

	public static tara.lang.semantics.Constraint.Component component(final String type, Rule rule, final Tag... flags) {
		return new Component(type, Collections.singletonList(rule), asList(flags));
	}

	public static tara.lang.semantics.Constraint.OneOf oneOf(List<Rule> rules, final tara.lang.semantics.Constraint.Component... components) {
		return new OneOf(asList(components), rules);
	}

	public static tara.lang.semantics.Constraint.Parameter
	parameter(final String name, final Primitive type, String facet, final Size size, final int position, String scope, VariableRule rule, Tag... tags) {
		return new PrimitiveParameter(name, type, facet, size, position, scope, rule, asList(tags));
	}

	public static tara.lang.semantics.Constraint.Parameter parameter(final String name, String type, String facet, final Size size, final int position, String scope, VariableRule rule, Tag... tags) {
		return new ReferenceParameter(name, type, facet, size, position, scope, rule, asList(tags));
	}

	public static tara.lang.semantics.Constraint.Facet facet(final String type, boolean terminal, String[] with, String[] without) {
		return new FacetConstraint(type, terminal, with, without);
	}

	public static tara.lang.semantics.Constraint.MetaFacet metaFacet(final String type, String... with) {
		return new MetaFacetConstraint(type, with);
	}

	public static Constraint.ComponentNotFound rejectOtherComponents(List<String> types) {
		return new Constraint.ComponentNotFound() {

			@Override
			public void check(Element element) throws SemanticException {
				NodeContainer node = (NodeContainer) element;
				for (Node component : node.components()) {
					if (!areCompatibles(component, types))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.type.not.exists", component, Collections.singletonList(component.type().replace(":", ""))));
				}
			}
		};
	}

	private static boolean areCompatibles(Node node, List<String> types) {
		for (String nodeType : node.types())
			if (nodeType != null && (types.contains(nodeType) || (node.container() != null && fromFacet(node.container().facets(), nodeType, types))))
				return true;
		return checkFacets(node, types);
	}

	public static Constraint.RejectOtherParameters rejectOtherParameters(List<Constraint.Parameter> parameters) {
		return new Constraint.RejectOtherParameters() {
			@Override
			public void check(Element element) throws SemanticException {
				Parametrized parametrized = (Parametrized) element;
				for (tara.lang.model.Parameter parameter : parametrized.parameters()) {
					if (!isAcceptable(parameter, parameters))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.other.parameter.in.context", parameter, Collections.singletonList(parameter.name())));
				}

			}

			private boolean isAcceptable(tara.lang.model.Parameter parameter, List<Parameter> parameters) {
				for (Parameter constraint : parameters)
					if (constraint.name().equals(parameter.name()) && hasFacet(constraint.facet(), parameter.container().facets()))
						return true;
				return false;
			}

			private boolean hasFacet(String requiredFacet, List<tara.lang.model.Facet> facets) {
				if (requiredFacet.isEmpty()) return true;
				for (tara.lang.model.Facet facet : facets) if (facet.type().equals(requiredFacet)) return true;
				return false;
			}
		};
	}

	public static Constraint.RejectOtherParameters rejectOtherFacets(List<Constraint.Facet> facets) {
		return new Constraint.RejectOtherParameters() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				for (tara.lang.model.Facet facet : node.facets())
					if (!isAcceptable(facets, facet))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.other.facet.in.context", facet, Collections.singletonList(facet.type())));

			}

			private boolean isAcceptable(List<Facet> facets, tara.lang.model.Facet facet) {
				for (Facet constraint : facets)
					if (constraint.type().equals(facet.type())) return true;
				return false;
			}
		};
	}


	private static boolean fromFacet(List<Facet> facets, String nodeType, List<String> types) {
		return types.contains(nodeType) && asFacet(facets, nodeType.split(":")[0]);
	}

	private static boolean asFacet(List<Facet> facets, String facet) {
		for (Facet f : facets) if (f.type().equals(facet)) return true;
		return false;
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
				if (!node.flags().contains(Tag.Instance)) {
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
			public void assume(tara.lang.model.Node node) {
				if (!node.flags().contains(Tag.Facet)) node.addFlag(Tag.Facet);
				if (!node.flags().contains(Tag.Terminal)) node.addFlag(Tag.Terminal);
			}
		};
	}

	public static Assumption isFacetInstance() {
		return new Assumption.FacetInstance() {
			@Override
			public void assume(tara.lang.model.Node node) {
				if (!node.flags().contains(FacetInstance)) node.addFlag(FacetInstance);
			}
		};
	}

	public static Assumption isFeature() {
		return new Assumption.Feature() {
			@Override
			public void assume(tara.lang.model.Node node) {
				if (!node.flags().contains(Tag.Feature)) node.addFlag(Tag.Feature);
				propagateFlags(node, Tag.Feature);
			}
		};
	}

	public static Assumption isTerminal() {
		return new Assumption.Terminal() {
			@Override
			public void assume(tara.lang.model.Node node) {
				if (node.isReference()) return;
				if (!node.flags().contains(Tag.Terminal)) node.addFlag(Tag.Terminal);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.Terminal)).forEach(variable -> variable.addFlags(Tag.Terminal));
				propagateFlags(node, Tag.Terminal);
			}
		};
	}

	public static Assumption isVersioned() {
		return new Assumption.Versioned() {
			@Override
			public void assume(tara.lang.model.Node node) {
				if (node.isReference()) return;
				if (!node.flags().contains(Tag.Versioned)) node.addFlag(Tag.Versioned);
				propagateFlags(node, Tag.Versioned);
			}
		};
	}

	public static Assumption isVolatile() {
		return new Assumption.Volatile() {
			@Override
			public void assume(tara.lang.model.Node node) {
				if (node.isReference()) return;
				if (!node.flags().contains(Tag.Versioned)) node.addFlag(Tag.Versioned);
				propagateFlags(node, Tag.Versioned);
			}
		};
	}

	public static Assumption isComponent() {
		return new Assumption.Component() {
			@Override
			public void assume(tara.lang.model.Node node) {
				if (!node.flags().contains(Tag.Component)) node.addFlag(Tag.Component);
			}
		};
	}


	public static Assumption isInstance() {
		return new Assumption.Instance() {
			@Override
			public void assume(tara.lang.model.Node node) {
				if (!node.flags().contains(Tag.Instance)) node.addFlag(Tag.Instance);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.Instance)).forEach(variable -> variable.addFlags(Tag.Instance));
				propagateFlags(node, Tag.Instance);
			}
		};
	}

	private static void propagateFlags(Node node, Tag flag) {
		for (Node component : node.components()) {
			if (component.isReference()) continue;
			if (!component.flags().contains(flag)) component.addFlag(flag);
			if (!component.equals(node)) propagateFlags(component, flag);
		}
	}

}
