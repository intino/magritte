package monet.tara.compiler.core.error_collection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class MorphWithoutParentError extends SemanticError implements SemanticError.FatalError {

	protected MorphWithoutParentError(String token, ASTNode node, String message) {
		super(token, node, message);
	}
}
