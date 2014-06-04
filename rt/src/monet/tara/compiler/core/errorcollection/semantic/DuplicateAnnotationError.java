package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.Node;

public class DuplicateAnnotationError extends SemanticError implements SemanticError.FatalError {

	public DuplicateAnnotationError(String token, Node node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Duplicated annotation: " + token + " @ line " + this.line;
	}
}
