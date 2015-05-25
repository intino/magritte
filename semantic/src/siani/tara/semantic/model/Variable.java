package siani.tara.semantic.model;

public interface Variable extends Element {

	String NATIVE_SEPARATOR = "#";

	String name();

	String type();

	String[] flags();

	boolean isReference();

	Object[] defaultValue();
}
