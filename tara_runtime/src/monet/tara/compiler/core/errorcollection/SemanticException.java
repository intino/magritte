package monet.tara.compiler.core.errorcollection;


import monet.tara.compiler.core.errorcollection.semantic.SemanticError;

public class SemanticException extends TaraException {

	SemanticError[] errors;

	public SemanticException(SemanticError[] errors) {
		this.errors = errors.clone();
	}

	public SemanticError[] getErrors() {
		return errors;
	}
}
