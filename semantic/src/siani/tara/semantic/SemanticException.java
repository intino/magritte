package siani.tara.semantic;

import siani.tara.semantic.model.Element;

public class SemanticException extends Exception {

	private final SemanticError error;

	public SemanticException(SemanticError error) {
		this.error = error;
	}

	public SemanticError getError() {
		return error;
	}

	@Override
	public String getMessage() {
		return MessageProvider.message(error.key(), error.parameters());
	}

	public String key() {
		return error.key();
	}

	public Element getOrigin() {
		return error.origin();
	}
}
