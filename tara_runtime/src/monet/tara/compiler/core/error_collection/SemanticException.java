package monet.tara.compiler.core.error_collection;


import monet.tara.compiler.core.error_collection.semantic.SemanticError;

public class SemanticException extends TaraException {

	SemanticError[] errors;


	public SemanticException(SemanticError[] errors) {
		this.errors = errors;
	}

	public SemanticError[] getErrors() {
		return errors;
	}
}
