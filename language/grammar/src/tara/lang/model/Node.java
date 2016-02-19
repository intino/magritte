package tara.lang.model;

import java.util.Collections;
import java.util.List;

public interface Node extends Parametrized, NodeContainer, Refactorizable, Element {

	String ANNONYMOUS = "annonymous@";

	String name();

	void name(String name);

	boolean isSub();

	List<Node> subs();

	boolean isFacet();

	boolean is(Tag tag);

	boolean into(Tag tag);

	boolean isAbstract();

	boolean isTerminal();

	List<Tag> annotations();

	List<Tag> flags();

	void addAnnotations(Tag... annotations);

	void addFlags(List<Tag> flags);

	void addFlag(Tag flags);

	Node parent();

	String parentName();

	boolean isAnonymous();

	default String simpleType() {
		return type();
	}

	List<String> types();

	List<String> secondaryTypes();

	void type(String type);

	default List<String> metaTypes() {
		return Collections.emptyList();
	}


	default void metaTypes(List<String> types) {

	}

	Node resolve();

	boolean isReference();

	List<Node> referenceComponents();

	NodeContainer destinyOfReference();

	List<Node> children();

	default void addUses(List<String> imports) {
	}

	default <T extends Node> void addChild(T node) {
	}

	List<Facet> facets();

	default List<String> allowedFacets() {
		return Collections.emptyList();
	}

	default void addAllowedFacets(String... facet) {
	}

	default void addFacets(Facet... facets) {
	}

	default FacetTarget facetTarget() {
		return null;
	}

	default void facetTarget(FacetTarget target) {
	}

	String tableName();

	default void table(String table, List<String> parameters) {

	}

	@Override
	String toString();

	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();
}
