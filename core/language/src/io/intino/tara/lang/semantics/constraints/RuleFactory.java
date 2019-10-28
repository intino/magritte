package io.intino.tara.lang.semantics.constraints;

import io.intino.tara.Resolver;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.variable.VariableRule;
import io.intino.tara.lang.semantics.Assumption;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.constraints.component.Component;
import io.intino.tara.lang.semantics.constraints.component.OneOf;
import io.intino.tara.lang.semantics.constraints.parameter.PrimitiveParameter;
import io.intino.tara.lang.semantics.constraints.parameter.ReferenceParameter;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.intino.tara.lang.model.Tag.AspectInstance;
import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class RuleFactory {
	private RuleFactory() {
	}

	public static Constraint.Component component(final String type, List<Rule> rules, final Tag... flags) {
		return new Component(type, rules, asList(flags));
	}

	@Deprecated
	public static Constraint.Component component(final String type, Rule rule, final Tag... flags) {
		return new Component(type, singletonList(rule), asList(flags));
	}

	public static Constraint.OneOf oneOf(List<Rule> rules, final Constraint.Component... components) {
		return new OneOf(asList(components), rules);
	}

	public static Constraint.Parameter parameter(final String name, final Primitive type, String aspect, final Size size, final int position, String scope, VariableRule rule, Tag... tags) {
		return new PrimitiveParameter(name, type, aspect, size, position, scope, rule, asList(tags));
	}

	public static Constraint.Parameter parameter(final String name, String type, String aspect, final Size size, final int position, String scope, VariableRule rule, Tag... tags) {
		return new ReferenceParameter(name, type, aspect, size, position, scope, rule, asList(tags));
	}

	public static Constraint.Aspect aspect(final String type, boolean terminal, String[] with, String[] without) {
		return aspect(type, terminal, false, with, without);
	}

	public static Constraint.Aspect aspect(final String type, boolean terminal, boolean required, String[] with, String[] without) {
		return new AspectConstraint(type, terminal, required, with, without);
	}

	public static Constraint.MetaAspect metaAspect(final String type, String... with) {
		return new MetaAspectConstraint(type, with);
	}

	public static Constraint.ComponentNotFound rejectOtherComponents(List<String> types) {
		return new Constraint.ComponentNotFound() {

			@Override
			public void check(Element element) throws SemanticException {
				NodeContainer node = (NodeContainer) element;
				for (Node component : node.components()) {
					if (!areCompatibles(component, types))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.type.not.exists", component, singletonList(component.type().replace(":", ""))));
				}
			}
		};
	}

	private static boolean areCompatibles(Node node, List<String> allowedTypes) {
		return node.types().stream().anyMatch(type -> type != null &&
				(allowedTypes.contains(type) || (node.container() != null && fromAspect(node.container().appliedAspects(), type, allowedTypes))))
				|| checkAspect(node, allowedTypes);
	}

	public static Constraint.RejectOtherParameters rejectOtherParameters(List<Constraint.Parameter> parameters) {
		return new Constraint.RejectOtherParameters() {
			@Override
			public void check(Element element) throws SemanticException {
				Parametrized parametrized = (Parametrized) element;
				for (io.intino.tara.lang.model.Parameter parameter : parametrized.parameters()) {
					if (!isAcceptable(parameter, parameters))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.other.parameter.in.context", parameter, singletonList(parameter.name())));
				}
			}

			private boolean isAcceptable(io.intino.tara.lang.model.Parameter parameter, List<Parameter> parameters) {
				for (Parameter constraint : parameters) {
					if (constraint.name().equals(parameter.name()) && hasAspect(constraint.aspect(), parameter.container().appliedAspects()))
						return true;
				}
				return false;
			}

			private boolean hasAspect(String requiredAspect, List<io.intino.tara.lang.model.Aspect> aspects) {
				if (requiredAspect.isEmpty()) return true;
				for (io.intino.tara.lang.model.Aspect aspect : aspects) if (aspect.type().equals(requiredAspect)) return true;
				return false;
			}
		};
	}

	public static Constraint.RejectOtherParameters rejectOtherAspects(List<Constraint.Aspect> aspects) {
		return new Constraint.RejectOtherParameters() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				for (io.intino.tara.lang.model.Aspect aspect : node.appliedAspects()) {
					if (!isAcceptable(aspects, aspect))
						throw new SemanticException(new SemanticNotification(ERROR, "reject.other.aspect.in.context", aspect, singletonList(aspect.type())));
				}
			}

			private boolean isAcceptable(List<Aspect> aspects, io.intino.tara.lang.model.Aspect aspect) {
				return aspects.stream().anyMatch(a -> a.type().equals(aspect.fullType()));
			}
		};
	}

	private static boolean fromAspect(List<Aspect> aspects, String nodeType, List<String> types) {
		return aspectComponent(aspects, nodeType, types) || asAspect(aspects, nodeType.split(":")[0]);
	}

	private static boolean aspectComponent(List<Aspect> aspects, String nodeType, List<String> types) {
		return aspects.stream().anyMatch(aspect -> types.contains(nodeType));
	}

	private static boolean asAspect(List<Aspect> aspects, String aspect) {
		return aspects.stream().anyMatch(a -> a.type().equals(aspect));
	}

	private static boolean checkAspect(Node node, List<String> types) {
		List<String> shortTypes = types.stream().map(Resolver::shortType).collect(Collectors.toList());
		return node.appliedAspects().stream().anyMatch(aspect -> shortTypes.contains(aspect.type()));
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

	public static Assumption isAspect() {
		return new Assumption.Aspect() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.Aspect)) node.addFlags(Tag.Aspect);
				if (!node.flags().contains(Tag.Terminal)) node.addFlags(Tag.Terminal);
			}
		};
	}

	public static Assumption isAspectInstance() {
		return new Assumption.AspectInstance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(AspectInstance)) node.addFlags(AspectInstance);
			}
		};
	}

	public static Assumption isFeature() {
		return new Assumption.Feature() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.Feature)) node.addFlags(Tag.Feature);
				propagateFlags(node, Tag.Feature);
			}
		};
	}

	public static Assumption isTerminal() {
		return new Assumption.Terminal() {
			@Override
			public void assume(Node node) {
				if (node.isReference()) return;
				if (!node.flags().contains(Tag.Terminal)) node.addFlags(Tag.Terminal);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.Terminal)).forEach(variable -> variable.addFlags(Tag.Terminal));
				propagateFlags(node, Tag.Terminal);
			}
		};
	}

	public static Assumption isVolatile() {
		return new Assumption.Volatile() {
			@Override
			public void assume(Node node) {
				if (node.isReference()) return;
				if (!node.flags().contains(Tag.Volatile)) node.addFlags(Tag.Volatile);
				propagateFlags(node, Tag.Volatile);
			}
		};
	}

	public static Assumption isComponent() {
		return new Assumption.Component() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.Component)) node.addFlags(Tag.Component);
			}
		};
	}


	public static Assumption isInstance() {
		return new Assumption.Instance() {
			@Override
			public void assume(Node node) {
				if (!node.flags().contains(Tag.Instance)) node.addFlags(Tag.Instance);
				node.variables().stream().filter(variable -> !variable.flags().contains(Tag.Instance)).forEach(variable -> variable.addFlags(Tag.Instance));
				propagateFlags(node, Tag.Instance);
			}
		};
	}

	public static Assumption stashNodeName(String stashNodeName) {
		return new Assumption.StashNodeName() {
			@Override
			public String stashNodeName() {
				return stashNodeName;
			}

			@Override
			public void assume(Node node) {
				node.stashNodeName(stashNodeName);
			}
		};
	}

	private static void propagateFlags(Node node, Tag flag) {
		for (Node component : node.components()) {
			if (component.isReference()) continue;
			if (!component.flags().contains(flag)) component.addFlags(flag);
			if (!component.equals(node)) propagateFlags(component, flag);
		}
	}

}
