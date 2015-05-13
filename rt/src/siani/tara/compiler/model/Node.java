package siani.tara.compiler.model;

import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.model.Tag;

import java.util.Collection;

public interface Node extends NodeContainer, Parameterized {

	String ANNONYMOUS = "annonymous@";

	String getName();

	void setName(String name);

	String getLanguage();

	void setLanguage(String language);


	String getDoc();

	boolean isSub();

	boolean isRoot();

	Collection<Node> getSubNodes();

	boolean isFacet();

	boolean isAbstract();

	boolean isRequired();

	boolean isSingle();

	boolean isNamed();

	boolean isFeature();

	boolean isFeatureInstance();

	boolean isProperty();

	boolean isPropertyInstance();

	boolean isTerminal();

	boolean isTerminalInstance();

	Long getAddress();

	void setAddress(Long address);

	Collection<Tag> getAnnotations();

	Collection<Tag> getFlags();

	void addAnnotations(String... annotations);

	void addFlags(String... flags);

	void addImports(Collection<String> imports);

	Node getParent();

	String getParentName();

	boolean isAnonymous();

	String getType();

	void setType(String type);

	String getFullType();

	void setFullType(String type);

	Node resolve();

	Collection<NodeReference> getInnerNodeReferences();

	Collection<Node> getChildren();

	void addChild(Node node);

	Collection<Facet> getFacets();

	Collection<String> getAllowedFacets();

	void addAllowedFacets(String... facet);

	void addFacets(Facet... facets);

	Collection<FacetTarget> getFacetTargets();

	void addFacetTargets(FacetTarget... targets);

	String toString();

	boolean equals(Object obj);

	int hashCode();
}
