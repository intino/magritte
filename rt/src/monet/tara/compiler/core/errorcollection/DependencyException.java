package monet.tara.compiler.core.errorcollection;

import monet.tara.lang.ASTNode;

public class DependencyException extends TaraException {


	private final String message;
	private final ASTNode node;
	private final int line;

	public DependencyException(String message, ASTNode node) {
		this.message = message;
		this.node = node;
		if (node != null)
			this.line = node.getLine();
		else this.line = -1;
	}

	public String getMessage() {
		return "Inconsistent dependency in " + node.getIdentifier();
	}

	public ASTNode getNode() {
		return node;
	}

	public int getLine() {
		return line;
	}
}
