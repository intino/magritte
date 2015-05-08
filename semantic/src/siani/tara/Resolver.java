package siani.tara;

import siani.tara.semantic.model.Node;
import siani.tara.semantic.Allow;

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
		Collection<Allow> allows = language.allows(node.context().type());
		if (allows == null) return;
		for (Allow allow : allows)
			if (checkAllowInclude(node, allow)) return;
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