package tara.lang.model;

public interface Facet extends Parametrized, Element {

	String type();

	Node container();

	void container(Node container);
}
