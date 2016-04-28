package tara.lang.model;

import tara.lang.model.rules.CompositionRule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface NodeContainer extends Element {

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

	List<Node> siblings();

	List<Variable> variables();

	default <T extends Variable> void add(T... variables) {
	}

	default <T extends Variable> void add(int pos, T... variables) {
	}

	NodeContainer container();

	default <T extends NodeContainer> void container(T container) {
	}

	default List<Node> find(String type) {
		List<Node> result = new ArrayList<>();
		result.addAll(components().stream().filter(n -> n.type().equals(type)).collect(Collectors.toList()));
		for (Node node : components()) result.addAll(node.find(type));
		return result;
	}

	List<String> uses();

	String qualifiedName();

	String qualifiedNameCleaned();

	String doc();

	default void addDoc(String doc) {
	}
}
