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
import static tara.lang.model.Tag.FacetInstance;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;


public class RuleFactory {

	private RuleFactory() {
	}

	public static tara.lang.semantics.Constraint.Component component(final String type, CompositionRule size, final Tag... flags) {
		return new Component(type, size, asList(flags));
	}

	public static tara.lang.semantics.Constraint.OneOf oneOf(CompositionRule size, final tara.lang.semantics.Constraint.Component... components) {
		return new OneOf(asList(components), size);
	}

	public static tara.lang.semantics.Constraint.Parameter parameter(final String name, final Primitive type, final Size size, final Object defaultValue, final int position, Rule rule, Tag... tags) {
		return new PrimitiveParameter(name, type, size, defaultValue, position, rule, asList(tags));
	}

	public static tara.lang.semantics.Constraint.Parameter parameter(final String name, String type, final Size size, final Object defaultValue, final int position, ReferenceRule rule, Tag... tags) {
		return new ReferenceParameter(name, type, size, defaultValue, position, rule, asList(tags));
	}

	public static tara.lang.semantics.Constraint.Facet facet(final String type, boolean terminal, String... with) {
		return new FacetConstraint(type, terminal, with);
	}

	public static tara.lang.semantics.Constraint.MetaFacet metaFacet(final String type, String... with) {
		return new MetaFacetConstraint(type, with);
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
						throw new SemanticException(new SemanticNotification(ERROR, "reject.other.parameter.in.context", parameter, Collections.singletonList(parameter.name())));

			}

			private boolean isAcceptable(tara.lang.model.Parameter parameter, List<Parameter> parameters) {
				for (Parameter constraint : parameters)
					if (constraint.name().equals(parameter.name())) return true;
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
			public void assume(Node node) {
				if (!node.flags().contains(Tag.Facet)) node.addFlag(Tag.Facet);
			}
		};
	}

	public static Assumption isFacetInstance() {
		return new Assumption.FacetInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(FacetInstance)) node.addFlag(FacetInstance);
			}
		};
	}

	public static Assumption isFeature() {
		return new Assumption.Feature() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.Feature)) node.addFlag(Tag.Feature);
				propagateFlags(node, Tag.Feature);
			}
		};
	}

	public static Assumption isTerminal() {
		return new Assumption.Terminal() {
			@Override
			public void assume(Node node) {
				if (node.isReference()) return;
				if (!node.flags().contains(Tag.Terminal)) node.addFlag(Tag.Terminal);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.Terminal)).forEach(variable -> variable.addFlags(Tag.Terminal));
				propagateFlags(node, Tag.Terminal);
			}
		};
	}

	public static Assumption isPrototype() {
		return new Assumption.Prototype() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.Prototype)) node.addFlag(Tag.Prototype);
				propagateFlags(node, Tag.Prototype);
			}
		};
	}

	public static Assumption isComponent() {
		return new Assumption.Component() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.Component)) node.addFlag(Tag.Component);
			}
		};
	}


	public static Assumption isInstance() {
		return new Assumption.Instance() {
			@Override
			public void assume(Node node) {
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
			propagateFlags(component, flag);
		}
	}

}
