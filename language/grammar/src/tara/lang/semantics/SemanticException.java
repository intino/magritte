package tara.lang.semantics;

import tara.lang.model.Element;

import java.util.List;
import java.util.stream.Collectors;

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
		return MessageProvider.message(error.key(), error.parameters().toArray());
	}

	public String[] getParameters() {
		List<String> parameters = error.parameters().stream().map(Object::toString).collect(Collectors.toList());
		return parameters.toArray(new String[parameters.size()]);
	}

	public String key() {
		return error.key();
	}

	public Element getOrigin() {
		return error.origin();
	}
}
