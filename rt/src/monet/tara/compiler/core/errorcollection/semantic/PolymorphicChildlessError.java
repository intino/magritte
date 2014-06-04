package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.Node;

public class PolymorphicChildlessError extends SemanticError implements SemanticError.FatalError {
	public PolymorphicChildlessError(String token, Node node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Polymorphic concept: " + token + " without morphs" + " @ line " + this.line;
	}
}
