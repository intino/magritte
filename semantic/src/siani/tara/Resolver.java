package siani.tara;

import siani.tara.semantic.Allow;
import siani.tara.semantic.model.Node;

import java.util.Collection;

public class Resolver {
	private final Language language;

	public Resolver(Language language) {
		this.language = language;
	}

	public void resolve(Node node) {
		if (node.context() == null) return; //TODO add resolved flag to nodes to improve resolve process
		checkAllowsInclude(node);
	}

	private void checkAllowsInclude(Node node) {
		resolve(node.context());
		Collection<Allow> allows = getContextAllows(node);
		if (allows == null) return;
		for (Allow allow : allows) if (checkAllowInclude(node, allow)) return;
	}

	private Collection<Allow> getContextAllows(Node node) {
		Collection<Allow> allows = language.allows(node.context().type());
		if (allows != null && contextAllowsNode(allows, node)) return allows;
		allows = findInFacets(node);
		return allows;
	}

	private boolean contextAllowsNode(Collection<Allow> context, Node node) {
		for (Allow allow : context)
			if (allow instanceof Allow.Include && ((Allow.Include) allow).type().endsWith("." + node.type()))
				return true;
		return false;
	}

	private Collection<Allow> findInFacets(Node node) {
		for (String type : node.context().secondaryTypes()) {
			Collection<Allow> allows = language.allows(type);
			if (allows != null) return allows;
		}
		return null;
	}

	private boolean checkAllowInclude(Node node, Allow allow) {
		if (!(allow instanceof Allow.Include)) return false;
		String absoluteType = ((Allow.Include) allow).type();
		if (node.type() != null && getType(node.type()).equals(getType(absoluteType))) {
			node.type(absoluteType);
			return true;
		}
		return false;
	}

	private String getType(String allowedType) {
		return allowedType.contains(".") ? allowedType.substring(allowedType.lastIndexOf(".") + 1) : allowedType;
	}
}