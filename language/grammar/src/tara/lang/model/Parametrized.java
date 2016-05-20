package tara.lang.model;

import java.util.List;

public interface Parametrized {

	List<Parameter> parameters();

	default void addParameter(String name, String facet, int position, String extension, int line, int column, List<Object> values) {
	}

}
