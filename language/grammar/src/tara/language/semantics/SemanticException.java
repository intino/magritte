package tara.language.semantics;

import tara.language.model.Element;

public class SemanticException extends Exception {

	private final transient SemanticError error;

	public SemanticException(SemanticError error) {
		this.error = error;
	}

	public SemanticError getError() {
		return error;
	}

	@Override
	public String getMessage() {
		return MessageProvider.message(error.key(), error.parameters().toArray(new Object[error.parameters().size()]));
	}

	public String key() {
		return error.key();
	}

	public Element getOrigin() {
		return error.origin();
	}
}
