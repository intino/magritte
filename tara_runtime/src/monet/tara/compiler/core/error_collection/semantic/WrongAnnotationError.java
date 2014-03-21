package monet.tara.compiler.core.error_collection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class WrongAnnotationError extends SemanticError implements SemanticError.FatalError {
	protected WrongAnnotationError(String token, ASTNode node, String message) {
		super(token, node, message);
	}
}
