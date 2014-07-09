package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.lang.DeclaredNode;

public class InvalidHeritageError extends SemanticError implements SemanticError.FatalError {
	public InvalidHeritageError(String token, DeclaredNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Invalid Heritage from: " + token + " @ line " + this.line;
	}
}
