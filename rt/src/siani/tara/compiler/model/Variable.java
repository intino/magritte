package siani.tara.compiler.model;

import java.util.Collection;

public interface Variable {

	String getType();

	String getName();

	void setName(String name);

	String getExtension();

	void setExtension(String extension);

	void setType(String type);

	boolean isMultiple();

	void setMultiple(boolean multiple);

	Collection<Object> getAllowedValues();

	void addAllowedValues(Object... values);

	Collection<Object> getDefaultValues();

	void addDefaultValues(Object... values);

}
