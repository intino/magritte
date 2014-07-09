package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.lang.DeclaredNode;

public class UndefinedReferenceError extends SemanticError implements SemanticError.FatalError {
	public UndefinedReferenceError(String name, DeclaredNode node) {
		super(name, node);
	}

	@Override
	public String getMessage() {
		return "Unreached Reference: "+ token + " @ line " + this.line;
	}
}
