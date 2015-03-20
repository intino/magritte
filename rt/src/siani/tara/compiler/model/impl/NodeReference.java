package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.compiler.model.Annotation.*;

public class NodeReference extends Element implements Node {

	private NodeImpl container;
	private NodeImpl destiny;
	private String reference;
	private String file;
	private int line;
	private String doc;
	private List<Annotation> annotations = new ArrayList<>();

	private List<String> imports = new ArrayList<>();
	private boolean has;

	public NodeReference(String reference) {
		this.reference = reference;
	}

	public NodeReference(NodeImpl destiny) {
		this.destiny = destiny;
	}

	public NodeImpl getDestiny() {
		return destiny;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public String getFile() {
		return file;
	}

	@Override
	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String getLanguage() {
		return null;
	}

	@Override
	public void setLanguage(String language) {

	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public String getDoc() {
		return doc;
	}

	@Override
	public boolean isSub() {
		return false;
	}

	public boolean isHas() {
		return has;
	}

	public void setHas(boolean has) {
		this.has = has;
	}

	@Override
	public boolean isRoot() {
		return false;
	}

	@Override
	public Collection<Node> getSubNodes() {
		return destiny.getSubNodes();
	}

	@Override
	public Node getContainer() {
		return container;
	}

	@Override
	public void setContainer(NodeContainer container) {

	}

	@Override
	public boolean isIntention() {
		return destiny.isIntention() || annotations.contains(ABSTRACT);
	}

	@Override
	public boolean isFacet() {
		return destiny.isFacet() || annotations.contains(FACET);
	}

	@Override
	public boolean isAddressed() {
		return destiny.isAddressed() || annotations.contains(ADDRESSED);
	}

	@Override
	public boolean isAbstract() {
		return destiny.isAbstract() || annotations.contains(ABSTRACT);
	}

	@Override
	public boolean isAggregated() {
		return destiny.isAggregated() || annotations.contains(Annotation.AGGREGATED);
	}

	@Override
	public boolean isProperty() {
		return destiny.isProperty() || annotations.contains(PROPERTY);
	}

	@Override
	public boolean isComponent() {
		return destiny.isComponent() || annotations.contains(COMPONENT);
	}

	@Override
	public boolean isCase() {
		return destiny.isCase() || annotations.contains(CASE);
	}

	@Override
	public Long getAddress() {
		return destiny.getAddress();
	}

	@Override
	public void setAddress(Long address) {
	}

	@Override
	public Collection<Annotation> getAnnotations() {
		List<Annotation> annotations = new ArrayList<>();
		annotations.addAll(destiny.getAnnotations());
		annotations.addAll(this.annotations);
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
		return false;
	}

	@Override
	public Node getParent() {
		return null;
	}

	@Override
	public String getParentName() {
		return null;
	}

	@Override
	public boolean isAnonymous() {
		return true;
	}

	@Override
	public String getQualifiedName() {
		return container.getQualifiedName() + "." + destiny.getName();
	}

	@Override
	public String getType() {
		return destiny.getType();
	}

	@Override
	public void setType(String type) {

	}

	@Override
	public String getFullType() {
		return container.getFullType() + "." + getType();
	}

	@Override
	public void setFullType(String type) {

	}

	@Override
	public Node resolve() {
		return this;
	}

	@Override
	public Collection<Parameter> getParameters() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addParameter(String name, int position, Object... values) {
	}

	@Override
	public void addParameter(int position, Object... values) {
	}

	@Override
	public Collection<Node> getNodeSiblings() {
		return container.getIncludedNodes(); //TODO Remove me
	}

	@Override
	public Collection<Node> getIncludedNodes() {
		return destiny.getIncludedNodes();
	}

	@Override
	public void addIncludedNodes(Node... nodes) {
	}

	@Override
	public Collection<Variable> getVariables() {
		return destiny.getVariables();
	}

	@Override
	public void addVariables(Variable... variables) {

	}

	@Override
	public Collection<NodeReference> getInnerNodeReferences() {
		return destiny.getInnerNodeReferences();
	}

	@Override
	public Collection<Node> getChildren() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addChild(Node node) {

	}

	@Override
	public Collection<Facet> getFacets() {
		return destiny.getFacets();
	}

	@Override
	public void addFacets(Facet... facets) {

	}

	@Override
	public Collection<FacetTarget> getFacetTargets() {
		return destiny.getFacetTargets();
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {

	}
}
