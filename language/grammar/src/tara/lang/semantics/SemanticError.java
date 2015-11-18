package tara.lang.semantics;

import tara.lang.model.Element;

import java.util.Collections;
import java.util.List;

public class SemanticError {

	private final String key;
	private final List<? extends Object> parameters;
	private final Element origin;

	public SemanticError(String key, Element origin) {
		this(key, origin, Collections.emptyList());
	}

	public SemanticError(String key, Element origin, List<? extends Object> parameters) {
		this.key = key;
		this.origin = origin;
		this.parameters = parameters;
	}

	public String key() {
		return key;
	}

	public List<?> parameters() {
		return parameters;
	}

	public Element origin() {
		return origin;
	}
}