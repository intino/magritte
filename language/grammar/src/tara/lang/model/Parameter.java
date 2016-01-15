package tara.lang.model;

import java.util.List;

public interface Parameter extends Valued, Element {

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

	Rule rule();

	void rule(Rule rule);

	String metric();

	void metric(String metric);

	boolean isVariableInit();

	boolean hasReferenceValue();

	void substituteValues(List<? extends Object> newValues);

	String getUID();
}
