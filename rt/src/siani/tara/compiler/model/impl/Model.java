package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Model extends Element implements Node {

	private String name;
	private String file;
	private String language;
	private boolean terminal;
	private List<Node> includes = new ArrayList<>();


	public Model(String file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public int getLine() {
		return 0;
	}

	@Override
	public void setLine(int line) {

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
	public Node getContainer() {
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
	public List<Annotation> getAnnotations() {
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
		return "";
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
	public Collection<Parameter> getParameters() {
		return null;
	}

	@Override
	public void addParameter(String name, int position, Object... values) {

	}

	@Override
	public void addParameter(int position, Object... values) {

	}

	@Override
	public Collection<Node> getNodeSiblings() {
		return Collections.EMPTY_LIST;
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
		return Collections.EMPTY_LIST;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isTerminal() {
		return terminal;
	}

	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
}
