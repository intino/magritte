package siani.tara.semantic.model;

import java.util.List;

public interface Parameter extends Element {

	String inferredType();

	void setInferredType(String type);

	List<String> getAnnotations();

	void setAnnotations(List<String> annotations);

	void setMultiple(boolean multiple);

	boolean isMultiple();

	int getPosition();

	String getName();

	void setName(String name);

	List<Object> getValues();

	String getContract();

	String getMetric();

	void setContract(String contract);

	boolean isVariableInit();

	void addAllowedParameters(List<String> values);
}
