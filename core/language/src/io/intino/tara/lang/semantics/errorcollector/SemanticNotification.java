package io.intino.tara.lang.semantics.errorcollector;

import io.intino.tara.lang.model.Element;

import java.util.Collections;
import java.util.List;

public class SemanticNotification {

	public enum Level {
		INFO, WARNING, RECOVERABLE_ERROR, ERROR, INSTANCE
	}

	private final Level level;
	private final String key;
	private final List<?> parameters;
	private final Element[] origin;

	public SemanticNotification(Level level, String key, Element origin) {
		this(level, key, origin, Collections.emptyList());
	}

	public SemanticNotification(Level level, String key, Element origin, List<?> parameters) {
		this.level = level;
		this.key = key;
		this.origin = new Element[]{origin};
		this.parameters = parameters;
	}

	public SemanticNotification(Level level, String key, Element[] origin) {
		this(level, key, origin, Collections.emptyList());
	}

	public SemanticNotification(Level level, String key, Element[] origin, List<?> parameters) {
		this.level = level;
		this.key = key;
		this.origin = origin;
		this.parameters = parameters;
	}

	public Level level() {
		return level;
	}

	public String key() {
		return key;
	}

	public List<?> parameters() {
		return parameters;
	}

	public Element[] origin() {
		return origin;
	}
}
