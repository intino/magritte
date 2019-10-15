package io.intino.tara;

import io.intino.tara.lang.model.Aspect;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.Constraint.OneOf;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Resolver {
	private final Language language;

	public Resolver(Language language) {
		this.language = language;
	}

	public void resolve(Node node) {
		if (context(node) == null) return;
		resolveNode(node);
	}

	private void resolveNode(Node node) {
		resolve(context(node));
		List<Constraint> contextConstraints = contextConstraints(node);
		if (contextConstraints == null) return;
		for (Constraint constraint : components(contextConstraints))
			if (checkComponentConstraint(node, constraint)) return;
	}

	private List<Constraint> contextConstraints(Node node) {
		if (node == null || language == null) return Collections.emptyList();
		final Node context = context(node);
		List<Constraint> constraints = context != null && context.type() != null ? language.constraints(context.type()) : null;
		if (constraints != null && contextNodeConstraints(constraints, node)) return constraints;
		return findInAspects(node);
	}

	private boolean contextNodeConstraints(List<Constraint> context, Node node) {
		return context.stream().anyMatch(constraint -> constraint instanceof Constraint.Component &&
				(shortType(((Constraint.Component) constraint).type()).equals(node.type()) ||
						((Constraint.Component) constraint).type().equals(node.type()) ||
						isOneOf((Constraint.Component) constraint, node.type())));
	}

	private List<Constraint.Component> components(List<Constraint> context) {
		return context.stream().filter(c -> c instanceof Constraint.Component).map(c -> (Constraint.Component) c).collect(Collectors.toList());
	}

	private boolean isOneOf(Constraint.Component allow, String type) {
		if (!(allow instanceof OneOf)) return false;
		return ((OneOf) allow).components().stream().anyMatch(one -> one.type().endsWith("." + type));
	}

	private List<Constraint> findInAspects(Node node) {
		final Node context = context(node);
		for (Aspect aspect : context.appliedAspects()) {
			List<Constraint> constraints = language.constraints(context.type() + "." + aspect.type());
			if (constraints != null && contextNodeConstraints(constraints, node)) return constraints;
		}
		return null;
	}

	private void resolveAspects(Node node) {
		for (Aspect aspect : node.appliedAspects()) {
			if (language.constraints(node.type() + "." + aspect.type()) != null)
				aspect.fullType(node.type() + "." + aspect.type());
		}
	}

	private boolean checkComponentConstraint(Node node, Constraint constraint) {
		if (!(constraint instanceof Constraint.Component)) return false;
		if (constraint instanceof OneOf) return checkAllowOneOf(node, constraint);
		return checkAsComponent(node, (Constraint.Component) constraint);
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
				return true;
			}
		}
		return false;
	}

	public Node context(Node node) {
		if (node == null) return null;
		return node.container();
	}


	public static String shortType(String absoluteType) {
		return absoluteType.contains(".") ? absoluteType.substring(absoluteType.lastIndexOf('.') + 1) : absoluteType;
	}
}