package siani.tara.semantic.model;

import java.util.List;

public interface Node extends NodeContainer {
	Node context();

	String type();

	boolean isReference();

	Node destinyOfReference();

	void type(String type);

	List<String> secondaryTypes();

	List<String> types();

	String name();

	Node parent();

	boolean hasSubs();

	String plate();

	List<String> annotations();

	List<String> flags();

	void flags(String... flags);

	void annotations(String... annotations);

	void moveToTheTop();

	List<Facet> facets();

	List<FacetTarget> facetTargets();

	List<Parameter> parameters();

	List<Variable> variables();
}
