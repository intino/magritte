package siani.tara.compiler.model;

import java.util.Collection;

public interface Parameter {

	Variable inferredVariable();

	void setInferredVariable(Variable type);

	String getName();

	int getPosition();

	Collection<Object> getValues();

	String getExtension();
}
