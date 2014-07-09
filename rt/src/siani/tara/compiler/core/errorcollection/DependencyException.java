package siani.tara.compiler.core.errorcollection;

import siani.tara.lang.DeclaredNode;

public class DependencyException extends TaraException {

	private final String message;
	private final DeclaredNode node;
	private final int line;

	public DependencyException(String message, DeclaredNode node) {
		this.message = message;
		this.node = node;
		if (node != null)
			this.line = node.getLine();
		else this.line = -1;
	}

	public String getMessage() {
		return "Inconsistent dependency in " + node.getName();
	}

	public DeclaredNode getNode() {
		return node;
	}

	public int getLine() {
		return line;
	}
}
