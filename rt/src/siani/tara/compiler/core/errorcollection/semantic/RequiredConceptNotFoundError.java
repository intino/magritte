package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.lang.Node;

public class RequiredConceptNotFoundError extends SemanticError implements SemanticError.FatalError {

	public RequiredConceptNotFoundError(String token, Node node) {
		super(token, node);
	}



	@Override
	public String getMessage() {
		return "Required Concept not found in the model: " + node.getName();
	}
}
