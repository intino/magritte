package io.intino.magritte;

import io.intino.magritte.lang.model.Aspect;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.semantics.Constraint;
import io.intino.magritte.lang.semantics.Constraint.OneOf;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Resolver {
	private final Language language;

	public Resolver(Language language) {
		this.language = language;
	}

	public static String shortType(String absoluteType) {
		return absoluteType.contains(".") ? absoluteType.substring(absoluteType.lastIndexOf('.') + 1) : absoluteType;
	}

	public void resolve(Node node) {
		if (context(node) == null) return;
		resolveNode(node);
	}

	private void resolveNode(Node node) {
		resolve(context(node));
		List<Constraint> contextConstraints = contextConstraints(node);
		if (contextConstraints == null) return;
		for (Constraint constraint : components(contextConstraints)) if (checkComponentConstraint(node, constraint)) return;
		metaAspects(contextConstraints).forEach(constraint -> checkMetaAspectConstraint(node, constraint));
	}

	private List<Constraint> contextConstraints(Node node) {
		if (node == null || language == null) return Collections.emptyList();
		final Node context = context(node);
		List<Constraint> constraints = context != null && context.type() != null ? language.constraints(context.type()) : null;
		if (constraints != null && (isComponent(constraints, node) || isMetaAspect(constraints, node))) return constraints;
		return findInAspects(node);
	}

	private boolean isMetaAspect(List<Constraint> constraints, Node node) {
		return metaAspects(constraints).stream().anyMatch(constraint -> shortType(constraint.type()).equals(node.type()));
	}

	private boolean isComponent(List<Constraint> context, Node node) {
		return context.stream().anyMatch(constraint -> constraint instanceof Constraint.Component &&
				(shortType(((Constraint.Component) constraint).type()).equals(node.type()) ||
						((Constraint.Component) constraint).type().equals(node.type()) ||
						isOneOf((Constraint.Component) constraint, node.type())));
	}

	private List<Constraint.Component> components(List<Constraint> context) {
		return context.stream().filter(c -> c instanceof Constraint.Component).map(c -> (Constraint.Component) c).collect(Collectors.toList());
	}

	private List<Constraint.MetaAspect> metaAspects(List<Constraint> context) {
		return context.stream().filter(c -> c instanceof Constraint.MetaAspect).map(c -> (Constraint.MetaAspect) c).collect(Collectors.toList());
	}

	private boolean isOneOf(Constraint.Component allow, String type) {
		if (!(allow instanceof OneOf)) return false;
		return ((OneOf) allow).components().stream().anyMatch(one -> one.type().endsWith("." + type) || one.type().equals(type));
	}

	private List<Constraint> findInAspects(Node node) {
		final Node context = context(node);
		for (Aspect aspect : context.appliedAspects()) {
			List<Constraint> constraints = language.constraints(aspect.fullType());
			if (constraints != null && isComponent(constraints, node)) return constraints;
		}
		return null;
	}

	private void resolveAspects(Node node) {
		for (Aspect aspect : node.appliedAspects()) {
			Constraint.Aspect constraint = (Constraint.Aspect) language.constraints(node.type()).stream().
					filter(c -> c instanceof Constraint.Aspect &&
							simpleType((Constraint.Aspect) c).equals(aspect.type())).findFirst().orElse(null);
			if (constraint != null) aspect.fullType(constraint.type());
		}
	}

	private String simpleType(Constraint.Aspect aspect) {
		return aspect.type().contains(".") ? aspect.type().substring(aspect.type().lastIndexOf(".") + 1) : aspect.type();
	}

	private boolean checkComponentConstraint(Node node, Constraint constraint) {
		if (!(constraint instanceof Constraint.Component)) return false;
		if (constraint instanceof OneOf) return checkAllowOneOf(node, constraint);
		return checkAsComponent(node, (Constraint.Component) constraint);
	}

	private void checkMetaAspectConstraint(Node node, Constraint.MetaAspect constraint) {
		if (node.type() != null && shortType(node.type()).equals(shortType(constraint.type()))) node.type(constraint.type());
	}

	private boolean checkAsComponent(Node node, Constraint.Component allow) {
		String absoluteType = allow.type();
		if (node.type() != null && shortType(node.type()).equals(shortType(absoluteType))) {
			node.type(absoluteType);
			node.metaTypes(language.types(absoluteType));
			resolveAspects(node);
			return true;
		}
		return false;
	}

	private boolean checkAllowOneOf(Node node, Constraint allow) {
		for (Constraint one : ((OneOf) allow).components()) {
			String absoluteType = ((Constraint.Component) one).type();
			if (node.type() != null && shortType(node.type()).equals(shortType(absoluteType))) {
				node.type(absoluteType);
				node.metaTypes(language.types(absoluteType));
				resolveAspects(node);
				return true;
			}
		}
		return false;
	}

	public Node context(Node node) {
		if (node == null) return null;
		return node.container();
	}
}