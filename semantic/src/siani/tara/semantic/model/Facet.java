package siani.tara.semantic.model;

public interface Facet extends Element {

	String type();

	Parameter[] parameters();

	Node[] includes();

}
