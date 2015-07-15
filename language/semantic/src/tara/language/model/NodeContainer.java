package tara.language.model;

import java.util.List;

public interface NodeContainer extends Element {

	List<? extends Node> components();

	String type();

	default <T extends Node> void add(T... nodes) {

	}

	default <T extends Node> void add(int pos, T... nodes) {

	}

	Node component(String name);

	<T extends Node> boolean contains(T node);

	<T extends Node> boolean remove(T node);

	default void moveToTheTop(){

	}

	List<? extends Node> siblings();

	List<? extends Variable> variables();

	default <T extends Variable> void add(T... variables) {

	}

	default <T extends Variable> void add(int pos, T... variables) {

	}

	NodeContainer container();

	<T extends tara.language.model.NodeContainer> void container(T container);

	String qualifiedName();

	String doc();

	void addDoc(String doc);
}
