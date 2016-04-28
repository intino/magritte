package tara.lang.model;


import tara.lang.model.rules.variable.VariableRule;

import java.util.List;

public interface Parameter extends Valued {

	String name();

	void name(String name);

	Parametrized container();

	void type(Primitive type);

	List<Tag> flags();

	void flags(List<Tag> annotations);

	boolean isMultiple();

	void multiple(boolean multiple);

	int position();

	VariableRule rule();

	void rule(VariableRule rule);

	void scope(String scope);

	String scope();

	String metric();

	void metric(String metric);

	boolean isVariableInit();

	boolean hasReferenceValue();

	void substituteValues(List<?> newValues);

	String getUID();
}
