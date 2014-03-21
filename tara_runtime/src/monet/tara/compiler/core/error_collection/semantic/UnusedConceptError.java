package monet.tara.compiler.core.error_collection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class UnusedConceptError extends SemanticError implements SemanticError.Warning {
	protected UnusedConceptError(String token, ASTNode node, String message) {
		super(token, node, message);
	}
}
