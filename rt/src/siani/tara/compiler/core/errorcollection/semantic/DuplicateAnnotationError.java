package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.compiler.model.Node;

public class DuplicateAnnotationError extends SemanticError implements SemanticError.FatalError {

	public DuplicateAnnotationError(String token, Node node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Duplicated annotation: " + token + " @ line " + this.line;
	}
}
