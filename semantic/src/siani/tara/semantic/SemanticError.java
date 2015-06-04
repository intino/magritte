package siani.tara.semantic;

import siani.tara.semantic.model.Element;

import java.util.Collections;
import java.util.List;

public class SemanticError {

	private final String key;
	private Element origin;
	private final List<Object> parameters;

	public SemanticError(String key, Element origin) {
		this(key, origin, Collections.emptyList());
	}

	public SemanticError(String key, Element origin, List<Object> parameters) {
		this.key = key;
		this.origin = origin;
		this.parameters = parameters;
	}

	public String key() {
		return key;
	}

	public List<Object> parameters() {
		return parameters;
	}

	public Element origin() {
		return origin;
	}
}