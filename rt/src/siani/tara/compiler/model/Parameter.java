package siani.tara.compiler.model;

import java.util.List;

public interface Parameter {

	String REFERENCE = "ref:";

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

	List<Object> getValues();

	String getContract();

	String getMetric();

	void setMetric(String metric);

	void setContract(String contract);

	boolean hasReferenceValue();

	List<String> getAllowedValues();

	void addAllowedValues(String[] allowedValues);
}
