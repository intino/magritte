package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.AbstractNode;

public class InvalidHeritageError extends SemanticError implements SemanticError.FatalError {
	public InvalidHeritageError(String token, AbstractNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Invalid Heritage from: " + token + " @ line " + this.line;
	}
}
