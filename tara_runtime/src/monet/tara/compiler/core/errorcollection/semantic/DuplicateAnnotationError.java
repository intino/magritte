package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class DuplicateAnnotationError extends SemanticError implements SemanticError.FatalError {

	public DuplicateAnnotationError(String token, ASTNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Duplicated annotation: " + token + " @ line " + this.line;
	}
}
