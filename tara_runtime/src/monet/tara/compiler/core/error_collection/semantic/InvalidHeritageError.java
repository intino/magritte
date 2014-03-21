package monet.tara.compiler.core.error_collection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class InvalidHeritageError extends SemanticError implements SemanticError.FatalError {
	public InvalidHeritageError(String token, ASTNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Invalid Heritage from: " + token + " @ line " + this.line;
	}
}
