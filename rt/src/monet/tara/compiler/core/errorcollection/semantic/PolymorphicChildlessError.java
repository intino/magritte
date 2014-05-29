package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.AbstractNode;

public class PolymorphicChildlessError extends SemanticError implements SemanticError.FatalError {
	public PolymorphicChildlessError(String token, AbstractNode node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Polymorphic concept: " + token + " without morphs" + " @ line " + this.line;
	}
}
