package tara.compiler.model;

import java.util.List;

public interface Parametrized {

	List<Parameter> getParameters();

	void addParameter(String name, int position, String extension, Object... values);

	void addParameter(int position, String extension, Object... values);
}
