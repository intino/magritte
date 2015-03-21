package siani.tara.compiler.core.errorcollection;


import siani.tara.compiler.semantic.wrappers.LanguageElement;
import siani.tara.semantic.SemanticError;

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
		int line = this.errors[0].origin() == null ? 0 : ((LanguageElement) this.errors[0].origin()).element().getLine();
		return message + " @ line " + line + ".";
	}
}
