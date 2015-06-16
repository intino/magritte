package siani.tara.semantic.model;

public interface Variable extends Element {

	String NATIVE_SEPARATOR = "#";

	String name();

	String type();

	String[] flags();

	void flags(String... flag);

	boolean isReference();

	boolean isOverriden();

	Object[] defaultValue();
}
