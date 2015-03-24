package siani.tara.compiler.model;

import java.util.Collection;

public interface Parameter {

	String getInferredType();

	void setInferredType(String type);

	String[] getAnnotations();

	void setAnnotations(String[] annotations);

	String getName();

	void setName(String name);

	int getPosition();

	Collection<Object> getValues();

	String getExtension();
}
