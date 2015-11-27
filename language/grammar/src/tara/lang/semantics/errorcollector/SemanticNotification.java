package tara.lang.semantics.errorcollector;

import tara.lang.model.Element;

import java.util.Collections;
import java.util.List;

public class SemanticNotification {

	public static int INFO = 0;
	public static int WARNING = 1;
	public static int ERROR = 2;

	private final int level;
	private final String key;
	private final List<?> parameters;
	private final Element origin;

	public SemanticNotification(int level, String key, Element origin) {
		this(level, key, origin, Collections.emptyList());
	}

	public SemanticNotification(int level, String key, Element origin, List<?> parameters) {
		this.level = level;
		this.key = key;
		this.origin = origin;
		this.parameters = parameters;
	}

	public int level() {
		return level;
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
