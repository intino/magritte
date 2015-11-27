package tara.lang.model;

import java.util.Collections;
import java.util.List;

public interface Node extends Parametrized, NodeContainer, Element {

	String ANNONYMOUS = "annonymous@";

	String name();

	void name(String name);

	boolean isSub();

	boolean isMain();

	List<Node> subs();

	boolean isFacet();

	boolean isAbstract();

	boolean isNamed();

	boolean isFeature();

	boolean isFeatureInstance();

	boolean isFinal();

	boolean isEnclosed();

	boolean isTerminal();

	boolean isPrototype();

	boolean isDeclaration();

	boolean intoMain();

	String anchor();

	void anchor(String plate);

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

	Node resolve();

	boolean isReference();

	List<Node> referenceComponents();

	Node destinyOfReference();

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

	List<FacetTarget> facetTargets();

	default <T extends FacetTarget> void addFacetTargets(T... targets) {
	}

	@Override
	String toString();

	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();
}
