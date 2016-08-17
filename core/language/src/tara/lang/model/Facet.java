package tara.lang.model;

public interface Facet extends Element {

	String type();

	Node container();

	default void container(Node container) {

	}
}
