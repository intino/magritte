package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.AbstractNode;

public class DuplicateIdentifierError extends SemanticError implements SemanticError.FatalError {
	public DuplicateIdentifierError(String token, AbstractNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Duplicated token:" + token + " @ line " + this.line;
	}
}
