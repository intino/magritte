package tara;

import tara.semantic.Allow;
import tara.semantic.model.Node;
import tara.semantic.model.NodeContainer;

import java.util.Collection;

public class Resolver {
	private final Language language;

	public Resolver(Language language) {
		this.language = language;
	}

	public void resolve(Node node) {
		if (getContext(node) == null) return; //TODO add resolved flag to nodes to improve resolve process
		checkAllowsInclude(node);
	}

	private void checkAllowsInclude(Node node) {
		resolve(getContext(node));
		Collection<Allow> allows = getContextAllows(node);
		if (allows == null) return;
		for (Allow allow : allows) if (checkAllowInclude(node, allow)) return;
	}

	private Collection<Allow> getContextAllows(Node node) {
		Collection<Allow> allows = language.allows(getContext(node).type());
		if (allows != null && contextAllowsNode(allows, node)) return allows;
		allows = findInFacets(node);
		return allows;
	}

	private boolean contextAllowsNode(Collection<Allow> context, Node node) {
		for (Allow allow : context)
			if (allow instanceof Allow.Include && ((Allow.Include) allow).type().endsWith("." + node.type()) || isOneOf(allow, node.type()))
				return true;
		return false;
	}

	private boolean isOneOf(Allow allow, String type) {
		if (!(allow instanceof Allow.OneOf)) return false;
		Allow.OneOf oneOf = (Allow.OneOf) allow;
		for (Allow one : oneOf.allows())
			if (((Allow.Include) one).type().endsWith("." + type)) return true;
		return false;
	}

	private Collection<Allow> findInFacets(Node node) {
		for (String type : getContext(node).secondaryTypes()) {
			Collection<Allow> allows = language.allows(type);
			if (allows != null) return allows;
		}
		return null;
	}

	private boolean checkAllowInclude(Node node, Allow allow) {
		if (!(allow instanceof Allow.Include)) return false;
		if (allow instanceof Allow.OneOf) return checkAllowOneOf(node, allow);
		return checkAsInclude(node, (Allow.Include) allow);
	}

	private boolean checkAsInclude(Node node, Allow.Include allow) {
		String absoluteType = allow.type();
		if (node.type() != null && getType(node.type()).equals(getType(absoluteType))) {
			node.type(absoluteType);
			return true;
		}
		return false;
	}

	private boolean checkAllowOneOf(Node node, Allow allow) {
		for (Allow one : ((Allow.OneOf) allow).allows()) {
			String absoluteType = ((Allow.Include) one).type();
			if (node.type() != null && getType(node.type()).equals(getType(absoluteType))) {
				node.type(absoluteType);
				return true;
			}
		}
		return false;
	}

	private String getType(String allowedType) {
		return allowedType.contains(".") ? allowedType.substring(allowedType.lastIndexOf(".") + 1) : allowedType;
	}

	private Node getContext(Node node) {
		NodeContainer container = node.container();
		while (!(container instanceof Node))
			container = container.container();
		return (Node) container;

	}
}