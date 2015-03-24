package siani.tara.compiler.model;

import java.util.Collection;

public interface Variable extends Cloneable{

	String getName();

	NodeContainer getContainer();

	void setContainer(NodeContainer container);

	String getType();

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

	Variable cloneIt(NodeContainer container) throws CloneNotSupportedException;
}
