package tara.lang.model;

import java.util.List;

public interface Parameter extends Element {

	String REFERENCE_PREFIX = "ref:";

	String name();

	void name(String name);

	NodeContainer container();

	Primitive inferredType();

	void inferredType(Primitive type);

	List<String> flags();

	void flags(List<String> annotations);

	boolean isMultiple();

	void multiple(boolean multiple);

	int position();

	List<Object> values();

	Rule rule();

	void rule(Rule rule);

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
