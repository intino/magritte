package tara.compiler.core.errorcollection;


import tara.lang.semantics.SemanticError;

public class SemanticException extends TaraException {

	private final String message;
	private final transient SemanticError[] errors;

	public SemanticException(String message, SemanticError... errors) {
		this.message = message;
		this.errors = errors.clone();
	}

	public SemanticError[] getErrors() {
		return errors;
	}

	@Override
	public String getMessage() {
		int line = this.errors[0].origin() == null ? 0 : this.errors[0].origin().line();
		return message + " @ line " + line + ".";
	}
}
