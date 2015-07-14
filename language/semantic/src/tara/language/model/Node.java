package tara.language.model;

import java.util.Collection;
import java.util.List;

public interface Node extends Parametrized, NodeContainer, Element {

	String ANNONYMOUS = "annonymous@";

	String name();

	void name(String name);

	String language();

	void language(String language);

	String doc();

	boolean isSub();

	boolean isMain();

	List<Node> subs();

	boolean isFacet();

	boolean isAbstract();

	boolean isRequired();

	boolean isSingle();

	boolean isNamed();

	boolean isFeature();

	boolean isFeatureInstance();

	boolean isFinal();

	boolean isTerminal();

	boolean isTerminalInstance();

	boolean intoSingle();

	boolean intoRequired();

	String plate();

	void plate(String address);

	List<Tag> annotations();

	List<Tag> flags();

	void addAnnotations(Tag... annotations);

	void addFlags(Tag... flags);

	void addImports(Collection<String> imports);

	Node parent();

	String parentName();

	boolean isAnonymous();

	String type();

	List<String> types();

	List<String> secondaryTypes();

	void type(String type);

	String getFullType();

	void setFullType(String type);

	Node resolve();

	boolean isReference();

	List<? extends Node> getReferenceComponents();

	Node destinyOfReference();

	List<? extends Node> children();

	void addChild(Node node);

	List<? extends Facet> facets();

	Collection<String> allowedFacets();

	void addAllowedFacets(String... facet);

	void addFacets(Facet... facets);

	List<? extends FacetTarget> facetTargets();

	void addFacetTargets(FacetTarget... targets);

	String toString();

	boolean equals(Object obj);

	int hashCode();
}
