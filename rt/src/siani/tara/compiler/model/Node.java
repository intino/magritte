package siani.tara.compiler.model;

import siani.tara.compiler.model.impl.NodeReference;

import java.util.Collection;

public interface Node extends NodeContainer, Parameterized {

	String getName();

	void setName(String name);

	String getFile();

	void setFile(String file);

	int getLine();

	void setLine(int line);

	String getLanguage();

	void setLanguage(String language);

	String getDoc();

	boolean isSub();

	boolean isRoot();

	Collection<Node> getSubNodes();

	boolean isIntention();

	boolean isFacet();

	boolean isAddressed();

	boolean isAbstract();

	boolean isAggregated();

	boolean isProperty();

	boolean isComponent();

	boolean isCase();

	Long getAddress();

	void setAddress(Long address);

	Collection<Annotation> getAnnotations();

	void addAnnotations(String... annotations);

	void addImports(Collection<String> imports);

	boolean contains(String type);

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

	void addFacets(Facet... facets);

	Collection<FacetTarget> getFacetTargets();

	void addFacetTargets(FacetTarget... targets);

	String toString();

	boolean equals(Object obj);


	int hashCode();
}
