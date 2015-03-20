package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.compiler.model.Node;

public class UnusedNodeError extends SemanticError implements SemanticError.Warning {
	public UnusedNodeError(String token, Node node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Concept: " + token + " not used" + " @ line " + this.line;
	}
}
