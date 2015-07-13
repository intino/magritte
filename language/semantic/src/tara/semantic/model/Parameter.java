package tara.semantic.model;

import java.util.List;

public interface Parameter extends Element {

	String inferredType();

	void setInferredType(String type);

	List<String> getAnnotations();

	void setAnnotations(List<String> annotations);

	boolean isMultiple();

	void setMultiple(boolean multiple);

	int getPosition();

	String getName();

	void setName(String name);

	List<Object> getValues();

	String getContract();

	void setContract(String contract);

	String getMetric();

	boolean isVariableInit();

	void addAllowedParameters(List<String> values);
}
