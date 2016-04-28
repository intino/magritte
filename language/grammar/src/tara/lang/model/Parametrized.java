package tara.lang.model;

import java.util.List;

public interface Parametrized {

	List<Parameter> parameters();

	Node container();

	default void addParameter(String name, int position, String extension, int line, int column, List<Object> values) {
	}

	default void addParameter(int position, String extension, int line, int column, List<Object> values) {
	}
}
