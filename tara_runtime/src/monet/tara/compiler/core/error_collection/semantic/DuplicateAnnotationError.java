package monet.tara.compiler.core.error_collection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class DuplicateAnnotationError extends SemanticError implements SemanticError.FatalError {

	protected DuplicateAnnotationError(String token, ASTNode node, String message) {
		super(token, node, message);
	}
}
