package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.lang.DeclaredNode;

public class UnusedConceptError extends SemanticError implements SemanticError.Warning {
	public UnusedConceptError(String token, DeclaredNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Concept: " + token + " not used" + " @ line " + this.line;
	}
}
