package tara.language.model;

import java.util.List;

public interface Parametrized {

	List<Parameter> parameters();

	default void addParameter(String name, int position, String extension, int line, int column, Object... values) {
	}

	default void addParameter(int position, String extension, int line, int column, Object... values) {
	}
}
