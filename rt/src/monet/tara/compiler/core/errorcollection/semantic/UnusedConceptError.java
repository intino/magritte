package monet.tara.compiler.core.errorcollection.semantic;

import monet.tara.lang.Node;

public class UnusedConceptError extends SemanticError implements SemanticError.Warning {
	public UnusedConceptError(String token, Node node) {
		super(token, node);
	}

	@Override
	public String getMessage() {
		return "Concept: " + token + " not used" + " @ line " + this.line;
	}
}
