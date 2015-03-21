package siani.tara.compiler.model;

import siani.tara.compiler.model.impl.NodeReference;

import java.util.Collection;

public class EmptyNode implements Node {
	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public String getLanguage() {
		return null;
	}

	@Override
	public void setLanguage(String language) {

	}

	@Override
	public String getDoc() {
		return null;
	}

	@Override
	public boolean isSub() {
		return false;
	}

	@Override
	public boolean isRoot() {
		return false;
	}

	@Override
	public Collection<Node> getSubNodes() {
		return null;
	}

	@Override
	public NodeContainer getContainer() {
		return null;
	}

	@Override
	public void setContainer(NodeContainer container) {

	}

	@Override
	public boolean isIntention() {
		return false;
	}

	@Override
	public boolean isFacet() {
		return false;
	}

	@Override
	public boolean isAddressed() {
		return false;
	}

	@Override
	public boolean isAbstract() {
		return false;
	}

	@Override
	public boolean isAggregated() {
		return false;
	}

	@Override
	public boolean isProperty() {
		return false;
	}

	@Override
	public boolean isComponent() {
		return false;
	}

	@Override
	public boolean isCase() {
		return false;
	}

	@Override
	public Long getAddress() {
		return null;
	}

	@Override
	public void setAddress(Long address) {

	}

	@Override
	public Collection<Annotation> getAnnotations() {
		return null;
	}

	@Override
	public void addAnnotations(String... annotations) {

	}

	@Override
	public void addImports(Collection<String> imports) {

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
		return false;
	}

	@Override
	public String getQualifiedName() {
		return null;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public void setType(String type) {

	}

	@Override
	public String getFullType() {
		return null;
	}

	@Override
	public void setFullType(String type) {

	}

	@Override
	public Node resolve() {
		return null;
	}

	@Override
	public Collection<Node> getNodeSiblings() {
		return null;
	}

	@Override
	public Collection<Node> getIncludedNodes() {
		return null;
	}

	@Override
	public void addIncludedNodes(Node... nodes) {

	}

	@Override
	public Node getInclude(String name) {
		return null;
	}

	@Override
	public Collection<Variable> getVariables() {
		return null;
	}

	@Override
	public void addVariables(Variable... variables) {

	}

	@Override
	public Collection<NodeReference> getInnerNodeReferences() {
		return null;
	}

	@Override
	public Collection<Node> getChildren() {
		return null;
	}

	@Override
	public void addChild(Node node) {

	}

	@Override
	public Collection<Facet> getFacets() {
		return null;
	}

	@Override
	public void addFacets(Facet... facets) {

	}

	@Override
	public Collection<FacetTarget> getFacetTargets() {
		return null;
	}

	@Override
	public void addFacetTargets(FacetTarget... targets) {

	}

	@Override
	public Collection<Parameter> getParameters() {
		return null;
	}

	@Override
	public void addParameter(String name, int position, Object... values) {

	}

	@Override
	public void addParameter(int position, Object... values) {

	}
}
