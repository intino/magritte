package siani.tara.compiler.model;

import java.util.Collection;

public interface Parameter {

	NodeContainer getOwner();

	String getInferredType();

	void setInferredType(String type);

	void setMultiple(boolean multiple);

	boolean isMultiple();

	String[] getAnnotations();

	void setAnnotations(String[] annotations);

	String getName();

	void setName(String name);

	int getPosition();

	Collection<Object> getValues();

	String getMetric();
}
