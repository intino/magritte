package tara.language.model;

import java.util.List;

public interface NodeContainer extends Element {

	List<Node> components();

	String type();

	default <T extends Node> void add(T... nodes) {

	}

	default <T extends Node> void add(int pos, T... nodes) {

	}

	Node component(String name);

	<T extends Node> boolean contains(T node);

	default <T extends Node> boolean remove(T node) {
		return false;
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

	default <T extends tara.language.model.NodeContainer> void container(T container) {
	}

	List<String> uses();

	String qualifiedName();

	String doc();

	default void addDoc(String doc) {
	}

	default String language() {
		return null;
	}

	default void language(String language) {
	}
}
