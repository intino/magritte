package siani.tara.semantic.model;

public interface Parameter extends Element {

	String inferredType();

	void setInferredType(String type);

	String[] getAnnotations();

	void setAnnotations(String[] annotations);

	void setMultiple(boolean multiple);

	boolean isMultiple();

	int getPosition();

	String getName();

	void setName(String name);

	Object[] getValues();

	String getContract();

	String getMetric();

	void setContract(String contract);

	boolean isVariableInit();

}
