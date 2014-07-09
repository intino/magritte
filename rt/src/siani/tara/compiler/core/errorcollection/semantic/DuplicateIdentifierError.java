package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.lang.DeclaredNode;

public class DuplicateIdentifierError extends SemanticError implements SemanticError.FatalError {
	public DuplicateIdentifierError(String token, DeclaredNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Duplicated token:" + token + " @ line " + this.line;
	}
}
