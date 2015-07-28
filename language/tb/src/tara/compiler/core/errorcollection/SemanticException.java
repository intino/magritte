package tara.compiler.core.errorcollection;


import tara.language.semantics.SemanticError;

public class SemanticException extends TaraException {

	private final String message;
	private final SemanticError[] errors;

	public SemanticException(String message, SemanticError... errors) {
		this.message = message;
		this.errors = errors.clone();
	}

	public SemanticError[] getErrors() {
		return errors;
	}

	public String getMessage() {
		int line = this.errors[0].origin() == null ? 0 : this.errors[0].origin().line();
		return message + " @ line " + line + ".";
	}
}
