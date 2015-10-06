package tara;

import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.semantics.Allow;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static tara.language.semantics.constraints.ConstraintHelper.shortType;

public class Resolver {
	private final Language language;

	public Resolver(Language language) {
		this.language = language;
	}

	public void resolve(Node node) {
		if (context(node) == null) return;
		checkAllowsInclude(node);
	}

	private void checkAllowsInclude(Node node) {
		resolve(context(node));
		List<Allow> allows = getContextAllows(node);
		if (allows == null) return;
		for (Allow allow : allows) if (checkAllowInclude(node, allow)) return;
	}

	private List<Allow> getContextAllows(Node node) {
		if (node == null) return Collections.emptyList();
		final Node context = context(node);
		List<Allow> allows = context != null ? language.allows(context.type()) : null;
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

	private List<Allow> findInFacets(Node node) {
		for (String type : context(node).secondaryTypes()) {
			List<Allow> allows = language.allows(type);
			if (allows != null && contextAllowsNode(allows, node)) return allows;
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
		if (node.type() != null && shortType(node.type()).equals(shortType(absoluteType))) {
			node.type(absoluteType);
			return true;
		}
		return false;
	}

	private boolean checkAllowOneOf(Node node, Allow allow) {
		for (Allow one : ((Allow.OneOf) allow).allows()) {
			String absoluteType = ((Allow.Include) one).type();
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

}