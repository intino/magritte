package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.ASTNode;

public class UndefinedReferenceError extends SemanticError implements SemanticError.FatalError {
	public UndefinedReferenceError(String name, ASTNode node) {
		super(name, node);
	}

	@Override
	public String getMessage() {
		return "Unreached Reference: "+ token + " @ line " + this.line;
	}
}