package siani.tara.semantic.model;

public interface Node extends Element {
	Node context();

	String type();

	boolean isReference();

	void type(String type);

	String[] secondaryTypes();

	String[] types();

	String name();

	Node parent();

	boolean hasSubs();

	String plate();

	String[] annotations();

	String[] flags();

	void flags(String... flags);

	void annotations(String... annotations);

	void moveToTheTop();

	Facet[] facets();

	FacetTarget[] facetTargets();

	Parameter[] parameters();

	Node[] includes();

	Variable[] variables();
}
