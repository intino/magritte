package io.intino.magritte.lang.model;

import io.intino.magritte.lang.semantics.errorcollector.SemanticNotification.Level;

import java.util.Collections;
import java.util.List;

public interface Rule<T> {

	boolean accept(T value);

	default boolean accept(T value, String metric) {
		return accept(value);
	}

	default String errorMessage() {
		return "This element is not compliant with the rule " + this.getClass().getSimpleName();
	}

	default List<Object> errorParameters() {
		return Collections.emptyList();
	}

	default Level level() {
		return Level.ERROR;
	}
}
