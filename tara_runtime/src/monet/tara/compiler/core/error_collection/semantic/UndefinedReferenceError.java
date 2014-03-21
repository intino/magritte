package monet.tara.compiler.core.error_collection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class UndefinedReferenceError extends SemanticError implements SemanticError.FatalError {
	public UndefinedReferenceError(String name, ASTNode node) {
		super(name, node);
	}

	@Override
	public String getMessage() {
		return "Unreached Reference: "+ token + " @ line " + this.line;
	}
}
