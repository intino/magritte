package siani.tara.compiler.model;

import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.model.Tag;

import java.util.Collection;
import java.util.List;

public interface Node extends NodeContainer, Parametrized {

	String ANNONYMOUS = "annonymous@";

	String getName();

	void setName(String name);

	String getLanguage();

	void setLanguage(String language);

	String getDoc();

	boolean isSub();

	boolean isMain();

	List<Node> getSubNodes();

	boolean isFacet();

	boolean isAbstract();

	boolean isRequired();

	boolean isSingle();

	boolean isNamed();

	boolean isFeature();

	boolean isFeatureInstance();

	boolean isImplicit();

	boolean isPropertyInstance();

	boolean isTerminal();

	boolean isTerminalInstance();

	boolean intoSingle();

	boolean intoRequired();

	String getPlate();

	void setPlate(String address);

	List<Tag> getAnnotations();

	List<Tag> getFlags();

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

	List<NodeReference> getInnerNodeReferences();

	List<Node> getChildren();

	void addChild(Node node);

	List<Facet> getFacets();

	Collection<String> getAllowedFacets();

	void addAllowedFacets(String... facet);

	void addFacets(Facet... facets);

	List<FacetTarget> getFacetTargets();

	void addFacetTargets(FacetTarget... targets);

	String toString();

	boolean equals(Object obj);

	int hashCode();
}
