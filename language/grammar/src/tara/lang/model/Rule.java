package tara.lang.model;

import java.util.Collections;
import java.util.List;

public interface Rule<T> {

	boolean accept(T value);

	default boolean accept(T value, String metric) {
		return true;
	}

	default String errorMessage() {
		return "";
	}

	default List<Object> errorParameters() {
		return Collections.emptyList();
	}
}
