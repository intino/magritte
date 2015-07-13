package siani.tara.semantic;

import siani.tara.semantic.model.Node;

abstract class ScriptNode implements Node {
	private Node context;

	@Override
	public Node context() {
		return context;
	}

	public void context(Node context) {
		this.context = context;
	}

}
