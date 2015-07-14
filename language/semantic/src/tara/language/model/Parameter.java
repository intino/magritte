package tara.language.model;

import java.util.List;

public interface Parameter extends Element {

	String REFERENCE = "ref:";

	NodeContainer owner();

	String inferredType();

	void inferredType(String type);

	List<String> annotations();

	void annotations(List<String> annotations);

	boolean isMultiple();

	void multiple(boolean multiple);

	int position();

	String name();

	void name(String name);

	List<Object> values();

	String contract();

	void contract(String contract);

	String metric();

	void metric(String metric);

	boolean isVariableInit();

	void addAllowedParameters(List<String> values);

	boolean hasReferenceValue();

	List<String> getAllowedValues();

	void addAllowedValues(List<String> allowedValues);

	void substituteValues(List<? extends Object> newValues);

	String getUID();
}
