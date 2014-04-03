package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.compiler.core.ast.ASTNode;

public class UnusedConceptError extends SemanticError implements SemanticError.Warning {
	public UnusedConceptError(String token, ASTNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Concept: " + token + " not used" + " @ line " + this.line;
	}
}
