package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.Node;

public class UndefinedReferenceError extends SemanticError implements SemanticError.FatalError {
	public UndefinedReferenceError(String name, Node node) {
		super(name, node);
	}

	@Override
	public String getMessage() {
		return "Unreached Reference: "+ token + " @ line " + this.line;
	}
}
