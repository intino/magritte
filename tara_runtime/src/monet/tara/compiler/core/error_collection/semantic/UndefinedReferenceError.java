package monet.tara.compiler.core.error_collection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class UndefinedReferenceError extends SemanticError {
	protected UndefinedReferenceError(String name, ASTNode node, String message) {
		super(name, node, message);
	}
}
