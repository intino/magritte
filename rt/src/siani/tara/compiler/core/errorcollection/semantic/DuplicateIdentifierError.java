package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.lang.Node;

public class DuplicateIdentifierError extends SemanticError implements SemanticError.FatalError {
	public DuplicateIdentifierError(String token, Node node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Duplicated token:" + token + " @ line " + this.line;
	}
}
