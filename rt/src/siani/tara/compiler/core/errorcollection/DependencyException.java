package siani.tara.compiler.core.errorcollection;

import siani.tara.lang.LinkNode;
import siani.tara.lang.Node;

public class DependencyException extends TaraException {

	private final String message;
	private final Node node;
	private final int line;

	public DependencyException(String message, Node node) {
		this.message = message;
		this.node = node;
		if (node != null)
			this.line = node.getLine();
		else this.line = -1;
	}

	public String getMessage() {
		return "Inconsistent dependency in concept " + (node instanceof LinkNode ? ((LinkNode) node).getDestinyName() : node.getName())+ "; " + message + " @ line " + this.line + ", column " + 1 + ".";
	}

	public Node getNode() {
		return node;
	}

	public int getLine() {
		return line;
	}
}
