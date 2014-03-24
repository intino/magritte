package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class MorphWithoutParentError extends SemanticError implements SemanticError.FatalError {

	public MorphWithoutParentError(String token, ASTNode node) {
		super(token, node);
	}



	@Override
	public String getMessage() {
		return "Morph out of context" + " @ line " + this.line;
	}
}
