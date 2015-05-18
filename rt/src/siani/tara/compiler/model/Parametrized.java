package siani.tara.compiler.model;

import java.util.Collection;

public interface Parametrized {

	Collection<Parameter> getParameters();

	void addParameter(String name, int position, String extension, Object... values);

	void addParameter(int position, String extension, Object... values);
}
