package siani.tara.compiler.model;

import java.util.List;

public interface Parameter {

	String REFERENCE = "ref:";

	NodeContainer getOwner();

	String getInferredType();

	void setInferredType(String type);

	boolean isMultiple();

	void setMultiple(boolean multiple);

	List<String> getAnnotations();

	void setAnnotations(List<String> annotations);

	String getName();

	void setName(String name);

	int getPosition();

	List<Object> getValues();

	String getContract();

	void setContract(String contract);

	String getMetric();

	void setMetric(String metric);

	boolean hasReferenceValue();

	List<String> getAllowedValues();

	void addAllowedValues(List<String> allowedValues);

	void substituteValues(List<? extends Object> newValues);
}
