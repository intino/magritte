package monet.tara.compiler.core.errorcollection;

import monet.tara.lang.AbstractNode;

public class DependencyException extends TaraException {


	private final String message;
	private final AbstractNode node;
	private final int line;

	public DependencyException(String message, AbstractNode node) {
		this.message = message;
		this.node = node;
		if (node != null)
			this.line = node.getLine();
		else this.line = -1;
	}

	public String getMessage() {
		return "Inconsistent dependency in " + node.getIdentifier();
	}

	public AbstractNode getNode() {
		return node;
	}

	public int getLine() {
		return line;
	}
}
