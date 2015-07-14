package tara.language.model;

import java.util.List;

public interface Parametrized {

	List<? extends Parameter> parameters();

	void addParameter(String name, int position, String extension, Object... values);

	void addParameter(int position, String extension, Object... values);
}
