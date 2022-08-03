package io.intino.magritte.builder.compiler.core.errorcollection;


import io.intino.magritte.lang.semantics.errorcollector.SemanticNotification;

public class SemanticException extends TaraException {

	private final String message;
	private final transient SemanticNotification[] errors;

	public SemanticException(String message, SemanticNotification... errors) {
		this.message = message;
		this.errors = errors.clone();
	}

	public SemanticNotification[] getErrors() {
		return errors;
	}

	@Override
	public String getMessage() {
		int line = this.errors[0].origin() == null ? 0 : this.errors[0].origin()[0].line();
		return message + " @ line " + line + ".";
	}
}
