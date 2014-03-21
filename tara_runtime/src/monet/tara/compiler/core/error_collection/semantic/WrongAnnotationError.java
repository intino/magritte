package monet.tara.compiler.core.error_collection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class WrongAnnotationError extends SemanticError implements SemanticError.FatalError {
	public WrongAnnotationError(String token, ASTNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Invalid annotation: " + token + " in context" + " @ line " + this.line;
	}
}
