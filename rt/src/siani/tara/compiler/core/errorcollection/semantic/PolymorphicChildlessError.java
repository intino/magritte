package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.model.DeclaredNode;

public class PolymorphicChildlessError extends SemanticError implements SemanticError.FatalError {
	public PolymorphicChildlessError(String token, DeclaredNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Polymorphic concept: " + token + " without morphs" + " @ line " + this.line;
	}
}
