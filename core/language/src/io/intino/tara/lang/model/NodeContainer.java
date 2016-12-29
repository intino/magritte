package io.intino.tara.lang.model;

import io.intino.tara.lang.model.rules.Size;

import java.util.List;
import java.util.stream.Collectors;

public interface NodeContainer extends Element {

	Node container();

	default void container(Node container) {
	}

	List<Node> components();

	String type();

	default void add(Node node, List<Rule> rule) {

	}

	default List<Node> component(String name) {
		return components().stream().filter(component -> name.equals(component.name())).collect(Collectors.toList());
	}

	List<Rule> rulesOf(Node component);

	default Size sizeOf(Node component) {
		return (Size) rulesOf(component).stream().filter(r -> r instanceof Size).findAny().orElse(Size.MULTIPLE());
	}

	<T extends Node> boolean contains(T node);

	default <T extends Node> void remove(T node) {
	}

	default <T extends Variable> void add(T... variables) {
	}

	default <T extends Variable> void add(int pos, T... variables) {
	}

	List<String> uses();

	String doc();

	default void doc(String doc) {
	}
}
