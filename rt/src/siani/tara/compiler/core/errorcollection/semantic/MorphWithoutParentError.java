package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.lang.Node;

public class MorphWithoutParentError extends SemanticError implements SemanticError.FatalError {

	public MorphWithoutParentError(String token, Node node) {
		super(token, node);
	}



	@Override
	public String getMessage() {
		return "Morph out of context" + " @ line " + this.line;
	}
}
