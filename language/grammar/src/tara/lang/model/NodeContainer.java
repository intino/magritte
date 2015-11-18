package tara.lang.model;

import tara.lang.model.rules.CompositionRule;

import java.util.List;

public interface NodeContainer extends Element {

	List<Node> components();

	String type();

	default <T extends Node> void add(T node, CompositionRule rule) {
	}

	default <T extends Node> void add(int pos, T node, CompositionRule rule) {
	}

	Node component(String name);

	CompositionRule ruleOf(Node component);

	<T extends Node> boolean contains(T node);

	default <T extends Node> void remove(T node) {
	}

	default void moveToTheTop() {
	}

	List<Node> siblings();

	List<Variable> variables();

	default <T extends Variable> void add(T... variables) {
	}

	default <T extends Variable> void add(int pos, T... variables) {
	}

	NodeContainer container();

	default <T extends NodeContainer> void container(T container) {
	}

	List<String> uses();

	String qualifiedName();

	String qualifiedNameCleaned();

	String doc();

	default void addDoc(String doc) {
	}
}