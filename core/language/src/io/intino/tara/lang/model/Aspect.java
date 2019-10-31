package io.intino.tara.lang.model;

public interface Aspect extends Element {

	String type();

	void fullType(String s);

	String fullType();

	Node container();

	default void container(Node container) {

	}
}
