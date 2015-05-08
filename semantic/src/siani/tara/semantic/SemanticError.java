package siani.tara.semantic;

import siani.tara.semantic.model.Element;

public class SemanticError {

	private final String key;
	private Element origin;
	private final Object[] parameters;

	public SemanticError(String key, Object... parameters) {
		this.key = key;
		this.parameters = parameters;
	}

	public SemanticError(String key, Element origin, Object[] parameters) {
		this.key = key;
		this.origin = origin;
		this.parameters = parameters;
	}

	public String key() {
		return key;
	}

	public Object[] parameters() {
		return parameters;
	}

	public Element origin() {
		return origin;
	}
}
