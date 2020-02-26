package io.intino.magritte.lang.model;

import java.util.List;

public interface Aspect extends Element {

	String type();

	void fullType(String s);

	String fullType();

	List<Parameter> parameters();

	Node container();

	default void container(Node container) {

	}
}
