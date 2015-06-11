package siani.tara.compiler.model;

import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.model.Tag;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
	public void setDoc(String doc) {

	}

	@Override
	public boolean isSub() {
		return false;
	}

	@Override
	public boolean isMain() {
		return false;
	}

	@Override
	public List<Node> getSubNodes() {
		return null;
	}

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public NodeContainer getContainer() {
		return null;
	}

	@Override
	public void setContainer(NodeContainer container) {

	}

	@Override
	public boolean isFacet() {
		return false;
	}

	@Override
	public boolean isAbstract() {
		return false;
	}

	@Override
	public boolean isRequired() {
		return false;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public boolean isNamed() {
		return false;
	}

	@Override
	public boolean isFeature() {
		return false;
	}

	@Override
	public boolean isFeatureInstance() {
		return false;
	}

	@Override
	public boolean isFinal() {
		return false;
	}

	@Override
	public boolean isTerminalInstance() {
		return false;
	}

	@Override
	public boolean intoSingle() {
		return false;
	}

	@Override
	public boolean intoRequired() {
		return false;
	}

	@Override
	public String getPlate() {
		return null;
	}

	@Override
	public void setPlate(String address) {

	}

	@Override
	public List<Tag> getAnnotations() {
		return null;
	}

	@Override
	public List<Tag> getFlags() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addAnnotations(String... annotations) {

	}

	@Override
	public void addFlags(String... flags) {

	}

	@Override
	public void addImports(Collection<String> imports) {

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
	public List<Node> getNodeSiblings() {
		return null;
	}

	@Override
	public List<Node> getIncludedNodes() {
		return null;
	}

	@Override
	public void addIncludedNodes(Node... nodes) {

	}

	@Override
	public void addIncludedNodes(int pos, Node... nodes) {

	}

	@Override
	public Node getInclude(String name) {
		return null;
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return false;
	}

	@Override
	public boolean remove(Node node) {
		return false;
	}

	@Override
	public void moveToTheTop() {

	}

	@Override
	public List<Variable> getVariables() {
		return null;
	}

	@Override
	public void addVariables(Variable... variables) {

	}

	@Override
	public void addVariables(int pos, Variable... variables) {

	}

	@Override
	public List<NodeReference> getInnerNodeReferences() {
		return null;
	}

	@Override
	public List<Node> getChildren() {
		return null;
	}

	@Override
	public void addChild(Node node) {

	}

	@Override
	public List<Facet> getFacets() {
		return null;
	}

	@Override
	public List<String> getAllowedFacets() {
		return null;
	}

	@Override
	public void addAllowedFacets(String... facet) {

	}

	@Override
	public void addFacets(Facet... facets) {

	}

	@Override
	public List<FacetTarget> getFacetTargets() {
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
	public void addParameter(String name, int position, String extension, Object... values) {

	}

	@Override
	public void addParameter(int position, String extension, Object... values) {

	}
}
