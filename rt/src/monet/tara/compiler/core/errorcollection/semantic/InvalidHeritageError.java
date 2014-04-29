package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.ASTNode;

public class InvalidHeritageError extends SemanticError implements SemanticError.FatalError {
	public InvalidHeritageError(String token, ASTNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Invalid Heritage from: " + token + " @ line " + this.line;
	}
}
