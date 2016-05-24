package tara.lang.model;

import tara.lang.model.rules.CompositionRule;

import java.util.List;
import java.util.stream.Collectors;

public interface NodeContainer extends Element {

	Node container();

	default void container(Node container) {
	}

	List<Node> components();

	String type();

	default <T extends Node> void add(T node, CompositionRule rule) {
	}

	default <T extends Node> void add(int pos, T node, CompositionRule rule) {
	}

	default List<Node> component(String name) {
		return components().stream().filter(component -> name.equals(component.name())).collect(Collectors.toList());
	}

	CompositionRule ruleOf(Node component);

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
