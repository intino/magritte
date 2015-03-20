package siani.tara.compiler.model.impl;

import siani.tara.Language;
import siani.tara.Resolver;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.model.*;
import siani.tara.compiler.semantic.LanguageLoader;
import siani.tara.compiler.semantic.wrappers.LanguageNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static siani.tara.compiler.model.Annotation.*;

public class NodeImpl extends Element implements Node {

	private static final Logger LOG = Logger.getLogger(NodeImpl.class.getName());
	private String file;
	private int line;
	private NodeContainer container;
	private List<String> imports = new ArrayList<>();
	private String type;
	private String fullType;
	private String doc;
	private boolean sub;
	private List<Node> includes = new ArrayList<>();
	private List<Annotation> annotations = new ArrayList<>();
	private Long address;
	private String name;
	private String parentName;
	private Node parent;
	private List<Parameter> parameters = new ArrayList<>();
	private List<Variable> variables = new ArrayList();
	private List<Facet> facets = new ArrayList<>();
	private List<FacetTarget> facetTargets = new ArrayList<>();
	private String language;
	private List<Node> children = new ArrayList<>();


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public void setSub(boolean sub) {
		this.sub = sub;
	}

	@Override
	public String getDoc() {
		return doc;
	}

	@Override
	public boolean isSub() {
		return sub;
	}

	@Override
	public boolean isRoot() {
		return container == null;
	}

	@Override
	public Collection<Node> getSubNodes() {
		List<Node> nodes = new ArrayList<>();
		for (Node include : includes)
			if (include.isSub()) {
				nodes.add(include);
				nodes.addAll(include.getSubNodes());
			}
		return nodes;
	}

	@Override
	public NodeContainer getContainer() {
		return container;
	}

	@Override
	public void setContainer(NodeContainer container) {
		this.container = container;
	}

	@Override
	public boolean isIntention() {
		return annotations.contains(INTENTION);
	}

	@Override
	public boolean isFacet() {
		return annotations.contains(FACET);
	}

	@Override
	public boolean isAddressed() {
		return annotations.contains(ADDRESSED);
	}

	@Override
	public boolean isAbstract() {
		return annotations.contains(ABSTRACT);
	}

	@Override
	public boolean isAggregated() {
		return annotations.contains(AGGREGATED);
	}

	@Override
	public boolean isProperty() {
		return annotations.contains(PROPERTY);
	}

	@Override
	public boolean isComponent() {
		return annotations.contains(COMPONENT);
	}

	@Override
	public boolean isCase() {
		return annotations.contains(CASE);
	}

	@Override
	public Long getAddress() {
		return address;
	}

	@Override
	public void setAddress(Long address) {
		this.address = address;
	}

	@Override
	public Collection<Annotation> getAnnotations() {
		return annotations;
	}

	@Override
	public void addAnnotations(String... annotations) {
		for (String annotation : annotations)
			this.annotations.add(Annotation.valueOf(annotation.toUpperCase().replace("+", "META_")));
	}

	@Override
	public void addImports(Collection<String> imports) {
		this.imports.addAll(imports);
	}

	@Override
	public boolean contains(String type) {
		for (Node include : includes) if (include.getType().equals(type)) return true;
		return false;
	}

	@Override
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
	public boolean isAnonymous() {
		return name == null;
	}

	@Override
	public String getQualifiedName() {
		String containerQN = container.getQualifiedName();
		return isInFacet() ? "" : (containerQN.isEmpty() ? "" : ".") + (name == null ? "annonymous@" + type : name);
	}

	private boolean isInFacet() {
		NodeContainer context = this.container;
		while (context != null && (!(context instanceof Facet) || !(context instanceof FacetTarget)))
			context = context.getContainer();
		return context != null;
	}

	@Override
	public String getType() {
		return type;
	}

	public String setType() {
		return type;
	}


	@Override
	public String getFullType() {
		return fullType;
	}

	@Override
	public void setFullType(String fullType) {
		this.type = fullType;
		this.fullType = fullType;
	}

	@Override
	public Node resolve() {
		try {
			Language language = LanguageLoader.load("", "");//TODO
			if (language == null) return this;
			LanguageNode node = new LanguageNode(this);
			new Resolver(language).resolve(node);
			return this;
		} catch (TaraException e) {
			return this;
		}
	}

	@Override
	public Collection<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public void addParameter(String name, int position, Object... values) {
		parameters.add(new ParameterImpl(name, position, values));
	}

	@Override
	public void addParameter(int position, Object... values) {
		addParameter("", position, values);
	}

	@Override
	public Collection<Node> getNodeSiblings() {
		return null;
	}

	@Override
	public Collection<Node> getIncludedNodes() {
		return includes;
	}

	@Override
	public void addIncludedNodes(Node... nodes) {
		Collections.addAll(includes, nodes);
	}

	@Override
	public Collection<Variable> getVariables() {
		return variables;
	}

	@Override
	public void addVariables(Variable... variables) {
		Collections.addAll(this.variables, variables);
	}

	@Override
	public Collection<NodeReference> getInnerNodeReferences() {
		List<NodeReference> nodes = new ArrayList<>();
		for (Node include : includes)
			if (include instanceof NodeReference)
				nodes.add((NodeReference) include);
		return nodes;
	}

	@Override
	public Collection<Node> getChildren() {
		return this.children;
	}

	@Override
	public void addChild(Node node) {
		children.add(node);
	}

	@Override
	public Collection<Facet> getFacets() {
		return facets;
	}

	@Override
	public void addFacets(Facet... facets) {
		Collections.addAll(this.facets, facets);
	}

	@Override
	public Collection<FacetTarget> getFacetTargets() {
		return facetTargets;
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {
		Collections.addAll(this.facetTargets, targets);
	}

	@Override
	public String toString() {
		return getQualifiedName() + "@" + type;
	}
}
