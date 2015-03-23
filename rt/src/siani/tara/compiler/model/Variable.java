package siani.tara.compiler.model;

import java.util.Collection;

public interface Variable {

	String getType();

	String getName();

	boolean isMultiple();

	String getExtension();

	Collection<Annotation> getAnnotations();

	void addAnnotations(String... annotations);

	boolean isTerminal();

	boolean isReadOnly();

	void setExtension(String extension);

	void setName(String name);

	void setType(String type);

	void setMultiple(boolean multiple);

	Collection<Object> getAllowedValues();

	void addAllowedValues(Object... values);

	Collection<Object> getDefaultValues();

	void addDefaultValues(Object... values);

	String getDefaultExtension();

	void setDefaultExtension(String defaultExtension);

}
