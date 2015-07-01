package siani.tara.semantic.model;

import java.util.List;

public interface Variable extends Element {

	String NATIVE_SEPARATOR = "#";

	String name();

	String type();

	String contract();

	List<String> flags();

	void flags(String... flag);

	boolean isReference();

	boolean isMultiple();

	boolean isOverriden();

	List<Object> defaultValue();
}
