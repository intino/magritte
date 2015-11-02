package tara;

import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.semantics.Constraint;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Resolver {
	private final Language language;

	public Resolver(Language language) {
		this.language = language;
	}

	public void resolve(Node node) {
		if (context(node) == null) return;
		checkComponentConstraints(node);
	}

	private void checkComponentConstraints(Node node) {
		resolve(context(node));
		List<Constraint> constraints = getContextAllows(node);
		if (constraints == null) return;
		for (Constraint constraint : constraints) if (checkAllowComponent(node, constraint)) return;
	}

	private List<Constraint> getContextAllows(Node node) {
		if (node == null) return Collections.emptyList();
		final Node context = context(node);
		List<Constraint> allows = context != null ? language.constraints(context.type()) : null;
		if (allows != null && contextAllowsNode(allows, node)) return allows;
		allows = findInFacets(node);
		return allows;
	}

	private boolean contextAllowsNode(Collection<Constraint> context, Node node) {
		for (Constraint constraint : context)
			if (constraint instanceof Constraint.Component &&
				(shortType(((Constraint.Component) constraint).type()).equals(node.type()) || isOneOf((Constraint.Component) constraint, node.type())))
				return true;
		return false;
	}

	private boolean isOneOf(Constraint.Component allow, String type) {
		if (!(allow instanceof Constraint.Component.OneOf)) return false;
		Constraint.Component.OneOf oneOf = (Constraint.Component.OneOf) allow;
		for (Constraint one : oneOf.components())
			if (((Constraint.Component) one).type().endsWith("." + type)) return true;
		return false;
	}

	private List<Constraint> findInFacets(Node node) {
		for (String type : context(node).secondaryTypes()) {
			List<Constraint> constraints = language.constraints(type);
			if (constraints != null && contextAllowsNode(constraints, node)) return constraints;
		}
		return null;
	}

	private boolean checkAllowComponent(Node node, Constraint constraint) {
		if (!(constraint instanceof Constraint.Component)) return false;
		if (constraint instanceof Constraint.OneOf) return checkAllowOneOf(node, constraint);
		return checkAsInclude(node, (Constraint.Component) constraint);
	}

	private boolean checkAsInclude(Node node, Constraint.Component allow) {
		String absoluteType = allow.type();
		if (node.type() != null && shortType(node.type()).equals(shortType(absoluteType))) {
			node.type(absoluteType);
			return true;
		}
		return false;
	}

	private boolean checkAllowOneOf(Node node, Constraint allow) {
		for (Constraint one : ((Constraint.OneOf) allow).components()) {
			String absoluteType = ((Constraint.Component) one).type();
			if (node.type() != null && shortType(node.type()).equals(shortType(absoluteType))) {
				node.type(absoluteType);
				return true;
			}
		}
		return false;
	}

	public Node context(Node node) {
		if (node == null || node.container() == null) return null;
		return getContainerNode(node);
	}

	public Node getContainerNode(Node node) {
		NodeContainer container = node.container();
		while (!(container instanceof Node))
			container = container.container();
		return (Node) container;
	}

	public static String shortType(String absoluteType) {
		return absoluteType.contains(".") ? absoluteType.substring(absoluteType.lastIndexOf('.') + 1) : absoluteType;
	}
}