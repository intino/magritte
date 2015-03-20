package siani.tara.compiler.core.errorcollection.semantic;

import siani.tara.compiler.model.Node;

public class RequiredNodeNotFoundError extends SemanticError implements SemanticError.FatalError {

	public RequiredNodeNotFoundError(String token, Node node) {
		super(token, node);
	}



	@Override
	public String getMessage() {
		return "Required Concept not found in the model: " + node.getName();
	}
}
