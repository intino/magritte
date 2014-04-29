package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.ASTNode;

public class PolymorphicChildlessError extends SemanticError implements SemanticError.FatalError {
	public PolymorphicChildlessError(String token, ASTNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Polymorphic concept: " + token + " without morphs" + " @ line " + this.line;
	}
}
